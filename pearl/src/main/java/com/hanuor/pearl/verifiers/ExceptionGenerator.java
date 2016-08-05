package com.hanuor.pearl.verifiers;

/**
 * Created by Shantanu Johri on 31-07-2016.
 */
public class ExceptionGenerator extends RuntimeException{
    private int httpErrorCode;
    private int appErrorCode;
    public ExceptionGenerator() {
    }


    public ExceptionGenerator(String detailMessage) {
        super(detailMessage);
    }

    public ExceptionGenerator(Throwable throwable) {
        super(throwable);
    }

    public ExceptionGenerator(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    public ExceptionGenerator(String detailMessage, int httpErrorCode, int appErrorCode) {
        super(detailMessage);
        this.httpErrorCode = httpErrorCode;
        this.appErrorCode = appErrorCode;
    }

    public ExceptionGenerator(int httpErrorCode, int appErrorCode) {
        this.httpErrorCode = httpErrorCode;
        this.appErrorCode = appErrorCode;
    }

    public void setHttpErrorCode(int httpErrorCode) {
        this.httpErrorCode = httpErrorCode;
    }

    public int getHttpErrorCode() {
        return this.httpErrorCode;
    }

    public void setAppErrorCode(int appErrorCode) {
        this.appErrorCode = appErrorCode;
    }

    public int getAppErrorCode() {
        return this.appErrorCode;
    }

}
