package com.katafoni.kindlevocabulary.vocabulary.vocabularyimport;

import com.katafoni.kindlevocabulary.dto.extern.PhraseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping(value = "/files/vocabulary")
public class VocabularyImportController {

    private VocabularyImportService vocabularyImportService;

    @GetMapping
    public List<PhraseDto> getVocabularyList(@RequestParam("file") @NotNull MultipartFile file,
                                             @RequestParam(value = "rem_dupl", required = false) Boolean removeDuplicates,
                                             @RequestParam(value = "trans_lang", required = false) String translationLanguage,
                                             @RequestParam(value="filter_lang", required = false) String filteringLanguage){

        ImportRequest importRequest = new ImportRequest(file, removeDuplicates, translationLanguage, filteringLanguage);
        List<PhraseDto> vocabularyList = this.vocabularyImportService.createVocabularyList(importRequest);
        return vocabularyList;
    }
}
