package com.example.graham.sunshine.app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends ActionBarActivity {

    private String LOG_TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new ForecastFragment())
                    .commit();
        }
        Log.v(LOG_TAG, "On Create");
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Intent launchSettings = new Intent(getApplicationContext(), SettingsActivity.class);
            startActivity(launchSettings);
            return true;
        }
        if (id == R.id.action_map){
            Intent launchMap = new Intent(Intent.ACTION_VIEW);
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            String location = prefs.getString(getString(R.string.pref_location_key), "Vancouver,CA");
            Uri uriLocation = Uri.parse("geo:0,0").buildUpon()
                    .encodedQuery(location)
                    .build();
            launchMap.setData(uriLocation);
            if (launchMap.resolveActivity(getPackageManager()) != null){
                startActivity(launchMap);
            } else Log.v("Launch Map", "No app");
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.v(LOG_TAG, "On Pause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.v(LOG_TAG, "On Resume");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.v(LOG_TAG, "On Stop");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.v(LOG_TAG, "On Start");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.v(LOG_TAG, "On Destroy");
    }
}
