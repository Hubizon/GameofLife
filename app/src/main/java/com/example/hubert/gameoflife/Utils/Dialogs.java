package com.example.hubert.gameoflife.Utils;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;

import com.example.hubert.gameoflife.MainActivity;
import com.example.hubert.gameoflife.R;

public class Dialogs {

    public static void showDialogWithChoose(final SharedPreferences sharedPref, final Context context, final String title, final String message, final int whichOneEvent)
    {
        final SharedPreferences.Editor editor = sharedPref.edit();

        AlertDialog.Builder dialog = new AlertDialog.Builder(context, R.style.Theme_AppCompat_Light_Dialog_Alert);
        dialog.setTitle(title)
                //.setIcon(R.drawable.ic_launcher)
                .setMessage(message)
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialoginterface, int i) {
                        switch (whichOneEvent)
                        {
                            case 1:
                                //Die();
                                break;
                        }
                        dialoginterface.cancel();
                        //TODO: start timer
                    }})
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialoginterface, int i) {
                        switch (whichOneEvent)
                        {
                            case 1:
                                if(sharedPref.getInt(context.getResources().getString(R.string.saved_character_money_key), SharedPreferencesDefaultValues.DefaultMoney) >= 15000)
                                    dialoginterface.cancel();
                                else
                                   // Die();
                                editor.apply();
                                dialoginterface.cancel();
                                break;

                            case 2:
                                editor.putInt(context.getResources().getString(R.string.saved_character_money_key), (sharedPref.getInt(context.getResources().getString(R.string.saved_character_money_key), SharedPreferencesDefaultValues.DefaultMoney) + 25000));
                                editor.apply();
                                dialoginterface.cancel();
                                break;

                            default:
                                dialoginterface.cancel();
                                break;
                        }
                        //TODO: Michal!!! start timer
                    }
                }).show();
    }

    public static void showAlertDialog(Context context, String title, final String message)
    {
        AlertDialog.Builder dialog = new AlertDialog.Builder(context, R.style.Theme_AppCompat_Light_Dialog_Alert);
        dialog.setTitle(title)
                //.setIcon(R.drawable.ic_launcher)
                .setMessage(message)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialoginterface, int i) {
                        dialoginterface.cancel();
                    }
                })
                .setCancelable(false)
                .show();
    }

    public static void showResumeDialog(Context context, final MenuItem item, final Runnable runnable) {
        Dialog resumeDialog = new Dialog(context) {
            @Override
            public boolean onTouchEvent(@NonNull MotionEvent event) {
                this.dismiss();
                MainActivity.onResumeDialogClicked(item, runnable);
                return true;
            }
        };
        resumeDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        resumeDialog.setContentView(R.layout.dialog_stop);
        final Window window = resumeDialog.getWindow();
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        window.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        window.setBackgroundDrawableResource(R.color.translucent_black);
        resumeDialog.show();
    }

}