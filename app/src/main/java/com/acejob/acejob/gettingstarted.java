package com.acejob.acejob;

import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.os.Bundle;
import android.transition.TransitionManager;
import android.util.ArraySet;
import android.util.Log;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Spinner;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

/**
 * Created by User on 25/11/2017.
 */

public class gettingstarted extends rootActivity {


EditText jobtitle;
AutoCompleteTextView companylocation;
Spinner companycountry;

String[] countries;
String[] locations;

ArrayAdapter companyCountryAdapter;
    ArrayAdapter locationAdapter;

    FileInputStream input;
FileOutputStream outputStream;
Font font;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gettingstarted);
jobtitle = (EditText)findViewById(R.id.jobtitle);
companylocation = (AutoCompleteTextView)findViewById(R.id.companylocation);
companycountry = (Spinner)findViewById(R.id.companycountry);

font = new Font(this,getAssets());


font.setEditTextFont(jobtitle,"font/droidsans.ttf");
        font.setEditTextFont(companylocation,"font/roboto_light.ttf");

//openAssetFile("countries.txt");

        Locale[] locale = Locale.getAvailableLocales();
        ArrayList<String> countries = new ArrayList<String>();
        String country;
        for( Locale loc : locale ){
            country = loc.getDisplayCountry();
            if( country.length() > 0 && !countries.contains(country) ){
                countries.add( country );
            }
        }
        Collections.sort(countries, String.CASE_INSENSITIVE_ORDER);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.spinner_dropdown_item, countries);
        companycountry.setAdapter(adapter);

    }




    public void openAssetFile(String filename){

        BufferedReader readfile = null;
        File file = null;
        InputStreamReader reader = null;

        //Read a file and input into spinner
        try {

            AssetManager asset = this.getResources().getAssets();
            String[] list = asset.list("");

            for (String path : list){
                Log.d("debug",path);

                if(path.equals(filename)){
                    Log.d("debug",path +" Found");
                    try {
                        Log.d("debug",path +" Reading..");
                        reader = new InputStreamReader(asset.open(path));
                        readfile = new BufferedReader(reader);


                        String read = null;


                        while((read = readfile.readLine()) != null){

                            String[] d = new String[]{read};


                            ArrayList<String> arrlist = new ArrayList<String>();

                            int cnt = 0;

                            for (int i = 0; i < d.length;i++){
                                for (int j = i+1; j<d.length;j++){
                                    if(d[i].equals(d[j])){
                                        cnt+=1;
                                    }
                                }
                                if(cnt < 1){
                                    arrlist.add(d[i]);

                                    companyCountryAdapter = new ArrayAdapter<String>(this,R.layout.spinner_dropdown_item,arrlist);
                                    companyCountryAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item);

                                    companycountry.setAdapter(companyCountryAdapter);
                                    companycountry.setAnimation(new AlphaAnimation(Animation.INFINITE,Animation.ABSOLUTE));
                                    companycountry.setClickable(true);
                                }
                                cnt=0;
                            }


                            for (int k = 0; k <arrlist.size();k++){

                                Log.d("debug",arrlist.get(k));

                            }





                            /*
                            Set<String> as = new HashSet<String>(Arrays.asList(d));
                            Collections.addAll(as,d);
                            */



/*find duplicate
*/

                        }



                    }catch (Exception e){
                        readfile.close();
                    }finally {
                        readfile.close();
                    }
                }else{
                    Log.d("debug","Not Found");
                }

            }
        }catch (Exception e){

            e.printStackTrace();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

    }
}
