package com.example.hw20;

import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DepartmentService {
    private final EmployeeService employeeService;

    public DepartmentService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public double maxSalary(int departmentId) {
        return employeeService.getAll()
                 .stream()
                 .filter(employee -> employee.getDepartment() == departmentId)
                 .map(Employee::getSalary)
                 .max(Comparator.comparingDouble(o -> o))
                 .orElseThrow(EmployeeNotFoundException::new);
    }

    public double minSalary(int departmentId) {
        return employeeService.getAll()
                .stream()
                .filter(employee -> employee.getDepartment() == departmentId)
                .map(Employee::getSalary)
                .min(Comparator.comparingDouble(o -> o))
                .orElseThrow(EmployeeNotFoundException::new);
    }

    public List<Employee> findAllByDepartment(int departmentId) {
        return employeeService.getAll()
                .stream()
                .filter(employee -> employee.getDepartment() == departmentId)
                .collect(Collectors.toList());
    }

    public Map<Integer, List<Employee>> groupByDepartment(int departmentId) {
            return employeeService.getAll()
                .stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));
    }
}
