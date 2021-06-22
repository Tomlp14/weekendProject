package com.tommywebdesigns.myweekendhomework;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.tommywebdesigns.myweekendhomework.databinding.ActivityMainBinding;

import static com.tommywebdesigns.myweekendhomework.util.Constants.DATA_KEY;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding activityMainBinding;

    private String mainData;
    private SharedPreferences sharedPreferences;
    private String data;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding= ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(activityMainBinding.getRoot());

        //It loads Will Smith's photo
        Glide.with(this)
                .load(R.drawable.will)
                .into(activityMainBinding.mainImage);

        sharedPreferences=getSharedPreferences(getPackageName(), Context.MODE_PRIVATE);
        readFromSharedPref();

    /*    Intent intent= getIntent();
        String msg= intent.getStringExtra("message_key");
        activityMainBinding.mainTv.setText(msg); */

        activityMainBinding.mainSentBt.setOnClickListener(v -> {
            Intent intent= new Intent(this, SecondActivity.class);

            startActivity(intent);

            String input= activityMainBinding.mainEt.getText().toString().trim();

            if(input.length() == 0)
                Toast.makeText(this, "Text cannot be empty", Toast.LENGTH_LONG).show();
            else{
                sharedPreferences.edit()
                        .putString(DATA_KEY, mainData+"\n"+"Will: "+input+"\n")
                        .apply();
            }
        });

        activityMainBinding.clearBt.setOnClickListener(v -> {
            clearTextView();
        });


    }
    private void readFromSharedPref() {
        mainData= sharedPreferences.getString(DATA_KEY, "empty" );
        activityMainBinding.mainTv.setText(mainData);
    }
    private void clearTextView(){
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.clear();
        editor.commit();
        activityMainBinding.mainTv.setText("");
    }
}