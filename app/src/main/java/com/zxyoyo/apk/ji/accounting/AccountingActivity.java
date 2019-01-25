package com.zxyoyo.apk.ji.accounting;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.zxyoyo.apk.ji.R;

public class AccountingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fl_container,new AccountingFragment())
                .commit();

    }
}
