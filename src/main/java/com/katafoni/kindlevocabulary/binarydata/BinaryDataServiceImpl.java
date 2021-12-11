package com.katafoni.kindlevocabulary.binarydata;

import com.katafoni.kindlevocabulary.common.exception.ExceptionMessageCodes;
import com.katafoni.kindlevocabulary.common.exception.FileUploadException;
import com.katafoni.kindlevocabulary.common.properties.BinaryDataProperties;
import com.katafoni.kindlevocabulary.util.LoggingUtil;
import com.katafoni.kindlevocabulary.util.MessageSourceFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static com.katafoni.kindlevocabulary.binarydata.BinaryDataErrorCodes.MULTIPART_FILE_FAILURE;

@Service
public class BinaryDataServiceImpl implements BinaryDataService {

    private static final Logger logger = LoggerFactory.getLogger(BinaryDataServiceImpl.class);

    private BinaryDataProperties binaryDataProperties;

    private BinaryDataProviderFactory binaryDataProviderFactory;

    private MessageSourceFacade messageSource;

    public BinaryDataServiceImpl(BinaryDataProperties binaryDataProperties,
                                 BinaryDataProviderFactory binaryDataProviderFactory,
                                 MessageSourceFacade messageSource) {
        this.binaryDataProperties = binaryDataProperties;
        this.binaryDataProviderFactory = binaryDataProviderFactory;
        this.messageSource = messageSource;
    }

    @Override
    public String saveFileAsDatabaseStorage(MultipartFile multipartFile) {
        BinaryDataProvider binaryDataProvider =
                binaryDataProviderFactory.findBinaryDataProvider(BinaryDataProviderName.LOCAL_STORAGE);
        String fileName;
        try {
            fileName = binaryDataProvider.saveFile(multipartFile.getInputStream());
        } catch (IOException e) {
            String message = messageSource.getMessage(MULTIPART_FILE_FAILURE.getMessageCode(), e.getMessage());
            logger.error(LoggingUtil.getLoggingMessage(MULTIPART_FILE_FAILURE.getErrorCode(), message));
            throw new FileUploadException(MULTIPART_FILE_FAILURE.getErrorCode(),
                    messageSource.getMessage(ExceptionMessageCodes.FILE_UPLOAD_EXCEPTION));
        }
        return fileName;
    }

    @Override
    public void deleteFileSavedAsDatabaseStorage(String fileName) {
        BinaryDataProvider binaryDataProvider =
                binaryDataProviderFactory.findBinaryDataProvider(BinaryDataProviderName.LOCAL_STORAGE);
        binaryDataProvider.deleteFile(fileName);
    }
}
