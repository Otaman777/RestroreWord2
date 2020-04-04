package com.maksymov.restoretheword2;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

public class WordLoaderService extends Service {
    final String LOG_TAG = "myLog";
    private int time;
    private String level;
    private final String LEVEL = "LEVEL";
    private final String TIME = "TIME";
    private SharedPreferences sPref;
    private CustomBinder binder = new CustomBinder();

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(LOG_TAG, "WordLoaderService has been created!!!");
        sPref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
    }

    public int readTime() {
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
//        SharedPreferences.Editor ed = sPref.edit();
//        ed.putString(LEVEL, level);
//        ed.putInt(TIME, time);

        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt(TIME, time);
        editor.putString(LEVEL, level);

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
