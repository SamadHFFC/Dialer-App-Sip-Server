package com.example.dialerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class SipManager<sipManager> extends AppCompatActivity {

    public SipManager manager = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sip_manager);



    }


    public SipManager sipManager = null;
//        if (sipManager == null) {
//        sipManager = SipManager.newInstance(this);
//    }

    private static SipManager newInstance(SipManager sipManager) {
            return null;
    }


}