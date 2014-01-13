package com.devteam.accounting.web.controller.rest;

import com.devteam.accounting.dto.AccountDto;
import com.devteam.accounting.service.AccountService;
import com.devteam.accounting.util.Helper;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasValue;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * User: pancara
 * Date: 1/10/14
 * Time: 1:55 PM
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/spring/beans-test.xml", "/spring/controller.xml"})
@ActiveProfiles("dev")
@WebAppConfiguration
public class AccountControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private AccountService accountService;

    @BeforeClass
    public static void beforeClass() {
    }

    @Before
    public void before() {
        accountService.removeAll();
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }


    @Test
    public void getAccount() throws Exception {
        AccountDto dto = new AccountDto();
        dto.setCode(Helper.createStringWithLength(6));
        dto.setName(Helper.createStringWithLength(11));
        dto.setDescription(Helper.createStringWithLength(200));
        accountService.save(dto);

        MockHttpServletRequestBuilder request = get("/account/" + dto.getId())
                .accept(Helper.APPLICATION_JSON_UTF8);

        this.mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().contentType(Helper.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.id", is(dto.getId().intValue())))
                .andExpect(jsonPath("$.code", equalTo(dto.getCode())))
                .andExpect(jsonPath("$.description", equalTo(dto.getDescription())));
    }

    @Test
    public void saveAccount() throws Exception {
        AccountDto parent = new AccountDto();
        parent.setCode(Helper.createStringWithLength(6));
        parent.setName(Helper.createStringWithLength(11));
        parent.setDescription(Helper.createStringWithLength(200));
        accountService.save(parent);

        AccountDto dto = new AccountDto();
        dto.setCode(Helper.createStringWithLength(5));
        dto.setName(Helper.createStringWithLength(10));
        dto.setDescription(Helper.createStringWithLength(200));
        dto.setParent(parent);

        byte[] bodyRequest = Helper.convertObjectToJsonBytes(dto);

        MockHttpServletRequestBuilder request = post("/account")
                .contentType(Helper.APPLICATION_JSON_UTF8)
                .content(bodyRequest)
                .accept(Helper.APPLICATION_JSON_UTF8);

        this.mockMvc.perform(request)
                .andExpect(status().isOk());
    }

    @Test
    public void updateAccount() throws Exception {
        AccountDto parent = new AccountDto();
        parent.setId(1L);
        parent.setCode("PARENT01");
        parent.setName("PARENT01");
        accountService.save(parent);

        AccountDto dto = new AccountDto();
        dto.setId(5L);
        dto.setVersion(0L);
        dto.setCode("FOO 2");
        dto.setName("foo 2");
        dto.setDescription("foo 2");
        dto.setParent(parent);
        accountService.save(dto);

        dto.setName("FOO [modified]");

        byte[] bodyRequest = Helper.convertObjectToJsonBytes(dto);
        MockHttpServletRequestBuilder request = put("/account")
                .contentType(Helper.APPLICATION_JSON_UTF8)
                .content(bodyRequest)
                .accept(Helper.APPLICATION_JSON_UTF8);

        this.mockMvc.perform(request).andExpect(status().isOk());
    }

    @Test
    public void deleteAccount() throws Exception {
        AccountDto dto = new AccountDto();
        dto.setId(5L);
        dto.setVersion(0L);
        dto.setCode("FOO2[modifixxed]");
        dto.setName("foo2[modified]");
        dto.setDescription("foo2");
        accountService.save(dto);

        MockHttpServletRequestBuilder request = delete("/account/" + dto.getId())
                .contentType(Helper.APPLICATION_JSON_UTF8);

        this.mockMvc.perform(request)
                .andExpect(status().isOk());
    }
}
