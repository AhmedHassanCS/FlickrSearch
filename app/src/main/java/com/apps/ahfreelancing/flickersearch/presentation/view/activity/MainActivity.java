package com.apps.ahfreelancing.flickersearch.presentation.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.Navigation;

import android.os.Bundle;

import com.apps.ahfreelancing.flickersearch.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onNavigateUp() {
        return Navigation.findNavController(this, R.id.fragment).navigateUp();

    }
}
