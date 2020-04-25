package com.claramaria.golfscorecard;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSharedPreferences = getSharedPreferences(PREFS_FILE, Context.MODE_PRIVATE);
        mEditor = mSharedPreferences.edit();

        int strokes = 0;
        for (int i = 0; i < mHoles.length; i++) {
            strokes = mSharedPreferences.getInt(KEY_STROKE_COUNT + i, 0);
            mHoles[i] = new Hole("Hole" + (i + 1) + " :", strokes);
        }

        mListAdapter = new ListAdapter(this, mHoles);
        setListAdapter(mListAdapter);

    }

    @Override
    protected void onPause() {
        super.onPause();

        for(int i = 0; i < mHoles.length; i++){
            mEditor.putInt(KEY_STROKE_COUNT + i, mHoles[i].getmStrokeCount()); //18 different keys for our sharedPreferences
        }
        mEditor.apply();
    }
}
