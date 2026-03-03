# ETL - Bulk CSV Data Insertion into MySQL (Spring Boot)

A Spring Boot application demonstrating **3 different approaches** to bulk-insert **1 million rows** from a CSV file into MySQL. This project helps you understand the trade-offs between each strategy and when to use which.

## 📊 Performance Benchmark (1 Million Rows)

| Approach | Time Taken | Speed Rank |
|----------|-----------|------------|
| **LOAD DATA INFILE** | ~12 seconds | 🥇 Fastest |
| **JDBC Batch** | ~55 seconds | 🥈 |
| **JPA Batch** | ~1 min 6 seconds | 🥉 |

---

## 📁 Project Structure

```
src/main/java/com/learnspring/etl/
├── DataExtractorApplication.java       # Main Spring Boot class
├── ProductCategory.java                # Shared enum (ELECTRONIC, EDIBLE, GARMENT, ORNAMENT, NATURAL)
│
├── jdbcbatch/                          # Approach 1: JDBC Batch
│   ├── JdbcProduct.java                # Plain POJO
│   ├── JdbcProductService.java         # JdbcTemplate.batchUpdate()
│   └── JdbcProductController.java      # POST /api/jdbc/upload
│
├── jpabatch/                           # Approach 2: JPA Batch
│   ├── JpaProduct.java                 # @Entity → jpa_products table
│   ├── JpaProductRepository.java       # JpaRepository
│   ├── JpaProductService.java          # EntityManager.persist() + flush/clear
│   └── JpaProductController.java       # POST /api/jpa/upload
│
└── loaddatainfile/                     # Approach 3: LOAD DATA INFILE
    ├── LoadDataProduct.java            # @Entity → loaddata_products table
    ├── LoadDataProductRepository.java  # JpaRepository (for DDL auto-create)
    ├── LoadDataProductService.java     # Raw JDBC LOAD DATA LOCAL INFILE
    └── LoadDataProductController.java  # POST /api/loaddata/upload
```

---

## 🔧 Configuration (application.properties)

Key settings that enable bulk insertion performance:

```properties
# Append to JDBC URL for batch optimization + LOAD DATA support
spring.datasource.url=jdbc:mysql://localhost:3306/etl?rewriteBatchedStatements=true&allowLoadLocalInfile=true

# Hibernate batching settings
spring.jpa.properties.hibernate.jdbc.batch_size=1000
spring.jpa.properties.hibernate.order_inserts=true

# Allow large file uploads (CSV can be ~53MB)
spring.servlet.multipart.max-file-size=100MB
spring.servlet.multipart.max-request-size=100MB
```

| Property | Why It Matters |
|----------|---------------|
| `rewriteBatchedStatements=true` | MySQL driver rewrites individual INSERT statements into multi-row INSERTs (`INSERT INTO t VALUES (...), (...), (...)`) — **massive speedup** for JDBC and JPA batch |
| `allowLoadLocalInfile=true` | Enables the client-side permission for `LOAD DATA LOCAL INFILE` |
| `hibernate.jdbc.batch_size=1000` | Tells Hibernate to group 1000 INSERTs into a single batch before sending to DB |
| `hibernate.order_inserts=true` | Hibernate reorders INSERT statements to maximize batching efficiency |

---

## 📝 Step-by-Step Explanation of Each Approach

---

### Approach 1: JDBC Batch (`~55 seconds`)

**Package:** `com.learnspring.etl.jdbcbatch`  
**API:** `POST /api/jdbc/upload`  
**Table:** `jdbc_products`

#### How It Works

This approach uses Spring's `JdbcTemplate.batchUpdate()` which directly works with JDBC `PreparedStatement` — no ORM overhead.

**Step-by-step flow:**

1. **Controller** receives the CSV file as a `MultipartFile` via the REST endpoint.

2. **Service creates the table** if it doesn't exist using a raw `CREATE TABLE IF NOT EXISTS` SQL statement. Since this approach doesn't use JPA entities, Hibernate won't auto-create the table — so we do it manually.

3. **CSV parsing** starts. The service opens the uploaded file's `InputStream`, wraps it in an OpenCSV `CSVReader`, and skips the header line.

