package com.katafoni.kindlevocabulary.vocabulary.transformation;

import com.katafoni.kindlevocabulary.binarydata.BinaryDataService;
import com.katafoni.kindlevocabulary.entity.Phrase;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;

@Service
public class TransformationServiceImpl implements TransformationService {

    private BinaryDataService binaryDataService;

    public TransformationServiceImpl(BinaryDataService binaryDataService) {
        this.binaryDataService = binaryDataService;
    }

    @Override
    public Set<Phrase> transformFileToPhrases(MultipartFile multipartFile) {
        this.binaryDataService.saveFileAsDatabaseStorage(multipartFile);
        return null;
    }
}
