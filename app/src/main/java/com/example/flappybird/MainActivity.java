package com.example.flappybird;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AppConstants.initialization(this.getApplicationContext());
    }

    public void startGame(View view) {
        //Log.i("ImageButton","clicked");
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
        finish();
    }
}
