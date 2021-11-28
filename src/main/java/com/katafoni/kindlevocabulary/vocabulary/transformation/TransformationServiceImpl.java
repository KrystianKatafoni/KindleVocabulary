package com.katafoni.kindlevocabulary.vocabulary.transformation;

import com.katafoni.kindlevocabulary.entity.Phrase;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;

@Service
public class TransformationServiceImpl implements TransformationService{
    @Override
    public Set<Phrase> transformFileToPhrases(MultipartFile multipartFile) {
        return null;
    }
}
