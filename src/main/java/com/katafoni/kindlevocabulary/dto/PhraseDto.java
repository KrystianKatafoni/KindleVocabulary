package com.katafoni.kindlevocabulary.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PhraseDto {

    private long id;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private String sourceText;

    private String translatedText;

    private String language;
}
