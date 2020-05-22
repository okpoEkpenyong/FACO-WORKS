package com.facomobile.doctors.ui.auth.signup;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.crashlytics.android.Crashlytics;
import com.facomobile.R;
import com.facomobile.utilities.InputUtils;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseNetworkException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.stargradegroup.vtuservices.cta.utilities.CustomDateUtils;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class PhoneVerification extends AppCompatActivity {

    public static final String EXTRA_PHONE_NUMBER = "phone_number";
    private static final String KEY_VERIFY_IN_PROGRESS = "key_verify_in_progress";
    private ViewGroup mRoot;
    private FirebaseAuth mAuth;

    private boolean mVerificationInProgress = false, shouldResendCode = true;
    private String mVerificationId, mPhoneNumber;
    private PhoneAuthProvider.ForceResendingToken mResendToken;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;

    private EditText etCode;
    private TextView tvInfo, tvResendCode, tvResendCodeTimer;
    private CountDownTimer cdtResendCode;
    private Button btnVerify;

    private InputUtils inputUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_verification);

        mPhoneNumber = getIntent().getStringExtra(EXTRA_PHONE_NUMBER);
        mRoot = findViewById(R.id.root);
        ProgressBar pbWorking = findViewById(R.id.pb_working);
        btnVerify = findViewById(R.id.btn_verify);
        tvInfo = findViewById(R.id.tv_info);
        tvResendCode = findViewById(R.id.tv_resend_code);
        tvResendCodeTimer = findViewById(R.id.tv_resend_code_timer);
        etCode = findViewById(R.id.et_code);

        cdtResendCode = new CountDownTimer(60000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                // adding one second to millisUntilFinished because CountdownTimer starts counting
                // immediately.
                long adjustedMillisUntilFinished = millisUntilFinished + 1000;
                String timeRemaining = CustomDateUtils.INSTANCE.getDigitalTime(adjustedMillisUntilFinished);
                if (adjustedMillisUntilFinished >= 1000) tvResendCodeTimer.setText(timeRemaining);
            }

            @Override
            public void onFinish() {
                tvResendCodeTimer.setText("");
                resolveResendCodeAccess(true);
                shouldResendCode = true;
            }
        };

        mAuth = FirebaseAuth.getInstance();

        // Initialize phone auth callbacks
        // [START phone_auth_callbacks]
        mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            @Override
            public void onVerificationCompleted(@NotNull PhoneAuthCredential credential) {
                // This callback will be invoked in two situations:
                // 1 - Instant verification. In some cases the phone number can be instantly
                //     verified without needing to send or enter a verification code.
                // 2 - Auto-retrieval. On some devices Google Play services can automatically
                //     detect the incoming verification SMS and perform verification without
                //     user action.
                mVerificationInProgress = false;
                tvInfo.setText(getString(R.string.phone_verification_code_sent));
                signInWithPhoneAuthCredential(credential);
            }

            @Override
            public void onVerificationFailed(@NotNull FirebaseException e) {
                // This callback is invoked in an invalid request for verification is made,
                // for instance if the the phone number format is not valid.
                // [START_EXCLUDE silent]
                mVerificationInProgress = false;
                // [END_EXCLUDE]
                Crashlytics.logException(e);

                if (e instanceof FirebaseTooManyRequestsException) {
                    // The SMS quota for the project has been exceeded
                    // [START_EXCLUDE]
                    Snackbar.make(mRoot, getString(R.string.error_creating_account_unknown),
                            Snackbar.LENGTH_LONG).show();
                    // [END_EXCLUDE]
                }
            }

            @Override
            public void onCodeSent(@NotNull String verificationId,
                                   @NotNull PhoneAuthProvider.ForceResendingToken token) {
                // The SMS verification code has been sent to the provided phone number, we
                // now need to ask the user to enter the code and then construct a credential
                // by combining the code with a verification ID.

                // Save verification ID and resending token so we can use them later
                mVerificationInProgress = false;
                mVerificationId = verificationId;
                mResendToken = token;
                tvInfo.setText(getString(R.string.phone_verification_code_sent));
                enableInput();
                etCode.requestFocus();
                if (shouldResendCode) resolveResendCodeAccess(true);
            }
        };
        // [END phone_auth_callbacks]


        btnVerify.setOnClickListener(view -> {
            try {
                String code = Objects.requireNonNull(etCode.getText()).toString();
                if (TextUtils.isEmpty(code)) etCode.setError("Please enter the verification code");
                else if (code.length() < 6) etCode.setError("Please enter a valid code");
                else {
                    disableInput();
                    verifyPhoneNumberWithCode(mVerificationId, code);
                }
            } catch (NullPointerException exception) {
                Crashlytics.logException(exception);
                Snackbar.make(mRoot, getString(R.string.error_unknown), Snackbar.LENGTH_SHORT).show();
            }
        });

        tvResendCode = findViewById(R.id.tv_resend_code);
        tvResendCode.setOnClickListener(view -> {
            if (shouldResendCode) {
                Toast.makeText(this, R.string.phone_verification_code_resent, Toast.LENGTH_LONG).show();
                resendVerificationCode(mPhoneNumber, mResendToken);
            } else {
                Snackbar.make(mRoot, getString(R.string.notif_code_sent), Snackbar.LENGTH_LONG).show();
            }
        });

        inputUtils = InputUtils.init(pbWorking, etCode, btnVerify);

        startPhoneNumberVerification(mPhoneNumber);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(KEY_VERIFY_IN_PROGRESS, mVerificationInProgress);
    }

    @Override
    protected void onRestoreInstanceState(@NotNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        mVerificationInProgress = savedInstanceState.getBoolean(KEY_VERIFY_IN_PROGRESS);
    }

    @Override
    public void onBackPressed() {
        Intent returnIntent = new Intent();
        setResult(RESULT_FIRST_USER, returnIntent);
        finish();
    }

    private void resolveResendCodeAccess(boolean isAllowed) {
        tvResendCode.setTextColor(ContextCompat.getColor(this,
                isAllowed ? android.R.color.white : R.color.grey_20));
        tvResendCode.setEnabled(isAllowed);
    }

    /**
     * Temporarily prevents user from changing parameters that are currently in use by an operation.
     * In this Activity, this means making the continue button ({@link this#btnVerify})
     * unresponsive to clicks, showing the progress bar on the button to
     * indicate that an operation is in progress, disabling input on the <em>verification code</em>
     * EditText, and preventing the <em>resend code</em> TextView
     * ({@link this#tvResendCode}) from being clicked.
     */
    private void disableInput() {
        inputUtils.disableInput();
        resolveResendCodeAccess(false);
    }

    /**
     * Reverses all actions performed by {@link this#disableInput()}
     * In this Activity, this means making the continue button ({@link this#btnVerify})
     * responsive to clicks, hiding the progress bar on the button to
     * indicate that no operation is in progress, enabling input on the <em>verification code</em>
     * EditText, and allowing the <em>resend code</em> TextView
     * ({@link this#tvResendCode}) to be clicked.
     */
    private void enableInput() {
        inputUtils.enableInput();
        if (shouldResendCode) resolveResendCodeAccess(true);
    }

    private void startPhoneNumberVerification(String phoneNumber) {
        // [START start_phone_auth]
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phoneNumber,        // Phone number to verify
                60,                 // Timeout duration
                TimeUnit.SECONDS,   // Unit of timeout
                this,               // Activity (for callback binding)
                mCallbacks);        // OnVerificationStateChangedCallbacks
        // [END start_phone_auth]

        mVerificationInProgress = true;
    }

    private void verifyPhoneNumberWithCode(String verificationId, String code) {
        // [START verify_with_code]
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId, code);
        // [END verify_with_code]
        signInWithPhoneAuthCredential(credential);
    }

    // [START resend_verification]
    private void resendVerificationCode(String phoneNumber,
                                        PhoneAuthProvider.ForceResendingToken token) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phoneNumber,        // Phone number to verify
                60,                 // Timeout duration
                TimeUnit.SECONDS,   // Unit of timeout
                this,               // Activity (for callback binding)
                mCallbacks,         // OnVerificationStateChangedCallbacks
                token);             // ForceResendingToken from callbacks
        etCode.setEnabled(false);
        etCode.setText("");
        resolveResendCodeAccess(false);
        shouldResendCode = false;
        cdtResendCode.start();
    }

    // [END resend_verification]

    // [START sign_in_with_phone]
    private void signInWithPhoneAuthCredential(final PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        enableInput();
                        Intent returnIntent = new Intent();
                        setResult(Activity.RESULT_OK, returnIntent);
                        finish();
                    } else {
                        // Sign in failed, display a message and update the UI
                        Exception exception = task.getException();
                        Crashlytics.logException(exception);
                        if (exception instanceof FirebaseAuthInvalidCredentialsException) {
                            // The verification code entered was invalid
                            Snackbar.make(mRoot, getString(R.string.error_incorrect_code), Snackbar.LENGTH_LONG).show();
                        } else if (exception instanceof FirebaseNetworkException) {
                            Snackbar.make(mRoot, getString(R.string.error_unable_to_connect), Snackbar.LENGTH_LONG).show();
                        }
                        enableInput();
                    }
                });
        // [END sign_in_with_phone]
    }
}