package com.acejob.acejob;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by User on 24/11/2017.
 */

public class Font extends AppCompatActivity{

    AssetManager assetM;
    Typeface typeface;
    Context context;
    public Font(Context ctx,AssetManager asset){
        this.context = ctx;
        this.assetM=asset;
    }

    public void setEditTextFont(EditText view, String path){
        try {

          assetM = context.getResources().getAssets();
          typeface= Typeface.createFromAsset(assetM,path);
            view.setTypeface(typeface);

        }catch (Exception e){
            e.printStackTrace();
        }
    }


    //setTextViewFont
    public void setTextViewFont(TextView view, String path){
        try {
            assetM= context.getResources().getAssets();
            typeface= Typeface.createFromAsset(assetM,path);
            view.setTypeface(typeface);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
