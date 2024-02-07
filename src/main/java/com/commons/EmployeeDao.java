package com.commons;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EmployeeDao {

    public static List<Employee> getEmployees(){
        return Stream.of(new Employee(1, "john", "A", 60000),
                        new Employee(2, "peter", "B", 30000),
                        new Employee(3, "mak", "A", 80000),
                        new Employee(4, "jim", "A", 90000),
                        new Employee(5, "johnson", "C", 150000)
                ).collect(Collectors.toList());
    }
}
