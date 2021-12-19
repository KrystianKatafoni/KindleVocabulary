package com.katafoni.kindlevocabulary.util;

import com.katafoni.kindlevocabulary.domain.entity.Language;
import com.katafoni.kindlevocabulary.domain.entity.Phrase;
import com.katafoni.util.creators.PhraseCreator;
import org.assertj.core.util.Sets;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class PhraseUtilsTest {

    private Language germanSourceLanguage = new Language("german", "de");
    private Set<Phrase> testPhrases = PhraseCreator.createPhraseSetWithAdditionalSourceLang(11, germanSourceLanguage);

    @Test
    void when_filterByLanguage_english_then_five_phrases_in_result() {

        //when
        Set<Phrase> resultPhrasesEnglish = PhraseUtils.filterByLanguage(this.testPhrases, PhraseCreator.SOURCE_LANGUAGE);

        //then
        assertEquals(5, resultPhrasesEnglish.size());
        assertThat(resultPhrasesEnglish).extracting(Phrase::getSourceLanguage).containsOnly(PhraseCreator.SOURCE_LANGUAGE);
    }

    @Test
    void when_filterByLanguage_german_then_six_phrases_in_result() {

        //when
        Set<Phrase> resultPhrasesGerman = PhraseUtils.filterByLanguage(this.testPhrases, this.germanSourceLanguage);

        //then
        assertEquals(6, resultPhrasesGerman.size());
        assertThat(resultPhrasesGerman).extracting(Phrase::getSourceLanguage).containsOnly(this.germanSourceLanguage);
    }

    @Test
    void when_filterByLanguage_russian_then_zero_phrases_in_result() {

        //given
        Language russianSourceLanguage = new Language("russian", "ru");

        //when
        Set<Phrase> resultPhrases = PhraseUtils.filterByLanguage(this.testPhrases, russianSourceLanguage);

        //then
        assertTrue(resultPhrases.isEmpty());
    }

    @Test
    void when_filterByLanguage_empty_phrases_then_return_empty_phrases() {

        //when
        Set<Phrase> resultPhrases = PhraseUtils.filterByLanguage(Sets.newHashSet(), PhraseCreator.SOURCE_LANGUAGE);

        //then
        assertTrue(resultPhrases.isEmpty());
    }

    @Test
    void when_filterByLanguage_phrases_null_then_throw_exception() {

        //when
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> PhraseUtils.filterByLanguage(null, this.germanSourceLanguage));

        //then
        assertEquals("Argument phrases cannot be null", exception.getMessage());
    }

    @Test
    void when_filterByLanguage_language_null_then_throw_exception() {

        //when
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> PhraseUtils.filterByLanguage(testPhrases, null));

        //then
        assertEquals("Argument language cannot be null", exception.getMessage());
    }

}