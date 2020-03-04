package com.rest.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestAuth {

  @RequestMapping("/allUsers")
  public String allUserAPI() {
    return "This API can be used by all users";
  }

  @RequestMapping("/hrUser")
  public String hrUserAPI() {
    return "This API can be used by HRs only";
  }

  @RequestMapping("/adminUser")
  public String adminUserAPI() {
    return "This API can be used by Admins only";
  }

}
