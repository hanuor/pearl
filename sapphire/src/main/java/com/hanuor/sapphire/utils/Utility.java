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
import java.util.ArrayList;
public class Utility {
    public static void throwExceptionIfNullOrBlank(Object obj, String name) {
        if(obj == null) {
            throw new SapphireException(name + " parameter can not be null ");
        } else if(obj instanceof String && ((String)obj).trim().equals("")) {
            throw new SapphireException(name + " parameter can not be blank ");
        } else if(obj instanceof ArrayList && ((ArrayList)obj).size() == 0) {
            throw new SapphireException(name + " cannot be empty");
        }
    }
    public static void throwExceptionIfNull(Object obj, String name) {
        if(obj == null) {
            throw new SapphireException(name + " parameter can not be null ");
        } else if(obj instanceof ArrayList && ((ArrayList)obj).size() == 0) {
            throw new SapphireException(name + " cannot be empty");
        }
    }
    public static void throwRuntimeException(){
        throw new SapphireException("Have you initialized Sapphire SDK with the keys properly?");
    }
    public static void throwRuntimeException(String message){
        if(message!=null){
            throw new SapphireException(message);
        }
    }
    public static void throwException(Object obj, String message){
        throw new SapphireException(obj + "" + message);
    }
}
