package com.example.accountlinking;

import android.content.Context;
import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.StringEntity;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.annotation.Target;

public class BackendHelper {
    private static final String TAG = "BackendHelper";
    private static String backendURL = "https://9w2unnk8l9.execute-api.us-west-1.amazonaws.com/dev/account-exchange";

    public void completeAccountLinking(String authCode, String theState, Context theContext) {
        Log.d(TAG, "Finishing account linking");
        AsyncHttpClient client = new AsyncHttpClient();
        JSONObject jsonParams = new JSONObject();

        try {
            jsonParams.put("AuthCode", authCode);
            jsonParams.put("State", theState);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        StringEntity entity = null;
        entity = new StringEntity(jsonParams.toString(), "UTF-8");
        if (entity != null) {
            client.post(theContext, backendURL, entity, "application/json", new JsonHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    // Root JSON in response is an dictionary i.e { "data : [ ... ] }
                    // Handle resulting parsed JSON response here
                    Log.d(TAG, "Successful call to backend with status: " + statusCode);
                    Log.d(TAG, "Linking compeleted");
//                    try {
//                        Log.d(TAG, "Access Token: " + response.getString("access_token"));
//                        Log.d(TAG, "Refresh Token: " + response.getString("refresh_token"));
//                        Log.d(TAG, "We are done here");
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, String res, Throwable t) {
                    // called when response HTTP status is "4XX" (eg. 401, 403, 404)
                    Log.e(TAG, "Failed call to backend.  Status: " + statusCode + "Reason: " + res);
                }
            });
        } else {
            Log.e(TAG, "String entity was null!!");
        }
    }
}
