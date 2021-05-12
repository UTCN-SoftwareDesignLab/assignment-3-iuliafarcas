package com.lab4.demo.clinic.controller;

import com.lab4.demo.BaseControllerTest;
import com.lab4.demo.TestCreationFactory;
import com.lab4.demo.clinic.model.dto.PatientDTO;
import com.lab4.demo.clinic.service.PatientService;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.mockito.Mockito.when;

class PatientControllerTest extends BaseControllerTest {

    @InjectMocks
    private PatientController patientController;

    @Mock
    private PatientService patientService;

    @BeforeEach
    protected void setUp() {
        super.setUp();
        MockitoAnnotations.openMocks(this);
        patientController = new PatientController(patientService);
        mockMvc = MockMvcBuilders.standaloneSetup(patientController).build();
    }

    @Test
    void findAll() throws Exception {
        List<PatientDTO> patientDTO = TestCreationFactory.listOf(PatientDTO.class);
        when(patientService.finadAll()).thenReturn(patientDTO);

        ResultActions result = mockMvc.perform(get(PATIENT));
        result.andExpect(status().isOk())
                .andExpect(jsonContentToBe(patientDTO));
    }

    @Test
    void createPatient() throws Exception {

        PatientDTO patientDTO = (PatientDTO) TestCreationFactory.listOf(PatientDTO.class).get(1);

        when(patientService.save(patientDTO)).thenReturn(patientDTO);

        ResultActions result = performPostWithRequestBody(PATIENT,patientDTO);
        result.andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonContentToBe(patientDTO));
    }

    @Test
    void editPatient() throws Exception {
        PatientDTO patientDTO = (PatientDTO) TestCreationFactory.listOf(PatientDTO.class).get(1);
        patientDTO.setId(1L);

        when(patientService.update(patientDTO.getId(),patientDTO)).thenReturn(patientDTO);

        ResultActions result = performPatchWithRequestBodyAndPathVariable(PATIENT+ENTITY,patientDTO, patientDTO.getId());
        result.andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonContentToBe(patientDTO));

    }

    @Test
    void deletePatient() throws Exception {
        PatientDTO patientDTO = (PatientDTO) TestCreationFactory.listOf(PatientDTO.class).get(1);
        patientDTO.setId(1L);

        ResultActions result = performDeleteWIthPathVariable(PATIENT+ENTITY, patientDTO.getId());
        result.andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());
    }
}