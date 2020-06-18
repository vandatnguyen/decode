package com.magicfluids;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.service.wallpaper.WallpaperService;
import android.view.MotionEvent;
import android.view.SurfaceHolder;

public class GLWallpaperService extends WallpaperService {
    public static GLEngine mostRecentEngine = null;
    /* access modifiers changed from: private */
    public static GLEngine previewEngine = null;

    public Engine onCreateEngine() {
        if (previewEngine != null) {
            LogUtil.e("WallpaperEngine", "Manual preview engine pause");
            previewEngine.onPause();
        }
        return new GLEngine();
    }

    public class GLEngine extends Engine {
        public NativeInterface ntv;
        private WallpaperGLSurfaceView glSurfaceView;
        private GLES20Renderer renderer;
        private boolean rendererHasBeenSet;

        public GLEngine() {
            super();
        }

        /* access modifiers changed from: private */
        public void logE(String message) {
            LogUtil.e("LWP", String.valueOf(toString()) + ":" + message);
        }

        public void onCreate(SurfaceHolder surfaceHolder) {
            super.onCreate(surfaceHolder);
            setTouchEventsEnabled(true);
            GLWallpaperService.mostRecentEngine = this;
            if (isPreview()) {
                GLWallpaperService.previewEngine = this;
            }
            logE("GLEngine onCreate");
            this.glSurfaceView = new WallpaperGLSurfaceView(GLWallpaperService.this);
            this.ntv = new NativeInterface();
            this.glSurfaceView.setEGLContextClientVersion(2);
            this.glSurfaceView.setEGLConfigChooser(new MultisampleConfigChooser());
            GLES20Renderer gLES20Renderer = new GLES20Renderer(null, this.ntv, null, Settings.LWPCurrent);
            this.renderer = gLES20Renderer;
            setRenderer(gLES20Renderer);
            this.renderer.setInitialScreenSize(300, 200);
            this.ntv.setAssetManager(GLWallpaperService.this.getAssets());
            this.ntv.onCreate(300, 200, true);
            Preset.init(GLWallpaperService.this.getAssets());
            SettingsStorage.loadSessionSettings(GLWallpaperService.this, Settings.LWPCurrent, SettingsStorage.SETTINGS_LWP_NAME);
            if (Settings.LWPCurrent.QualityBaseValue == 0) {
                Settings.LWPCurrent.setFromInternalPreset(Preset.List.get(0).Set);
                Settings.LWPCurrent.QualityBaseValue = this.ntv.perfHeuristic();
                Settings.LWPCurrent.GPUQuality = Settings.LWPCurrent.QualityBaseValue >= 320 ? 2 : 1;
            }
            Settings.LWPCurrent.process();
            this.ntv.updateSettings(Settings.LWPCurrent);
            logE("GLEngine onCreate end");
        }

        public void onPause() {
            this.glSurfaceView.onPause();
            this.ntv.onPause();
        }

        public void onDesiredSizeChanged(int desiredWidth, int desiredHeight) {
            this.ntv.windowChanged(desiredWidth, desiredHeight);
            logE("GLEngine onDesiredSizeChanged");
            super.onDesiredSizeChanged(desiredWidth, desiredHeight);
        }

        public void onVisibilityChanged(boolean visible) {
            logE("GLEngine onVisibilityChanged " + visible);
            super.onVisibilityChanged(visible);
            if (!this.rendererHasBeenSet) {
                return;
            }
            if (visible) {
                if (!isPreview()) {
                    Settings.LWPCurrent.process();
                    this.ntv.updateSettings(Settings.LWPCurrent);
                }
                this.glSurfaceView.onResume();
                this.ntv.onResume();
                return;
            }
            onPause();
        }

        public void onSurfaceChanged(SurfaceHolder holder, int format, int width, int height) {
            logE("GLEngine onSurfaceChanged");
            super.onSurfaceChanged(holder, format, width, height);
        }

        public void onSurfaceCreated(SurfaceHolder holder) {
            logE("GLEngine onSurfaceCreated");
            super.onSurfaceCreated(holder);
        }

        public void onSurfaceDestroyed(SurfaceHolder holder) {
            logE("GLEngine onSurfaceDestroyed");
            super.onSurfaceDestroyed(holder);
        }

        public void onDestroy() {
            super.onDestroy();
            this.glSurfaceView.onDestroy();
            logE("GLEngine onDestroy");
            if (isPreview()) {
                GLWallpaperService.previewEngine = null;
            }
            this.ntv.onDestroy();
        }

        /* access modifiers changed from: protected */
        public void setRenderer(GLSurfaceView.Renderer renderer2) {
            this.glSurfaceView.setRenderer(renderer2);
            this.rendererHasBeenSet = true;
            logE("GLEngine setRenderer");
        }

        public void onTouchEvent(MotionEvent event) {
            this.glSurfaceView.onTouchEvent(event);
        }

        class WallpaperGLSurfaceView extends MyGLSurfaceView {
            WallpaperGLSurfaceView(Context context) {
                super(context);
                GLEngine.this.logE("WallpaperGLSurfaceView()");
            }

            public SurfaceHolder getHolder() {
                GLEngine.this.logE("WallpaperGLSurfaceView getHolder");
                return GLEngine.this.getSurfaceHolder();
            }

            public void onDestroy() {
                GLEngine.this.logE("WallpaperGLSurfaceView  onDestroy");
                super.onDetachedFromWindow();
            }
        }
    }
}
