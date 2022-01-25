package com.gmail.mateendev3.sharedpreferencesdatastore;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.gmail.mateendev3.sharedpreferencesdatastore.databinding.ActivityLoginBinding;
import com.gmail.mateendev3.sharedpreferencesdatastore.model.Student;
import com.google.gson.Gson;

public class LoginActivity extends AppCompatActivity {
    public static final String FILE_NAME = "my_file";
    public static final String EMAIL_KEY = "email.key";
    public static final String GSON_KEY = "gson.key";
    private ActivityLoginBinding loginBinding;
    private EditText etEmail, etPassword, etRollNo, etName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loginBinding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(loginBinding.getRoot());

        etEmail = loginBinding.etEmail;
        etPassword = loginBinding.etPassword;
        etRollNo = loginBinding.etRollNo;
        etName = loginBinding.etName;

        loginBinding.btnSettingsHome.setOnClickListener(v -> openSettingsHomeActivity());
        loginBinding.btnLogin.setOnClickListener(v -> loginAndTakeData());
        loginBinding.btnSavePrefsAndShow.setOnClickListener(v -> savePrefsAndShowData());
    }

    private void savePrefsAndShowData() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                //getting input
                String name = etName.getText().toString();
                int rollNo = Integer.parseInt(etRollNo.getText().toString());

                //creating model object
                Student student = new Student(name, rollNo);

                //creating gson object
                Gson gson = new Gson();
                //converting student to JSON using serialization
                String jsonString = gson.toJson(student, Student.class);

                //getting instance of shared prefs
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                //inserting value in prefs
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString(GSON_KEY, jsonString);
                editor.apply();

                //sleep for 5 sec
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                //starting new activity
                Intent intent = new Intent(getApplicationContext(), SettingsHome.class);
                startActivity(intent);
            }
        })
                .start();
    }

    private void openSettingsHomeActivity() {
        Intent intent = new Intent(this, SettingsHome.class);
        startActivity(intent);
    }

    private void loginAndTakeData() {
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intent = new Intent(this, HomeActivity.class);
        intent.putExtra(EMAIL_KEY, email);
        startActivity(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();

        SharedPreferences preferences = getSharedPreferences(FILE_NAME, MODE_PRIVATE);
        etEmail.setText(preferences.getString(EMAIL_KEY, "default value..."));
    }
}