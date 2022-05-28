package com.example.zakatcalculate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater= getMenuInflater();
        inflater.inflate(R.menu.menu,menu);

        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.weargold:
                Toast.makeText(this,"This is wear gold page",Toast.LENGTH_LONG).show();
                Intent intent=new Intent(this, WearActivity.class);
                startActivity(intent);
                break;

            case R.id.keepgold:
                Toast.makeText(this,"This is wear keep page",Toast.LENGTH_LONG).show();
                Intent intent2=new Intent(this,KeepActivity.class);
                startActivity(intent2);
                break;

            case R.id.about:
                Toast.makeText(this,"This is about page",Toast.LENGTH_LONG).show();
                Intent intent3=new Intent(this, AboutActivity.class);
                startActivity(intent3);
                break;

        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onClick(View v) {

    }
}
