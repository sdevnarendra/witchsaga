package com.geekseat.test.witchsaga.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.geekseat.test.witchsaga.WitchsagaApplication;
import com.geekseat.test.witchsaga.constant.APIConstant;
import com.geekseat.test.witchsaga.dto.KillSubjectDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest(classes = {WitchsagaApplication.class})
public class WitchSagaControllerTest {

    @Autowired
    MockMvc mockMvc;

    ObjectMapper mapper = new ObjectMapper();

    @Test
    void onTestBasedOnGivenExamplePdfFile() throws Exception {
//        Given:
//        Person A: Age of death = 10, Year of Death = 12
//        Person B: Age of death = 13, Year of Death = 17

        List<KillSubjectDTO> request = new ArrayList<>();
        KillSubjectDTO personA = new KillSubjectDTO();
        personA.setAgeOfDeath(10);
        personA.setYearOfDeath(12);
        request.add(personA);

        KillSubjectDTO personB = new KillSubjectDTO();
        personB.setAgeOfDeath(13);
        personB.setYearOfDeath(17);
        request.add(personB);

        String api = APIConstant.WITCH_SAGA_CONTROLLER + APIConstant.SOLVE_WITCH_PROBLEM;

        mockMvc.perform(post(api)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsBytes(request)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(jsonPath("$.averageResult", equalTo(4.5)));
    }

    @Test
    void onNegativeCalculationShouldReturnNegative() throws Exception {
        List<KillSubjectDTO> request = new ArrayList<>();
        KillSubjectDTO person = new KillSubjectDTO();
        person.setAgeOfDeath(10);
        person.setYearOfDeath(1);
        request.add(person);

        String api = APIConstant.WITCH_SAGA_CONTROLLER + APIConstant.SOLVE_WITCH_PROBLEM;

        mockMvc.perform(post(api)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsBytes(request)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(jsonPath("$.averageResult", equalTo(-1)));
    }

    @Test
    void onUserInputBeyondIntegerMaxShouldReturnBadRequest() throws Exception {
        // Used jsonString to prevent error Max Integer from IDE
        String jsonString = "[{\"ageOfDeath\": 1,\"yearOfDeath\": 2147483648}]";

        String api = APIConstant.WITCH_SAGA_CONTROLLER + APIConstant.SOLVE_WITCH_PROBLEM;

        mockMvc.perform(post(api)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonString))
                .andExpect(status().isBadRequest());
    }

    @Test
    void onCalculationOperationBeyondIntegerMaxShouldReturnNegative() throws Exception {
        List<KillSubjectDTO> request = new ArrayList<>();
        KillSubjectDTO person = new KillSubjectDTO();
        person.setAgeOfDeath(1);
        person.setYearOfDeath(46);
        request.add(person);

        String api = APIConstant.WITCH_SAGA_CONTROLLER + APIConstant.SOLVE_WITCH_PROBLEM;

        mockMvc.perform(post(api)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsBytes(request)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(jsonPath("$.averageResult", equalTo(-1)))
                .andExpect(jsonPath("$.message", equalTo("Success")));
    }

    @Test
    void onNegativeInputShouldReturnNegative() throws Exception {
        List<KillSubjectDTO> request = new ArrayList<>();
        KillSubjectDTO person = new KillSubjectDTO();
        person.setAgeOfDeath(-1);
        person.setYearOfDeath(-1);
        request.add(person);

        String api = APIConstant.WITCH_SAGA_CONTROLLER + APIConstant.SOLVE_WITCH_PROBLEM;

        mockMvc.perform(post(api)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsBytes(request)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(jsonPath("$.averageResult", equalTo(-1)));
    }
}