4. **Batch accumulation:** Each CSV row is mapped to a `JdbcProduct` POJO and added to a `List`. Once the list reaches **1000 items** (the batch size), it's sent to the database.

5. **Batch execution:** `JdbcTemplate.batchUpdate()` is called with a `BatchPreparedStatementSetter` lambda. This sets each of the 6 columns on the `PreparedStatement` for every row in the batch. Under the hood, with `rewriteBatchedStatements=true`, the MySQL driver rewrites these into multi-row INSERT statements.

6. **After the loop**, any remaining rows (fewer than 1000) are flushed in a final batch.

7. **Response** returns the total rows inserted and time taken.

#### Key Code Snippet
```java
jdbcTemplate.batchUpdate(INSERT_SQL, products, BATCH_SIZE, (ps, product) -> {
    ps.setLong(1, product.getProductId());
    ps.setString(2, product.getProductName());
    ps.setDouble(3, product.getProductPrice());
    ps.setDate(4, Date.valueOf(product.getProductMnfDate()));
    ps.setDate(5, Date.valueOf(product.getProductExpDate()));
    ps.setString(6, product.getProductCategory().name());
});
```

#### Why This Speed?
- **No ORM overhead** — no entity state tracking, no dirty checking, no first-level cache
- **Direct PreparedStatement** — minimal object creation per row
- `rewriteBatchedStatements=true` converts 1000 separate INSERTs into a single multi-row INSERT

---

### Approach 2: JPA Batch (`~1 min 6 seconds`)

**Package:** `com.learnspring.etl.jpabatch`  
**API:** `POST /api/jpa/upload`  
**Table:** `jpa_products`

#### How It Works

This approach uses JPA's `EntityManager.persist()` with Hibernate's built-in batching. The entire operation runs in a single `@Transactional` method.

**Step-by-step flow:**

1. **Controller** receives the CSV file as `MultipartFile`.

2. **Table auto-creation:** Since `JpaProduct` is annotated with `@Entity` and `@Table(name = "jpa_products")`, Hibernate auto-creates the table thanks to `spring.jpa.hibernate.ddl-auto=update`.

3. **CSV parsing** with OpenCSV — same as the JDBC approach.

4. **Persist in loop:** Each CSV row is mapped to a `JpaProduct` entity and passed to `entityManager.persist()`. This puts the entity into Hibernate's **persistence context** (first-level cache).

5. **Flush + Clear every 1000 rows:** This is the most critical part. Without this, Hibernate would keep all 1 million entities in memory → `OutOfMemoryError`.
   - `entityManager.flush()` — forces Hibernate to send all pending INSERTs to the database. Because `hibernate.jdbc.batch_size=1000`, Hibernate groups them into batches of 1000 rows.
   - `entityManager.clear()` — detaches all entities from the persistence context, freeing memory.

6. **Final flush** after the loop for any remaining rows.

7. **`@Transactional`** ensures the entire operation is a single database transaction. If anything fails, all inserts are rolled back.

#### Key Code Snippet
```java
@Transactional
public String uploadCsvData(MultipartFile file) {
    // ... CSV reading ...
    while ((line = csvReader.readNext()) != null) {
        JpaProduct product = mapToProduct(line);
        entityManager.persist(product);
        totalRows++;

        if (totalRows % BATCH_SIZE == 0) {
            entityManager.flush();    // Send batch to DB
            entityManager.clear();    // Free memory
        }
    }
    entityManager.flush();
    entityManager.clear();
}
```

#### Why Is It Slower Than JDBC?
- **Entity lifecycle management** — Hibernate tracks the state of every entity (NEW → MANAGED → DETACHED)
- **Dirty checking overhead** — even though we're only inserting, Hibernate still checks entity state at flush time
- **First-level cache** — entities are stored in the persistence context until `clear()` is called
- **Object mapping** — more abstraction layers between your code and JDBC

---

### Approach 3: LOAD DATA INFILE (`~12 seconds`)

**Package:** `com.learnspring.etl.loaddatainfile`  
**API:** `POST /api/loaddata/upload`  
**Table:** `loaddata_products`

#### How It Works

