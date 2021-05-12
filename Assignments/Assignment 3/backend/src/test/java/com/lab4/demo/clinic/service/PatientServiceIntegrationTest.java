package com.lab4.demo.clinic.service;

import com.lab4.demo.TestCreationFactory;
import com.lab4.demo.clinic.model.dto.PatientDTO;
import com.lab4.demo.clinic.repository.PatientRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class PatientServiceIntegrationTest {

    @Autowired
    private PatientService patientService;

    @Autowired
    private PatientRepository patientRepository;

    @BeforeEach
    void setUp(){
        patientRepository.deleteAll();
    }

    @Test
    void finadAll() {

        List<PatientDTO> patients = patientService.finadAll();

        Assertions.assertEquals(patients.size(), 0);
    }

    @Test
    void save() {

        PatientDTO patient = (PatientDTO) TestCreationFactory.listOf(PatientDTO.class).get(1);

        patient = patientService.save(patient);

        PatientDTO patientDTO = patientService.findById(patient.getId());
        Assertions.assertNotNull(patientDTO);
    }

    @Test
    void update() {
        PatientDTO patient = (PatientDTO) TestCreationFactory.listOf(PatientDTO.class).get(1);

        patient = patientService.save(patient);
        patient.setAddress("ADDRESS");

        PatientDTO patientDTO = patientService.update(patient.getId(), patient);

        Assertions.assertEquals(patient.getAddress(), patientDTO.getAddress());

    }
}