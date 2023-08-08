package com.example.hw20;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/department")
public class DepartmentController {

    private final DepartmentService service;

    public DepartmentController(DepartmentService service) {
        this.service = service;
    }

    @GetMapping("/max-salary")
    public double max(@RequestParam int departmentId) {
        return service.maxSalary(departmentId);
    }

    @GetMapping("/min-salary?departmentId=5")
    public double min(@RequestParam int departmentId) {
        return service.minSalary(departmentId);
    }

    @GetMapping("/departments/all?departmentId=5")
    public List<Employee> allByDepart(@RequestParam int departmentId) {
        return (List<Employee>) service.groupByDepartment(departmentId);
    }

    @GetMapping("/departments/all")
    public List<Employee> all(@RequestParam int departmentId) {
        return (List<Employee>) service.findAllByDepartment(departmentId);
    }
}
