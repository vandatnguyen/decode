package com.magicfluids;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.widget.FrameLayout;
import android.widget.TextView;

public class PolicyManager {
    static final String POLICY_CONSENT_ID = "Consent";
    static final String PREF_FILE_NAME = "PolicyData";
    /* access modifiers changed from: private */
    public boolean adMgrInitialized = false;

    private static int getIntValue(Context ctx, String ID) {
        return ctx.getSharedPreferences(PREF_FILE_NAME, 0).getInt(ID, 0);
    }

    /* access modifiers changed from: private */
    public static void setIntValue(Context ctx, String ID, int value) {
        SharedPreferences.Editor editor = ctx.getSharedPreferences(PREF_FILE_NAME, 0).edit();
        editor.putInt(ID, value);
        editor.commit();
    }

    @SuppressLint("WrongConstant")
    private void showAcceptPolicy(final Activity ctx) {
        AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
        builder.setCancelable(false);
        builder.setTitle("First things first");
        FrameLayout layout = new FrameLayout(ctx);
        FrameLayout.LayoutParams layoutparams = new FrameLayout.LayoutParams(-1, -2, 17);
        layoutparams.setMargins(16, 16, 16, 16);
        layout.setLayoutParams(layoutparams);
        TextView message = new TextView(ctx);
        SpannableString s = new SpannableString(String.valueOf(String.valueOf("This app is supported by advertisements in the settings screen. By pressing 'I Accept', you consent to our Privacy Policy, which allows us to show ads.\n\n") + "You can read it here: http://madscientist.pl/magicfluids/policy.html\n\n") + "You can decline and keep using the app in the default configuration.\n");
        Linkify.addLinks(s, 1);
        message.setText(s);
        message.setMovementMethod(LinkMovementMethod.getInstance());
        layout.addView(message, layoutparams);
        builder.setView(layout);
        final Context ctxFinal = ctx;
        builder.setPositiveButton(" I Accept ", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                PolicyManager.setIntValue(ctxFinal, PolicyManager.POLICY_CONSENT_ID, 1);
                PolicyManager.this.adMgrInitialized = true;
            }
        });
        builder.setNegativeButton(" I Decline ", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                ctx.onBackPressed();
            }
        });
        builder.create().show();
    }

    public void updateOnRun(Activity ctx) {
        if (getIntValue(ctx, POLICY_CONSENT_ID) != 0) {
            this.adMgrInitialized = true;
        }
    }

    public void updateOnOpenSettings(Activity ctx) {
        if (!this.adMgrInitialized && getIntValue(ctx, POLICY_CONSENT_ID) == 0) {
            showAcceptPolicy(ctx);
        }
    }
}
