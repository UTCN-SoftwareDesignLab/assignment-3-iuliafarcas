package com.lab4.demo.clinic.controller;

import com.lab4.demo.clinic.model.dto.ConsultationDTO;
import com.lab4.demo.clinic.service.ConsultationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


import java.util.List;

import static com.lab4.demo.UrlMapping.*;

@RequiredArgsConstructor
@RestController
@RequestMapping(CONSULTATION)
public class ConsultationController {

    private final ConsultationService consultationService;

    @GetMapping
    public List<ConsultationDTO> findAll(){
        return consultationService.findAll();
    }

    @PostMapping
    public ConsultationDTO createConsultation(@RequestBody ConsultationDTO consultationDTO){
        return consultationService.save(consultationDTO);
    }

    @PatchMapping(ENTITY)
    public ConsultationDTO editConsultation(@RequestBody ConsultationDTO consultationDTO, @PathVariable Long id){
        return consultationService.update(id, consultationDTO);
    }

    @DeleteMapping(ENTITY)
    public void deleteConsultation(@PathVariable Long id){
        consultationService.deleteById(id);
    }
}
