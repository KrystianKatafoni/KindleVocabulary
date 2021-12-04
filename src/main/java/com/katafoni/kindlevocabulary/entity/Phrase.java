package com.katafoni.kindlevocabulary.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Entity
public class Phrase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @NotBlank(message = "Sourcetext is mandatory")
    private String sourceText;

    private String translatedText;

    @ManyToOne()
    @JoinColumn(name = "language_id")
    private Language language;

    private LocalDateTime createdInKindle;


}
