package com.example.hw20;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class EmployeeService {
    private static final int SIZE = 10;
    private final Map<String, Employee> employees = new HashMap<>();

    public void addEmployee(String firstname, String lastName) {
        if (employees.size() == SIZE) {
            throw new EmployeeStorageIsFullException();
        }

        var key = makeKey(firstname, lastName);
        if (employees.containsKey(key)) {
            throw new EmployeeAlreadyAddedException();
        }
        employees.put(key, new Employee(firstname, lastName));
    }

    public Employee findEmployee(String firstName, String lastName) {
        var employee = employees.get(makeKey(firstName, lastName));
        if (employee == null) {
            throw new EmployeeNotFoundException();
        }
        return employee;
    }

    public boolean removeEmployee(String firstName, String lastName) {
        Employee removed = employees.remove(makeKey(firstName, lastName));
        if (removed == null) {
            throw new EmployeeNotFoundException();
        }
        return true;
    }

    public Collection<Employee> getAll() {
        return employees.values();
    }

    private String makeKey(String firstName, String lastName) {
        return (firstName + "_" + lastName).toLowerCase();
    }
}
