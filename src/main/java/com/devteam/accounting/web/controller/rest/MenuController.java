package com.devteam.accounting.web.controller.rest;


import com.devteam.accounting.dto.app.MenuDto;
import com.devteam.accounting.persistence.type.MenuLocation;
import com.devteam.accounting.persistence.type.MenuType;
import com.devteam.accounting.service.app.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;


    @RequestMapping(value = "{location}", method = RequestMethod.GET)
    private ResponseEntity getMenuByType(@PathVariable("type") String strLocation) {
        MenuLocation loc = MenuLocation.valueOf(strLocation);
        List<MenuDto> menus = menuService.getRootByLocation(loc);
        return new ResponseEntity(menus, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<MenuDto> getCountry(@PathVariable("id") Long id) {
        MenuDto dto = menuService.findById(id);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity save(@RequestBody final MenuDto dto) {
        menuService.save(dto);
        return new ResponseEntity(dto, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity update(@RequestBody final MenuDto dto) {
        menuService.update(dto);
        return new ResponseEntity(dto, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity delete(@PathVariable("id") Long id) {
        menuService.removeById(id);
        return new ResponseEntity(HttpStatus.OK);
    }


}
