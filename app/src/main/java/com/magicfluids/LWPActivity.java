package com.magicfluids;

import android.os.Bundle;
import com.magicfluids.demo.R;

public class LWPActivity extends SettingsActivity {
    NativeInterface ntv = null;
    PolicyManager policyMgr;
    private SettingsController settingsController = new SettingsController();

    /* access modifiers changed from: protected */
    public void initSettings() {
        Preset.init(getAssets());
        QualitySetting.init();
        SettingsStorage.loadSessionSettings(this, Settings.LWPCurrent, SettingsStorage.SETTINGS_LWP_NAME);
        if (RunManager.getNumLwpRuns(this) == 0) {
            Settings.LWPCurrent.setFromInternalPreset(Preset.List.get(0).Set);
            QualitySetting.setQualitySettingsToDefault(Settings.LWPCurrent);
        }
        Settings.LWPCurrent.process();
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (NativeInterface.loadingFailed) {
            CommonAlerts.showInstallationFailed(this);
        }
        if (NewWallpaperService.mostRecentEngine != null) {
            this.ntv = NewWallpaperService.mostRecentEngine.ntv;
        } else {
            initSettings();
        }
        setContentView(R.layout.activity_lwp);
        findViewById(R.id.settings_view);
        this.policyMgr = new PolicyManager();
        this.policyMgr.updateOnRun(this);
        this.policyMgr.updateOnOpenSettings(this);
        this.settingsController.initControls(this, this.ntv, Settings.LWPCurrent, true);
        RunManager.newLWPSettingsScreenRun(this);
        VersionManager.checkVersion(this, true);
        LogUtil.i("LWP activity", "onCreate");
    }

    /* access modifiers changed from: package-private */
    public void onSettingsChanged() {
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        this.settingsController.reloadEverything();
        LogUtil.i("LWP activity", "onResume");
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        onSettingsChanged();
        SettingsStorage.saveSessionSettings(this, Settings.LWPCurrent, SettingsStorage.SETTINGS_LWP_NAME);
        super.onPause();
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
    }

    public void onPointerCaptureChanged(boolean hasCapture) {
    }

    /* access modifiers changed from: package-private */
    public void setLiveWallpaper() {
    }
}
