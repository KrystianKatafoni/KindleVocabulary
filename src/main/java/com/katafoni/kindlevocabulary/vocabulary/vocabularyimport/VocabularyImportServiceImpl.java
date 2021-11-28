package com.katafoni.kindlevocabulary.vocabulary.vocabularyimport;

import com.katafoni.kindlevocabulary.dto.extern.PhraseDto;
import com.katafoni.kindlevocabulary.entity.Phrase;
import com.katafoni.kindlevocabulary.util.PhraseUtil;
import com.katafoni.kindlevocabulary.vocabulary.transformation.TransformationService;
import com.katafoni.kindlevocabulary.vocabulary.translation.TranslationService;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class VocabularyImportServiceImpl implements VocabularyImportService{

    private TransformationService transformationService;

    private TranslationService translationService;


    @Override
    public List<PhraseDto> createVocabularyList(ImportRequest importRequest) {
        Set<Phrase> phrases = this.transformationService.transformFileToPhrases(importRequest.getMultipartFile());

        if(BooleanUtils.isTrue(importRequest.getRemoveDuplicates())) {
            phrases = PhraseUtil.removeDuplicates(phrases);
        }

        if(StringUtils.isNotBlank(importRequest.getFilteringLanguage())) {
            PhraseUtil.filterByLanguage(phrases, null);
        }

        if(StringUtils.isNotBlank(importRequest.getTranslationLanguage())) {
            phrases = translationService.translatePhrases(phrases);
        }


    }
}
