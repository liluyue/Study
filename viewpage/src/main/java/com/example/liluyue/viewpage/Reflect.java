package com.example.liluyue.viewpage;

import android.util.Log;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Type;

/**
 * Created by liluyue on 2017/5/18.
 */

public class Reflect {
    private static final String TAG = "Reflect";

    public static void reflectMembers() {
        try {
//得到对象
            Class c = Class.forName("com.aliyun.ams.systembar.SystemBarColorManager");
//            Object yourObj = c.newInstance();
            Constructor[] cons=c.getDeclaredConstructors();
            for (int i=0;i<cons.length;i++){
                Constructor constructor=cons[i];
                Log.v(TAG, "reflectMembers: constructor name :"+constructor.getName());
                for (Type t : constructor.getGenericParameterTypes()) {
                    Log.v(TAG, "reflectMembers: constructor  p :" + t);
                }
                for (Type t : constructor.getParameterTypes()) {
                    Log.v(TAG, "reflectMembers: constructor  p :" + t);
                }
            }
//得到方法
            Method methlist[] = c.getDeclaredMethods();
            for (int i = 0; i < methlist.length; i++) {
                Method m = methlist[i];
                Log.v(TAG, "reflectMembers: name:"+m.getName());
                for (Type t : m.getGenericParameterTypes()) {
                    Log.v(TAG, "reflectMembers: " + t);
                }
                for (Type t : m.getParameterTypes()) {
                    Log.v(TAG, "reflectMembers: " + t);
                }
            }
        } catch (Exception e) {
            Log.e(TAG, "reflectMembers: "+e.getLocalizedMessage() );
        }

    }

}
