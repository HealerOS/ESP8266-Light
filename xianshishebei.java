package com.example.zhi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class xianshishebei extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xianshishebei);
        ImageButton imageButton1=(ImageButton) findViewById(R.id.close);
        imageButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        ImageButton imageButton2=(ImageButton) findViewById(R.id.add);
        imageButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(xianshishebei.this,addDevice.class);
                startActivity(intent);
            }
        });
        Button button=(Button)findViewById(R.id.button5);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(xianshishebei.this,shebei1.class);
                startActivity(intent);
            }
        });
    }
}