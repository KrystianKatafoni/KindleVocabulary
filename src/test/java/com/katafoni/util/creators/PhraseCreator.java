package com.katafoni.util.creators;

import com.google.common.collect.Iterables;
import com.google.common.collect.Sets;
import com.katafoni.kindlevocabulary.domain.entity.Language;
import com.katafoni.kindlevocabulary.domain.entity.Phrase;
import org.apache.commons.lang3.RandomStringUtils;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public class PhraseCreator {

    public final static Language TRANSLATED_LANGUAGE = new Language("polish", "pl");
    public final static Language SOURCE_LANGUAGE = new Language("english", "en");

    public static Set<Phrase> createPhraseSet(long size) {
        return LongStream.range(0, size)
                .mapToObj(longNum -> createPhrase(longNum))
                .collect(Collectors.toSet());
    }

    public static Set<Phrase> createPhraseSetWithAdditionalSourceLang(long size, Language secondSourceLang) {
        if(secondSourceLang == null) {
            throw new IllegalArgumentException("Argument 'secondSourceLang' couldn't be null");
        }

        Set<Phrase> phrasesWithDefaultSourceLang = LongStream.range(0, size / 2)
                .mapToObj(longNum -> createPhrase(longNum))
                .collect(Collectors.toSet());

        Set<Phrase> phrasesWithSecondSourceLang = LongStream.range(size / 2, size)
                .mapToObj(longNum -> createPhraseWithSourceLang(longNum, secondSourceLang))
                .collect(Collectors.toSet());

        return Sets.newHashSet(Iterables.concat(phrasesWithDefaultSourceLang, phrasesWithSecondSourceLang));
    }

    private static Phrase createPhraseWithSourceLang(long id, Language sourceLang) {
        return Phrase.builder()
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now().plusHours(1))
                .id(id)
                .sourceLanguage(sourceLang)
                .translatedLanguage(TRANSLATED_LANGUAGE)
                .sourceText(RandomStringUtils.random(5, true, false))
                .translatedText(RandomStringUtils.random(5, true, false))
                .build();
    }

    private static Phrase createPhrase(long id) {
        return Phrase.builder()
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now().plusHours(1))
                .id(id)
                .sourceLanguage(SOURCE_LANGUAGE)
                .translatedLanguage(TRANSLATED_LANGUAGE)
                .sourceText(RandomStringUtils.random(5, true, false))
                .translatedText(RandomStringUtils.random(5, true, false))
                .build();
    }
}