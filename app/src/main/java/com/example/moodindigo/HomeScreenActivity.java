package com.example.moodindigo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;

import com.example.moodindigo.Login.Login_Fragment;
import com.example.moodindigo.Login.Utils;
import com.example.moodindigo.mainapp.MainScreen;

public class HomeScreenActivity extends AppCompatActivity {
    private static FragmentManager fragmentManager;
    private static PrefManager prefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        prefManager = new PrefManager(HomeScreenActivity.this);
        if(prefManager.hasLoggedIn()){
            Intent intent = new Intent(HomeScreenActivity.this, MainScreen.class);
            startActivity(intent);
            finish();
        }
        setContentView(R.layout.activity_home_screen);
        fragmentManager = getSupportFragmentManager();

        if(savedInstanceState == null){
            fragmentManager.beginTransaction().replace(R.id.frameContainer, new Login_Fragment(), Utils.Login_Fragment).commit();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
