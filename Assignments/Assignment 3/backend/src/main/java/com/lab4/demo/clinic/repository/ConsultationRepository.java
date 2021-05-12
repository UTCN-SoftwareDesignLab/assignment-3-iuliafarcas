package com.lab4.demo.clinic.repository;

import com.lab4.demo.clinic.model.Consultation;
import com.lab4.demo.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;

public interface ConsultationRepository extends JpaRepository<Consultation, Long> {
    //Consultation findByDoctorIdAndStart_date(Long id, Date start_date);
    Consultation findConsultationByDoctorAndStartDate(User doctor, Date start_date);
}
