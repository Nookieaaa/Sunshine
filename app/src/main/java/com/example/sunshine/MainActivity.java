package com.example.sunshine;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id) {
            case R.id.action_settings: {
                Intent preferences = new Intent(this, SettingsActivity.class);
                startActivity(preferences);
                break;
            }
            case R.id.action_map: {
                openPreferredLocationInMap();
                break;

            }

        }

        return super.onOptionsItemSelected(item);
    }



        private void openPreferredLocationInMap(){
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
            String location = preferences.getString(getString(R.string.location),
                    getString(R.string.pref_default_location));
            Uri geo = Uri.parse("geo:0,0?").buildUpon().appendQueryParameter("q",location).build();

            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(geo);

            if (intent.resolveActivity(getPackageManager())!=null) {
                startActivity(intent);
            }
            else
                Toast.makeText(this,"NO MAP ON DEVICE",Toast.LENGTH_SHORT).show();
        }
}
