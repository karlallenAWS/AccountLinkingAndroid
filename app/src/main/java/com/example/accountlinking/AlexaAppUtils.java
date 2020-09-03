package com.example.accountlinking;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Build;
import android.util.Log;

import java.util.List;

public class AlexaAppUtils {
    private static final String TAG = AlexaAppUtils.class.getName();
    private static String ALEXA_PACKAGE_NAME = "com.amazon.dee.app";
    private static String ALEXA_APP_TARGET_ACTIVITY_NAME = "com.amazon.dee.app.ui.main.MainActivity";
    private static long REQUIRED_MINIMUM_VERSION_CODE = 866607211;

    public Boolean doesAlexaAppSupportAppToApp(Context theContext) {
        //TODO:  Need to better understand why Pie is required for App linking to Alexa.  It works fine in Oreo
        // For now this routine aways returns true
        Boolean retVal = true;
        Log.d(TAG, "--------START--------");

        try {
            PackageManager thePackageMagager = theContext.getPackageManager();
            PackageInfo packageInfo = thePackageMagager.getPackageInfo(ALEXA_PACKAGE_NAME, 0);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                Log.d(TAG, "SDK version is good");
                if (packageInfo.getLongVersionCode() > REQUIRED_MINIMUM_VERSION_CODE) {
                    Log.d(TAG, "Meets required min version");
                    if (packageInfo != null) {
                        Log.d(TAG, "Package info is not null.  Everything looks good");
                        retVal = true;
                    }
                }
            }

        } catch (PackageManager.NameNotFoundException e) {
            Log.e(TAG, "Cannot resolve info for" + theContext.getPackageName(), e);
        }

        Log.d(TAG, "--------END--------");
        return retVal;
    }


}
