package com.example.tops.filesystemdemo;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
TextView textView;
    EditText  editText;
    Button button1,button2,button3;
    File file;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView =(TextView)findViewById(R.id.textid1);
        editText=(EditText)findViewById(R.id.edttext1);
        button1=(Button)findViewById(R.id.create);
        button2=(Button)findViewById(R.id.save);
        button3=(Button)findViewById(R.id.show);

        file= Environment.getExternalStorageDirectory();
        file=new File(file.getAbsolutePath()+"/ravi1212");
        file.mkdir();
        file=new File(file.getAbsolutePath()+"/data.txt");

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            if(file.exists()){
                Toast.makeText(MainActivity.this, "file allrady exixst", Toast.LENGTH_SHORT).show();
            }else {
                try{
                    file.createNewFile();
                    Toast.makeText(MainActivity.this, "file is created", Toast.LENGTH_SHORT).show();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    FileOutputStream fileOutputStream=new FileOutputStream(file);
                    fileOutputStream.write(editText.getText().toString().getBytes());
                    fileOutputStream.flush();
                    fileOutputStream.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    FileInputStream  fileInputStream=new FileInputStream(file);
                    String s="";
                            int i;
                    while ((i=fileInputStream.read())!=-1){
                        s+=(char)i;
                    }
                    textView.setText(s);
                    fileInputStream.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

    }
}
