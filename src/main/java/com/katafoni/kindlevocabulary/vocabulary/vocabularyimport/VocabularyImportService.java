package com.katafoni.kindlevocabulary.vocabulary.vocabularyimport;

import com.katafoni.kindlevocabulary.dto.extern.PhraseDto;

import java.util.Set;

public interface VocabularyImportService {
    Set<PhraseDto> createVocabularySet(ImportRequest importRequest);
}
