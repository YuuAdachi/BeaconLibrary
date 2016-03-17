package com.example.k13006kk.mylibrary;

/**
 * Created by k13006kk on 2016/02/22.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestHandle;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;

public class mainActivity {


    //String room_uuid;

    /*
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startService(new Intent(MainActivity.this, BeaconApplication.class));

    }
    */

    public String[] data_com(String url) {

        String[] beacon_info = new String[3];

        final String[] stringArray = new String[10];
        //stringArray = new String[8];

        DataHolder holder = DataHolder.getInstance();
        beacon_info = holder.getTestString();

        /*
        TextView tv = (TextView) findViewById(R.id.uuid);//テスト用
        tv.setText(room_uuid);//テスト用
        */

        AsyncHttpClient client = new AsyncHttpClient(); //通信準備

        final RequestParams params = new RequestParams(); //リクエストパラメータ
        params.put("uuid", beacon_info[0]); //送るパラメータ1
        String server_url = url;//"http://192.168.100.211:808/beacon_server.php""""http://192.168.51.180:808/beacon_server.php

        client.get(server_url, params, new AsyncHttpResponseHandler() {

            @Override
            public void onSuccess(/*View view, */int i, Header[] headers, byte[] bytes) {
                //InputStream input;
                try {

                    String json = new String(bytes);
                    System.out.println(json);
                    JSONObject jsonObject = new JSONObject(json);


                    //System.out.println(jsonObject.getString("room_number")); // "fuga"
                    //System.out.println(jsonObject.getString("room_name"));

                    System.out.println(jsonObject.getString("building_id"));
                    System.out.println(jsonObject.getString("building_name"));
                    System.out.println(jsonObject.getString("roomnumber_id"));
                    System.out.println(jsonObject.getString("roomnumber_no"));
                    System.out.println(jsonObject.getString("room_id"));
                    System.out.println(jsonObject.getString("beacon_identifier"));
                    System.out.println(jsonObject.getString("room_name"));

                    stringArray[0] = jsonObject.getString("building_id");
                    stringArray[1] = jsonObject.getString("building_name");
                    stringArray[2] = jsonObject.getString("roomnumber_id");
                    stringArray[3] = jsonObject.getString("roomnumber_no");
                    stringArray[4] = jsonObject.getString("room_id");
                    stringArray[5] = jsonObject.getString("beacon_identifier");
                    stringArray[6] = jsonObject.getString("room_name");


                    /*
                    TextView tv = (TextView) findViewById(R.id.room_id);//？を指定
                    tv.setText(jsonObject.getString("room_number"));//？を変更
                    TextView tv2 = (TextView) findViewById(R.id.room_name);//？を指定
                    tv2.setText(jsonObject.getString("name"));//？を変更
                    */

                } catch (JSONException e) {
                    e.printStackTrace();
                } finally {
                }
                /*
                TextView tv = (TextView) findViewById(R.id.myTextView);//テスト用
                tv.setText("成功");//テスト用
                */
            }

            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                /*
                TextView tv = (TextView) findViewById(R.id.myTextView);//
                tv.setText("失敗");//テスト用
                */
            }


        });

        stringArray[7] = beacon_info[0];
        stringArray[8] = beacon_info[1];
        stringArray[9] = beacon_info[2];

        return stringArray;
    }
}
