package com.ms_square.debugoverlay.sample;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.widget.Button;
import timber.log.Timber;

public class MainActivity extends AppCompatActivity {
private  int count=0;
Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
button=findViewById(R.id.button);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Timber.d("fab clicked");
                startActivity(new Intent(MainActivity.this, ScrollingActivity.class));
            }
        });

        Timber.d("onCreate() called");
    }

    @Override
    public void onResume() {
        super.onResume();
        Timber.d("onResume() called");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void test(View view) {
        count++;
        button.setText(Integer.toString(count));
        Log.d("lzqtest","MainActivity.java.test: 64 count="+count);
        Log.i("lzqtest","MainActivity.java.test: 64 count="+count);
        Log.w("lzqtest","MainActivity.java.test: 64 count="+count);
        Log.e("lzqtest","MainActivity.java.test: 64 count="+count);

        Log.d("d","MainActivity.java.test: 64 count="+count);
        Log.i("s","MainActivity.java.test: 64 count="+count);
        Log.w("b","MainActivity.java.test: 64 count="+count);
        Log.e("w","MainActivity.java.test: 64 count="+count);

    }
}
