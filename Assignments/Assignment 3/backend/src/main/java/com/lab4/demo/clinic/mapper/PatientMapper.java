package com.lab4.demo.clinic.mapper;

import com.lab4.demo.clinic.model.Patient;
import com.lab4.demo.clinic.model.dto.PatientDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PatientMapper {

    Patient patientFromDTO(PatientDTO patientDTO);

    PatientDTO patientToDTO(Patient patient);
}
