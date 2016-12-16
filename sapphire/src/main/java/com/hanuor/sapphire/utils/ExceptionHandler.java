package com.hanuor.sapphire.utils;

import android.util.Log;

public class ExceptionHandler {
    public static void writeMessage(String message) {
        Log.d("SapphireApi-Message", message);
    }

    public static void writeError(String error) {
        Log.e("SapphireApi-Error", error);
    }

}
