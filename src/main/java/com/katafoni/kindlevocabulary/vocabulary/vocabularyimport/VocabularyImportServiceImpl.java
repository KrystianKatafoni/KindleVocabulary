package com.katafoni.kindlevocabulary.vocabulary.vocabularyimport;

import com.katafoni.kindlevocabulary.dto.extern.PhraseDto;
import com.katafoni.kindlevocabulary.entity.Phrase;
import com.katafoni.kindlevocabulary.mapping.PhraseAndPhraseDtoMapper;
import com.katafoni.kindlevocabulary.util.PhraseUtil;
import com.katafoni.kindlevocabulary.vocabulary.transformation.TransformationService;
import com.katafoni.kindlevocabulary.vocabulary.translation.TranslationService;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.sql.DriverManager;
import java.util.Set;

@Service
public class VocabularyImportServiceImpl implements VocabularyImportService {

    private TransformationService transformationService;

    private TranslationService translationService;

    private PhraseAndPhraseDtoMapper phraseAndPhraseDtoMapper;

    public VocabularyImportServiceImpl(TransformationService transformationService,
                                       TranslationService translationService,
                                       PhraseAndPhraseDtoMapper phraseAndPhraseDtoMapper) {
        this.transformationService = transformationService;
        this.translationService = translationService;
        this.phraseAndPhraseDtoMapper = phraseAndPhraseDtoMapper;
    }

    @Override
    public Set<PhraseDto> createVocabularySet(ImportRequest importRequest) {

        Set<Phrase> phrases = this.transformationService.transformFileToPhrases(importRequest.getMultipartFile());

        if (BooleanUtils.isTrue(importRequest.getRemoveDuplicates())) {
            phrases = PhraseUtil.removeDuplicates(phrases);
        }

        if (StringUtils.isNotBlank(importRequest.getFilteringLanguage())) {
            phrases = PhraseUtil.filterByLanguage(phrases, null);
        }

        if (StringUtils.isNotBlank(importRequest.getTranslationLanguage())) {
            phrases = translationService.translatePhrases(phrases);
        }

        Set<PhraseDto> phraseDtos = this.phraseAndPhraseDtoMapper.phrasesToPhraseDtos(phrases);

        return phraseDtos;
    }
}
