package com.example.zhi;

import java.io.IOException;
import java.net.*;
public class ClientClose {


    public static void main(String[] args) {
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
}