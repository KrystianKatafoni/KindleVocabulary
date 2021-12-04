package com.katafoni.kindlevocabulary.binarydata;

enum BinaryDataErrorCodes {

    DIRECTORY_PATH_FAILURE("binarydata.directoryPathFailure","BDA001");

    private final String messageCode;
    private final String errorCode;

    BinaryDataErrorCodes(String messageCode, String errorCode) {
        this.messageCode = messageCode;
        this.errorCode = errorCode;
    }

    public String getMessageCode() {
        return messageCode;
    }

    public String getErrorCode() {
        return errorCode;
    }
}
