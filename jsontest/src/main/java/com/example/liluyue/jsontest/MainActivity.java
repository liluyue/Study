package com.example.liluyue.jsontest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static android.R.attr.key;
import static android.R.attr.tag;
import static android.R.attr.value;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();
        JSONObject jsonObject=new JSONObject();
        Log.v(TAG,"jsonObject:"+jsonObject);
        Map<String,Object> objectMap=new IdentityHashMap<>();
//        getJsonByMap(objectMap);
        JSONArray jsonArray=new JSONArray();
        jsonArray.put("a");
        jsonArray.put("a");
        jsonArray.put(true);
        Log.v(TAG,"jsonArray:"+jsonArray);
        String name = null;
        Object o=jsonObject.opt(name);
    }

    private JSONObject getJsonByMap(Map<String, Object> objectMap) {
        JSONObject jsonObject=new JSONObject();
        if(objectMap!=null){
            Iterator<Map.Entry<String, Object>> iterator = objectMap.entrySet().iterator();
            while (iterator.hasNext()){
                Map.Entry<String, Object> entrySet = iterator.next();
                String key=entrySet.getKey();
                 Object value=entrySet.getValue();
                if (value instanceof Map){
                    value=getJsonByMap((Map<String, Object>) value);
                }
                try {
                    jsonObject.put(key,value);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        return jsonObject;
    }
}
