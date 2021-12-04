package com.katafoni.kindlevocabulary.binarydata;

import com.katafoni.kindlevocabulary.common.exception.ExceptionMessageCodes;
import com.katafoni.kindlevocabulary.common.exception.InternalFailureException;
import com.katafoni.kindlevocabulary.properties.BinaryDataProperties;
import com.katafoni.kindlevocabulary.util.LoggingUtil;
import com.katafoni.kindlevocabulary.util.MessageSourceFacade;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static com.katafoni.kindlevocabulary.binarydata.BinaryDataErrorCodes.DIRECTORY_PATH_FAILURE;

@Component
class LocalStorageProvider implements BinaryDataProvider {

    private static final Logger logger = LoggerFactory.getLogger(LocalStorageProvider.class);

    private BinaryDataProperties binaryDataProperties;

    private MessageSourceFacade messageSource;

    public LocalStorageProvider(BinaryDataProperties binaryDataProperties, MessageSourceFacade messageSource) {
        this.binaryDataProperties = binaryDataProperties;
        this.messageSource = messageSource;
    }

    @Override
    public void saveFile(File file) {
    }

    @Override
    public void saveFile(InputStream inputStream) {
        validateInternalPathConfiguration();

        File targetFile = new File(binaryDataProperties.getLocalStoragePath() + "/tmp.db");
        try {
            FileUtils.copyInputStreamToFile(inputStream, targetFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void validateInternalPathConfiguration() {
        Path path = Paths.get(binaryDataProperties.getLocalStoragePath());
        if (!Files.isDirectory(path)) {
            String message = messageSource.getMessage(DIRECTORY_PATH_FAILURE.getMessageCode(),
                    binaryDataProperties.getLocalStoragePath());
            logger.error(LoggingUtil.getLoggingMessage(DIRECTORY_PATH_FAILURE.getErrorCode(), message));

            throw new InternalFailureException(DIRECTORY_PATH_FAILURE.getErrorCode(),
                    messageSource.getMessage(ExceptionMessageCodes.INTERNAL_FAILURE_EXCEPTION));
        }
    }
    @Override
    public BinaryDataProviderName getBinaryDataProviderName() {
        return BinaryDataProviderName.LOCAL_STORAGE;
    }
}
