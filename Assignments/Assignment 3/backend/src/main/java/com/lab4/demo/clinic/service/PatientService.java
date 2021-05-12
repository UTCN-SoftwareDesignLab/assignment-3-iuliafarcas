package com.lab4.demo.clinic.service;


import com.lab4.demo.clinic.mapper.PatientMapper;
import com.lab4.demo.clinic.model.Patient;
import com.lab4.demo.clinic.model.dto.PatientDTO;
import com.lab4.demo.clinic.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PatientService {

    private final PatientRepository patientRepository;
    private final PatientMapper patientMapper;

    public List<PatientDTO> finadAll(){
        return patientRepository.findAll().stream()
                .map(patientMapper::patientToDTO)
                .collect(Collectors.toList());
    }

    public PatientDTO findById(Long id){
        return patientRepository.findById(id).map(patientMapper::patientToDTO).orElseThrow(()-> new EntityNotFoundException("Patient not found: " + id));
    }

    public void deleteById(Long id){
        patientRepository.deleteById(id);
    }

    public PatientDTO save(PatientDTO patientDTO){
        Patient patient = patientMapper.patientFromDTO(patientDTO);
        return patientMapper.patientToDTO(patientRepository.save(patient));
    }

    public PatientDTO update(Long id, PatientDTO patientDTO){
        Patient patient = patientMapper.patientFromDTO(patientDTO);
        patient.setId(id);
        return patientMapper.patientToDTO(patientRepository.save(patient));
    }
}
