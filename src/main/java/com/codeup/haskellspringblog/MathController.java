package com.codeup.haskellspringblog;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MathController {
    @GetMapping("/add/{num1}/and/{num2}")
    @ResponseBody
    public double add(@PathVariable int num1, @PathVariable int num2){
        return (double) num1 + num2;
    }

    @GetMapping("/subtract/{num1}/from/{num2}")
    @ResponseBody
    public double subtract(@PathVariable int num1, @PathVariable int num2){
        return (double) num1 - num2;
    }

    @GetMapping("/multiply/{num1}/and/{num2}")
    @ResponseBody
    public double multiply(@PathVariable int num1, @PathVariable int num2){
        return (double) num1 * num2;
    }

    @GetMapping("/divide/{num1}/by/{num2}")
    @ResponseBody
    public double divide(@PathVariable int num1, @PathVariable int num2){
        return (double) num1 / num2;
    }
}
