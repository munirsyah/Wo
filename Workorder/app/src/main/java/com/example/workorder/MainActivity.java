package com.example.workorder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView bottomnav = findViewById(R.id.bottom_nav);
        bottomnav.setOnNavigationItemSelectedListener(navlist);

        Fragment selectedFragment = new FragmentHomeActivity();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                selectedFragment).commit();
        ;
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navlist =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment= null;

                    switch (item.getItemId()){
                        case R.id.home:
                            selectedFragment = new FragmentHomeActivity();
                            break;
                        case R.id.notif:
                            selectedFragment = new NotifikasiActivity();
                            break;
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            selectedFragment).commit();
                    return true;
                };
            };
}