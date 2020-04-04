package com.maksymov.restoretheword2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.maksymov.restoretheword2.wordsStore.Words;

import java.util.Random;

public class GameActivity extends AppCompatActivity {

    final String LOG_TAG = "myLog";
    private Intent intent;
    private WordLoaderService wordLoaderService;
    private ServiceConnection sConn;
    boolean bound = false;


    private CountDownTimer countDownTimer = null;
    private boolean level = false;
    private long remainingTime = 999999999;
    private byte counter = 0;
    private int stage;
    private Words words;
    private TextView counterTextView;
    private TextView textView1;
    private TextView textView2;
    private TextView textView3;
    private TextView textView4;
    private TextView textView5;
    private TextView textView6;
    private TextView textView7;
    private TextView textView8;
    private TextView textView9;
    private TextView textView11;
    private TextView textView22;
    private TextView textView33;
    private TextView textView44;
    private TextView textView55;
    private TextView textView66;
    private TextView textView77;
    private TextView textView88;
    private TextView textView99;
    private TextView timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        intent = new Intent(this, WordLoaderService.class);
        if (wordLoaderService == null) return;
        level = wordLoaderService.readLevel();
        remainingTime = wordLoaderService.readTime();
//        sConn = new ServiceConnection() {
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

        textView1 = findViewById(R.id.firstR);
        textView2 = findViewById(R.id.secondR);
        textView3 = findViewById(R.id.thirdR);
        textView4 = findViewById(R.id.fourthR);
        textView5 = findViewById(R.id.fifthR);
        textView6 = findViewById(R.id.sixthR);
        textView7 = findViewById(R.id.seventhR);
        textView8 = findViewById(R.id.eighthR);
        textView9 = findViewById(R.id.ninthR);

        textView11 = findViewById(R.id.first);
        textView22 = findViewById(R.id.second);
        textView33 = findViewById(R.id.third);
        textView44 = findViewById(R.id.fourth);
        textView55 = findViewById(R.id.fifth);
        textView66 = findViewById(R.id.sixth);
        textView77 = findViewById(R.id.seventh);
        textView88 = findViewById(R.id.eighth);
        textView99 = findViewById(R.id.ninth);
        counterTextView = findViewById(R.id.counter);
        counterTextView.setText("0");
        timer = findViewById(R.id.timer);
        stage = 1;
        fillingInTheRandomLayout(stage);
        textView1.setOnClickListener((View.OnClickListener) this);
        textView2.setOnClickListener((View.OnClickListener) this);
        textView3.setOnClickListener((View.OnClickListener) this);
        textView4.setOnClickListener((View.OnClickListener) this);
        textView5.setOnClickListener((View.OnClickListener) this);
        textView6.setOnClickListener((View.OnClickListener) this);
        textView7.setOnClickListener((View.OnClickListener) this);
        textView8.setOnClickListener((View.OnClickListener) this);
        textView9.setOnClickListener((View.OnClickListener) this);
        textView11.setOnClickListener((View.OnClickListener) this);
        textView22.setOnClickListener((View.OnClickListener) this);
        textView33.setOnClickListener((View.OnClickListener) this);
        textView44.setOnClickListener((View.OnClickListener) this);
        textView55.setOnClickListener((View.OnClickListener) this);
        textView66.setOnClickListener((View.OnClickListener) this);
        textView77.setOnClickListener((View.OnClickListener) this);
        textView88.setOnClickListener((View.OnClickListener) this);
        textView99.setOnClickListener((View.OnClickListener) this);

        timer.setText((int) remainingTime);

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


    private String randomizeLetters(String word) {
        Random random = new Random();
        char a[] = word.toCharArray();
        // Scramble the letters using the standard Fisher-Yates shuffle
        for (int i = 0; i < a.length; i++) {
            int j = random.nextInt(a.length);
            // Swap letters
            char temp = a[i];
            a[i] = a[j];
            a[j] = temp;
        }
        return new String(a);
    }

