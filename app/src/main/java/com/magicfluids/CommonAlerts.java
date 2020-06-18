package com.magicfluids;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.magicfluids.demo.R;

public class CommonAlerts {
    public static void showInstallationFailed(Context ctx) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(ctx);
        dialog.setTitle("Installation failed!");
        dialog.setMessage("Magic Fluids installed incorrectly. Please reinstall the application.");
        final Context fctx = ctx;
        dialog.setPositiveButton(" OK ", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
                ((Activity) fctx).finish();
            }
        });
        dialog.show();
    }

    public static void showOkAlert(CharSequence title, CharSequence content, Context ctx) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(ctx);
        dialog.setTitle(title);
        dialog.setMessage(content);
        dialog.setPositiveButton(" OK ", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    public static void showHideMenuButton(final MainActivity ctx) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(ctx);
        dialog.setTitle("Menu button visibility");
        dialog.setMessage("What do you want to do?");
        MainActivity mainActivity = ctx;
        dialog.setPositiveButton("Hide the button completely", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                mainActivity.onMenuButtonVisibilityChanged(2);
                dialog.dismiss();
            }
        });
        dialog.setNegativeButton("Dim the button", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                mainActivity.onMenuButtonVisibilityChanged(1);
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    public static void showHelp(Context ctx) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(ctx);
        dialog.setTitle("Help - Common problems");
        dialog.setMessage(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf("1. I PAID FOR THE FULL VERSION BUT NOTHING CHANGED\n") + "Full and free version are two separate apps on your device. (One called 'Magic Fluids', the other 'Magic Fluids Free'). Make sure you're actually using the full version.\n\n") + "2. I'M ONLY SEEING BLACK SCREEN AND NOTHING HAPPENS\n") + "Try selecting a preset without extra effects (for example 'Crazy Colors'), and change the Paint quality setting to 'Lowest'. See if it works and let me know about the issue.\n\n") + "3. LIVE WALLPAPER SHOWS UP BLACK AND EMPTY\n") + "Try setting a different wallpaper, and then Magic Fluids wallpaper back again.\n\n") + "4. 'SET AS LIVE WALLPAPER' BUTTON DOESN'T WORK\n") + "Try using the official Google 'Wallpapers' app from the Google Play Store.\n\n") + "5. APP IS STUTTERING/LAGGING (ANIMATION ISN'T SMOOTH)\n") + "Try any of the following:\n- select a lower quality setting\n- pick a preset that doesn't use additional graphical effects, for example 'Crazy Colors'\n\n") + "6. I STILL HAVE A PROBLEM\n") + "Use the button below to write us an email and describe the issue you're having.\n");
        final Context ctxFinal = ctx;
        dialog.setPositiveButton(" Contact us ", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
                Intent emailIntent = new Intent("android.intent.action.SEND");
                emailIntent.setType("text/plain");
                emailIntent.putExtra("android.intent.extra.EMAIL", new String[]{"magicfluids@madscientist.pl"});
                ctxFinal.startActivity(Intent.createChooser(emailIntent, "Send email"));
            }
        });
        dialog.setNegativeButton(" Close ", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    public static void showMessageOk(Context ctx, CharSequence title, CharSequence message) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(ctx);
        dialog.setTitle(title);
        dialog.setMessage(message);
        Context context = ctx;
        dialog.setPositiveButton(" OK ", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    public static void showDetailTexturesInfo(Context ctx) {
        showMessageOk(ctx, "Textures info", "If you're using a very high fluid quality setting together with the HD Graphics option enabled, you might want to consider decreasing/disabling them when using fluid textures - the textures tend to look better on big masses of fluid, rather than on tiny little details.");
    }

    public static void showEffectsQualInfo(Context ctx) {
        showMessageOk(ctx, "Effects quality", "Controls the quality of glow, light and shadow settings (see 'Effects' tab).");
    }

    public static void showSimQualInfo(Context ctx) {
        showMessageOk(ctx, "Simulation resolution", "Changing resolution of the simulation changes the fluid behaviour to a degree. Presets are designed to look best on the Medium resolution, so for a typical use you don't want to change this property (unless your device can only handle the Low setting). High quality is fun to experiment with, but some input modes may work ");
    }

    public static void showKolazBuy(Activity act) {
        AlertDialog.Builder alertadd = new AlertDialog.Builder(act);
        alertadd.setTitle("See the full version?");
        View view = LayoutInflater.from(act).inflate(R.layout.image_dialog, null);
        ((TextView) view.findViewById(R.id.imageDialogText)).setText(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf("Unlock Radioactive Rumble, Transient Thoughts, Wizard Wand and many more!\n\n") + "Here's what you get in the full version of Magic Fluids:\n") + "- 50+ presets, with regular additions and updates\n") + "- access to all settings that were used to configure the presets\n") + "- design and save your own presets\n") + "- save screenshots\n") + "- no ads\n");
        alertadd.setView(view);
        final Context ctxFinal = act;
        alertadd.setPositiveButton(" See full version ", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
                ctxFinal.startActivity(new Intent("android.intent.action.VIEW").setData(Uri.parse("market://details?id=com.magicfluids")));
            }
        });
        alertadd.setNegativeButton(" Close ", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });
        alertadd.show();
    }
}
