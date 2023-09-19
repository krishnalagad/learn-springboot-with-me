package com.learnspring.boot_320.restclient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Todo {

    private Integer id;

    private Boolean completed;

    private String title;

    private Integer userId;
}