    private void fillingInTheRandomLayout(int stage) {
        String timeRand = randomizeLetters(words.TIME.toString());
        String eventRand = randomizeLetters(words.EVENT.toString());
        String coronaRand = randomizeLetters(words.CORONA.toString());
        String restoreRand = randomizeLetters(words.RESTORE.toString());
        String bookmarkRand = randomizeLetters(words.BOOKMARK.toString());
        String microsoftRand = randomizeLetters(words.MICROSOFT.toString());
        if (!level) {
            level = false;
        } else if (level)
            level = true;
        if (!level && stage == 1) {
            textView1.setText(String.valueOf(timeRand.charAt(0)));
            textView2.setText(String.valueOf(timeRand.charAt(1)));
            textView3.setText(String.valueOf(timeRand.charAt(2)));
            if (timeRand.length() > 3)
                textView4.setText(String.valueOf(timeRand.charAt(3)));
            if (timeRand.length() > 4)
                textView5.setText(String.valueOf(timeRand.charAt(4)));
            if (timeRand.length() > 5)
                textView6.setText(String.valueOf(timeRand.charAt(5)));
            if (timeRand.length() > 6)
                textView7.setText(String.valueOf(timeRand.charAt(6)));
            if (timeRand.length() > 7)
                textView8.setText(String.valueOf(timeRand.charAt(7)));
            if (timeRand.length() > 8)
                textView9.setText(String.valueOf(timeRand.charAt(8)));
        } else if (!level && stage == 2) {
            textView1.setText(String.valueOf(eventRand.charAt(0)));
            textView2.setText(String.valueOf(eventRand.charAt(1)));
            textView3.setText(String.valueOf(eventRand.charAt(2)));
            textView4.setText(String.valueOf(eventRand.charAt(3)));
            if (eventRand.length() > 4)
                textView5.setText(String.valueOf(eventRand.charAt(4)));
            if (eventRand.length() > 5)
                textView6.setText(String.valueOf(eventRand.charAt(5)));
            if (eventRand.length() > 6)
                textView7.setText(String.valueOf(eventRand.charAt(6)));
            if (eventRand.length() > 7)
                textView8.setText(String.valueOf(eventRand.charAt(7)));
            if (eventRand.length() > 8)
                textView9.setText(String.valueOf(eventRand.charAt(8)));
        } else if (!level && stage == 3) {
            textView1.setText(String.valueOf(coronaRand.charAt(0)));
            textView2.setText(String.valueOf(coronaRand.charAt(1)));
            textView3.setText(String.valueOf(coronaRand.charAt(2)));
            textView4.setText(String.valueOf(coronaRand.charAt(3)));
            textView5.setText(String.valueOf(coronaRand.charAt(4)));
            if (coronaRand.length() > 5)
                textView6.setText(String.valueOf(coronaRand.charAt(5)));
            if (coronaRand.length() > 6)
                textView7.setText(String.valueOf(coronaRand.charAt(6)));
            if (coronaRand.length() > 7)
                textView8.setText(String.valueOf(coronaRand.charAt(7)));
            if (coronaRand.length() > 8)
                textView9.setText(String.valueOf(coronaRand.charAt(8)));
        } else if (level && stage == 1) {
            textView1.setText(String.valueOf(restoreRand.charAt(0)));
            textView2.setText(String.valueOf(restoreRand.charAt(1)));
            textView3.setText(String.valueOf(restoreRand.charAt(2)));
            textView4.setText(String.valueOf(restoreRand.charAt(3)));
            textView5.setText(String.valueOf(restoreRand.charAt(4)));
            textView6.setText(String.valueOf(restoreRand.charAt(5)));
            if (restoreRand.length() > 6)
                textView7.setText(String.valueOf(restoreRand.charAt(6)));
            if (restoreRand.length() > 7)
                textView8.setText(String.valueOf(restoreRand.charAt(7)));
            if (restoreRand.length() > 8)
                textView9.setText(String.valueOf(restoreRand.charAt(8)));
        } else if (level && stage == 2) {
            textView1.setText(String.valueOf(bookmarkRand.charAt(0)));
            textView2.setText(String.valueOf(bookmarkRand.charAt(1)));
            textView3.setText(String.valueOf(bookmarkRand.charAt(2)));
            textView4.setText(String.valueOf(bookmarkRand.charAt(3)));
            textView5.setText(String.valueOf(bookmarkRand.charAt(4)));
            textView6.setText(String.valueOf(bookmarkRand.charAt(5)));
            if (bookmarkRand.length() > 6)
                textView7.setText(String.valueOf(bookmarkRand.charAt(6)));
            if (bookmarkRand.length() > 7)
                textView8.setText(String.valueOf(bookmarkRand.charAt(7)));
            if (bookmarkRand.length() > 8)
                textView9.setText(String.valueOf(bookmarkRand.charAt(8)));
        } else if (level && stage == 3) {
            textView1.setText(String.valueOf(microsoftRand.charAt(0)));
            textView2.setText(String.valueOf(microsoftRand.charAt(1)));
            textView3.setText(String.valueOf(microsoftRand.charAt(2)));
            textView4.setText(String.valueOf(microsoftRand.charAt(3)));
            textView5.setText(String.valueOf(microsoftRand.charAt(4)));
            textView6.setText(String.valueOf(microsoftRand.charAt(5)));
            if (microsoftRand.length() > 6)
                textView7.setText(String.valueOf(microsoftRand.charAt(6)));
            if (microsoftRand.length() > 7)
                textView8.setText(String.valueOf(microsoftRand.charAt(7)));
            if (microsoftRand.length() > 8)
                textView9.setText(String.valueOf(microsoftRand.charAt(8)));
        }
    }
}
