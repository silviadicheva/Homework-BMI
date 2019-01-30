package com.silvia.fmi.courceproject;

import android.database.Cursor;
import android.database.SQLException;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity{
    private DbManager dbManager;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.dbManager=new DbManager(this);
        try{
            dbManager.open();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        final String name="Imam rabota";
        final String desc ="Dnes shte rabotq";

        dbManager.insert(name,desc);
        dbManager.insert(name,desc);
        dbManager.insert(name,desc);
        ArrayList<String> rr = dbManager.getDb();
        for (String res : rr){
            Log.d("r-a e ",res);
        }
        Cursor cursor = dbManager.fetch();


    }
}
