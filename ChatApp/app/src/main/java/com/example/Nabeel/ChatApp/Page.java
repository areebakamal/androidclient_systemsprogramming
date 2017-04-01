package com.example.Nabeel.ChatApp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Group 15 on 3/21/2017
 *
 */


/**
 * page maps to firstpage.xml
 */
public class Page extends Activity implements View.OnClickListener {


    EditText ef1,ef2, ef3;
    Button bf1;
    Intent I;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.firstpage);
        ef1=(EditText)findViewById(R.id.ef1);
        ef2=(EditText)findViewById(R.id.ef2);
        ef3=(EditText)findViewById(R.id.ef3);
        bf1=(Button)findViewById(R.id.bf1);
        bf1.setOnClickListener(this);
    }


    /**
     * Called upon hitting Connect button
     */
    @Override
    public void onClick(View v) {


        I=new Intent("com.example.Nabeel.ChatApp.MAINACTIVITY");
        I.putExtra("ip",ef1.getText().toString());
        I.putExtra("port",ef2.getText().toString());
        I.putExtra("name",ef3.getText().toString());
        startActivity(I);

    }
}
