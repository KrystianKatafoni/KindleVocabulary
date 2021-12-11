package com.katafoni.kindlevocabulary.vocabulary.transformation;

import com.katafoni.kindlevocabulary.common.exception.ExceptionMessageCodes;
import com.katafoni.kindlevocabulary.common.exception.RequestParameterException;
import com.katafoni.kindlevocabulary.common.mapping.PhraseAndPhraseDtoMapper;
import com.katafoni.kindlevocabulary.dto.PhraseDto;
import com.katafoni.kindlevocabulary.entity.Language;
import com.katafoni.kindlevocabulary.entity.Phrase;
import com.katafoni.kindlevocabulary.language.LanguageService;
import com.katafoni.kindlevocabulary.util.LoggingUtil;
import com.katafoni.kindlevocabulary.util.MessageSourceFacade;
import com.katafoni.kindlevocabulary.util.PhraseUtil;
import com.katafoni.kindlevocabulary.vocabulary.translation.TranslationService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import static com.katafoni.kindlevocabulary.vocabulary.transformation.TranformationErrorCodes.LANGUAGE_NAME_INVALID;


@Service
class VocabularyTransformationServiceImpl implements VocabularyTransformationService {

    private static final Logger logger = LoggerFactory.getLogger(VocabularyTransformationServiceImpl.class);

    private FileConverter fileConverter;

    private TranslationService translationService;

    private PhraseAndPhraseDtoMapper phraseAndPhraseDtoMapper;

    private LanguageService languageService;

    private MessageSourceFacade messageSourceFacade;

    public VocabularyTransformationServiceImpl(FileConverter fileConverter, TranslationService translationService,
                                               PhraseAndPhraseDtoMapper phraseAndPhraseDtoMapper,
                                               LanguageService languageService,
                                               MessageSourceFacade messageSourceFacade) {
        this.fileConverter = fileConverter;
        this.translationService = translationService;
        this.phraseAndPhraseDtoMapper = phraseAndPhraseDtoMapper;
        this.languageService = languageService;
        this.messageSourceFacade = messageSourceFacade;
    }

    @Override
    public Set<PhraseDto> getVocabularySet(TransformationRequest transformationRequest) {

        Set<Phrase> phrases = this.fileConverter.convertFileToPhrases(transformationRequest.getMultipartFile());
        removePhrasesWithoutLanguage(phrases);

        if (StringUtils.isNotBlank(transformationRequest.getFilteringLanguage())) {
            Optional<Language> language =
                    languageService.getLanguageByName(transformationRequest.getFilteringLanguage());

            if (language.isPresent()) {
                int initialPhrasesCount = phrases.size();

                phrases = PhraseUtil.filterByLanguage(phrases, language.get());

                int filteredPhrases = initialPhrasesCount - phrases.size();

                logger.info("There was {} filtered phrases for language: {}", filteredPhrases,
                        language.get().getLanguageName());
            } else {
                String message = messageSourceFacade.getMessage(LANGUAGE_NAME_INVALID.getMessageCode(),
                        transformationRequest.getFilteringLanguage());
                logger.warn(LoggingUtil.getLoggingMessage(LANGUAGE_NAME_INVALID.getErrorCode(), message));
                throw new RequestParameterException(LANGUAGE_NAME_INVALID.getErrorCode(),
                        messageSourceFacade.getMessage(ExceptionMessageCodes.REQUEST_PARAMETER_EXCEPTION,
                                "filter_lang", transformationRequest.getFilteringLanguage()));
            }
        }

        if (StringUtils.isNotBlank(transformationRequest.getTranslationLanguage())) {
            phrases = translationService.translatePhrases(phrases);
        }

        Set<PhraseDto> phraseDtos = this.phraseAndPhraseDtoMapper.phrasesToPhraseDtos(phrases);

        return phraseDtos;
    }

    private void removePhrasesWithoutLanguage(Set<Phrase> phrases) {

        int initialPhrasesCount = phrases.size();

        phrases.stream().filter(phrase -> Objects.isNull(phrase.getLanguage()));

        int afterRemovingPhrasesCount = phrases.size();
        int deletedPhrasesCount = initialPhrasesCount - afterRemovingPhrasesCount;
        logger.info("There was {} deleted phrases without language", deletedPhrasesCount);
    }
}