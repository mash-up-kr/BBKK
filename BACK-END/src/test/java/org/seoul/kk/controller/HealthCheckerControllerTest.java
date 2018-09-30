package org.seoul.kk.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.seoul.kk.MockitoExtension;
import org.seoul.kk.SpringMockMvcTestSupport;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class HealthCheckerControllerTest extends SpringMockMvcTestSupport {

    @Test
    void healthCheckTest() throws Exception {
        this.mockMvc.perform(get("/v1/health-checker"))
                .andDo(print())
                .andExpect(status().is(HttpStatus.OK.value()))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.deployDate").exists())
                .andExpect(jsonPath("$.deployVersion").exists())
                .andExpect(jsonPath("$.distributor").exists());
    }


}
