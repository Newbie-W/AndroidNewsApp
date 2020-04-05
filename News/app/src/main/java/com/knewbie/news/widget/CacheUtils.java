package com.knewbie.news.widget;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_MULTI_PROCESS;

public class CacheUtils {

    public static boolean getBoolean(Context context, String key) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("newsDatas", MODE_MULTI_PROCESS);

        return sharedPreferences.getBoolean(key, false);
    }
}
