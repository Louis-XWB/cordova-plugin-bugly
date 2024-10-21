package com.novar.plugin.bugly;

import org.apache.cordova.CordovaPlugin;

import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.ConfigXmlParser;
import org.apache.cordova.CordovaPreferences;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.CordovaInterface;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.widget.Toast;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.os.Environment;
import com.tencent.bugly.crashreport.CrashReport;

public class CordovaBugly extends CordovaPlugin {

    private static final String TAG = "CordovaBugly";

    @Override
    public void initialize(CordovaInterface cordova, CordovaWebView webView) {
        super.initialize(cordova, webView);
        System.out.println("Bugly running.......");
        initBugly();
    }

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        Log.d(TAG, "execute called with action: " + action);
        Toast.makeText(cordova.getContext(), "hulala------", Toast.LENGTH_LONG).show();
        if (action.equals("sayHello")) {
            String message = args.getString(0);
            this.sayHello(message, callbackContext);
            return true;
        }

        if (action.equals("testCrash")) {
            this.testCrash();
            return true;
        }
        return false;
    }

    private void sayHello(String message, CallbackContext callbackContext) {

        if (message != null && message.length() > 0) {
            Toast.makeText(cordova.getActivity(), message, Toast.LENGTH_LONG).show();
            Log.d(TAG, "Immediate toast shown" + message);
            callbackContext.success("Native received: " + message);
        } else {
            callbackContext.error("message is null");
        }
    }

    private void initBugly() {
        Toast.makeText(cordova.getContext(), "initBugly------", Toast.LENGTH_LONG).show();
        ConfigXmlParser parser = new ConfigXmlParser();
        parser.parse(cordova.getActivity());// 解析res/xml/config.xml文件
        Log.d("buglyPlugin", "try get appid");
        CordovaPreferences preferences = parser.getPreferences();
        String appId = preferences.getString("buglyAndroidAppId", "");
        Log.d("buglyPlugin", "appid:" + appId);
        Toast.makeText(cordova.getContext(), "initBugly------" + appId, Toast.LENGTH_LONG).show();
        if (appId != null && appId.length() > 0) {
            CrashReport.initCrashReport(cordova.getActivity().getApplicationContext(), appId, false); 
        } else {
            Log.e(TAG, "appId is null");
        }
    }

    private void testCrash() {
        Toast.makeText(cordova.getContext(), "testCrash------", Toast.LENGTH_LONG).show();
        cordova.getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                throw new RuntimeException("This is a crash");
            }
        });
    }
}