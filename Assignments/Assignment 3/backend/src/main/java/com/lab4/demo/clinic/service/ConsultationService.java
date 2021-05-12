package com.lab4.demo.clinic.service;

import com.lab4.demo.clinic.mapper.ConsultationMapper;
import com.lab4.demo.clinic.model.Consultation;
import com.lab4.demo.clinic.model.Patient;
import com.lab4.demo.clinic.model.dto.ConsultationDTO;
import com.lab4.demo.clinic.repository.ConsultationRepository;
import com.lab4.demo.clinic.repository.PatientRepository;
import com.lab4.demo.user.model.User;
import com.lab4.demo.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ConsultationService {

    private final ConsultationRepository consultationRepository;
    private final ConsultationMapper consultationMapper;

    private final PatientRepository patientRepository;
    private final UserRepository userRepository;

    public List<ConsultationDTO> findAll(){
        return consultationRepository.findAll().stream()
                .map(consultationMapper::consultationToDTO)
                .collect(Collectors.toList());
    }

    public ConsultationDTO findById(Long id){
        return consultationRepository.findById(id).map(consultationMapper::consultationToDTO).orElseThrow(() -> new EntityNotFoundException("Could not find consultation: " + id));
    }

    public void deleteById(Long id){
        consultationRepository.deleteById(id);
    }

    public ConsultationDTO save(ConsultationDTO consultationDTO){
        User doctor = userRepository.findById(consultationDTO.getDoctor_id()).orElseThrow(() -> new EntityNotFoundException("Could not find doctor"));
        Patient patient = patientRepository.findById(consultationDTO.getPatient_id()).orElseThrow(() -> new EntityNotFoundException("Could not find patient"));

        Consultation check = consultationRepository.findConsultationByDoctorAndStartDate(doctor, consultationDTO.getStart_date());

        if(check == null)
        {
            Consultation consultation = Consultation.builder()
                    .doctor(doctor)
                    .patient(patient)
                    .startDate(consultationDTO.getStart_date())
                    .endDate(consultationDTO.getEnd_date())
                    .details(consultationDTO.getDetails())
                    .build();

            return consultationMapper.consultationToDTO(consultationRepository.save(consultation));
        }else
        {
            throw new RuntimeException("Not free");
        }
    }

    public ConsultationDTO update(Long id, ConsultationDTO consultationDTO){
        Consultation consultation = consultationMapper.consultationFromDTO(consultationDTO);
        consultation.setId(id);

        return consultationMapper.consultationToDTO(consultationRepository.save(consultation));
    }
}
