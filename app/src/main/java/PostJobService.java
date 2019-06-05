package com.acejob.acejob;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.app.AlertDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;

/**
 * Created by User on 30/11/2017.
 */

public class PostJobService extends AsyncTask<String,String,String>{

Context context;
AlertDialog alert;
private String result;
    ProgressDialog progressDialog;
private int progressStatus=0;
private static postjob parent;
private Handler handler;
boolean pass = false;

    public PostJobService(Context cont ) {
context = cont;
    }


    @Override
    protected String doInBackground(String... arg0) {

        String pattern = "(\\w)(\\s+)([\\.,])";
        //parse arguments to string
        String companyname = (String)arg0[0];
        String companysize = (String)arg0[1];
        String postername = (String)arg0[2];
        String posterdepartment = (String)arg0[3];
        String companyphone =(String) arg0[4];
        String companyemail = (String)arg0[5];



        //get url location to insert data webhook
String url = "https://acejob.000webhostapp.com/job/insertdata.php?company_name="+companyname.replaceAll("\\s","%20")+"&company_size="+companysize.replaceAll("\\s","%20") + "&poster_name="+postername.replaceAll("\\s","%20")+"&poster_department="+posterdepartment.replaceAll("\\s","%20")+"&company_phone="+companyphone.replaceAll("\\s","%20")+"&company_email="+companyemail.replaceAll("\\s","%20");
        try {
            URL link = new URL(url);

    HttpClient httpClient = new DefaultHttpClient();
    HttpGet request = new HttpGet();

    request.setURI(new URI(url));

    HttpResponse response = httpClient.execute(request);
    BufferedReader  in = new BufferedReader( new InputStreamReader(response.getEntity().getContent()));

    StringBuffer sb = new StringBuffer("");
    String  line ="";

    while((line = in.readLine())!=null){
        sb.append(line);
        break;
    }
    in.close();
    return sb.toString();

}catch (final Exception e){

            Log.w("WARNING","ERROR:"+e.getMessage());

            Looper.prepare();

            alert = new AlertDialog.Builder(context).create();
        alert.setMessage("Error: Check internet connection");
        alert.setCanceledOnTouchOutside(true);
        alert.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                Intent intent = new Intent(context.getApplicationContext(),gettingstarted.class);
                context.startActivity(intent);
            }
        });
        alert.show();





          }


return null;
    }

    @Override
    protected void onPreExecute() {
        Log.w("WARNING","INSERTING .........");
        progressDialog = new ProgressDialog(context);
        progressDialog.setTitle("Acejob.com");
        progressDialog.setProgress(0);
for(int i =0 ; i<100;i++){
    progressDialog.setProgress(i);
    progressDialog.setMessage("Inseting data....."+i);
}
progressDialog.show();
    }

    @Override
    protected void onPostExecute(String s){

        try{



        this.result = s;
        setResult(result);
            Log.w("WARNING","INSERTED : "+getResult());



            if(result !=null){
                if(progressDialog.isShowing()){
                    progressDialog.dismiss();



                    progressDialog = new ProgressDialog(context);
                    progressDialog.setTitle("Acejob.com");

                    while (progressStatus < 100){
                        progressStatus+=1;
                        progressDialog.setProgress(progressStatus);
                        progressDialog.setMessage("Getting Started... in " +progressStatus +"/"+progressDialog.getProgress());
                        if(progressDialog.getProgress() == 100){
                            progressDialog.dismiss();
                        }
                        try {
                            Thread.sleep(10);
                        }catch (InterruptedException e){
                            e.printStackTrace();
                        }

                    }

                    progressDialog.show();
                    progressDialog.setCancelable(false);

                }
}
        }catch (Exception e){
e.printStackTrace();
        }
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public String toString(){
        return result+" is correct";
    }
}
