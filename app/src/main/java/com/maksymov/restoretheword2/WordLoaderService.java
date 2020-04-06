package com.maksymov.restoretheword2;

import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

public class WordLoaderService extends Service {
    final String LOG_TAG = "myLog";
    private final String LEVEL = "LEVEL";
    private final String TIME = "TIME";

    private final String TIME_WORD = "time";
    private final String EVENT_WORD = "event";
    private final String CORONA_WORD = "corona";
    private final String RESTORE_WORD = "restore";
    private final String BOOKMARK_WORD = "bookmark";
    private final String MICROSOFT_WORD = "microsoft";

    private SharedPreferences sPref;
    private CustomBinder binder = new CustomBinder();

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(LOG_TAG, "WordLoaderService has been created!!!");
        sPref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
        fillInWords();
    }

    public void fillInWords() {
        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(TIME_WORD, "TIME");
        editor.putString(EVENT_WORD, "EVENT");
        editor.putString(CORONA_WORD, "CORONA");
        editor.putString(RESTORE_WORD, "RESTORE");
        editor.putString(BOOKMARK_WORD, "BOOKMARK");
        editor.putString(MICROSOFT_WORD, "MICROSOFT");
        editor.apply();
    }

    public String readWordTime() {
        return sPref.getString(TIME_WORD, "");
    }

    public String readWordEvent() {
        return sPref.getString(EVENT_WORD, "");
    }

    public String readWordCorona() {
        return sPref.getString(CORONA_WORD, "");
    }

    public String readWordRestore() {
        return sPref.getString(RESTORE_WORD, "");
    }

    public String readWordBookmark() {
        return sPref.getString(BOOKMARK_WORD, "");
    }

    public String readWordMicrosoft() {
        return sPref.getString(MICROSOFT_WORD, "");
    }

    public int readTime() {
        Log.d("AAA", String.valueOf(sPref.getInt(TIME, 999999999)));
        return sPref.getInt(TIME, 999999999);
    }

    public boolean readLevel() {
        if (sPref.getString(LEVEL, "short").equals("short")) {
            return false;
        } else if (sPref.getString(LEVEL, "short").equals("veteran"))
            return true;
        return false;
    }

    public void writeData(String level, int time) {
        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt(TIME, time);
        editor.putString(LEVEL, level);
        editor.apply();
        Log.d("AAA", String.valueOf(sPref.getInt(TIME, 999999999)));
        Log.d("AAAB", String.valueOf(time));

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(LOG_TAG, "onBind");
        return binder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(LOG_TAG, "onUnbind");
        return super.onUnbind(intent);
    }


    public class CustomBinder extends Binder {
        public WordLoaderService getService() {
            return WordLoaderService.this;
        }
    }

}
