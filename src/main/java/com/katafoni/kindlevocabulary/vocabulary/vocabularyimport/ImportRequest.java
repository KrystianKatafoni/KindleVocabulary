package com.katafoni.kindlevocabulary.vocabulary.vocabularyimport;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImportRequest {

    private MultipartFile multipartFile;

    private Boolean removeDuplicates;

    private String translationLanguage;

    private String filteringLanguage;
}
