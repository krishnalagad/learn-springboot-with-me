package com.learnspring.boot_320.jsonfile_to_mysql.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity
@Table(name = "json_to_mysql_post")
public record Post(@Id String id, String title, String slug, LocalDate date, int timeToRead, String tags) {
}
