package com.magicfluids;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;

public class VersionManager {
    private static int getLastVersionCode(Context context) {
        return context.getSharedPreferences("Version", 0).getInt("VERSION", 1);
    }

    private static void setVersionCode(Context context, int versionCode) {
        SharedPreferences.Editor editor = context.getSharedPreferences("Version", 0).edit();
        editor.putInt("VERSION", versionCode);
        editor.commit();
    }

    private static void show11Changes(Context ctx) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(ctx);
        dialog.setTitle("Magic Fluids updated!");
        dialog.setMessage("Changelog for version 1.1:\n- Added framerate limit option for battery saving\n- Removed highest quality setting, since no one was able to use it anyway\n\nFollowing changes concern full version:\n- Added automatic drawing sources - it's great for Live Wallpaper!\n- Added two new presets that show automatic drawing in action\n- Fixed bug with saved images showing in Gallery with delay");
        dialog.setPositiveButton(" OK ", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private static void show111Changes(Context ctx) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(ctx);
        dialog.setTitle("Magic Fluids updated!");
        dialog.setMessage("This is a minor update that is supposed to fix stability issues with Live Wallpaper mode. If you had problems with Live Wallpaper before, please let me know whether it helped or not in comments or on the support email address.\n\nUpdate with new features is coming soon!");
        dialog.setPositiveButton(" OK ", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private static void show12Changes(Context ctx) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(ctx);
        dialog.setTitle("Magic Fluids updated!");
        dialog.setMessage("This update includes two new features:\n- Color inversion - gives really interesting color effects and kind of allows to change background color\n- Gravity - makes fluid move when you tilt the device\n\nThere are two new presets that show the effects in action. They're available in both free and paid version, but settings that modify new effects (which are located in new \"Effects\" tab) are unlocked in paid version only.\n\nAdditionaly, a few bugs were fixed, so hopefully the app will be more stable now.");
        dialog.setPositiveButton(" OK ", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private static void show14Changes(Context ctx) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(ctx);
        dialog.setTitle("Magic Fluids updated!");
        dialog.setMessage("New features:\n- Two new color modes\n- Five new presets\n- Cool option that allows fluid to cross the edges of the screen\n- Improved graphics\n\nCheck out presets to see the new features in action!");
        dialog.setPositiveButton(" OK ", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private static void show15Changes(Context ctx) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(ctx);
        dialog.setTitle("Menu button added");
        dialog.setMessage("To comply with Android design guidelines, an on-screen menu button has been added to replace the system menu button.\n - if your device has a hardware menu button, it will still work too\n - if the button bothers you, there is a menu option to hide it\n - this does not apply to live wallpaper mode\n\nThe user interface has been slightly updated as well.\n\nIf something's not working properly, please let me know!");
        dialog.setPositiveButton(" OK ", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private static void show153Changes(Context ctx) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(ctx);
        dialog.setTitle("Magic Fluids updated!");
        dialog.setMessage("There is a new Glow option in the Effects tab. It's pretty cool!\nCheck out a new Glowing Glare preset to see it in action.");
        dialog.setPositiveButton(" OK ", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private static void show154Changes(Context ctx) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(ctx);
        dialog.setTitle("Magic Fluids updated!");
        dialog.setMessage("The bug with a weird text in the settings window has been fixed (hopefully). Let me know whether the update helps (magicfluids@madscientist.pl).");
        dialog.setPositiveButton(" OK ", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private static void show155Changes(Context ctx) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(ctx);
        dialog.setTitle("Magic Fluids updated!");
        dialog.setMessage("HD GRAPHICS is a new EXPERIMENTAL feature that allows to simulate the fluid with much more detail. You can find it in the ANIMATION tab of the settings screen.");
        dialog.setPositiveButton(" OK ", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private static void show156Changes(Context ctx) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(ctx);
        dialog.setTitle("A tiny update");
        dialog.setMessage("This update is not very exciting, but hopefully it fixes at least some of the bugs you may have been suffering from. But stay tuned for the next, meaty update!");
        dialog.setPositiveButton(" OK ", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private static void show160Changes(Context ctx) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(ctx);
        dialog.setTitle("Update! Textures!");
        dialog.setMessage("Fluids with textures look really cool! You get a new preset that shows the effect in action. Check out the screenshots on the app's market page to see what you can do with it in the full app!");
        dialog.setPositiveButton(" OK ", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private static void show161Changes(Context ctx) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(ctx);
        dialog.setTitle("Update!");
        dialog.setMessage("- One new preset demonstrating fluid textures feature (Lake of Lava)\n- Some new settings are now unlocked\n- A small ad banner is now displayed at the bottom of the settings screen. I've tried to make it as unobtrusive as possible. If you find it terrible, let me know.");
        dialog.setPositiveButton(" OK ", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private static void showOkMessage(CharSequence title, CharSequence content, Context ctx) {
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

    private static void show163Changes(Context ctx, String firstPart) {
        showOkMessage("Update!", String.valueOf(firstPart) + "- You can now apply settings directly from the app to live wallpaper! (\"Apply settings to live wallpaper\" button in the app mode settings screen.)\n", ctx);
    }

    private static void show163ChangesFull(Context ctx) {
        show163Changes(ctx, "- 4 new cool presets added!\n- New 'Invert Colors' option in the 'Paint' settings tab\n");
    }

    private static void show163ChangesFree(Context ctx) {
        show163Changes(ctx, "- One new preset unlocked! (\"Crazy Colors\")\n- You can now unlock some of the full-version presets by watching a video ad!\n");
    }

    private static void show164ChangesFree(Context ctx) {
        showOkMessage("Magic Fluids updated!", "A new preset can now be unlocked by watching a video ad: Grainy Greatness!\n", ctx);
    }

    private static void show164ChangesFull(Context ctx) {
        showOkMessage("Magic Fluids updated!", "A new cool preset is now available: Grainy Greatness!\n", ctx);
    }

    private static void show165ChangesFree(Context ctx) {
        showOkMessage("Big update!", "- 2 new presets (one unlockable)\n- Quick sources option: autoplay on each app open / live wallpaper display\n- Updates to performance and stabiliy\n- More presets and settings in the full version\n", ctx);
    }

    private static void show165ChangesFull(Context ctx) {
        showOkMessage("Big update!", "- 3 new presets\n- New settings (Momentum lifetime, Swirliness, Quick sources, Glow intensity\n- Updates to performance and stabiliy\n", ctx);
    }

    private static void show166ChangesFree(Context ctx) {
        showOkMessage("Big update!", "- 3 new presets (one free, two unlockable)\n- New cool light & shadow effects (adjustable in the full version)\n- Updates to performance", ctx);
    }

    private static void show166ChangesFull(Context ctx) {
        showOkMessage("Big update!", "- 3 new presets\n- New cool light & shadow effects\n- New 'Fill screen' particles mode\n- Updates to performance", ctx);
    }

    private static void show168ChangesFree(Context ctx) {
        showOkMessage("Update!", "- Cosmic Charm - a new (unlockable) preset\n- Life of Lights preset updated\n- Glow & light effects modified and improved", ctx);
    }

    private static void show168ChangesFull(Context ctx) {
        showOkMessage("Update!", "- 2 new presets\n- New 'Inverse shadow' setting\n- Life of Lights preset updated\n- Glow & light effects modified and improved", ctx);
    }

    private static void show169ChangesFree(Context ctx) {
        showOkMessage("Big update!", "- Input modes! (see menu under the new 'hand' button)\n - 3 new presets (2 unlockable)\n- Quality settings are now divided differently, allowing for better control of performance. Adjust them in the 'Animation' tab", ctx);
    }

    private static void show169ChangesFull(Context ctx) {
        showOkMessage("Big update!", "- Input modes! (see menu under the new 'hand' button)\n- 5 new presets\n- Quality settings are now divided differently, allowing for better control of performance. Adjust them in the 'Animation' tab\n- Many small changes, including the 'Background color mode' support for Glow & Light effects", ctx);
    }

    private static void show180ChangesFull(Context ctx) {
        showOkMessage("Update!", "Magic Fluids just got a big update: it has a number of new features, but it also changed and optimized how things work 'under the hood'. Because of that, please don't hesitate to contact me if you uncover any issues.\n\nOn a separate note, your own presets may now look quite a bit different, especially if they make a heavy use of glow, which now has a new algorithm. If this is the case, please try adjusting the glow intensity settings (Near, Mid, Far) - you should be able to get a desired look with just a bit of tuning.\n\nHere's a list of new features:\n- 8 new presets\n- A 3D effect (see 'Effects' tab)\n- A new color mode: Color scale\n- Color saturation can now be adjusted for random colors", ctx);
    }

    private static void show180ChangesFree(Context ctx) {
        showOkMessage("Update!", "Magic Fluids just got a big update: it has a number of new features, but it also changed and optimized how things work 'under the hood'. Please don't hesitate to contact me if you uncover any issues.\n\nHere's a list of new features:\n- 8 new presets (2 free, 5 unlockable)\n - improved gravity setting\n- A 3D effect (adjustable in the full version)", ctx);
    }

    private static void showNewHelpAndSupport(Context ctx) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(ctx);
        dialog.setTitle("Need help with the app?");
        dialog.setMessage(String.valueOf(String.valueOf("There's a new 'Help and support' section. It's available from the Info tab in the settings screen, as well as from the popup menu in the app.\n\n") + "If you're having technical issues with the app, please check the advice in there.\n\n") + "From there, you can also contact us and describe the issue you need assistance with.\n");
        dialog.setPositiveButton(" OK ", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });
        final Context fctx = ctx;
        dialog.setNegativeButton(" Open Help and support now ", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
                CommonAlerts.showHelp(fctx);
            }
        });
        dialog.show();
    }

    public static boolean checkVersion(Context ctx, boolean showChanges) {
        int versionCode = 1;
        try {
            versionCode = ctx.getPackageManager().getPackageInfo(ctx.getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        int lastVersionCode = getLastVersionCode(ctx);
        if (!SettingsStorage.prefFileExist(ctx, SettingsStorage.SETTINGS_LWP_NAME) && !SettingsStorage.prefFileExist(ctx, SettingsStorage.SETTINGS_NAME)) {
            setVersionCode(ctx, versionCode);
            return false;
        } else if (versionCode == lastVersionCode) {
            return false;
        } else {
            setVersionCode(ctx, versionCode);
            if (!showChanges) {
                return false;
            }
            if (versionCode == 11) {
                show11Changes(ctx);
                return true;
            } else if (versionCode == 111 || versionCode == 112) {
                show111Changes(ctx);
                return true;
            } else if (versionCode == 120) {
                show12Changes(ctx);
                return true;
            } else if (versionCode == 130 || versionCode == 141 || versionCode == 142) {
                return true;
            } else {
                if (versionCode == 151) {
                    if (lastVersionCode == 150) {
                        return true;
                    }
                    show15Changes(ctx);
                    return true;
                } else if (versionCode == 152 || versionCode == 153) {
                    return true;
                } else {
                    if (versionCode == 154) {
                        show154Changes(ctx);
                        return true;
                    } else if (versionCode == 155) {
                        show155Changes(ctx);
                        return true;
                    } else if (versionCode == 156) {
                        show156Changes(ctx);
                        return true;
                    } else if (versionCode == 160) {
                        show160Changes(ctx);
                        return true;
                    } else if (versionCode == 161) {
                        show161Changes(ctx);
                        return true;
                    } else if (versionCode == 162 && lastVersionCode != 161) {
                        show161Changes(ctx);
                        return true;
                    } else if (versionCode == 163) {
                        showNewHelpAndSupport(ctx);
                        show163ChangesFree(ctx);
                        return true;
                    } else if (versionCode == 164) {
                        show164ChangesFree(ctx);
                        return true;
                    } else if (versionCode == 165) {
                        show165ChangesFree(ctx);
                        return true;
                    } else if (versionCode == 166) {
                        show166ChangesFree(ctx);
                        return true;
                    } else if (versionCode == 168) {
                        show168ChangesFree(ctx);
                        return true;
                    } else if (versionCode == 169) {
                        show169ChangesFree(ctx);
                        return true;
                    } else if (versionCode == 170 && lastVersionCode != 169) {
                        show169ChangesFree(ctx);
                        return true;
                    } else if (versionCode != 180) {
                        return false;
                    } else {
                        show180ChangesFree(ctx);
                        return true;
                    }
                }
            }
        }
    }
}
