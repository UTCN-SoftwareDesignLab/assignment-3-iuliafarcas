package com.lab4.demo.clinic.service;

import com.lab4.demo.TestCreationFactory;
import com.lab4.demo.clinic.model.Patient;
import com.lab4.demo.clinic.model.dto.ConsultationDTO;
import com.lab4.demo.clinic.repository.ConsultationRepository;
import com.lab4.demo.clinic.repository.PatientRepository;
import com.lab4.demo.user.mapper.UserMapper;
import com.lab4.demo.user.model.User;
import com.lab4.demo.user.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class ConsultationServiceTest {

    @Autowired
    private ConsultationService consultationService;

    @Autowired
    private ConsultationRepository consultationRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @BeforeEach
    void setUp(){
        consultationRepository.deleteAll();
    }

    @Test
    void findAll() {
        List<ConsultationDTO> consultations = consultationService.findAll();

        Assertions.assertEquals(consultations.size(), 0);
    }

    @Test
    void save() {
        Patient p = (Patient) TestCreationFactory.listOf(Patient.class).get(1);
        p = patientRepository.save(p);

        User d = User.builder()
                .username("Username")
                .password("password")
                .email("a@a.com")
                .build();
        d= userRepository.save(d);


        ConsultationDTO consultation = (ConsultationDTO) TestCreationFactory.listOf(ConsultationDTO.class).get(1);
        consultation.setDoctor_id(d.getId());
        consultation.setPatient_id(p.getId());

        consultation = consultationService.save(consultation);

        ConsultationDTO consultationDTO = consultationService.findById(consultation.getId());
        Assertions.assertNotNull(consultationDTO);
    }

    @Test
    void update() {

        Patient p = (Patient) TestCreationFactory.listOf(Patient.class).get(1);
        p = patientRepository.save(p);

        User d = User.builder()
                .username("Username")
                .password("password")
                .email("a@a.com")
                .build();
        d= userRepository.save(d);

        ConsultationDTO consultation = (ConsultationDTO) TestCreationFactory.listOf(ConsultationDTO.class).get(1);
        consultation.setDoctor_id(d.getId());
        consultation.setPatient_id(p.getId());

        consultation = consultationService.save(consultation);
        consultation.setDetails("DETAILS");

        ConsultationDTO consultationDTO = consultationService.update(consultation.getId(), consultation);

        Assertions.assertEquals(consultation.getDetails(), consultationDTO.getDetails());

    }
}