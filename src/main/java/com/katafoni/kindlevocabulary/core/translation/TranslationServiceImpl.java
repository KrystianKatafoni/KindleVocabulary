package com.katafoni.kindlevocabulary.core.translation;

import com.katafoni.kindlevocabulary.domain.entity.Language;
import com.katafoni.kindlevocabulary.domain.entity.Phrase;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class TranslationServiceImpl implements TranslationService{
    @Override
    public Set<Phrase> translatePhrases(Set<Phrase> phrases, Language translationLanguage) {
        return null;
    }
}
