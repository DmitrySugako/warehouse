package com.sugako.exception.response;

public enum ApplicationErrorCodes {

    SQL_ERROR(11),
    BAD_REQUEST(20),
    ENTITY_NOT_FOUND(30),
    INVENTORY_ERROR(10),
    FATAL_ERROR(666);


    public int getCodeId() {
        return codeId;
    }

    private int codeId;

    ApplicationErrorCodes(int codeId) {
        this.codeId = codeId;
    }
}
