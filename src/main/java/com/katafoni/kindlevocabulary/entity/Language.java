package com.katafoni.kindlevocabulary.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
public class Language {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="language_id")
    private long id;
    
    @NotBlank(message="Name is mandatory")
    @Column(name="langugage_name")
    private String languageName;
    
    @NotBlank(message="Abbreviation is mandatory")
    @Column(name="abbreviation")
    private String abbreviation;
}
