package com.example.rashaadsrns;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.CountDownTimer;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;

import javax.net.ssl.HttpsURLConnection;


import android.annotation.SuppressLint;
        import android.app.Notification;
        import android.app.NotificationManager;
        import android.app.PendingIntent;
        import android.app.Service;
        import android.content.Context;
        import android.content.Intent;
        import android.os.AsyncTask;
        import android.os.CountDownTimer;
        import android.os.IBinder;
        import android.util.Log;
        import android.widget.Toast;

        import androidx.annotation.Nullable;
        import androidx.core.app.NotificationCompat;

        import org.json.JSONArray;
        import org.json.JSONException;
        import org.json.JSONObject;

        import java.io.BufferedReader;
        import java.io.BufferedWriter;
        import java.io.InputStreamReader;
        import java.io.OutputStream;
        import java.io.OutputStreamWriter;
        import java.net.HttpURLConnection;
        import java.net.URL;
        import java.net.URLEncoder;
        import java.util.ArrayList;
        import java.util.Iterator;

        import javax.net.ssl.HttpsURLConnection;

public class serviceads extends Service {
    ArrayList<String> namef = new ArrayList<String>();
    ArrayList<String> time = new ArrayList<String>();
    ArrayList<String> id = new ArrayList<String>();
    ArrayList<String> text = new ArrayList<String>();
    ArrayList<String> size = new ArrayList<String>();
    static int  b;
    int bd = 0 ;
    CountDownTimer CountDownTimer1 ;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        new SendRequest().execute();
        timer();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }





    public class SendRequest extends AsyncTask<String, Void, String> {

        protected void onPreExecute(){}

        @SuppressLint("WrongThread")
        protected String doInBackground(String... arg0) {

            try{

                URL url = new URL("http://rashanarmafzar.ir/amir/allmessage.php");

                JSONObject postDataParams = new JSONObject();

                Log.e("params",postDataParams.toString());

                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(15000 /* milliseconds */);
                conn.setConnectTimeout(15000 /* milliseconds */);
                conn.setRequestMethod("POST");
                conn.setRequestProperty("lang","fa");
                conn.setDoInput(true);
                conn.setDoOutput(true);
                OutputStream os = conn.getOutputStream();
                BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(os, "UTF-8"));
                writer.write(getPostDataString(postDataParams));
                writer.flush();
                writer.close();
                os.close();
                int responseCode=conn.getResponseCode();

                if (responseCode == HttpsURLConnection.HTTP_OK) {

                    BufferedReader in=new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    StringBuffer sb = new StringBuffer("");
                    String line="";

                    while((line = in.readLine()) != null) {

                        sb.append(line);
                        break;
                    }

                    in.close();
                    return sb.toString();

                }
                else {
                    return new String("false : "+responseCode);
                }
            }
            catch(Exception e){
                return new String("Exception: " + e.getMessage());
            }
        }
        @SuppressLint("ResourceType")
        @Override
        protected void onPostExecute(String result) {

//
            JSONObject reader = null;
            String masd = null;
            JSONArray JA = new JSONArray();
            try {
                reader = new JSONObject(result);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            try {
                JA  = reader.getJSONArray("users");

            } catch (JSONException e) {
                e.printStackTrace();
            }
            for(int i =0 ;i <JA.length(); i++){
                JSONObject JO = null;
                try {
                    JO = (JSONObject) JA.get(i);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                try {
                    id.add((String)JO.get("id"));
                    namef.add((String)JO.get("namef"));
                    time.add((String)JO.get("time"));
                    text.add((String)JO.get("text"));






                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }


            b = id.size() ;

            bd = id.size() ;
            String asd = String.valueOf(bd);
            Toast.makeText(serviceads.this,asd,Toast.LENGTH_SHORT).show();


        }

    }
    public class SendRequest1 extends AsyncTask<String, Void, String> {

        protected void onPreExecute(){}

        @SuppressLint("WrongThread")
        protected String doInBackground(String... arg0) {

            try{

                URL url = new URL("http://rashanarmafzar.ir/amir/allmessage.php");

                JSONObject postDataParams = new JSONObject();

                Log.e("params",postDataParams.toString());

                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(15000 /* milliseconds */);
                conn.setConnectTimeout(15000 /* milliseconds */);
                conn.setRequestMethod("POST");
                conn.setRequestProperty("lang","fa");
                conn.setDoInput(true);
                conn.setDoOutput(true);
                OutputStream os = conn.getOutputStream();
                BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(os, "UTF-8"));
                writer.write(getPostDataString(postDataParams));
                writer.flush();
                writer.close();
                os.close();
                int responseCode=conn.getResponseCode();

                if (responseCode == HttpsURLConnection.HTTP_OK) {

                    BufferedReader in=new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    StringBuffer sb = new StringBuffer("");
                    String line="";

                    while((line = in.readLine()) != null) {

                        sb.append(line);
                        break;
                    }

                    in.close();
                    return sb.toString();

                }
                else {
                    return new String("false : "+responseCode);
                }
            }
            catch(Exception e){
                return new String("Exception: " + e.getMessage());
            }
        }
        @SuppressLint("ResourceType")
        @Override
        protected void onPostExecute(String result) {
            for(int i = size.size()-1 ; i >= 0; i--){
                size.remove(size.get(i));

            }

            JSONObject reader = null;
            String masd = null;
            JSONArray JA = new JSONArray();
            try {
                reader = new JSONObject(result);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            try {
                JA  = reader.getJSONArray("users");

            } catch (JSONException e) {
                e.printStackTrace();
            }
            for(int i =0 ;i <JA.length(); i++){
                JSONObject JO = null;
                try {
                    JO = (JSONObject) JA.get(i);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                try {
                    size.add((String)JO.get("id"));





                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
            if (bd == size.size())
            {

            }
            else
            {
                for(int i = id.size()-1 ; i >= 0; i--){
                    id.remove(id.get(i));
                    namef.remove(namef.get(i));
                    time.remove(time.get(i));
                    text.remove(text.get(i));
                }
                Intent intent = new Intent(getApplicationContext(), serviceads.class);
                PendingIntent contentIntent = PendingIntent.getActivity(getApplicationContext(), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

                NotificationCompat.Builder b = new NotificationCompat.Builder(getApplicationContext());

                b.setAutoCancel(true)
                        .setDefaults(Notification.DEFAULT_ALL)
                        .setWhen(System.currentTimeMillis())
                        .setTicker("Hearty365")
                        .setContentTitle("پیام جدید ")
                        .setContentText("برای دیدن ضربه بزنید ")
                        .setDefaults(Notification.DEFAULT_LIGHTS| Notification.DEFAULT_SOUND)
                        .setContentIntent(contentIntent)
                        .setContentInfo("Info");


                NotificationManager notificationManager = (NotificationManager) getApplication().getSystemService(Context.NOTIFICATION_SERVICE);
                notificationManager.notify(1, b.build());
                new SendRequest().execute();

            }



        }

    }

    public void timer() {
        CountDownTimer1 =  new CountDownTimer(3000, 1000){
            public void onTick(long millisUntilFinished){



            }
            public  void onFinish(){


                new SendRequest1().execute();
                timer();
            }
        }.start();

    }

    public String getPostDataString(JSONObject params) throws Exception {

        StringBuilder result = new StringBuilder();
        boolean first = true;

        Iterator<String> itr = params.keys();

        while (itr.hasNext()) {

            String key = itr.next();
            Object value = params.get(key);

            if (first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(key, "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(value.toString(), "UTF-8"));

        }
        return result.toString();
    }

}