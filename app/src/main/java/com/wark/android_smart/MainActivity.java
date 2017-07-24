package com.wark.android_smart;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;



public class MainActivity extends AppCompatActivity {
    TextView edittext;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edittext = (EditText) findViewById(R.id.edittext);
        SharedPreferences sharedPreferences = getSharedPreferences("frist", MODE_PRIVATE);
        String savedString = sharedPreferences.getString("sampleString", "");
        edittext.setText(savedString);

        //--------------Json연습
        Json json = new Json();
        json.jsonadd();


        JSONParser parser = new JSONParser();

        try {
            JSONObject object = (JSONObject) parser.parse(json.json);

            JSONObject univ = (JSONObject) object.get("univ");
            String drama = (String) univ.get("1");
            String school = (String) univ.get("2");
            Log.e("1 : ", String.valueOf(drama));
            Log.e("2 : ", String.valueOf(school));

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
        //-----------------------
        @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.red:
                edittext.setTextColor(Color.RED);
                return true;
            case R.id.blue:
                edittext.setTextColor(Color.BLUE);
                return true;
            case R.id.green:
                edittext.setTextColor(Color.GREEN);
                return true;
            case R.id.save:
                SharedPreferences sharedPreferences = getSharedPreferences("frist", MODE_PRIVATE);
                SharedPreferences.Editor sharedPreferencesEditor = sharedPreferences.edit();

                // 데이터를 기록한다.
                sharedPreferencesEditor.putString("sampleString",edittext.getText().toString());
                sharedPreferencesEditor.commit();
                Toast.makeText(this,edittext.getText()+"이(가) 저장되었습니다.",Toast.LENGTH_LONG).show();
                return true;
            case R.id.share:
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT,edittext.getText().toString());
                sendIntent.setType("text/plain");
                startActivity(sendIntent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}



