package com.devteam.accounting.web.controller.rest;

import com.devteam.accounting.dto.AccountDto;
import com.devteam.accounting.dto.CountryDto;
import com.devteam.accounting.util.TestUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * User: pancara
 * Date: 1/10/14
 * Time: 1:55 PM
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/spring/beans-test.xml", "/spring/controller-test.xml"})
@WebAppConfiguration
public class CountryControllerTest {
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void getCountries() throws Exception {
        MockHttpServletRequestBuilder request = get("/country")
                .accept(TestUtil.APPLICATION_JSON_UTF8);

        this.mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.count", is(2)))
                .andExpect(jsonPath("$.data", hasSize(2)))

                .andExpect(jsonPath("$.data[0].id", is(1)))
                .andExpect(jsonPath("$.data[0].name", equalTo("indonesia")))
                .andExpect(jsonPath("$.data[0].iso2", equalTo("ID")))
                .andExpect(jsonPath("$.data[0].iso3", equalTo("IDN")))

                .andExpect(jsonPath("$.data[1].id", is(2)))
                .andExpect(jsonPath("$.data[1].name", equalTo("amerika")))
                .andExpect(jsonPath("$.data[1].iso2", equalTo("US")))
                .andExpect(jsonPath("$.data[1].iso3", equalTo("USA")));
    }

    @Test
    public void getCountry() throws Exception {
        MockHttpServletRequestBuilder request = get("/country/1")
                .accept(TestUtil.APPLICATION_JSON_UTF8);

        this.mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", equalTo("indonesia")))
                .andExpect(jsonPath("$.iso2", equalTo("ID")))
                .andExpect(jsonPath("$.iso3", equalTo("IDN")));
    }

    @Test
    public void saveCountry() throws Exception {
        CountryDto dto = new CountryDto();
        dto.setName("India");
        dto.setIso2("IN");
        dto.setIso3("IND");

        byte[] bodyRequest = TestUtil.convertObjectToJsonBytes(dto);

        MockHttpServletRequestBuilder request = post("/country")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(bodyRequest)
                .accept(TestUtil.APPLICATION_JSON_UTF8);

        this.mockMvc.perform(request)
                .andExpect(status().isOk());
    }

    @Test
    public void updateCountry() throws Exception {
        CountryDto dto = new CountryDto();
        dto.setId(3L);
        dto.setVersion(0L);
        dto.setName("india [updated]");
        dto.setIso2("IN");
        dto.setIso3("IND");

        byte[] bodyRequest = TestUtil.convertObjectToJsonBytes(dto);

        MockHttpServletRequestBuilder request = put("/country")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(bodyRequest)
                .accept(TestUtil.APPLICATION_JSON_UTF8);

        this.mockMvc.perform(request).andExpect(status().isOk());
    }

    @Test
    public void deleteCountry() throws Exception {
        MockHttpServletRequestBuilder request = delete("/country/3")
                .contentType(TestUtil.APPLICATION_JSON_UTF8);

        this.mockMvc.perform(request)
                .andExpect(status().isOk());
    }
}
