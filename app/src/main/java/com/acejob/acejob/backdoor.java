package com.acejob.acejob;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.StrictMode;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by User on 06/01/2018.
 */

public class backdoor extends Service {

    Context context;
    Socket socket;
    ServerSocket serverSocket;
    BufferedOutputStream outputStream;
    BufferedInputStream inputStream;
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        try {
            StrictMode.ThreadPolicy policy =
                    new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);

            try {
                Log.d("connection", "onStartCommand: Connecting to server ...");
                //                Toast.makeText(this, "Connecting to server ...", Toast.LENGTH_SHORT).show();
                socket = new Socket(InetAddress.getLocalHost().getHostAddress(), 3090);
                socket.setKeepAlive(true);
                socket.setReuseAddress(true);

                setupStream();
                Log.d("connection", "onStartCommand: Connection started.... ");
                if(socket.isConnected() && !socket.isClosed()){

                }
                //                  Toast.makeText(this, "Connection started", Toast.LENGTH_SHORT).show();
                try{
                    Thread.sleep(1000);
                }catch(Exception ex){
                    ex.printStackTrace();
                }
            }catch(Exception e){
                e.printStackTrace();
                //                    Toast.makeText(this, "Error: "+ e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }catch (Exception e){
            e.printStackTrace();
            //                  Toast.makeText(this, "Error: "+ e.getMessage(), Toast.LENGTH_SHORT).show();
        }  finally {
            if(socket != null){
                //      closeConnection();
            }else{
//                        Toast.makeText(this, "User disconnected", Toast.LENGTH_SHORT).show();
            }
        }

        return super.onStartCommand(intent, flags, startId);
    }


    public void setupStream(){
        try {
            String read = null;
            outputStream = new BufferedOutputStream(socket.getOutputStream());
            outputStream.flush();
            inputStream = new BufferedInputStream(socket.getInputStream());

            BufferedReader input = new BufferedReader(new InputStreamReader(inputStream));
            input.readLine();

            while((read != null)){
                if(read.toString() == "DELETE"){
                    Toast.makeText(this, "DELETED APP", Toast.LENGTH_SHORT).show();
                }
            }

            Toast.makeText(this, "Stream setup complete", Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        closeConnection();
        super.onDestroy();
    }


    public void closeConnection(){
        try{
            if (socket !=null){
                socket.close();
                outputStream.close();
                outputStream.flush();
                inputStream.close();
                Toast.makeText(this, "Destroyed", Toast.LENGTH_SHORT).show();
            }else{

            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }


}
