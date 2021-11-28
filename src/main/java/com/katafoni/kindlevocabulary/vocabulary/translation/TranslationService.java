package com.katafoni.kindlevocabulary.vocabulary.translation;

import com.katafoni.kindlevocabulary.entity.Phrase;

import java.util.Set;

public interface TranslationService {
    Set<Phrase> translatePhrases(Set<Phrase> phrases);
}
