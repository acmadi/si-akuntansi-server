package com.devteam.accounting.web.controller;

import com.devteam.accounting.dto.error.ErrorDto;
import com.devteam.accounting.dto.error.ValidationErrorDto;
import com.devteam.accounting.dto.form.LoginDto;
import com.devteam.accounting.dto.validator.DtoValidator;
import com.devteam.accounting.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * User: pancara
 * Date: 1/16/14
 * Time: 11:51 AM
 */

@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private DtoValidator validator;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView login() {
        return new ModelAndView("login-form");
    }

    @RequestMapping(value = "/validate", method = RequestMethod.POST)
    public ResponseEntity login(@RequestBody final LoginDto dto) {
        ValidationErrorDto errors = validator.validate(dto);
        if (errors.hasErrors()) {
            return new ResponseEntity(errors, HttpStatus.FORBIDDEN);
        }

        if (userService.validateUser(dto.getUserId(), dto.getPassword())) {
            return new ResponseEntity(HttpStatus.OK);
        } else {
            errors = new ValidationErrorDto();
            errors.addError(new ErrorDto(ErrorDto.USER_PASSWORD_NOT_RECOGNIZED));
            return new ResponseEntity(errors, HttpStatus.FORBIDDEN);
        }
    }


    @RequestMapping(value = "/logout")
    public ModelAndView logout(HttpServletRequest request) {
        Enumeration<String> names = request.getSession().getAttributeNames();
        while (names.hasMoreElements()) {
            String name = names.nextElement();
            request.getSession().removeAttribute(name);
        }

        request.getSession().invalidate();
        return new ModelAndView("redirect:login");
    }
}
