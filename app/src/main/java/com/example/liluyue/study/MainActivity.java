package com.example.liluyue.study;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;



public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
TextView tv_re;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

       FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

                final String src_url="http://10.1.0.39/multisrv/new1.php?sdkver=2009&apkver=300&vn=2.2.5&compver=34200&mainver=2014&ver=eyJiZCI6IjIiLCJ6aCI6IjAifQ%3D%3D%0A&chid=10023&subchid=1&type=20&uflag=2&pkg=com.excelliance.dualaid&api=22&release=5.1&abi=armeabi-v7a&abi2=armeabi&abilist=arm64-v8a%2Carmeabi-v7a%2Carmeabi&abilist32=armeabi-v7a%2Carmeabi&abilist64=arm64-v8a&brand=Meizu&manufacturer=Meizu&model=M578CA&product=m2&sign=308201a33082010ca0030201020204517a71a5300d06092a864886f70d01010505003015311330110603550403130a67616d626c65727669703020170d313330";
             tv_re = (TextView) findViewById(R.id.tv_resu);
        final EditText et_bd= (EditText) findViewById(R.id.et_bd);
        final EditText et_zh= (EditText) findViewById(R.id.et_zh);
        final EditText et_mainver= (EditText) findViewById(R.id.et_mainver);
        findViewById(R.id.bt_get).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_re.setText("");
                   String bd_ver=et_bd.getText().toString();
                   String zh_ver=et_zh.getText().toString();
                   String main_ver=et_mainver.getText().toString();
                JSONObject jsonObject=new JSONObject();
                try {
                    jsonObject.put("bd",bd_ver);
                    jsonObject.put("zh",zh_ver);
                    String ver= Base64.encodeToString(jsonObject.toString().getBytes(),Base64.DEFAULT);
                    try {
                        ver= URLEncoder.encode(ver, "UTF-8");
                        Log.v(TAG,"ver:"+ver);
                        Log.v(TAG,"ver encode:"+new String(Base64.decode(URLDecoder.decode(ver,"UTF-8"), Base64.DEFAULT)));
                        String url = src_url.replaceAll("&ver=eyJiZCI6IjIiLCJ6aCI6IjAifQ%3D%3D%0A", "&ver=" + ver + "");
                        url=url.replace("&mainver=2014","&mainver="+main_ver+"");
                        Log.v(TAG,url);
                        send(url);
                        Toast.makeText(v.getContext(),"send!",Toast.LENGTH_SHORT).show();
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                        Log.e(TAG,"UnsupportedEncodingException");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public static String streamToString(InputStream is) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = is.read(buffer)) != -1) {
                baos.write(buffer, 0, len);
            }
            baos.close();
            is.close();
            byte[] byteArray = baos.toByteArray();
            return new String(byteArray);
        } catch (Exception e) {
            Log.e(TAG, e.toString());
            return null;
        }
    }
    public void send(final String url){
        Runnable runnable = new Runnable() {
            public void run() {
                try {
                    URL url1=new URL(url);
                   HttpURLConnection httpURLConnection= (HttpURLConnection) url1.openConnection();
                    httpURLConnection.setConnectTimeout(5*1000);
                    httpURLConnection.connect();
                    if (httpURLConnection.getResponseCode()==HttpURLConnection.HTTP_OK){
                        Message msg = handler.obtainMessage(0);
                        msg.obj=streamToString(httpURLConnection.getInputStream());
                        handler.sendMessage(msg);
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };

         new Thread(runnable).start();
    }
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            tv_re.setText((String) msg.obj);
        }
    };
}
