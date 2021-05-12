package com.lab4.demo.clinic.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ConsultationDTO {

    private Long id;
    private Long doctor_id;
    private Long patient_id;
    private Date start_date;
    private Date end_date;
    private String details;
}
