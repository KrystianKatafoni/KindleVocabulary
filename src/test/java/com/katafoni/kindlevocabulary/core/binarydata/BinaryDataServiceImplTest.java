package com.katafoni.kindlevocabulary.core.binarydata;

import com.katafoni.kindlevocabulary.util.MessageSourceFacade;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;

import java.io.IOException;
import java.io.InputStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BinaryDataServiceImplTest {

    private static final String EXPECTED_FILE_NAME = "tmp12122021_1212111.db";

    private MockMultipartFile file = new MockMultipartFile("file", "mockFilename.db", "text/plain", "MockMultipartFile".getBytes());

    @InjectMocks
    private BinaryDataServiceImpl binaryDataServiceImpl;

    @Mock
    private BinaryDataProviderFactory binaryDataProviderFactory;

    @Mock
    private LocalStorageProvider localStorageProvider;

    @Mock
    private MessageSourceFacade messageSource;

    @Captor
    ArgumentCaptor<InputStream> inputStreamCaptor;

    @Test
    void whenSaveFileSuccess_thenReturnFileName() throws IOException {

        //given
        when(this.binaryDataProviderFactory.findBinaryDataProvider(eq(BinaryDataProviderName.LOCAL_STORAGE))).thenReturn(this.localStorageProvider);
        when(this.localStorageProvider.saveFile(any())).thenReturn(EXPECTED_FILE_NAME);

        //when
        String fileName = this.binaryDataServiceImpl.saveFileAsDatabaseStorage(file);

        //then
        verify(this.binaryDataProviderFactory, times(1)).findBinaryDataProvider(eq(BinaryDataProviderName.LOCAL_STORAGE));
        verify(this.localStorageProvider, times(1)).saveFile(this.inputStreamCaptor.capture());
        assertThat(this.file.getInputStream()).hasSameContentAs(this.inputStreamCaptor.getValue());
        assertEquals(EXPECTED_FILE_NAME, fileName);
    }

    @Test
    void deleteFileSavedAsDatabaseStorage() {
    }
}