package com.acejob.acejob;

import com.acejob.acejob.Font;
import com.acejob.acejob.PostJobService;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.StrictMode;
import android.text.style.UpdateAppearance;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TabWidget;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Timer;
import java.util.TimerTask;

public class postjob extends rootActivity {
EditText companyname,postername,posterdepartment,companyphone,companyemail;
Spinner companysize;
Button continuebtn;
ArrayAdapter sizeAdapter;
String[] sizeData;
ProgressBar progressBar;
ProgressDialog progressDialog;
Context  context;
    AlertDialog alert;
    int progressStatus =0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.createpostjob);
        setTitle(R.string.createpostjob);

        companyname = (EditText)findViewById(R.id.companyname);
        postername = (EditText)findViewById(R.id.postername);
        posterdepartment = (EditText)findViewById(R.id.posterdepartment);
        companyphone = (EditText)findViewById(R.id.companyphone);
        companyemail = (EditText)findViewById(R.id.companyemail);

        companysize = (Spinner)findViewById(R.id.companysize);
        continuebtn =(Button)findViewById(R.id.continuebtn);

        progressBar = (ProgressBar)findViewById(R.id.progressBar);

//        setEditTextFont(companyname,"font/droidsans_bold.ttf");


Font font = new Font(this,getAssets());
        font.setEditTextFont(companyname,"font/droidsans_bold.ttf");
        font.setEditTextFont(postername,"font/roboto_light.ttf");
        font.setEditTextFont(posterdepartment,"font/roboto_light.ttf");
        font.setEditTextFont(companyphone,"font/roboto_light.ttf");
        font.setEditTextFont(companyemail,"font/roboto_light.ttf");
    }

    @Override
    protected void onStart() {
        super.onStart();

         sizeData = new String[]{"Select Company Size","1-49","50-149","150-249","250-499","500-749","750-999","1000+"};
         sizeAdapter = new ArrayAdapter(this,R.layout.spinner_dropdown_item,sizeData);
         sizeAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item);

         companysize.setAdapter(sizeAdapter);
         companysize.setAnimation(new AlphaAnimation(Animation.INFINITE,Animation.ABSOLUTE));
         companysize.setClickable(true);
         continuebtn.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
             //    gettingStarted();
             onSubmit();
             }
         });


    }


    private void onSubmit(){

        if(companyname.getText().toString() !=""
                && companysize.getSelectedItem().toString() !="Select Company Size"
                && postername.getText().toString() !=""
                && posterdepartment.getText().toString() !=""
                && companyphone.getText().toString() !=""
                && companyemail.getText().toString() !=""){

            disable();
new loadingSync(this).execute();
   /*

            final PostJobService postservice = new PostJobService(this);
            postservice.execute(companyname.getText().toString(),companysize.getSelectedItem().toString(),postername.getText().toString(),posterdepartment.getText().toString(),companyphone.getText().toString(),companyemail.getText().toString());

                 setting time interval
                new Timer().scheduleAtFixedRate(new TimerTask() {
                    @Override
                    public void run() {
                        gettingStarted();
                    }
                },0,2000);



                postservice.alert.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialog) {

                        gettingStarted();

                    }
                });
*/

                /*
 setTimeout

                new android.os.Handler().postDelayed(new Runnable() {
     @Override
     public void run() {
   if(postservice.alert.isShowing() ==false){
postservice.alert.show();
       gettingStarted();
   }else{
       gettingStarted();
   }
         }
 },5000);
*/


        }else{
            alert = new AlertDialog.Builder(this).create();
            alert.setMessage("All Fields are required");
            alert.show();
            disable();
            alert.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialog) {
                    enable();
                }
            });
        }


        /*
        Connection connection = null;
        try{


            //String driver ="net.sourceforge.jtds.jdbc.Driver";

            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);

            String dbuser = "KINDER10";
            String dbpass = "efemena1";

            Class.forName("net.sourceforge.jtds.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection("jdbc:jtds:sqlserver://10.0.2.2:1433/acejob;encrypt=false;user="+dbuser+";password="+dbpass+";instance=SQLEXPRESS;");

Log.w("CONNECTION","Database connected");

            PreparedStatement ps = connection.prepareStatement("insert into customer_table(user_id,company_name,company_size,poster_name,poster_department,company_telephone,company_email) value(?,?,?,?,?,?,?)");

ps.setInt(1,1);
ps.setString(2,companyname.getText().toString());
            ps.setString(3,companysize.getSelectedItem().toString());
            ps.setString(4,postername.getText().toString());
            ps.setString(5,posterdepartment.getText().toString());
            ps.setString(6,companyphone.getText().toString());
            ps.setString(7,companyemail.getText().toString());

            while (ps.executeQuery().rowInserted()){
                Toast.makeText(this, "inserted One data", Toast.LENGTH_SHORT).show();
            }

        }catch (Exception e){

e.printStackTrace();
        }finally {

        }
*/

    }



    public void gettingStarted(){
this.runOnUiThread(new Runnable() {
    @Override
    public void run() {
        Intent gettingstarted = new Intent(postjob.this.getApplicationContext(),gettingstarted.class );
        startActivity(gettingstarted);
    }
});
    }


    protected void disable(){
        companyname.setEnabled(false);
        postername.setEnabled(false);
        posterdepartment.setEnabled(false);
        companyphone.setEnabled(false);
        companyemail.setEnabled(false);
        companysize.setEnabled(false);
        continuebtn.setEnabled(false);
    }

    protected void enable(){
        companyname.setEnabled(true);
        postername.setEnabled(true);
        posterdepartment.setEnabled(true);
        companyphone.setEnabled(true);
        companyemail.setEnabled(true);
        companysize.setEnabled(true);
        continuebtn.setEnabled(true);
    }



    class loadingSync extends AsyncTask<Void,Void,Void> {

        ProgressDialog progressDialog;
        int progressStatus =0;
        Context context;
        Handler handler;
        postjob postjob;
        public loadingSync(Context ctx){
            context = ctx;
        }
        @Override
        protected void onPreExecute() {

            progressDialog = new ProgressDialog(context);
            progressDialog.setTitle("Acejob");
            progressDialog.show();
            progressDialog.setCancelable(false);
            progressDialog.setProgress(0);
            progressDialog.setMax(100);



            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            Thread run = new Thread(new Runnable() {
                @Override
                public void run() {

                    while(progressDialog.getProgress() <= progressDialog.getMax()){

                        try {
                            progressStatus+=5;
                            Thread.sleep(500);

                            handler.sendMessage(handler.obtainMessage());

                            if(progressStatus ==100){
//                                progressDialog.setMessage("Done ... ");
                                getStarted();
                                progressDialog.dismiss();

                            }

                        }catch (InterruptedException e){
                            e.printStackTrace();
                        }
                    }

                }

            });

            run.start();

            handler = new Handler() {

                @Override
                public void handleMessage(Message msg) {
                    progressDialog.setMessage("Getting started ... ");
                    progressDialog.incrementProgressBy(5);

                    super.handleMessage(msg);
                }
            };

            super.onPostExecute(aVoid);
        }

        public void getStarted(){
            Intent intent = new Intent(context,gettingstarted.class);
            intent.setFlags(intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);

        }

    }


}
