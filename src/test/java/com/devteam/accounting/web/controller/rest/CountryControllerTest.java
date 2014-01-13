package com.devteam.accounting.web.controller.rest;

import com.devteam.accounting.dto.CountryDto;
import com.devteam.accounting.service.CountryService;
import com.devteam.accounting.util.Helper;
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

    @Autowired
    private CountryService countryService;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void getCountries() throws Exception {
        countryService.deleteAll();
        CountryDto dto = new CountryDto();
        dto.setName("indonesia");
        dto.setIso2("ID");
        dto.setIso3("IDN");
        countryService.save(dto);

        MockHttpServletRequestBuilder request = get("/country")
                .accept(Helper.APPLICATION_JSON_UTF8);

        this.mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().contentType(Helper.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.count", is(1)))
                .andExpect(jsonPath("$.data", hasSize(1)))

                .andExpect(jsonPath("$.data[0].id", is(dto.getId().intValue())))
                .andExpect(jsonPath("$.data[0].name", equalTo("indonesia")))
                .andExpect(jsonPath("$.data[0].iso2", equalTo("ID")))
                .andExpect(jsonPath("$.data[0].iso3", equalTo("IDN")));
    }

    @Test
    public void getCountry() throws Exception {
        countryService.deleteAll();
        CountryDto dto = new CountryDto();
        dto.setName("indonesia");
        dto.setIso2("ID");
        dto.setIso3("IDN");
        countryService.save(dto);

        MockHttpServletRequestBuilder request = get("/country/" + dto.getId())
                .accept(Helper.APPLICATION_JSON_UTF8);

        this.mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().contentType(Helper.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.id", is(dto.getId().intValue())))
                .andExpect(jsonPath("$.name", equalTo("indonesia")))
                .andExpect(jsonPath("$.iso2", equalTo("ID")))
                .andExpect(jsonPath("$.iso3", equalTo("IDN")));
    }

    @Test
    public void saveCountry() throws Exception {
        countryService.deleteAll();
        CountryDto dto = new CountryDto();
        dto.setName("India");
        dto.setIso2("IN");
        dto.setIso3("IND");

        byte[] bodyRequest = Helper.convertObjectToJsonBytes(dto);

        MockHttpServletRequestBuilder request = post("/country")
                .contentType(Helper.APPLICATION_JSON_UTF8)
                .content(bodyRequest)
                .accept(Helper.APPLICATION_JSON_UTF8);

        this.mockMvc.perform(request)
                .andExpect(status().isOk());
    }

    @Test
    public void updateCountry() throws Exception {
        CountryDto dto = new CountryDto();
        dto.setName("Indonesia");
        dto.setIso2("ID");
        dto.setIso3("IDN");
        countryService.save(dto);

        dto.setName("INDONESIA[modified]");

        byte[] bodyRequest = Helper.convertObjectToJsonBytes(dto);

        MockHttpServletRequestBuilder request = put("/country")
                .contentType(Helper.APPLICATION_JSON_UTF8)
                .content(bodyRequest)
                .accept(Helper.APPLICATION_JSON_UTF8);

        this.mockMvc.perform(request).andExpect(status().isOk());
    }

    @Test
    public void deleteCountry() throws Exception {
        CountryDto dto = new CountryDto();
        dto.setName(Helper.createStringWithLength(100));
        dto.setIso2(Helper.createStringWithLength(2));
        dto.setIso3(Helper.createStringWithLength(3));
        countryService.save(dto);

        MockHttpServletRequestBuilder request = delete("/country/" + dto.getId())
                .contentType(Helper.APPLICATION_JSON_UTF8);
        this.mockMvc.perform(request)
                .andExpect(status().isOk());
    }
}
