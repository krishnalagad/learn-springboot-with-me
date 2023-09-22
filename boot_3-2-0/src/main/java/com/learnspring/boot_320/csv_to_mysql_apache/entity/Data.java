package com.learnspring.boot_320.csv_to_mysql_apache.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "csv_to_mysql_apache_data")
@Getter
@Setter
@ToString
@NoArgsConstructor
//@AllArgsConstructor
public class Data {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int end_year;
    private int intensity;
    private String sector;
    private String topic;

    @Column(length = 5000)
    private String insight;

    public Data(int end_year, int intensity, String sector, String topic, String insight) {
        this.end_year = end_year;
        this.intensity = intensity;
        this.sector = sector;
        this.topic = topic;
        this.insight = insight;
    }
}
