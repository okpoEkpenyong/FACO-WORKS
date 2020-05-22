package com.facomobile.doctors.ui.auth.signup;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.facomobile.R;
import com.facomobile.utilities.InputUtils;
import com.google.android.material.snackbar.Snackbar;
import com.hbb20.CountryCodePicker;

public class SignUpActivity extends AppCompatActivity {

    private static final int PHONE_VERIFICATION_REQUEST_CODE = 1010;
    private InputUtils inputUtils;
    private View mRoot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mRoot = findViewById(R.id.root);

        EditText etFullname = findViewById(R.id.et_fullname),
                etPassword = findViewById(R.id.et_password),
                etPhoneNumber = findViewById(R.id.et_phone_number);
        ProgressBar pbWorking = findViewById(R.id.pb_working);
        Button btnSubmit = findViewById(R.id.btn_submit);

        CountryCodePicker ccp = findViewById(R.id.ccp);
        ccp.registerCarrierNumberEditText(etPhoneNumber);
        ccp.setOnCountryChangeListener(() -> new Handler().postDelayed(() -> {
            etPhoneNumber.requestFocus();
            InputMethodManager im = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            if (im != null) im.showSoftInput(etPhoneNumber, InputMethodManager.SHOW_IMPLICIT);
        }, 100));

        btnSubmit.setOnClickListener(view -> {
            if (isFormValid()) {
                inputUtils.disableInput();
                final String phoneNumber = ccp.getFullNumberWithPlus();
                startPhoneVerification(phoneNumber);
            }
        });

        inputUtils = InputUtils.init(pbWorking, ccp, etFullname, etPhoneNumber, etPassword, btnSubmit);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PHONE_VERIFICATION_REQUEST_CODE) {
            if (resultCode == RESULT_CANCELED)
                Snackbar.make(mRoot, getString(R.string.error_phone_number_verification), Snackbar.LENGTH_LONG).show();
            else if (resultCode == RESULT_OK) {
                CountryCodePicker ccp = findViewById(R.id.ccp);
                String phoneNumber = ccp.getFullNumberWithPlus();

                // rare instance of accessing Firebase from an Activity. If you find a better way
                // to do it, please implement. Done this way because WorkUtils.java has no
                // access to the Repository.
                /*String uid = mViewModel.getFirebaseUid();

                DoctorEntry userEntry = new DoctorEntry(uid == null ? "" : uid, "", phoneNumber, "");
                WorkUtils.scheduleUserDetailsUpdateWork(this, userEntry);
                mViewModel.insertNewUserInDatabase(userEntry);
                ImageUtils.fetchUserProfilePic(this);
                PreferenceUtils.setInternationalizationStatus(getApplicationContext(), true);
                WorkUtils.scheduleMessagingTokenUpdateWork(this);
                ActivityLauncher.launchPermissionsCheck(this);
                finish();*/

                Toast.makeText(this, "Good work bro!", Toast.LENGTH_SHORT).show();

            }
            inputUtils.enableInput();
        }
    }

    private void startPhoneVerification(String phoneNumber) {
        Intent intent = new Intent(this, PhoneVerification.class);
        intent.putExtra(PhoneVerification.EXTRA_PHONE_NUMBER, phoneNumber);
        startActivityForResult(intent, PHONE_VERIFICATION_REQUEST_CODE);
    }

    private boolean isFormValid() {
        boolean result = true;

        EditText etFullname = findViewById(R.id.et_fullname),
                etPassword = findViewById(R.id.et_password),
                etPhoneNumber = findViewById(R.id.et_phone_number);

        String fullName = etFullname.getText().toString();
        if (TextUtils.isEmpty(fullName)) {
            etFullname.setError("Please enter your full name");
            result = false;
        }

        CountryCodePicker ccp = findViewById(R.id.ccp);
        if (!ccp.isValidFullNumber()) {
            etPhoneNumber.setError("Please enter a valid phone number");
            result = false;
        }

        String password = etPassword.getText().toString();
        if (TextUtils.isEmpty(password)) {
            etPassword.setError("Please enter a valid password");
            result = false;
        } else if (password.length() < 6) {
            etPassword.setError("Passwords must be longer than 6 digits");
            result = false;
        }

        return result;
    }
}
