package com.example.graham.sunshine.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.ShareActionProvider;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class DetailActivity extends ActionBarActivity {

    ShareActionProvider mShareActionProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.detail_activity, menu);
        MenuItem shareItem = menu.findItem(R.id.action_share);
        mShareActionProvider = new ShareActionProvider(getApplicationContext());
        ShareActionProvider newShareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(shareItem);
        mShareActionProvider = newShareActionProvider;
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
        if (id == R.id.action_share){
            Intent launchShare = new Intent(Intent.ACTION_SEND);
            String shareString = "";
            if (getIntent().hasExtra(Intent.EXTRA_TEXT)){
                shareString = getIntent().getStringExtra(Intent.EXTRA_TEXT);
                shareString = shareString + " #Sunshine";
                launchShare.putExtra(Intent.EXTRA_TEXT, shareString);
                mShareActionProvider.setShareIntent(launchShare);
                Log.v("Share", shareString);
            } else Log.v("Share", "No Extras");

        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_detail_activity, container, false);
            Intent intent = getActivity().getIntent();
            if (intent != null && intent.hasExtra(Intent.EXTRA_TEXT)){
                TextView contentView = (TextView) rootView.findViewById(R.id.content_textview);
                contentView.setText(intent.getStringExtra(Intent.EXTRA_TEXT));
            }

            return rootView;
        }
    }
}
