package com.itheima.Controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/hello")
@RestController
public class HelloController {

    @RequestMapping("/add")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void add(){
        System.out.println("add");
    }

    @RequestMapping("/delete")
    @PreAuthorize("hasAuthority('delete')")
    public void delete(){
        System.out.println("delete");
    }

    @RequestMapping("/say")
    @PreAuthorize("isAuthenticated()")
    public void say(){
        System.out.println("say hello");
    }
}
