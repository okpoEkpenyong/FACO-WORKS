package com.facomobile.doctors.ui.auth.signup;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.bumptech.glide.Glide;
import com.facomobile.R;
import com.facomobile.utilities.InputUtils;
import com.facomobile.views.CustomFilePicker;
import com.github.chrisbanes.photoview.PhotoView;
import com.google.android.material.snackbar.Snackbar;
import com.hbb20.CountryCodePicker;

public class SignUpActivity extends AppCompatActivity {

    private static final int PHONE_VERIFICATION_REQUEST_CODE = 1010;
    private static final int PICKFILE_RESULT_CODE = 1002;
    private InputUtils inputUtils;
    private View mRoot;
    EditText etFirstName, etLastName, etOtherNames, etEmail, etPassword, etConfirmPassword,
            etPhoneNumber, etDoctorType, etLicenseNumber;
    private CustomFilePicker cfpDegreePhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mRoot = findViewById(R.id.root);

        etFirstName = findViewById(R.id.et_first_name);
        etLastName = findViewById(R.id.et_last_name);
        etOtherNames = findViewById(R.id.et_other_names);
        etEmail = findViewById(R.id.et_email);
        etPassword = findViewById(R.id.et_password);
        etConfirmPassword = findViewById(R.id.et_confirm_password);
        etPhoneNumber = findViewById(R.id.et_phone_number);
        etDoctorType = findViewById(R.id.et_doctor_type);
        etLicenseNumber = findViewById(R.id.et_license_number);
        ProgressBar pbWorking = findViewById(R.id.pb_working);
        Button btnContinue = findViewById(R.id.btn_continue);

        CountryCodePicker ccp = findViewById(R.id.ccp);
        ccp.registerCarrierNumberEditText(etPhoneNumber);
        ccp.setOnCountryChangeListener(() -> new Handler().postDelayed(() -> {
            etPhoneNumber.requestFocus();
            InputMethodManager im = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            if (im != null) im.showSoftInput(etPhoneNumber, InputMethodManager.SHOW_IMPLICIT);
        }, 100));

        btnContinue.setOnClickListener(view -> {
            if (isFormValid()) {
                inputUtils.disableInput();
                final String phoneNumber = ccp.getFullNumberWithPlus();
                startPhoneVerification(phoneNumber);
            }
        });

        setupDegreePhotoPicker();

        inputUtils = InputUtils.init(etFirstName, etLastName, etOtherNames, etEmail, etPassword,
                etConfirmPassword, etPhoneNumber, etDoctorType, etLicenseNumber, pbWorking, ccp,
                btnContinue, cfpDegreePhoto);
    }

    private void setupDegreePhotoPicker() {
        cfpDegreePhoto = findViewById(R.id.cfp_degree_photo);
        cfpDegreePhoto.setPickAction(() -> {
            Intent chooseFile = new Intent(Intent.ACTION_GET_CONTENT);
            chooseFile.setType("image/*");
            chooseFile = Intent.createChooser(chooseFile, "Select degree photo");
            startActivityForResult(chooseFile, PICKFILE_RESULT_CODE);
        });
        cfpDegreePhoto.setPreviewAction(() -> {
            DegreePhotoFragment degreePhotoFragment = new DegreePhotoFragment();
            Bundle bundle = new Bundle();
            bundle.putString("uid", cfpDegreePhoto.getDegreePhotoUri().toString());
            degreePhotoFragment.setArguments(bundle);
            degreePhotoFragment.show(getSupportFragmentManager(), "dialog");
        });
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
        } else if (requestCode == PICKFILE_RESULT_CODE) {
            if (resultCode == -1) {
                Uri fileUri = data.getData();
                if (fileUri != null) {
                    cfpDegreePhoto.setDegreePhotoUri(fileUri);
                }
            }
        }
    }

    private void startPhoneVerification(String phoneNumber) {
        Intent intent = new Intent(this, PhoneVerification.class);
        intent.putExtra(PhoneVerification.EXTRA_PHONE_NUMBER, phoneNumber);
        startActivityForResult(intent, PHONE_VERIFICATION_REQUEST_CODE);
    }

    private boolean isFormValid() {
        boolean result = true;

        EditText etFullname = findViewById(R.id.et_first_name),
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

    public static class DegreePhotoFragment extends DialogFragment {
        PhotoView photoView;

        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.degree_photo_dialog, container, false);
            photoView = view.findViewById(R.id.img_profile_pic);
            return view;
        }

        @Override
        public void onStart() {
            Bundle bundle = getArguments();
            if (bundle != null) {
                String filePath = bundle.getString("uid");
                if (!TextUtils.isEmpty(filePath)) {
                    Glide.with(getContext())
                            .load(Uri.parse(filePath))
                            .placeholder(R.drawable.default_profile_pic)
                            .into(photoView);
                }
            }
            super.onStart();
        }

        @NonNull
        @Override
        public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
            Dialog dialog = super.onCreateDialog(savedInstanceState);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            return dialog;
        }
    }
}
