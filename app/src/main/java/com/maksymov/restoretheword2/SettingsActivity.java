package com.maksymov.restoretheword2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
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

    private final String LEVEL = "LEVEL";
    private final String TIME = "TIME";

    final String LOG_TAG = "myLog";
    private SharedPreferences sharedPreferences;
    private String level;
    private int time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

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
    public void onBackPressed() {
        super.onBackPressed();
        //Intent intent = new Intent(SettingsActivity.this, MainActivity.class);
        //startActivity(intent);
    }

    public void onClickSaveSettings(View view) {
        Intent intent = new Intent(SettingsActivity.this, MainActivity.class);
        sharedPreferences = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(TIME, time);
        editor.putString(LEVEL, level);
        editor.apply();
        startActivity(intent);

    }
}
