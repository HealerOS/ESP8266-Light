package com.example.zhi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Toast;

import java.net.*;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;




public class shebei1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shebei1);
        Button btn1=(Button)findViewById(R.id.open1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        URL url = null;
                        HttpURLConnection connection = null;
                        try {

                            url = new URL("http://192.168.1.199:80/update?button=0");
                            connection = null;
                            try {
                                connection = (HttpURLConnection) url.openConnection();
                                System.out.println("设置链接成功");
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                            try {
                                connection.setRequestMethod("GET");
                                System.out.println("方法设置成功");
                            } catch (ProtocolException e) {
                                e.printStackTrace();
                            }
                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        }


                        try {
                            System.out.println("连接中");
                            connection.connect();
                            System.out.println("链接成功");
                            int responseCode = connection.getResponseCode();
                            System.out.println(responseCode);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }


                }).start();// Intent intent=new Intent(shebei1.this,ClientOpen.class);
                //Intent intent=new Intent(shebei1.this,Hello.class);
                //startActivity(intent);
            }
        });
        Button btn2=(Button)findViewById(R.id.close1);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent=new Intent(shebei1.this,ClientClose.class);
                //startActivity(intent);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        URL url = null;
                        HttpURLConnection connection = null;
                        try {

                            url = new URL("http://192.168.1.199:80/update?button=1");
                            connection = null;
                            try {
                                connection = (HttpURLConnection) url.openConnection();
                                System.out.println("设置链接成功");
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                            try {
                                connection.setRequestMethod("GET");
                                System.out.println("方法设置成功");
                            } catch (ProtocolException e) {
                                e.printStackTrace();
                            }
                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        }


                        try {
                            System.out.println("连接中");
                            connection.connect();
                            System.out.println("链接成功");
                            int responseCode = connection.getResponseCode();
                            System.out.println(responseCode);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }


                }).start();// Intent intent=new Intent(shebei1.this,ClientOpen.class);
                //Intent intent=new Intent(shebei1.this,Hello.class);
                //startActivity(intent);
            }

        });
        SeekBar seekBar=(SeekBar)findViewById(R.id.seekbar);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Toast.makeText(shebei1.this,"亮度级别"+progress,Toast.LENGTH_SHORT).show();
                new Thread(new Runnable() {
               @Override
               public void run() {
                   try {
                       URL url = new URL("http://192.168.1.199:80/setLED?pwm"+"="+progress);
                       HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                       System.out.println("设置链接成功");
                       connection.setRequestMethod("GET");
                       System.out.println("方法设置成功");
                       System.out.println("连接中");
                       connection.connect();
                       System.out.println("链接成功");
                       int responseCode = connection.getResponseCode();
                       System.out.println(responseCode);
                   }catch (IOException e) {
                       e.printStackTrace();
                   }
               }
           }).start();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                Toast.makeText(shebei1.this,"开始改变",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Toast.makeText(shebei1.this,"停止改变",Toast.LENGTH_SHORT).show();
            }
        });
        Button bt3=(Button)findViewById(R.id.huxideng);
        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        URL url = null;
                        HttpURLConnection connection = null;
                        try {

                            url = new URL("http://192.168.1.199:80/breath?breath=1");
                            connection = null;
                            try {
                                connection = (HttpURLConnection) url.openConnection();
                                System.out.println("设置链接成功");
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                            try {
                                connection.setRequestMethod("GET");
                                System.out.println("方法设置成功");
                            } catch (ProtocolException e) {
                                e.printStackTrace();
                            }
                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        }


                        try {
                            System.out.println("连接中");
                            connection.connect();
                            System.out.println("链接成功");
                            int responseCode = connection.getResponseCode();
                            System.out.println(responseCode);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }


                }).start();
            }
        });
    }
    public static void closeStrictMode(){
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectAll().penaltyLog().build());
    }
}