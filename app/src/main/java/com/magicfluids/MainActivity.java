package com.magicfluids;

import android.annotation.TargetApi;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.net.Uri;
import android.opengl.GLSurfaceView;
import android.os.Build;
import android.os.Bundle;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.core.view.MotionEventCompat;

import com.magicfluids.demo.R;

public class MainActivity extends SettingsActivity {
    static final int PERM_WRITE_CODE = 1;
    private boolean badOrientation = false;
    ImageButton buttonCloseSettings = null;
    ImageButton buttonOpenInputSettings = null;
    ImageButton buttonOpenMenu = null;
    private View inputSettingsView;
    boolean isPortrait = false;
    private GLSurfaceView mGLSurfaceView;
    private NativeInterface ntv;
    private OrientationSensor orientationSensor;
    private PolicyManager policyMgr;
    private GLES20Renderer renderer;
    /* access modifiers changed from: private */
    public SettingsController settingsController = new SettingsController();
    private View settingsView;

    enum UPDATE_BUTTON {
        VISIBLE,
        DIMMED,
        HIDDEN
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupMainWindowDisplayMode();
        boolean openSettingsRequired = getIntent().getBooleanExtra("openSettings", false);
        setContentView(R.layout.activity_main);
        this.mGLSurfaceView = (MyGLSurfaceView) findViewById(R.id.surface_view);
        this.ntv = new NativeInterface();
//        if (NativeInterface.loadingFailed) {
//            CommonAlerts.showInstallationFailed(this);
//        }
        this.ntv.setAssetManager(getAssets());
        this.orientationSensor = new OrientationSensor(this, getApplication());
        this.mGLSurfaceView.setEGLContextClientVersion(2);
        this.mGLSurfaceView.setEGLConfigChooser(new MultisampleConfigChooser());
        GLSurfaceView gLSurfaceView = this.mGLSurfaceView;
        GLES20Renderer gLES20Renderer = new GLES20Renderer(this, this.ntv, this.orientationSensor, Settings.Current);
        this.renderer = gLES20Renderer;
        gLSurfaceView.setRenderer(gLES20Renderer);
        this.renderer.setInitialScreenSize(300, 200);
        this.ntv.onCreate(300, 200, false);
        Preset.init(getAssets());
        QualitySetting.init();
        SettingsStorage.loadSessionSettings(this, Settings.Current, SettingsStorage.SETTINGS_NAME);
        if (RunManager.getNumAppRuns(this) == 0) {
            Settings.Current.setFromInternalPreset(Preset.List.get(0).Set);
            QualitySetting.setQualitySettingsFromPerf(Settings.Current, this.ntv);
        }
        LogUtil.i("MAIN_ACTIVITY", "Before first onSettingsChanged");
        onSettingsChanged();
        LogUtil.i("MAIN_ACTIVITY", "After first onSettingsChanged");
        this.settingsView = findViewById(R.id.settings_view);
        this.settingsView.setVisibility(View.GONE);
        this.inputSettingsView = findViewById(R.id.input_settings_view);
        this.inputSettingsView.setVisibility(View.GONE);
        Display display = getWindowManager().getDefaultDisplay();
        this.isPortrait = getResources().getConfiguration().orientation == 1;
        int displayBiggerSize = this.isPortrait ? display.getHeight() : display.getWidth();
        int settingsWindowSize = (int) (((float) displayBiggerSize) * 0.75f);
        if (displayBiggerSize >= 480) {
            settingsWindowSize = (int) (((float) displayBiggerSize) * 0.7f);
        }
        if (displayBiggerSize >= 640) {
            settingsWindowSize = (int) (((float) displayBiggerSize) * 0.65f);
        }
        if (displayBiggerSize >= 800) {
            settingsWindowSize = (int) (((float) displayBiggerSize) * 0.6f);
        }
        if (displayBiggerSize >= 1024) {
            settingsWindowSize = (int) (((float) displayBiggerSize) * 0.5f);
        }
        if (this.isPortrait) {
            this.settingsView.setLayoutParams(new FrameLayout.LayoutParams(-1, (int) (((double) settingsWindowSize) * 1.2d), 80));
            this.inputSettingsView.setLayoutParams(new FrameLayout.LayoutParams(-1, (int) (((double) settingsWindowSize) * 1.0d), 80));
        } else {
            this.settingsView.setLayoutParams(new FrameLayout.LayoutParams(settingsWindowSize, -1));
            this.inputSettingsView.setLayoutParams(new FrameLayout.LayoutParams((int) (((double) settingsWindowSize) * 0.8d), -1));
            findViewById(R.id.view_settings_portrait_separator).setVisibility(View.GONE);
            findViewById(R.id.view_input_settings_portrait_separator).setVisibility(View.GONE);
        }
        this.policyMgr = new PolicyManager();
        this.policyMgr.updateOnRun(this);
        this.settingsController.initControls(this, this.ntv, Settings.Current, false);
        RunManager.newAppRun(this);
        boolean checkVersion = VersionManager.checkVersion(this, true);
        initButtons();
        if (openSettingsRequired) {
            onOpenFromLWP();
        }
    }

