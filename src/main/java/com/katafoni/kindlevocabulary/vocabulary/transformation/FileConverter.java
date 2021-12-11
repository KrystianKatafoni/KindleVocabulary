package com.katafoni.kindlevocabulary.vocabulary.transformation;

import com.katafoni.kindlevocabulary.entity.Phrase;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;

interface FileConverter {

    Set<Phrase> convertFileToPhrases(MultipartFile multipartFile);
}
