package com.lab4.demo;

import com.lab4.demo.clinic.model.Patient;
import com.lab4.demo.clinic.repository.ConsultationRepository;
import com.lab4.demo.clinic.repository.PatientRepository;
import com.lab4.demo.security.AuthService;
import com.lab4.demo.security.dto.SignupRequest;
import com.lab4.demo.user.repository.RoleRepository;
import com.lab4.demo.user.repository.UserRepository;
import com.lab4.demo.user.model.ERole;
import com.lab4.demo.user.model.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class Bootstrapper implements ApplicationListener<ApplicationReadyEvent> {

    private final RoleRepository roleRepository;

    private final UserRepository userRepository;

    private final AuthService authService;

    private final PatientRepository patientRepository;
    private final ConsultationRepository consultationRepository;

    @Value("${app.bootstrap}")
    private Boolean bootstrap;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        if (bootstrap) {
            consultationRepository.deleteAll();
            patientRepository.deleteAll();
            userRepository.deleteAll();
            roleRepository.deleteAll();
            for (ERole value : ERole.values()) {
                roleRepository.save(
                        Role.builder()
                                .name(value)
                                .build()
                );
            }
            authService.register(SignupRequest.builder()
                    .email("iulia@email.com")
                    .username("iulia")
                    .password("iulia")
                    .roles(Set.of("ADMIN"))
                    .build());
            authService.register(SignupRequest.builder()
                    .email("iulia1@email.com")
                    .username("iulia1")
                    .password("iulia1")
                    .roles(Set.of("DOCTOR"))
                    .build());
            authService.register(SignupRequest.builder()
                    .email("iulia2@email.com")
                    .username("iulia2")
                    .password("iulia2")
                    .roles(Set.of("SECRETARY"))
                    .build());

            Patient patient = Patient.builder()
                    .name("Iulia")
                    .birth_date(new Date())
                    .card_id("11111111")
                    .cnp("1111111111111111")
                    .address("address")
                    .build();
            patientRepository.save(patient);
        }
    }
}
