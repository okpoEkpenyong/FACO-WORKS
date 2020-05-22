package com.facomobile.doctors.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;

import com.facomobile.R;
import com.facomobile.doctors.data.AppRepository;
import com.facomobile.doctors.utilities.CustomNavUtils;
import com.facomobile.doctors.utilities.PreferenceUtils;
import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.ErrorCodes;
import com.firebase.ui.auth.IdpResponse;
import com.google.android.material.snackbar.Snackbar;
import com.stargradegroup.vtuservices.cta.utilities.InjectorUtils;

import java.util.Arrays;


public class SplashActivity extends AppCompatActivity {

    private static final int RC_SIGN_IN = 1001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        TextView tvTheme = findViewById(R.id.tv_theme);
        tvTheme.setText(Html.fromHtml("<span style=\"color: #FF6363\">FIGHT</span> <span style=\"color: #FDE546\">AGAINST</span> <span style=\"color: #53E265\">CORONA</span>"));

        setupStatusBar();
        initComponent();
    }

    private void initComponent() {
        final boolean isOnboarded = PreferenceUtils.INSTANCE.getOnboardingStatus(this);
        if (isOnboarded) {
            AppRepository repository = InjectorUtils.INSTANCE.provideRepository(this);
            AUTH_STATUS authStatus = repository.getUserAuthStatus();
            switch (authStatus) {
                case SIGNED_OUT:
                    new Handler().postDelayed(this::launchAuthUI, 3000);
                    break;
                case SIGNED_IN:
                    new Handler().postDelayed(() ->
                            CustomNavUtils.launchMainScreen(this), 3000);
                    break;
            }
        } else {
            new Handler().postDelayed(() ->
                    CustomNavUtils.launchOnboarding(this), 3000);
        }
    }

    private void launchAuthUI() {
        startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setLogo(R.drawable.faco_logo_large)
                        .setAvailableProviders(Arrays.asList(
                                new AuthUI.IdpConfig.GoogleBuilder().build(),
                                new AuthUI.IdpConfig.EmailBuilder().build(),
                                new AuthUI.IdpConfig.PhoneBuilder().build()))
                        .build(),
                RC_SIGN_IN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // RC_SIGN_IN is the request code you passed into startActivityForResult(...) when starting the sign in flow.
        if (requestCode == RC_SIGN_IN) {
            IdpResponse response = IdpResponse.fromResultIntent(data);

            // Successfully signed in
            if (resultCode == RESULT_OK) {
                CustomNavUtils.launchMainScreen(this);
            } else {
                // Sign in failed
                if (response == null) {
                    launchAuthUI();
                    return;
                }

                View root = findViewById(R.id.root);
                if (response.getError().getErrorCode() == ErrorCodes.NO_NETWORK) {
                    Snackbar.make(root, getString(R.string.error_network_connection),
                            Snackbar.LENGTH_LONG).show();
                    new Handler().postDelayed(this::launchAuthUI, 2000);
                    return;
                }

                Snackbar.make(root, getString(R.string.error_unknown),
                        Snackbar.LENGTH_LONG).show();
                new Handler().postDelayed(this::launchAuthUI, 2000);
            }
        }
    }

    private void setupStatusBar() {
        View mRoot = findViewById(R.id.root);
        mRoot.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);
        ViewCompat.setOnApplyWindowInsetsListener(mRoot, (v, insets) -> {
            mRoot.setPadding(mRoot.getPaddingLeft(),
                    mRoot.getPaddingTop() + insets.getSystemWindowInsetTop(),
                    mRoot.getPaddingRight(), mRoot.getPaddingBottom() + insets.getSystemWindowInsetBottom());
            return insets;
        });
    }

    public enum AUTH_STATUS {SIGNED_OUT, SIGNED_IN}
}