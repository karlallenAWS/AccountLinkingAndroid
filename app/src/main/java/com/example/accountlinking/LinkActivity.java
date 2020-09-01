package com.example.accountlinking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

public class LinkActivity extends AppCompatActivity {
    private static final String TAG = "LinkActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_link);
        Log.d(TAG, "Got a link activity");
        // ATTENTION: This was auto-generated to handle app links.
        Intent appLinkIntent = getIntent();
        handleIntent(appLinkIntent);
    }

    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {
        Log.d(TAG, "Handling linkback");
        String appLinkAction = intent.getAction();
        Uri appLinkData = intent.getData();
        Log.d(TAG, "Link data: " + appLinkData);
        if (Intent.ACTION_VIEW.equals(appLinkAction) && appLinkData != null){
            String lastPathSegment = appLinkData.getLastPathSegment();
            Log.d(TAG, "Last Segment: " + lastPathSegment);
//            Uri appData = Uri.parse("content://com.recipe_app/recipe/").buildUpon()
//                    .appendPath(recipeId).build();
//            showRecipe(appData);
        } else {
            Log.d(TAG, "App link action was not valid");
        }
    }
}