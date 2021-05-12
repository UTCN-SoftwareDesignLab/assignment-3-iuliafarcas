package com.lab4.demo.clinic.mapper;

import com.lab4.demo.clinic.model.Consultation;
import com.lab4.demo.clinic.model.dto.ConsultationDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface ConsultationMapper {

    @Mappings({
            //@Mapping(source = "patient_id", target = "patient"),
            //@Mapping(source = "doctor_id", target = "doctor"),
            @Mapping(source = "start_date", target = "startDate"),
            @Mapping(source = "end_date", target = "endDate")
    })
    Consultation consultationFromDTO(ConsultationDTO consultationDTO);


    @Mappings({
            //@Mapping(target = "patient_id", source = "patient"),
            //@Mapping(target = "doctor_id", source = "doctor"),
            @Mapping(target = "start_date", source = "startDate"),
            @Mapping(target = "end_date", source = "endDate")
    })
    ConsultationDTO consultationToDTO(Consultation consultation);


}
