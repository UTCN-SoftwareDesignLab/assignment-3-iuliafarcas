package com.lab4.demo.clinic.repository;

import com.lab4.demo.clinic.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {
}