    /* access modifiers changed from: package-private */
    public void onOpenFromLWP() {
        openSettings();
    }

    /* access modifiers changed from: protected */
    public void onNewIntent(Intent intent) {
        if (intent.getBooleanExtra("openSettings", false)) {
            onOpenFromLWP();
        }
        super.onNewIntent(intent);
    }

    public void onWindowFocusChanged(boolean hasFocus) {
        if (hasFocus) {
            setSystemUiVisibilityMode();
        }
    }

    /* access modifiers changed from: package-private */
    public void openSettings() {
        closeInputSettings();
        updateSettingsView(this.settingsView, true);
        updateButtons();
        this.policyMgr.updateOnOpenSettings(this);
    }

    /* access modifiers changed from: package-private */
    public void closeSettings() {
        updateSettingsView(this.settingsView, false);
        updateButtons();
    }

    /* access modifiers changed from: package-private */
    public void openInputSettings() {
        closeSettings();
        updateSettingsView(this.inputSettingsView, true);
        updateButtons();
    }

    /* access modifiers changed from: package-private */
    public void closeInputSettings() {
        updateSettingsView(this.inputSettingsView, false);
        updateButtons();
    }

    /* access modifiers changed from: package-private */
    public void initButtons() {
        this.buttonOpenMenu = (ImageButton) findViewById(R.id.buttonOpenMenu);
        this.buttonOpenMenu.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                MainActivity.this.openOptionsMenu();
            }
        });
        this.buttonOpenInputSettings = (ImageButton) findViewById(R.id.buttonOpenInputSettings);
        this.buttonOpenInputSettings.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                MainActivity.this.openInputSettings();
            }
        });
        this.buttonCloseSettings = (ImageButton) findViewById(R.id.buttonCloseSettings);
        this.buttonCloseSettings.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                MainActivity.this.closeAllSettings();
            }
        });
        updateButtons();
    }

    private void setupMainWindowDisplayMode() {
        setSystemUiVisibilityMode().setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {
            public void onSystemUiVisibilityChange(int visibility) {
                View unused = MainActivity.this.setSystemUiVisibilityMode();
            }
        });
    }

    /* access modifiers changed from: private */
    public View setSystemUiVisibilityMode() {
        View decorView = getWindow().getDecorView();
        if (Build.VERSION.SDK_INT >= 19) {
            decorView.setSystemUiVisibility(5894);
        }
        return decorView;
    }

    public void openOptionsMenu() {
        Configuration config = getResources().getConfiguration();
        if ((config.screenLayout & 15) > 3) {
            int originalScreenLayout = config.screenLayout;
            config.screenLayout = 3;
            super.openOptionsMenu();
            config.screenLayout = originalScreenLayout;
            return;
        }
        super.openOptionsMenu();
    }

    /* access modifiers changed from: package-private */
    public void onMenuButtonVisibilityChanged(int value) {
        Settings.Current.MenuButtonVisibility = value;
        updateButtons();
    }

    /* access modifiers changed from: package-private */
    public void updateSettingsView(View view, boolean visible) {
        view.setVisibility(visible ? View.VISIBLE : View.GONE);
        view.requestLayout();
    }

    /* access modifiers changed from: package-private */
    public void closeAllSettings() {
        closeSettings();
        closeInputSettings();
    }

    /* access modifiers changed from: package-private */
    public boolean checkAnySettingsOpen() {
        return this.settingsView.getVisibility() == View.VISIBLE || this.inputSettingsView.getVisibility() == View.GONE;
    }

    /* access modifiers changed from: package-private */
    public void updateButton(ImageButton button, UPDATE_BUTTON vis) {
        int alpha = 0;
        int oldVerColor = 0;
        if (vis == UPDATE_BUTTON.VISIBLE) {
            alpha = 200;
            oldVerColor = -2011028958;
        }
        if (vis == UPDATE_BUTTON.DIMMED) {
            alpha = 25;
            oldVerColor = 253895202;
        }
        if (vis == UPDATE_BUTTON.HIDDEN) {
            alpha = 0;
            oldVerColor = 2236962;
        }
        button.setAlpha(alpha);
        if (Build.VERSION.SDK_INT < 11) {
            button.setBackgroundColor(oldVerColor);
        } else {
            button.getBackground().setAlpha(alpha);
        }
        if (vis == UPDATE_BUTTON.HIDDEN) {
            button.setVisibility(View.GONE);
        } else {
            button.setVisibility(View.VISIBLE);
        }
    }

    /* access modifiers changed from: package-private */
    public void updateButtons() {
        boolean dimmed = true;
        if (Settings.Current.MenuButtonVisibility != 1) {
            dimmed = false;
        }
        boolean anySettingsOpen = checkAnySettingsOpen();
        if (anySettingsOpen) {
            updateButton(this.buttonOpenInputSettings, UPDATE_BUTTON.HIDDEN);
        } else if (dimmed) {
            updateButton(this.buttonOpenInputSettings, UPDATE_BUTTON.DIMMED);
        } else {
            updateButton(this.buttonOpenInputSettings, UPDATE_BUTTON.VISIBLE);
        }
        if (anySettingsOpen && this.isPortrait) {
            updateButton(this.buttonOpenMenu, UPDATE_BUTTON.HIDDEN);
        } else if (dimmed) {
            updateButton(this.buttonOpenMenu, UPDATE_BUTTON.DIMMED);
        } else {
            updateButton(this.buttonOpenMenu, UPDATE_BUTTON.VISIBLE);
        }
        if (anySettingsOpen) {
            updateButton(this.buttonCloseSettings, UPDATE_BUTTON.VISIBLE);
        } else {
            updateButton(this.buttonCloseSettings, UPDATE_BUTTON.HIDDEN);
        }
        updateButtonIcons();
    }

    static boolean isColorBright(int color) {
        return ((0.3f * (((float) ((16711680 & color) >> 16)) / 255.0f)) + (0.59f * (((float) ((65280 & color) >> 8)) / 255.0f))) + (0.11f * (((float) (color & MotionEventCompat.ACTION_MASK)) / 255.0f)) > 0.4f;
    }

    /* access modifiers changed from: package-private */
    public void updateButtonIcons() {
        boolean brightBackgr;
        if (this.buttonOpenMenu != null) {
            if (Settings.Current.ColorOption != 2 || !isColorBright(Settings.Current.BackgroundColor)) {
                brightBackgr = false;
            } else {
                brightBackgr = true;
            }
            if (Settings.Current.InvertColors) {
                if (brightBackgr) {
                    brightBackgr = false;
                } else {
                    brightBackgr = true;
                }
            }
            this.buttonOpenMenu.setImageDrawable(getResources().getDrawable(brightBackgr ? R.drawable.ni_menu_black : R.drawable.ni_menu));
            this.buttonOpenInputSettings.setImageDrawable(getResources().getDrawable(brightBackgr ? R.drawable.ni_hand_black : R.drawable.ni_hand));
            this.buttonCloseSettings.setImageDrawable(getResources().getDrawable(brightBackgr ? R.drawable.ni_close_black : R.drawable.ni_close));
        }
    }

    /* access modifiers changed from: package-private */
    public void onSettingsChanged() {
        Settings.Current.process();
        updateButtonIcons();
        if (Settings.Current.Gravity > 3.0E-4f) {
            this.orientationSensor.register();
        } else {
            this.orientationSensor.unregister();
        }
        this.ntv.updateSettings(Settings.Current);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        setSystemUiVisibilityMode();
        this.mGLSurfaceView.onResume();
        this.ntv.onResume();
        this.settingsController.reloadPresets();
        if (Settings.Current.GPUQuality > 0) {
            this.ntv.clearScreen();
        }
        if (Settings.Current.Gravity > 3.0E-4f) {
            this.orientationSensor.register();
        } else {
            this.orientationSensor.unregister();
        }
        updateButtons();
        LogUtil.i("Main activity", "onResume");
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        this.ntv.onPause();
        this.mGLSurfaceView.onPause();
        SettingsStorage.saveSessionSettings(this, Settings.Current, SettingsStorage.SETTINGS_NAME);
        this.orientationSensor.unregister();
        super.onPause();
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        this.ntv.onDestroy();
        super.onDestroy();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_demo, menu);
        LogUtil.i("Main activity", "onCreateOptionsMenu");
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onPrepareOptionsMenu(Menu menu) {
        this.ntv.onPauseAnim();
        boolean res = super.onPrepareOptionsMenu(menu);
        menu.findItem(R.id.save_image).setEnabled(false).setTitle("Save image (full only)");
        if (Settings.Current.MenuButtonVisibility == 0) {
            menu.findItem(R.id.change_menu_button).setTitle("Hide menu buttons");
        } else {
            menu.findItem(R.id.change_menu_button).setTitle("Show menu buttons");
        }
        return res;
    }

    public void onOptionsMenuClosed(Menu menu) {
        this.ntv.onResumeAnim();
        super.onOptionsMenuClosed(menu);
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1 && grantResults.length > 0 && grantResults[0] == 0) {
            this.renderer.orderScreenshot();
        }
    }

    @TargetApi(23)
    private void tryOrderScreenshotOnSdk23() {
        if (checkSelfPermission("android.permission.WRITE_EXTERNAL_STORAGE") != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{"android.permission.WRITE_EXTERNAL_STORAGE"}, 1);
            return;
        }
        this.renderer.orderScreenshot();
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.settings /*2131296581*/:
                openSettings();
                return true;
            case R.id.problems /*2131296582*/:
                CommonAlerts.showHelp(this);
                return true;
            case R.id.save_image /*2131296583*/:
                if (Build.VERSION.SDK_INT >= 23) {
                    tryOrderScreenshotOnSdk23();
                    return true;
                }
                this.renderer.orderScreenshot();
                return true;
            case R.id.set_lwp /*2131296584*/:
                setLiveWallpaper();
                return true;
            case R.id.change_menu_button /*2131296585*/:
                if (Settings.Current.MenuButtonVisibility == 0) {
                    onMenuButtonVisibilityChanged(1);
                    return true;
                }
                onMenuButtonVisibilityChanged(0);
                return true;
            case R.id.clear_screen /*2131296586*/:
                this.ntv.clearScreen();
                return true;
            case R.id.buy /*2131296587*/:
                startActivity(new Intent("android.intent.action.VIEW").setData(Uri.parse("market://details?id=com.magicfluids")));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void onBackPressed() {
        if (checkAnySettingsOpen()) {
            closeAllSettings();
        } else {
            super.onBackPressed();
        }
    }

    public void onPointerCaptureChanged(boolean hasCapture) {
    }

    /* access modifiers changed from: package-private */
    public void setLiveWallpaper() {
        try {
            if (Build.VERSION.SDK_INT >= 16) {
                ComponentName component = new ComponentName(getPackageName(), "com.magicfluids.NewWallpaperService");
                Intent intent = new Intent("android.service.wallpaper.CHANGE_LIVE_WALLPAPER");
                intent.putExtra("android.service.wallpaper.extra.LIVE_WALLPAPER_COMPONENT", component);
                startActivity(intent);
                return;
            }
            Toast.makeText(this, "Choose Magic Fluids from the list", Toast.LENGTH_SHORT).show();
            startActivity(new Intent("android.service.wallpaper.LIVE_WALLPAPER_CHOOSER"));
        } catch (Exception e) {
            Toast.makeText(this, "Action not supported. Please set wallpaper manually", Toast.LENGTH_SHORT).show();
        }
    }

    public void updateFrameTime(final String frameTime) {
        runOnUiThread(new Runnable() {
            public void run() {
                MainActivity.this.settingsController.setFrameTime(frameTime);
            }
        });
    }
}
