package com.hanuor.sapphire.utils;/*
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

public class InformationHandler {
    private final String KEYID;
    private final String KEYSECRET;
    private final String VALIDATOR;

    public InformationHandler(String keyid, String keysecret, String validator) {
        this.KEYID = keyid;
        this.KEYSECRET = keysecret;
        this.VALIDATOR = validator;
    }
    public String getKEYID(){
        return KEYID;
    }
    public String getKEYSECRET(){
        return KEYSECRET;
    }
    public String getVALIDATOR(){
        return VALIDATOR;
    }
}
