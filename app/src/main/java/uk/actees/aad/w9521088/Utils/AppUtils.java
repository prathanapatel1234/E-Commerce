package uk.actees.aad.w9521088.Utils;

import android.app.Activity;
import android.content.Context;
import android.view.inputmethod.InputMethodManager;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import uk.actees.aad.w9521088.R;


public class AppUtils {

    public static void goNextFragmentReplace(Context context, Fragment fragment) {
        FragmentTransaction fragmentTransaction = ((FragmentActivity) context).getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.FrameLayout, fragment);
        fragmentTransaction.commit();
    }

    public static void goNextFragmentAdd(Context context, Fragment fragment) {
        FragmentTransaction fragmentTransaction = ((FragmentActivity) context).getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.FrameLayout, fragment);
        fragmentTransaction.commit();
    }

    public static void goFragmentReplaceWithBackStack(Context context, Fragment fragment) {
        FragmentTransaction fragmentTransaction = ((FragmentActivity) context).getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.FrameLayout, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    public static void goFragmentAddWithoutBackStack(Context context, Fragment fragment) {

        FragmentTransaction fragmentTransaction = ((FragmentActivity) context).getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.FrameLayout, fragment);
        fragmentTransaction.addToBackStack(null);
        //fragmentTransaction.commit();
        fragmentTransaction.commitAllowingStateLoss();
    }

    public static void goFragmentReplaceWithoutBackStack(Context context, Fragment fragment) {

        FragmentTransaction fragmentTransaction = ((FragmentActivity) context).getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.FrameLayout, fragment);
        fragmentTransaction.addToBackStack(null);
        //fragmentTransaction.commit();
        fragmentTransaction.commitAllowingStateLoss();
    }

    public static void hideSoftKeyboard(Activity activity) {
        final InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        if (inputMethodManager.isActive()) {
            if (activity.getCurrentFocus() != null) {
                inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
            }
        }
    }

}
