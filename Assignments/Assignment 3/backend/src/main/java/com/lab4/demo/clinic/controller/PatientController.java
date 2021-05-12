package com.lab4.demo.clinic.controller;


import com.lab4.demo.clinic.model.dto.PatientDTO;
import com.lab4.demo.clinic.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


import java.util.List;

import static com.lab4.demo.UrlMapping.*;

@RequiredArgsConstructor
@RestController
@RequestMapping(PATIENT)
public class PatientController {

    private final PatientService patientService;

    @GetMapping
    public List<PatientDTO> findAll(){
        return patientService.finadAll();
    }

    @PostMapping
    public PatientDTO createPatient(@RequestBody PatientDTO patientDTO){
        return patientService.save(patientDTO);
    }

    @PatchMapping(ENTITY)
    public PatientDTO editPatient(@RequestBody PatientDTO patientDTO, @PathVariable Long id){
        return patientService.update(id, patientDTO);
    }

    @DeleteMapping(ENTITY)
    public void deletePatient(@PathVariable Long id)
    {
        patientService.deleteById(id);
    }
}
