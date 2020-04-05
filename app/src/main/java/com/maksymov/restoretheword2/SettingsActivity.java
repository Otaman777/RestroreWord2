package com.maksymov.restoretheword2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class SettingsActivity extends AppCompatActivity {

    private String[] levels = {"Beginner (4 or 5 or 6 word length)", "Veteran (7 or 8 or 9 word length)"};
    private String[] times = {"1 min", "2 min", "3 min"};

    final String LOG_TAG = "myLog";
    private Intent intent;
    private WordLoaderService wordLoaderService;
    private ServiceConnection sConn;
    boolean bound = false;
    private String level;
    private int time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
//        intent = new Intent(this, WordLoaderService.class);
//        sConn = new ServiceConnection() {
//
//            public void onServiceConnected(ComponentName name, IBinder binder) {
//                Log.d(LOG_TAG, "Settings onServiceConnected");
//                wordLoaderService = ((WordLoaderService.CustomBinder) binder).getService();
//                bound = true;
//            }
//
//            public void onServiceDisconnected(ComponentName name) {
//                Log.d(LOG_TAG, "Settings onServiceDisconnected");
//                bound = false;
//            }
//        };

        // адаптер
        ArrayAdapter<String> adapterLevel = new ArrayAdapter<String>(this, R.layout.spinner_item, levels);
        adapterLevel.setDropDownViewResource(R.layout.spinner_item);
        Spinner spinnerLevel = findViewById(R.id.spinnerLevel);
        spinnerLevel.setAdapter(adapterLevel);
        // выделяем элемент
        spinnerLevel.setSelection(0);
        // устанавливаем обработчик нажатия
        spinnerLevel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0)
                    level = "short";
                else if (position == 1)
                    level = "veteran";
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                //nothing
            }
        });

        // адаптер
        ArrayAdapter<String> adapterTime = new ArrayAdapter<String>(this, R.layout.spinner_item, times);
        adapterTime.setDropDownViewResource(R.layout.spinner_item);
        Spinner spinnerTime = findViewById(R.id.spinnerTime);
        spinnerTime.setAdapter(adapterTime);
        // выделяем элемент
        spinnerTime.setSelection(0);
        // устанавливаем обработчик нажатия
        spinnerTime.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0)
                    time = 60000;
                else if (position == 1)
                    time = 120000;
                else if (position == 2)
                    time = 180000;
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                //nothing
            }
        });
    }
    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = new Intent(this, WordLoaderService.class);
        bindService(intent, serviceConnection, BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unbindService(serviceConnection);
    }

    private ServiceConnection serviceConnection =
            new ServiceConnection() {
                @Override
                public void onServiceConnected(ComponentName name, IBinder binder) {
                    wordLoaderService = ((WordLoaderService.CustomBinder) binder).getService();
                }

                @Override
                public void onServiceDisconnected(ComponentName name) {
                    wordLoaderService = null;
                }
            };

    public void onClickSaveSettings(View view) {
        Intent intent = new Intent(SettingsActivity.this, MainActivity.class);
        startActivity(intent);
        if (wordLoaderService == null) return;
        wordLoaderService.writeData(level, time);

    }
}
