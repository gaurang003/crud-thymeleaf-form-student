package com.luv2code.springboot.thymeleafdemo.controller;

import com.luv2code.springboot.thymeleafdemo.entity.Employee;
import com.luv2code.springboot.thymeleafdemo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    private EmployeeService employeeService;

    public EmployeeController(EmployeeService theEmployeeService) {
        employeeService = theEmployeeService;
    }

    @GetMapping("/list")
    public String listEmployees(Model theModel){

        List<Employee> theEmployees = employeeService.findAll();

        theModel.addAttribute("employees" , theEmployees);

        return "employee/list-employees";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel){

        //create model attribute to bind data
        Employee theEmployee = new Employee();

        theModel.addAttribute("employee", theEmployee);

        return "employee/employee-form";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("employeeId")int theId, Model theModel){

        //get the employees from the employee service
        Employee theEmployee = employeeService.findById(theId);

        //set the employee in the model to prepopulate the form
        theModel.addAttribute("employee" , theEmployee);

        //send over to our form
        return "employee/employee-form";

    }
    @GetMapping("/delete")
    public String deleteEmployee(@RequestParam("employeeId")int theId){

        employeeService.deleteById(theId);

        return "redirect:/employees/list";

    }


    @PostMapping("/save")
    public String saveEmployee(@ModelAttribute("employee") Employee theEmployee){

        employeeService.save(theEmployee);

        return "redirect:/employees/list";

    }

}














