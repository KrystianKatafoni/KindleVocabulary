package com.katafoni.kindlevocabulary.common.mapping;

import com.katafoni.kindlevocabulary.dto.PhraseDto;
import com.katafoni.kindlevocabulary.entity.Language;
import com.katafoni.kindlevocabulary.entity.Phrase;
import com.katafoni.kindlevocabulary.language.LanguageService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;
import java.util.Set;

@Mapper(componentModel = "spring")
public abstract class PhraseAndPhraseDtoMapper {

    @Autowired
    private LanguageService languageService;

    @Mappings({
            @Mapping(target = "id", source = "phrase.id"),
            @Mapping(target = "createdAt", source = "phrase.createdAt"),
            @Mapping(target = "updatedAt", source = "phrase.updatedAt"),
            @Mapping(target = "sourceText", source = "phrase.sourceText"),
            @Mapping(target = "translatedText", source = "phrase.translatedText"),
            @Mapping(target = "language", source = "phrase.language.languageName"),
    })
    public abstract PhraseDto phraseToPhraseDto(Phrase phrase);

    public abstract Phrase phraseDtoToPhrase(PhraseDto phraseDto);

    Language map(String name) {
        Optional<Language> languageByName = this.languageService.getLanguageByName(name);
        return languageByName.orElse(null);
    }

    public abstract Set<PhraseDto> phrasesToPhraseDtos(Set<Phrase> phrases);

    public abstract Set<Phrase> phraseDtosToPhrases(Set<PhraseDto> phraseDtos);
}