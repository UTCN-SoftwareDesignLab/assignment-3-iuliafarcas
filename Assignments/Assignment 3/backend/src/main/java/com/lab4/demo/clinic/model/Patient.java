package com.lab4.demo.clinic.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String card_id;

    @Column(nullable = false)
    private String cnp;

    @Column(nullable = false)
    private Date birth_date;

    @Column(nullable = false)
    private String address;
}
