package com.lab4.demo.clinic.controller;

import com.lab4.demo.BaseControllerTest;
import com.lab4.demo.TestCreationFactory;
import com.lab4.demo.clinic.model.dto.ConsultationDTO;
import com.lab4.demo.clinic.model.dto.PatientDTO;
import com.lab4.demo.clinic.service.ConsultationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static com.lab4.demo.UrlMapping.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ConsultationControllerTest extends BaseControllerTest {

    @InjectMocks
    private ConsultationController consultationController;

    @Mock
    private ConsultationService consultationService;

    @BeforeEach
    protected void setUp() {
        super.setUp();
        MockitoAnnotations.openMocks(this);
        consultationController = new ConsultationController(consultationService);
        mockMvc = MockMvcBuilders.standaloneSetup(consultationController).build();
    }

    @Test
    void findAll() throws Exception {
        List<ConsultationDTO> consultationDTO = TestCreationFactory.listOf(ConsultationDTO.class);
        when(consultationService.findAll()).thenReturn(consultationDTO);

        ResultActions result = mockMvc.perform(get(CONSULTATION));
        result.andExpect(status().isOk())
                .andExpect(jsonContentToBe(consultationDTO));
    }

    @Test
    void createConsultation() throws Exception {
        ConsultationDTO consultationDTO = (ConsultationDTO) TestCreationFactory.listOf(ConsultationDTO.class).get(1);
        when(consultationService.save(consultationDTO)).thenReturn(consultationDTO);

        ResultActions result = performPostWithRequestBody(CONSULTATION,consultationDTO);
        result.andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonContentToBe(consultationDTO));
    }

    @Test
    void editConsultation() throws Exception {
        ConsultationDTO consultationDTO = (ConsultationDTO) TestCreationFactory.listOf(ConsultationDTO.class).get(1);
        consultationDTO.setId(1L);
        when(consultationService.save(consultationDTO)).thenReturn(consultationDTO);

        ResultActions result = performPatchWithRequestBodyAndPathVariable(CONSULTATION+ENTITY,consultationDTO, consultationDTO.getId());
        result.andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonContentToBe(consultationDTO));
    }

    @Test
    void deleteConsultation() throws Exception {
        ConsultationDTO consultationDTO = (ConsultationDTO) TestCreationFactory.listOf(ConsultationDTO.class).get(1);
        consultationDTO.setId(1L);
        when(consultationService.save(consultationDTO)).thenReturn(consultationDTO);

        ResultActions result = performDeleteWIthPathVariable(CONSULTATION+ENTITY,consultationDTO.getId());
        result.andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());
    }
}