package com.example.moodindigo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

import com.example.moodindigo.Login.Login_Fragment;
import com.example.moodindigo.Login.Utils;

public class HomeScreenActivity extends AppCompatActivity {
    private  static FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
