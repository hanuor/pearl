package com.hanuor.sapphire.utils;
/*
 * Copyright (C) 2016 Hanuor Inc. by Shantanu Johri(https://hanuor.github.io/shanjohri/)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

public class SapphireException extends RuntimeException{
    private int httpErrorCode;
    private int appErrorCode;

    public SapphireException() {
    }

    public SapphireException(String detailMessage) {
        super(detailMessage);
    }

    public SapphireException(Throwable throwable) {
        super(throwable);
    }

    public SapphireException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    public SapphireException(String detailMessage, int httpErrorCode, int appErrorCode) {
        super(detailMessage);
        this.httpErrorCode = httpErrorCode;
        this.appErrorCode = appErrorCode;
    }

    public SapphireException(int httpErrorCode, int appErrorCode) {
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
