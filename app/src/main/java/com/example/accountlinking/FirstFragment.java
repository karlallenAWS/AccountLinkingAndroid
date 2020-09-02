package com.example.accountlinking;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import androidx.navigation.fragment.NavHostFragment;

import com.google.gson.JsonElement;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.StringEntity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

public class FirstFragment extends Fragment {
    private static final String TAG = "FirstFragment:";
    private static String clientID = "&client_id=amzn1.application-oa2-client.269a174435e04267aaffecc0b68160d1";
    private static String scope = "&scope=alexa::skills:account_linking";
    private static String skillStage = "&skill_stage=development";
    private static String responseType = "&response_type=code";
    private static String redirectURI = "&redirect_uri=https://d28y7w72j0qjmg.cloudfront.net/linkme";
    private static String alexaURL = "https://alexa.amazon.com/spa/skill-account-linking-consent?fragment=skill-account-linking-consent";
    private static String lwaURL = "https://www.amazon.com/ap/oa";
    private static String state = "&state=aU9TLjE1OTg3MjQ4NzEuWE9vaS9USnExZ29GdGcrY01OR2lzZkJydHA1WXdrdzBRbzFEQTFxbQ--";

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.button_first).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "Button action");
//                String url = "https://9w2unnk8l9.execute-api.us-west-1.amazonaws.com/dev/account-auth";
//                AsyncHttpClient client = new AsyncHttpClient();
//                JSONObject jsonParams = new JSONObject();
//                try {
//                    jsonParams.put("Parm1", parmVal1);
//                    jsonParams.put("Parm2", parmVal2);
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//
//                StringEntity entity = null;
//                entity = new StringEntity(jsonParams.toString(), "UTF-8");
//                if (entity != null) {
//                    Context theContext = getContext();
//                    client.post(theContext, url, entity, "application/json", new JsonHttpResponseHandler() {
//                        @Override
//                        public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
//                            // Root JSON in response is an dictionary i.e { "data : [ ... ] }
//                            // Handle resulting parsed JSON response here
//                            Log.d(TAG, "Successful call to backend");
//                            try {
//                                Log.d(TAG, "Access Token: " + response.getString("access_token"));
//                                Log.d(TAG, "Refresh Token: " + response.getString("refresh_token"));
//                                Log.d(TAG, "We are done here");
//                            } catch (JSONException e) {
//                                e.printStackTrace();
//                            }
//                        }
//
//                        @Override
//                        public void onFailure(int statusCode, Header[] headers, String res, Throwable t) {
//                            // called when response HTTP status is "4XX" (eg. 401, 403, 404)
//                            Log.e(TAG, "Failed call to backend.  Status: " + statusCode + "Reason: " + res);
//                        }
//                    });
//                } else {
//                    Log.e(TAG, "String entity was null!!");
//                }

                String alexaAppUrl = alexaURL + clientID + scope + skillStage + responseType + redirectURI + state;
                String lwaFallbackUrl = lwaURL + clientID + scope + responseType + redirectURI + state;
                Context theContext = getContext();
                AccountLinkingHelper accountLinker = new AccountLinkingHelper();
                Log.d(TAG, "Attempting to open Alexa app or LWA url");
                accountLinker.openAlexaAppToAppUrl(alexaAppUrl, lwaFallbackUrl, theContext);
//                NavHostFragment.findNavController(FirstFragment.this)
//                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
            }
        });
    }
}

