package com.example.utsavstha.feedapp.utils;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatCallback;
import android.view.View;

import com.example.utsavstha.feedapp.callBacks.ApplicationCallBacks;

/**
 * Created by utsavstha on 7/9/17.
 */

public class UIUtils {

    public static void showSnackbar(View view, String message, int length, String actionName,
                                    final ApplicationCallBacks.SnackBar snackBar) {
        Snackbar.make(view, message, length).setAction(actionName, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                snackBar.onClick();
            }
        });
    }
}
