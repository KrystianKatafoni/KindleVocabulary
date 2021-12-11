package com.katafoni.kindlevocabulary.vocabulary.transformation;

import com.katafoni.kindlevocabulary.dto.PhraseDto;

import java.util.Set;

public interface VocabularyTransformationService {
    Set<PhraseDto> getVocabularySet(TransformationRequest transformationRequest);
}
