package com.katafoni.kindlevocabulary.binarydata;

import java.io.File;
import java.io.InputStream;

interface BinaryDataProvider {

    void saveFile(File file);

    void saveFile(InputStream inputStream);

    BinaryDataProviderName getBinaryDataProviderName();
}
