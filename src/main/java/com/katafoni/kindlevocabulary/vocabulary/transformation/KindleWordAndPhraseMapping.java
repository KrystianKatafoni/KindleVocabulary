package com.katafoni.kindlevocabulary.vocabulary.transformation;

import com.katafoni.kindlevocabulary.entity.Language;
import com.katafoni.kindlevocabulary.entity.Phrase;
import com.katafoni.kindlevocabulary.language.LanguageService;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;
import java.util.Set;

@Mapper(componentModel = "spring")
public abstract class KindleWordAndPhraseMapping {

    @Autowired
    private LanguageService languageService;

    public Phrase kindleWordToPhrase(KindleWord kindleWord) {
        Phrase phrase = new Phrase();
        phrase.setSourceText(kindleWord.getWord());
        Optional<Language> languageOpt =
                this.languageService.getLanguageByAbbreviation(kindleWord.getLanguage());
        Language language = languageOpt.orElse(null);
        phrase.setLanguage(language);
        return phrase;
    }

    public abstract Set<Phrase> kindleWordsToPhrases(Set<KindleWord> kindleWords);
}
