package com.katafoni.kindlevocabulary.util;

import com.katafoni.kindlevocabulary.entity.Language;
import com.katafoni.kindlevocabulary.entity.Phrase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;
import java.util.stream.Collectors;

public class PhraseUtil {

    public static Set<Phrase> filterByLanguage(Set<Phrase> phrases, Language language) {
        return phrases.stream().filter(phrase -> phrase.getLanguage().equals(language)).collect(Collectors.toSet());
    }
}
