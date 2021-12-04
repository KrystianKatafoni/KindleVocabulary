package com.katafoni.kindlevocabulary.binarydata;

import com.katafoni.kindlevocabulary.properties.BinaryDataProperties;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class BinaryDataServiceImpl implements BinaryDataService {

    private BinaryDataProperties binaryDataProperties;

    private BinaryDataProviderFactory binaryDataProviderFactory;

    public BinaryDataServiceImpl(BinaryDataProperties binaryDataProperties,
                                 BinaryDataProviderFactory binaryDataProviderFactory) {
        this.binaryDataProperties = binaryDataProperties;
        this.binaryDataProviderFactory = binaryDataProviderFactory;
    }


    @Override
    public void saveFileAsDatabaseStorage(MultipartFile multipartFile) {
        BinaryDataProvider binaryDataProvider =
                binaryDataProviderFactory.findBinaryDataProvider(BinaryDataProviderName.LOCAL_STORAGE);
        try {
            binaryDataProvider.saveFile(multipartFile.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
