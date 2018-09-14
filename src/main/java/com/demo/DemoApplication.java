package com.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@SpringBootApplication
public class DemoApplication {

  @RequestMapping("/hello")
  public String hello(HttpServletRequest request){
    String name=request.getParameter("name");
    if (name==null || "".equals(name)){
      name="world";
    }
    return "hello "+name;
  }

  public static void main(String[] args) {
    SpringApplication.run(DemoApplication.class, args);
  }
}

