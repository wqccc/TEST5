package com.example.bupt.ex18;

import android.content.SharedPreferences;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Output;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private final static String MyFileName="myfile";

    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnWrite = (Button) findViewById(R.id.button_Write);
        Button btnRead = (Button) findViewById(R.id.button_Read);

        btnWrite.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                OutputStream out = null;
                try{
                    FileOutputStream fileOutputStream = openFileOutput(MyFileName,MODE_PRIVATE);
                    out = new BufferedOutputStream(fileOutputStream);
                    String content = "2015011360";
                    try{
                        out.write(content.getBytes(StandardCharsets.UTF_8));
                    }
                    finally {
                        if(out != null)
                            out.close();
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

        btnRead.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                InputStream in = null;
                try{
                    FileInputStream fileInputStream = openFileInput(MyFileName);
                    in = new BufferedInputStream(fileInputStream);

                    int c;
                    StringBuilder stringBuilder = new StringBuilder("");
                    try{
                        while((c = in.read()) != -1){
                            stringBuilder.append((char)c);
                        }
                        Toast.makeText(MainActivity.this,"姓名：王晴川 " + " 学号：" + stringBuilder.toString(),Toast.LENGTH_LONG).show();
                    }
                    finally {
                        if(in != null)
                            in.close();
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }
}

