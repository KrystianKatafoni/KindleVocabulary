package com.katafoni.kindlevocabulary.binarydata;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;

public interface BinaryDataService {

    void saveFileAsDatabaseStorage(MultipartFile multipartFile);
}
