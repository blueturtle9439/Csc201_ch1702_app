package com.example.eunhan.csc201_ch1702_app;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText filenameinput;
    Button savebtn;
    Button readbtn;
    TextView result;

    final static String foldername = Environment.getExternalStorageDirectory().getAbsolutePath() + "/savedfiles";
    String filename;

    String txt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        filenameinput = (EditText) findViewById(R.id.filename);
        savebtn = (Button) findViewById(R.id.savebtn);
        readbtn = (Button) findViewById(R.id.readbtn);
        result = (TextView) findViewById(R.id.result);
        savebtn.setOnClickListener(this);
        readbtn.setOnClickListener(this);

        filename = filenameinput.getText().toString();

        txt = "";
        for (int i = 0; i < 100; i++) {
            txt += ((int) (1 + Math.random() * 100)) + ",";
        }


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.savebtn:
                try {
                    System.out.println(foldername);
                    System.out.println(filename);
                    System.out.println(txt);
                    WriteTextFile(foldername,filename,txt);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.readbtn:
                try {
                    ReadTextFile(foldername, filename);
                } catch (IOException e) {
                    Toast.makeText(MainActivity.this, "please check file name!", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }

    }

    public void WriteTextFile(String foldername, String filename, String txt) throws IOException {
        String path = foldername + "/" + filename;

        File dir = new File(foldername);
        if (!dir.exists()) {
            dir.mkdir();
        }


        FileOutputStream fos = new FileOutputStream(path, false);
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fos));
        writer.write(txt);


        writer.close();
        fos.close();

        Toast.makeText(MainActivity.this, "file created!", Toast.LENGTH_SHORT).show();
    }
    public void ReadTextFile(String foldername, String filename) throws IOException {
        String path = foldername + "/" + filename;

        StringBuffer strBuffer = new StringBuffer();

            InputStream is = new FileInputStream(path);
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String line="";
            while((line=reader.readLine())!=null){
                strBuffer.append(line+"\n");
            }

            reader.close();
            is.close();

        result.setText(strBuffer.toString());

    }



}
