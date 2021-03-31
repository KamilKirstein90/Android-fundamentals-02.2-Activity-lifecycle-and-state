package com.example.androidfundamentals022_activity_lifecycle_and_state;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    final static int  REQ_SECOND_ACT = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // in this overridden function we get the results from intents that we started with startActivityForResult
    // the request code is the key for the activity we started and want to get results from
    // the result code ist to check if the intent has results was successful or canceled
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // check if result code  was ok
        // check for the right request code
        // check if data is not null
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        // to save the items when the phones rotation is changed
    }

    public void addItemFromSecondAct(View view) {

        Intent intent = new Intent(this, SecondActivity.class);
        startActivityForResult(intent, REQ_SECOND_ACT);
    }
}