package com.commons;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Employee {
    int id;
    String name;
    String grade;
    double salary;
}
