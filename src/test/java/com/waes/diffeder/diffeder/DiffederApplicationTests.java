package com.waes.diffeder.diffeder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.waes.diffeder.diffeder.model.DiffEqualsResult;
import com.waes.diffeder.diffeder.model.DiffRequestData;
import com.waes.diffeder.diffeder.model.DiffResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.Assert.assertEquals;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DiffederApplicationTests {

    private ObjectMapper objectMapper;

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @Before
    public void init() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    public void allDevicesRetrievedTest() throws Exception {
        String dataReq = objectMapper.writeValueAsString(new DiffRequestData("MTIzNDU="));
        RequestBuilder requestBuilderLeft = MockMvcRequestBuilders
                .post("/v1/diff/1/left")
                .content(dataReq)
                .contentType(APPLICATION_JSON)
                .accept(APPLICATION_JSON);
        MvcResult resultLeft = mockMvc.perform(requestBuilderLeft).andReturn();
        assertEquals(resultLeft.getResponse().getStatus(), 200);

        RequestBuilder requestBuilderRight = MockMvcRequestBuilders
                .post("/v1/diff/1/right")
                .content(dataReq)
                .contentType(APPLICATION_JSON)
                .accept(APPLICATION_JSON);
        MvcResult resultRight = mockMvc.perform(requestBuilderRight).andReturn();
        assertEquals(resultRight.getResponse().getStatus(), 200);

        RequestBuilder requestBuilderGetDiff = MockMvcRequestBuilders
                .get("/v1/diff/1");
        MvcResult resultGetDiff = mockMvc.perform(requestBuilderGetDiff).andReturn();

        assertEquals(resultGetDiff.getResponse().getStatus(), 200);

        DiffResponse diffResponse = objectMapper.readValue(resultGetDiff.getResponse().getContentAsString(), DiffResponse.class);

        assertEquals(diffResponse.getDiffResult(), DiffEqualsResult.EQUALS);

    }
}
