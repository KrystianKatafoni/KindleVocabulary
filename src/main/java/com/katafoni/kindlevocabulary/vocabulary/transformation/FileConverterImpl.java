package com.katafoni.kindlevocabulary.vocabulary.transformation;

import com.katafoni.kindlevocabulary.binarydata.BinaryDataService;
import com.katafoni.kindlevocabulary.entity.Phrase;
import com.katafoni.kindlevocabulary.common.properties.BinaryDataProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;

@Component
class FileConverterImpl implements FileConverter {

    private BinaryDataService binaryDataService;

    private DatabaseReader databaseReader;

    private BinaryDataProperties binaryDataProperties;

    private KindleWordAndPhraseMapping mapper;

    public FileConverterImpl(BinaryDataService binaryDataService, DatabaseReader databaseReader,
                             BinaryDataProperties binaryDataProperties, KindleWordAndPhraseMapping mapper) {
        this.binaryDataService = binaryDataService;
        this.databaseReader = databaseReader;
        this.binaryDataProperties = binaryDataProperties;
        this.mapper = mapper;
    }

    @Override
    public Set<Phrase> convertFileToPhrases(MultipartFile multipartFile) {
        String fileName = this.binaryDataService.saveFileAsDatabaseStorage(multipartFile);
        Set<KindleWord> kindleWords =
                this.databaseReader.readWordsFromKindleDatabase(binaryDataProperties.getLocalStoragePath() + fileName);
        Set<Phrase> phrases = mapper.kindleWordsToPhrases(kindleWords);

        this.binaryDataService.deleteFileSavedAsDatabaseStorage(fileName);

        return phrases;
    }
}
