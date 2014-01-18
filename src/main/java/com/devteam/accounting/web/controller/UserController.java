package com.devteam.accounting.web.controller;

import com.devteam.accounting.dto.AccountDto;
import com.devteam.accounting.dto.form.LoginForm;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;

/**
 * User: pancara
 * Date: 1/16/14
 * Time: 11:51 AM
 */

@Controller
@RequestMapping("/user")
public class UserController {

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public ModelAndView login() {
        return new ModelAndView("login-form");
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity login(@RequestBody final LoginForm form) {
        return new ResponseEntity(HttpStatus.OK);
    }


    @RequestMapping("logout")
    public ModelAndView logout() {
        return new ModelAndView("redirect:login");
    }
}
