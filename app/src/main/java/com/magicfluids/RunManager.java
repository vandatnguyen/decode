package com.magicfluids;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;

public class RunManager {
    static final String APP = "NUM_APP_RUNS";
    static final String LWP = "NUM_LWP_RUNS";

    public static int getTotalNumRuns(Context ctx) {
        return getNumRuns(ctx, APP) + getNumRuns(ctx, LWP);
    }

    public static int getNumAppRuns(Context ctx) {
        return getNumRuns(ctx, APP);
    }

    public static int getNumLwpRuns(Context ctx) {
        return getNumRuns(ctx, LWP);
    }

    private static int getNumRuns(Context ctx, String ID) {
        return ctx.getSharedPreferences("RunInfo", 0).getInt(ID, 0);
    }

    private static void askForRate(Context ctx) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(ctx);
        dialog.setTitle("Rate?");
        dialog.setMessage("Would you like to leave feedback about Magic Fluids in the Google Play store?");
        final Context ctxFinal = ctx;
        dialog.setPositiveButton(" Yes ", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
                ctxFinal.startActivity(new Intent("android.intent.action.VIEW").setData(Uri.parse("market://details?id=com.magicfluids.demo")));
            }
        });
        dialog.setNegativeButton(" Leave me alone! ", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private static void askForSubscribe(Context ctx) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(ctx);
        dialog.setTitle("Subscribe?");
        dialog.setMessage("Would you like to subscribe to our email newsletter?\n\nWe will occasionaly inform you about app updates and other stuff.\n\nWe won't share your email address with anyone.");
        final Context ctxFinal = ctx;
        dialog.setPositiveButton(" Subscribe ", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
                ctxFinal.startActivity(new Intent("android.intent.action.VIEW").setData(Uri.parse("https://lb.benchmarkemail.com//listbuilder/signupnew?om9I%252B75JX6HIbjW6bhqeAv5pwVnAjsSISlu1Syc0Xk%252FtO5iNRn8gS049TyW7spdJ")));
            }
        });
        dialog.setNegativeButton(" No, take a hike! ", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private static void askForFollow(Context ctx) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(ctx);
        dialog.setTitle("Follow?");
        dialog.setMessage("Would you like to follow us on social media?");
        final Context ctxFinal = ctx;
        dialog.setPositiveButton(" Follow on Instagram ", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
                ctxFinal.startActivity(new Intent("android.intent.action.VIEW").setData(Uri.parse("https://www.instagram.com/magicfluidsapp")));
            }
        });
        dialog.setNeutralButton(" Follow on Facebook ", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
                ctxFinal.startActivity(new Intent("android.intent.action.VIEW").setData(Uri.parse("https://www.facebook.com/madscientistgames")));
            }
        });
        dialog.setNegativeButton(" No, take a hike! ", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private static void afterGreetNotif(Context ctx) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(ctx);
        dialog.setTitle("HD Graphics");
        dialog.setMessage("Make sure to check out the HD Graphics option in the Animation settings tab!");
        dialog.setNeutralButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private static void greetDemo(Context ctx) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(ctx);
        dialog.setTitle("Welcome to Magic Fluids!");
        dialog.setMessage("This is a demo version of Magic Fluids. It is fully functional, but many configuration options are locked.\n\nYou can unlock some of the full-version configuration presets by watching video ads.\n\nIf you like the app, consider buying the full version and supporting an independent developer. :)");
        final Context ctxFinal = ctx;
        dialog.setPositiveButton(" Show me the full version ", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
                ctxFinal.startActivity(new Intent("android.intent.action.VIEW").setData(Uri.parse("market://details?id=com.magicfluids")));
            }
        });
        dialog.setNegativeButton(" Go away! ", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private static void greetFull(Context ctx) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(ctx);
        dialog.setTitle("Welcome to Magic Fluids!");
        dialog.setMessage("Thank you for purchasing Magic Fluids! Your support is very appreciated!");
        final Context ctxFinal = ctx;
        dialog.setPositiveButton(" Show me other Mad Scientist apps ", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
                ctxFinal.startActivity(new Intent("android.intent.action.VIEW").setData(Uri.parse("market://search?q=pub:Mad Scientist")));
            }
        });
        dialog.setNegativeButton(" I know! ", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private static int incrementNumRuns(Context ctx, String ID) {
        SharedPreferences prefs = ctx.getSharedPreferences("RunInfo", 0);
        int num = prefs.getInt(ID, 0) + 1;
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(ID, num);
        editor.commit();
        return num;
    }

    private static void checkAndAskForRate(Context ctx, String ID) {
        int numRuns = getNumRuns(ctx, ID);
        if (numRuns == 5 || numRuns == 25 || numRuns == 50 || numRuns == 100) {
            askForRate(ctx);
        }
        if (numRuns == 15) {
            askForSubscribe(ctx);
        }
        if (numRuns == 10) {
            askForFollow(ctx);
        }
    }

    public static void newAppRun(Context ctx) {
        if (incrementNumRuns(ctx, APP) != 1 || getNumRuns(ctx, LWP) < 1) {
        }
        checkAndAskForRate(ctx, APP);
    }

    public static void newLWPSettingsScreenRun(Context ctx) {
        if (incrementNumRuns(ctx, LWP) != 1 || getNumRuns(ctx, APP) < 1) {
        }
        checkAndAskForRate(ctx, LWP);
    }
}
