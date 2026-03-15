package org.sanjeet.springbootweb.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {
    @GetMapping("/employees/*")
    public String handleRandomURL(){
        return "Incorrect URL, please go back and hit correct URL";
    }
}
