package com.facomobile.doctors.utilities;

import android.app.Activity;
import android.content.Intent;

import com.facomobile.doctors.ui.SplashActivity;
import com.facomobile.doctors.ui.auth.login.LoginActivity;
import com.facomobile.doctors.ui.auth.signup.SignUpActivity;
import com.facomobile.doctors.ui.init.InitActivity;
import com.facomobile.doctors.ui.main.MainActivity;

public class CustomNavUtils {
    public static void launchSplashScreen(Activity source) {
        Intent intent = new Intent(source, SplashActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        source.startActivity(intent);
    }

    public static void launchInitScreen(Activity source) {
        Intent intent = new Intent(source, InitActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        source.startActivity(intent);
    }

    public static void launchMainScreen(Activity source) {
        Intent intent = new Intent(source, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        source.startActivity(intent);
    }

    public static void launchLogin(Activity source) {
        Intent intent = new Intent(source, LoginActivity.class);
        source.startActivity(intent);
    }

    /*public static void launchForgotPassword(Activity source) {
        Intent intent = new Intent(source, ForgotPasswordActivity.class);
        source.startActivity(intent);
    }*/

    public static void launchSignUp(Activity source) {
        Intent intent = new Intent(source, SignUpActivity.class);
        source.startActivity(intent);
    }

    /*public static void launchSignUpFormGeneral(Activity source, String extra, Bundle form) {
        Intent intent = new Intent(source, SignUpFormGeneralActivity.class);
        intent.putExtra(SignUpMainActivity.SIGN_UP_TYPE, extra);
        if (form != null) {
            intent.putExtras(form);
            intent.putExtra("is_institution_individual_reg", true);
        }
        source.startActivity(intent);
    }*/

    /*public static void launchSignUpFormInstitution(Activity source, @NonNull Bundle form) {
        Intent intent = new Intent(source, SignUpFormInstitutionActivity.class);
        intent.putExtra(SignUpMainActivity.SIGN_UP_TYPE, SignUpMainActivity.SIGN_UP_TYPE_INSTITUTION);
        intent.putExtras(form);
        source.startActivity(intent);
    }*/

    /*public static void launchSignUpFormIndividual(Activity source, @NonNull Bundle form,
                                                  String registrationType) {
        Intent intent = new Intent(source, SignUpFormIndividualActivity.class);
        intent.putExtra(SignUpMainActivity.SIGN_UP_TYPE, registrationType);
        intent.putExtras(form);
        source.startActivity(intent);
    }*/

    /*public static void launchSignUpAgreement(Activity source, String extra, @NonNull Bundle form) {
        Intent intent = new Intent(source, SignUpAgreementActivity.class);
        intent.putExtra(SignUpMainActivity.SIGN_UP_TYPE, extra);
        intent.putExtras(form);
        source.startActivity(intent);
    }*/

    /*public static void launchProfilePicActivity(Activity source) {
        Intent intent = new Intent(source, ProfilePicActivity.class);
        source.startActivity(intent);
    }*/
}