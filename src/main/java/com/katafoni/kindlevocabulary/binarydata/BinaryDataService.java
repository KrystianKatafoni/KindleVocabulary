package com.katafoni.kindlevocabulary.binarydata;

import org.springframework.web.multipart.MultipartFile;

public interface BinaryDataService {

    String saveFileAsDatabaseStorage(MultipartFile multipartFile);

    void deleteFileSavedAsDatabaseStorage(String fileName);
}
