package com.katafoni.kindlevocabulary.vocabulary.transformation;

import com.katafoni.kindlevocabulary.entity.Phrase;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;

public interface TransformationService {

    Set<Phrase> transformFileToPhrases(MultipartFile multipartFile);
}
