package com.hanuor.pearl.verifiers;

import java.util.ArrayList;

/**
 * Created by Shantanu Johri on 31-07-2016.
 */
public class Utils{


    public static void throwExceptionIfNullOrBlank(Object obj, String name) {
        if(obj == null) {
            throw new ExceptionGenerator(name + " parameter can not be null ");
        } else if(obj instanceof String && ((String)obj).trim().equals("")) {
            throw new ExceptionGenerator(name + " parameter can not be blank ");
        } else if(obj instanceof ArrayList && ((ArrayList)obj).size() == 0) {
            throw new ExceptionGenerator(name + " cannot be empty");
        }
    }

    public static void throwExceptionIfNull(Object obj, String name) {
        if(obj == null) {
            throw new ExceptionGenerator(name + " parameter can not be null ");
        } else if(obj instanceof ArrayList && ((ArrayList)obj).size() == 0) {
            throw new ExceptionGenerator(name + " cannot be empty");
        }
    }
    public static void throwExceptionIfKeyDNM(Object obj, String name) {
        if(obj == null) {
            throw new ExceptionGenerator(name + " parameter can not be null ");
        } else {
            throw new ExceptionGenerator(name + " keys do not match");
        }
    }

}
