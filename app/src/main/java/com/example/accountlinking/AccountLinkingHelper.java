package com.example.accountlinking;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import java.util.List;

public class AccountLinkingHelper {
    private static final String TAG = "AccountLinkingHelper";

    private Intent getAppToAppIntent(String appToUrl) {
        Intent retVal = new Intent(Intent.ACTION_VIEW, Uri.parse(appToUrl));
        return retVal;
    }

    private Boolean validateIntent(Intent theIntent, Context theContext) {
        PackageManager thePackageMagager = theContext.getPackageManager();
        List<ResolveInfo> activities = thePackageMagager.queryIntentActivities(theIntent, 0);

        return (activities.size() > 0);
    }

    public void openAlexaAppToAppUrl(String alexaAppUrl, String lwaFallbackUrl, Context theContext) {
        Log.d(TAG, "--------START--------");
        if (theContext == null) {
            Log.e(TAG, "Missing context");
            return;
        }

        AlexaAppUtils appUtils = new AlexaAppUtils();
//        Activity theActivity = new Activity();
        Log.d(TAG, "Checking which activity to start");
        if (appUtils.doesAlexaAppSupportAppToApp(theContext)) {
            Log.d(TAG, "Starting App activity");
            Intent appIntent = getAppToAppIntent(alexaAppUrl);
            if (validateIntent(appIntent, theContext)) {
                try {
                    theContext.startActivity(appIntent);
                } catch (Exception e) {
                    Log.d(TAG, "Exception on start activity Alexa URL: " + e);
                }
            } else {
                Log.d(TAG, "Invalid Alexa App URL intent");
            }
        } else {
            Log.d(TAG, "Starting LWA activity");
            Intent appIntent = getAppToAppIntent(lwaFallbackUrl);
            if (validateIntent(appIntent, theContext)) {
                try {
                    theContext.startActivity(appIntent);
                } catch (Exception e) {
                    Log.d(TAG, "Exception on start activity LWA fallback URL: " + e);
                }
            } else {
                Log.d(TAG, "Invalid LWA fallback URL intent");
            }
        }

        Log.d(TAG, "--------END--------");

    }
}