package com.katafoni.kindlevocabulary.vocabulary.vocabularyimport;

import com.katafoni.kindlevocabulary.dto.extern.PhraseDto;

import java.util.List;

public interface VocabularyImportService {
    List<PhraseDto> createVocabularyList(ImportRequest importRequest);
}
