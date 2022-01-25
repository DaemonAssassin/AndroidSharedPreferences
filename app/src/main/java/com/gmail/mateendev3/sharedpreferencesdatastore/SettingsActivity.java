package com.gmail.mateendev3.sharedpreferencesdatastore;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.gmail.mateendev3.sharedpreferencesdatastore.fragment.SettingsFragment;

/**
 * Steps to create Settings Activity
 * 1. Create a Setting Activity
 * 2. Create a resource directory with xml name and file type as xml
 * 3. create an xml file for settings layout
 * 4. Create a Fragment that extent PreferenceFragmentCompat
 * 5. override onCreatePreferences and inflate xml file there
 * 6. add this fragment in the activity file
 */

/**
 * Attaching listener to shared preference file
 * 1.
 */

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.settings_fragment_container_view, new SettingsFragment())
                .commit();
    }
}