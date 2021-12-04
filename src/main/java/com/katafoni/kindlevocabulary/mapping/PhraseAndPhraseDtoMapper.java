package com.katafoni.kindlevocabulary.mapping;

import com.katafoni.kindlevocabulary.dto.extern.PhraseDto;
import com.katafoni.kindlevocabulary.entity.Phrase;
import org.mapstruct.Mapper;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface PhraseAndPhraseDtoMapper {

    PhraseDto phraseToPhraseDto(Phrase phrase);

    Phrase phraseDtoToPhrase(PhraseDto phraseDto);

    Set<PhraseDto> phrasesToPhraseDtos(Set<Phrase> phrases);

    Set<Phrase> phraseDtosToPhrases(Set<PhraseDto> phraseDtos);
}
