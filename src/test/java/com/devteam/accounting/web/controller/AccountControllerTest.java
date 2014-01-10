package com.devteam.accounting.web.controller;

import com.devteam.accounting.dto.AccountDto;
import com.devteam.accounting.service.AccountService;
import com.devteam.accounting.util.TestUtil;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * User: pancara
 * Date: 1/10/14
 * Time: 1:55 PM
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/spring/beans-test.xml", "/spring/controller-test.xml"})
@WebAppConfiguration
public class AccountControllerTest {
    private MockMvc mockMvc;
    private ObjectMapper jsonMapper = new ObjectMapper();

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void getAccount() throws Exception {
        MockHttpServletRequestBuilder request = get("/account/1")
                .accept(TestUtil.APPLICATION_JSON_UTF8);

        this.mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.code", equalTo("FOO")))
                .andExpect(jsonPath("$.description", equalTo("foo")));
    }

    @Test
    public void saveAccount() throws Exception {
        AccountDto parent = new AccountDto();
        parent.setId(1L);

        AccountDto dto = new AccountDto();
        dto.setCode("FOO2");
        dto.setName("foo2");
        dto.setDescription("foo2");
        dto.setParent(parent);

        byte[] bodyRequest = jsonMapper.writeValueAsBytes(dto);

        MockHttpServletRequestBuilder request = post("/account")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(bodyRequest)
                .accept(TestUtil.APPLICATION_JSON_UTF8);

        this.mockMvc.perform(request)
                .andExpect(status().isOk());
    }

    @Test
    public void updateAccount() throws Exception {
        AccountDto parent = new AccountDto();
        parent.setId(1L);

        AccountDto dto = new AccountDto();
        dto.setId(5L);
        dto.setVersion(0L);
        dto.setCode("FOO2[modifixxed]");
        dto.setName("foo2[modified]");
        dto.setDescription("foo2");
        dto.setParent(parent);

        byte[] bodyRequest = jsonMapper.writeValueAsBytes(dto);

        MockHttpServletRequestBuilder request = put("/account")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(bodyRequest)
                .accept(TestUtil.APPLICATION_JSON_UTF8);

        this.mockMvc.perform(request).andExpect(status().isOk());
    }

    @Test
    public void deleteAccount() throws Exception {
        MockHttpServletRequestBuilder request = delete("/account/5")
                .contentType(TestUtil.APPLICATION_JSON_UTF8);

        this.mockMvc.perform(request)
                .andExpect(status().isOk());
    }
}
