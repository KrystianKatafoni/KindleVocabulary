package com.katafoni.kindlevocabulary.properties;

import com.katafoni.kindlevocabulary.binarydata.BinaryDataProviderName;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "binary")

public class BinaryDataProperties {
    private BinaryDataProviderName defaultStorage;
    private String localStoragePath;
}
