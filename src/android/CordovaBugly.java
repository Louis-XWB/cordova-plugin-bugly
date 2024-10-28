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
        initBugly(webView);
    }

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        Log.d(TAG, "execute called with action: " + action);
        if (action.equals("testCrash")) {
            this.testCrash();
            return true;
        }
        return false;
    }

    private void initBugly(CordovaWebView webView) {
        ConfigXmlParser parser = new ConfigXmlParser();
        parser.parse(cordova.getActivity());
        CordovaPreferences preferences = parser.getPreferences();
        String appId = preferences.getString("BuglyAndroidAppId", "");
        if (appId != null && appId.length() > 0) {
            CrashReport.initCrashReport(cordova.getActivity().getApplicationContext(), appId, false); 
            CrashReport.setJavascriptMonitor((android.webkit.WebView) webView.getView(), true);
        } else {
            Log.e(TAG, "appId is null");
        }
    }

    private void testCrash() {
        cordova.getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                throw new RuntimeException("This is a crash");
            }
        });
    }
}