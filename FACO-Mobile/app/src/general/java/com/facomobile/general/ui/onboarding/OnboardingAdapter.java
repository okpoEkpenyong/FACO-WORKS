package com.facomobile.doctors.ui.onboarding;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.crashlytics.android.Crashlytics;

public class OnboardingAdapter extends FragmentStatePagerAdapter {

    private final int numberOfFragments;

    OnboardingAdapter(FragmentManager fm, int numberOfFragments) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.numberOfFragments = numberOfFragments;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new OnboardingFragmentOne();
            case 1:
                return new OnboardingFragmentTwo();
            case 2:
                return new OnboardingFragmentThree();
            default:
                Crashlytics.logException(new IllegalArgumentException("The fragment at position " + position + " cannot be found"));
                return new OnboardingFragmentOne();
        }
    }

    @Override
    public int getCount() {
        return numberOfFragments;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return OnboardingFragmentOne.TITLE;
            case 1:
                return OnboardingFragmentTwo.TITLE;
            case 2:
                return OnboardingFragmentThree.TITLE;
            default:
                Crashlytics.logException(new IllegalArgumentException("The fragment at position " + position + " cannot be found"));
                return "";
        }
    }
}
