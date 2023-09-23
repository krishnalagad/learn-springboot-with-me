package com.learnspring.boot_320.csv_to_mysql_try2_springbatch.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "csv_to_mysql_try2_data")
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class DataCsv {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int end_year;
    private int intensity;
    private String sector;
    private String topic;

    @Column(length = 5000)
    private String insight;

}
