package com.facomobile.utilities;

public class FragmentUtils {

    /*public static final int FRAGMENT_CHANGE = 1;
    public static final int FRAGMENT_PROFILE = 2;
    private static final String LOG_TAG = FragmentUtils.class.getSimpleName();

    public static void addChangeFragment(FragmentManager fm, int fragmentContainerId, int foregroundFragment) {
        ChangeFragment changeFragment = new ChangeFragment();
        addFragment(fm, changeFragment, fragmentContainerId, foregroundFragment);
    }

    public static void addProfileFragment(FragmentManager fm, int fragmentContainerId, int foregroundFragment) {
        ProfileFragment profileFragment = new ProfileFragment();
        addFragment(fm, profileFragment, fragmentContainerId, foregroundFragment);
    }

    private static void addFragment(FragmentManager fm, Fragment fragment, int fragmentContainerId, int foregroundFragment) {
        switch (foregroundFragment) {
            case -1:
                addNewFragment(fm, fragment, fragmentContainerId);
                if (fragment instanceof ChangeFragment) foregroundFragment = FRAGMENT_CHANGE;
                else if (fragment instanceof ProfileFragment)
                    foregroundFragment = FRAGMENT_PROFILE;
                break;
            case FRAGMENT_CHANGE:
                if (!(fragment instanceof ChangeFragment)) {
                    replaceFragment(fm, fragment, fragmentContainerId);
                    if (fragment instanceof ProfileFragment)
                        foregroundFragment = FRAGMENT_PROFILE;
                }
                break;
            case FRAGMENT_PROFILE:
                if (!(fragment instanceof ProfileFragment)) {
                    replaceFragment(fm, fragment, fragmentContainerId);
                    if (fragment instanceof ChangeFragment)
                        foregroundFragment = FRAGMENT_CHANGE;
                }
                break;
        }
    }

    private static void addNewFragment(FragmentManager fm, Fragment fragment, int fragmentContainerId) {
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(fragmentContainerId, fragment).commit();
    }

    private static void replaceFragment(FragmentManager fm, Fragment fragment, int fragmentContainerId) {
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(fragmentContainerId, fragment).commit();
    }*/

}
