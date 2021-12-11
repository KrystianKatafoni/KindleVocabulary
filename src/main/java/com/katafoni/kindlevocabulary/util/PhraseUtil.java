package com.katafoni.kindlevocabulary.util;

import com.katafoni.kindlevocabulary.domain.entity.Language;
import com.katafoni.kindlevocabulary.domain.entity.Phrase;

import java.util.Set;
import java.util.stream.Collectors;

public class PhraseUtil {

    public static Set<Phrase> filterByLanguage(Set<Phrase> phrases, Language language) {
        return phrases.stream().filter(phrase -> phrase.getSourceLanguage().equals(language)).collect(Collectors.toSet());
    }
}
