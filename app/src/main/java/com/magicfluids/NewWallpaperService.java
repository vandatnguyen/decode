package com.magicfluids;

import android.view.MotionEvent;
import android.view.SurfaceHolder;

public class NewWallpaperService extends GLWallpaperServiceRBG {
    public static OpenGLES2Engine mostRecentEngine = null;

    class OpenGLES2Engine extends GLEngine {
        public NativeInterface ntv;
        private OrientationSensor orientationSensor;
        private GLES20Renderer renderer;

        OpenGLES2Engine() {
            super();
        }

        private void logE(String message) {
            LogUtil.i("LWP", String.valueOf(toString()) + ":" + message);
        }

        public void onCreate(SurfaceHolder surfaceHolder) {
            super.onCreate(surfaceHolder);
            NewWallpaperService.mostRecentEngine = this;
            setTouchEventsEnabled(true);
            this.ntv = new NativeInterface();
            setEGLContextClientVersion(2);
            this.orientationSensor = new OrientationSensor(NewWallpaperService.this, NewWallpaperService.this.getApplication());
            GLES20Renderer gLES20Renderer = new GLES20Renderer(null, this.ntv, this.orientationSensor, Settings.LWPCurrent);
            this.renderer = gLES20Renderer;
            setRenderer(gLES20Renderer);
            this.renderer.setInitialScreenSize(300, 200);
            this.ntv.setAssetManager(NewWallpaperService.this.getAssets());
            this.ntv.onCreate(300, 200, true);
            Preset.init(NewWallpaperService.this.getAssets());
            QualitySetting.init();
            SettingsStorage.loadSessionSettings(NewWallpaperService.this, Settings.LWPCurrent, SettingsStorage.SETTINGS_LWP_NAME);
            if (RunManager.getNumLwpRuns(NewWallpaperService.this) == 0) {
                Settings.LWPCurrent.setFromInternalPreset(Preset.List.get(0).Set);
                QualitySetting.setQualitySettingsFromPerf(Settings.LWPCurrent, this.ntv);
            }
            Settings.LWPCurrent.process();
            this.ntv.updateSettings(Settings.LWPCurrent);
            logE("GLEngine onCreate end. NTV ID: " + this.ntv.getID());
        }

        public void onDesiredSizeChanged(int desiredWidth, int desiredHeight) {
            this.ntv.windowChanged(desiredWidth, desiredHeight);
            logE("GLEngine onDesiredSizeChanged. NTV ID: " + this.ntv.getID());
            super.onDesiredSizeChanged(desiredWidth, desiredHeight);
        }

        public void onVisibilityChanged(boolean visible) {
            super.onVisibilityChanged(visible);
            logE("GLEngine onVisibilityChanged to " + visible + ", NTV ID: " + this.ntv.getID());
            if (visible) {
                Settings.LWPCurrent.process();
                this.ntv.updateSettings(Settings.LWPCurrent);
                if (!isPreview() && Settings.LWPCurrent.ReloadRequired) {
                    this.ntv.clearScreen();
                    Settings.LWPCurrent.ReloadRequired = false;
                }
                if (isPreview() && Settings.LWPCurrent.ReloadRequiredPreview) {
                    this.ntv.clearScreen();
                    Settings.LWPCurrent.ReloadRequiredPreview = false;
                }
                this.ntv.onResume();
                if (Settings.LWPCurrent.Gravity > 3.0E-4f) {
                    this.orientationSensor.register();
                    return;
                }
                return;
            }
            this.ntv.onPause();
            this.orientationSensor.unregister();
        }

        public void onDestroy() {
            super.onDestroy();
            logE("GLEngine onDestroy. NTV ID: " + this.ntv.getID());
            this.ntv.onDestroy();
            this.orientationSensor.unregister();
        }

        public void onTouchEvent(MotionEvent event) {
            InputBuffer.Instance.addEvent(event);
        }
    }

    public Engine onCreateEngine() {
        return new OpenGLES2Engine();
    }
}
