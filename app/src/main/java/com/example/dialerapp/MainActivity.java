package com.example.dialerapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.Manifest;
import android.app.PendingIntent;
import android.content.Intent;
import android.net.ParseException;
import android.net.sip.SipAudioCall;
import android.net.sip.SipException;
import android.net.sip.SipManager;
import android.net.sip.SipProfile;
import android.net.sip.SipRegistrationListener;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Patterns;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.net.Uri;
import android.text.method.LinkMovementMethod;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.karumi.dexter.listener.single.PermissionListener;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Button button;
//    private Button button1;

    Button blogin;

    EditText etFirstName, etPassword;

    // one boolean variable to check whether all the text fields
    // are filled by the user, properly or not.
    boolean isAllFieldsChecked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // register buttons with their proper IDs.
        blogin = findViewById(R.id.login);


        // register all the EditText fields with their IDs.
        etFirstName = findViewById(R.id.username);
        etPassword = findViewById(R.id.password);

        // redirect to the register Page
        button = findViewById(R.id.button1);
        button.setMovementMethod(LinkMovementMethod.getInstance());

        // handle the PROCEED button
        blogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // store the returned value of the dedicated function which checks
                // whether the entered data is valid or if any fields are left blank.
                isAllFieldsChecked = CheckAllFields();

                // the boolean variable turns to be true then
                // only the user must be proceed to the activity2
                if (isAllFieldsChecked) {
                        Intent i = new Intent(MainActivity.this, login.class);
                        startActivity(i);

                }
            }
        });

        // if user presses the cancel button then close the
        // application or the particular activity.
//        bCancel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                MainActivity.this.finish();
//                System.exit(0);
//            }
//        });
    }

    // function which checks all the text fields
    // are filled or not by the user.
    // when user clicks on the PROCEED button
    // this function is triggered.
    private boolean CheckAllFields() {
        if (etFirstName.length() == 0) {
            etFirstName.setError("Please Enter Valid User Name");
            return false;
        }

        if (etPassword.length() == 0) {
            etPassword.setError("Please Enter Valid Password");
            return false;
        } else if (etPassword.length() < 8) {
            etPassword.setError("Password must be minimum 8 characters");
            return false;
        }

        // after all validation return true.
        return true;
    }

    public String sipAddress = null;
    public SipManager manager = null;
    public SipProfile me = null;
    public SipAudioCall call = null;

    public void initializeManager() {

        // Initialize manager

        if(manager == null) {
            manager = SipManager.newInstance(this);
        }

        try {
            // Build the SIP profile

            SipProfile.Builder builder = new SipProfile.Builder("786000009", "max-fon.dyndns.org");
            builder.setPassword("786000009");
            me = builder.build();

            // Register a pending intent for incoming calls

            Intent i = new Intent();
            i.setAction("android.SipDemo.INCOMING_CALL");
            PendingIntent pi = PendingIntent.getBroadcast(this, 0, i, Intent.FILL_IN_DATA);
            manager.open(me, pi, null);

            // Send registration requests

            manager.setRegistrationListener(me.getUriString(),
                    new SipRegistrationListener() {
                        public void onRegistering(String localProfileUri) {
                            // Registering with SIP Server...

                        }

                        public void onRegistrationDone(String localProfileUri, long expiryTime) {
                            // Ready

                        }

                        public void onRegistrationFailed(String localProfileUri, int errorCode, String errorMessage) {
                            // Registration failed.  Check SIP details

                        }
                    });
        } catch (ParseException pe) {
            // Connection error

        } catch (SipException se) {
            // Connection error

        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }
    }

//    public void SetValidation() {
//
//        // Check for a valid password.
//        if (password.getText().toString().isEmpty()) {
//            password.setError(getResources().getString(R.string.password_error));
//            isPasswordValid = false;
//        } else if (password.getText().length() < 6) {
//            password.setError(getResources().getString(R.string.error_invalid_password));
//            isPasswordValid = false;
//        } else  {
//            isPasswordValid = true;
//        }
//        if (isEmailValid && isPasswordValid) {
//            Toast.makeText(getApplicationContext(), "Successfully", Toast.LENGTH_SHORT).show();
//        }
//
//    }




}
