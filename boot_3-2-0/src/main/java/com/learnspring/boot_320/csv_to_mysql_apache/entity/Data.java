package com.learnspring.boot_320.csv_to_mysql_apache.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "csv_to_mysql_apache_data")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Data {
}
