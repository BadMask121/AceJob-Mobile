package com.acejob.acejob;

import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class createaccount extends rootActivity {

    TextView textViewIntro;
    Button postjob,postresume;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.createacccount);
        postjob = (Button)findViewById(R.id.postjob);
        postresume = (Button)findViewById(R.id.postresume);
        textViewIntro = (TextView)findViewById(R.id.textViewIntro);
    setEditTextFont(textViewIntro,"font/opensans_regular.ttf");
    }

    @Override
    protected void onStart() {
        super.onStart();
postjob.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent i = new Intent(getApplicationContext(), postjob.class);
        startActivity(i);
    }
});
    }



    public void setEditTextFont(TextView view, String path){
        try {
            AssetManager assetM = this.getResources().getAssets();
            Typeface typeface = Typeface.createFromAsset(assetM,path);
            view.setTypeface(typeface);

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
