package com.tommywebdesigns.myweekendhomework;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.tommywebdesigns.myweekendhomework.databinding.ActivitySecond2Binding;

import static com.tommywebdesigns.myweekendhomework.util.Constants.DATA_KEY;

public class SecondActivity extends AppCompatActivity {

    private ActivitySecond2Binding binding;
    private SharedPreferences sP;
    private String secondData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivitySecond2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //It loads Carlton's photo
        Glide.with(this)
                .load(R.drawable.carton)
                .into(binding.mainImage);
        sP=getSharedPreferences(getPackageName(), Context.MODE_PRIVATE);
        readFromSharedPref();

        binding.secondSentBt.setOnClickListener(v -> {
            Intent intent= new Intent(this, MainActivity.class);

            String input= binding.secondEt.getText().toString().trim();
            intent.putExtra("message_key", input);

            startActivity(intent);
            if(input.length() == 0)
                Toast.makeText(this, "Text cannot be empty", Toast.LENGTH_LONG).show();
            else{
                if(secondData.equals("empty"))
                    secondData= "";

                sP.edit()
                        .putString(DATA_KEY, secondData+"\n"+"Carlton: "+input+"\n")
                        .apply();

            }
        });
        binding.clearBt.setOnClickListener(v -> {
            clearTextView();
        });

    }// onCreate


    private void readFromSharedPref() {
        secondData= sP.getString(DATA_KEY, "empty" );
        binding.secondTv.setText(secondData);
    }

    private void clearTextView(){
        SharedPreferences.Editor editor=sP.edit();
        editor.clear();
        editor.commit();
        binding.secondTv.setText("");
    }
}