This approach bypasses both JPA *and* JDBC batching entirely. It uses MySQL's native **`LOAD DATA LOCAL INFILE`** command, which is designed specifically for bulk data loading.

**Step-by-step flow:**

1. **Controller** receives the CSV file as `MultipartFile`.

2. **Table auto-creation:** `LoadDataProduct` entity + `LoadDataProductRepository` exist solely so Hibernate creates the `loaddata_products` table. They are not used for data insertion.

3. **Save to temp file:** The uploaded `MultipartFile` is written to a temporary file on disk because `LOAD DATA LOCAL INFILE` needs a file path — it can't work with an `InputStream`.

4. **Get raw JDBC connection:** The service injects `DataSource` and obtains a raw `java.sql.Connection`. This bypasses JPA/Hibernate completely.

5. **Execute LOAD DATA SQL:** A single SQL statement tells MySQL to:
   - Read the file from the **client machine** (`LOCAL` keyword)
   - Parse it as CSV (fields terminated by `,`, enclosed by `"`)
   - Skip the header line (`IGNORE 1 LINES`)
   - Map each column directly to the table columns

6. **Cleanup:** The temp file is deleted after execution.

#### Key Code Snippet
```java
String loadDataSql = String.format("""
    LOAD DATA LOCAL INFILE '%s'
    INTO TABLE loaddata_products
    FIELDS TERMINATED BY ','
    ENCLOSED BY '"'
    LINES TERMINATED BY '\\n'
    IGNORE 1 LINES
    (product_id, product_name, product_price, product_mnf_date, product_exp_date, product_category)
    """, filePath);

statement.executeUpdate(loadDataSql);
```

#### Why Is It So Fast?
- **MySQL does all the work** — parsing, type conversion, and insertion all happen inside the database engine
- **No row-by-row processing** in Java — the entire file is streamed to MySQL in one go
- **No SQL parsing overhead** — MySQL uses an optimized internal path for LOAD DATA, bypassing the SQL parser for each row
- **Minimal logging** — MySQL can optimize its redo/undo log writes during bulk loads
- **No network round-trips per batch** — unlike JDBC/JPA batch which still sends multiple batched INSERT statements

#### Prerequisites
Must be enabled on **both** client and server:
```sql
-- On MySQL server
SET GLOBAL local_infile = 1;
```
```properties
# In JDBC URL (client side)
allowLoadLocalInfile=true
```

---

## 🏁 When to Use Which Approach?

| Approach | Best For | Trade-offs |
|----------|---------|------------|
| **JDBC Batch** | When you need **data validation/transformation in Java** before insert and don't use JPA | Faster than JPA, more boilerplate code, manual table management |
| **JPA Batch** | When you're already in a **JPA-based app** and want to keep consistency with your ORM layer | Slowest, but integrates naturally with existing JPA entities, supports rollback |
| **LOAD DATA INFILE** | When **raw speed matters** and data is already in the right CSV format | Fastest by far, but MySQL-specific, requires server config, no Java-side validation |

---

## 🚀 How to Run

### 1. Prerequisites
- Java 21
- MySQL running on `localhost:3306` with database `etl`
- For LOAD DATA INFILE: `SET GLOBAL local_infile = 1;` in MySQL

### 2. Start the Application
```bash
./mvnw spring-boot:run
```

### 3. Test Each Endpoint (via curl or Postman)
```bash
# JDBC Batch
curl -X POST http://localhost:8080/api/jdbc/upload -F "file=@src/main/resources/product_data.csv"

# JPA Batch
curl -X POST http://localhost:8080/api/jpa/upload -F "file=@src/main/resources/product_data.csv"

# LOAD DATA INFILE
curl -X POST http://localhost:8080/api/loaddata/upload -F "file=@src/main/resources/product_data.csv"
```

Each endpoint returns a response like:
```
JDBC Batch Insert completed! Rows inserted: 1000000, Time taken: 55023 ms
```

---

## 🛠️ Tech Stack
- **Spring Boot 4.0.3**
- **Java 21**
- **MySQL** (with MySQL Connector/J 9.6.0)
- **Spring Data JPA** (Hibernate ORM)
- **Spring JDBC** (JdbcTemplate)
- **OpenCSV 5.9** (CSV parsing)
