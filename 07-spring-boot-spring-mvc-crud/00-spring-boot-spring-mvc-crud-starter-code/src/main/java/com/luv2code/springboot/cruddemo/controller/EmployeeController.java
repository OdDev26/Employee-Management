package com.luv2code.springboot.cruddemo.controller;

import com.luv2code.springboot.cruddemo.entity.Employee;
import com.luv2code.springboot.cruddemo.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/list")
    public String allEmployees(Model model){
        List<Employee> employees = employeeService.findAll();
        model.addAttribute("employees",employees);
        return "employees/list-employees";
    }
    @GetMapping("/showAddForm")
    public String showAddForm(Model model){
        Employee employee = new Employee();
        model.addAttribute("employee",employee);
        return "employees/employee-form";
    }
    @PostMapping("/save")
    public String save(@ModelAttribute("employee") Employee employee){
        System.out.println("Employee: "+employee);
        employeeService.save(employee);
        return "redirect:/";
    }
    @GetMapping("/showUpdateForm")
    public String showUpdateForm(@RequestParam("employeeId") int id,Model model){
        Employee employee = employeeService.findById(id);
        model.addAttribute("employee",employee);
        return "employees/employee-form";
    }
    @GetMapping("/delete")
    public String deleteEmployee(@RequestParam("employeeId") int id){
        employeeService.deleteById(id);
        return "redirect:/";
    }
}
