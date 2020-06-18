package com.magicfluids;

import android.service.wallpaper.WallpaperService;
import android.util.Log;
import android.view.SurfaceHolder;

public class GLWallpaperServiceRBG extends WallpaperService {
    private static final String TAG = "GLWallpaperService";

    public class GLEngine extends Engine {
        public static final int RENDERMODE_CONTINUOUSLY = 1;
        public static final int RENDERMODE_WHEN_DIRTY = 0;
        private int mDebugFlags;
        private EGLConfigChooser mEGLConfigChooser;
        private int mEGLContextClientVersion;
        private EGLContextFactory mEGLContextFactory;
        private EGLWindowSurfaceFactory mEGLWindowSurfaceFactory;
        private GLThread mGLThread;
        private GLWrapper mGLWrapper;

        public GLEngine() {
            super();
        }

        public void onVisibilityChanged(boolean visible) {
            if (visible) {
                onResume();
            } else {
                onPause();
            }
            super.onVisibilityChanged(visible);
        }

        public void onCreate(SurfaceHolder surfaceHolder) {
            super.onCreate(surfaceHolder);
        }

        public void onDestroy() {
            super.onDestroy();
            this.mGLThread.requestExitAndWait();
        }

        public void onSurfaceChanged(SurfaceHolder holder, int format, int width, int height) {
            this.mGLThread.onWindowResize(width, height);
            super.onSurfaceChanged(holder, format, width, height);
        }

        public void onSurfaceCreated(SurfaceHolder holder) {
            Log.d(GLWallpaperServiceRBG.TAG, "onSurfaceCreated()");
            this.mGLThread.surfaceCreated(holder);
            super.onSurfaceCreated(holder);
        }

        public void onSurfaceDestroyed(SurfaceHolder holder) {
            Log.d(GLWallpaperServiceRBG.TAG, "onSurfaceDestroyed()");
            this.mGLThread.surfaceDestroyed();
            super.onSurfaceDestroyed(holder);
        }

        public void setGLWrapper(GLWrapper glWrapper) {
            this.mGLWrapper = glWrapper;
        }

        public void setDebugFlags(int debugFlags) {
            this.mDebugFlags = debugFlags;
        }

        public int getDebugFlags() {
            return this.mDebugFlags;
        }

        public void setEGLContextClientVersion(int version) {
            checkRenderThreadState();
            this.mEGLContextClientVersion = version;
        }

        public void setRenderer(GLES20Renderer renderer) {
            checkRenderThreadState();
            if (this.mEGLConfigChooser == null) {
                this.mEGLConfigChooser = new BaseConfigChooser.SimpleEGLConfigChooser(true, this.mEGLContextClientVersion);
            }
            if (this.mEGLContextFactory == null) {
                this.mEGLContextFactory = new DefaultContextFactory();
            }
            if (this.mEGLWindowSurfaceFactory == null) {
                this.mEGLWindowSurfaceFactory = new DefaultWindowSurfaceFactory();
            }
            this.mGLThread = new GLThread(renderer, this.mEGLConfigChooser, this.mEGLContextFactory, this.mEGLWindowSurfaceFactory, this.mGLWrapper, this.mEGLContextClientVersion);
            this.mGLThread.start();
        }

        public void setEGLContextFactory(EGLContextFactory factory) {
            checkRenderThreadState();
            this.mEGLContextFactory = factory;
        }

        public void setEGLWindowSurfaceFactory(EGLWindowSurfaceFactory factory) {
            checkRenderThreadState();
            this.mEGLWindowSurfaceFactory = factory;
        }

        public void setEGLConfigChooser(EGLConfigChooser configChooser) {
            checkRenderThreadState();
            this.mEGLConfigChooser = configChooser;
        }

        public void setEGLConfigChooser(boolean needDepth) {
            setEGLConfigChooser((EGLConfigChooser) new BaseConfigChooser.SimpleEGLConfigChooser(needDepth, this.mEGLContextClientVersion));
        }

        public void setEGLConfigChooser(int redSize, int greenSize, int blueSize, int alphaSize, int depthSize, int stencilSize) {
            setEGLConfigChooser((EGLConfigChooser) new BaseConfigChooser.ComponentSizeChooser(redSize, greenSize, blueSize, alphaSize, depthSize, stencilSize, this.mEGLContextClientVersion));
        }

        public void setRenderMode(int renderMode) {
            this.mGLThread.setRenderMode(renderMode);
        }

        public int getRenderMode() {
            return this.mGLThread.getRenderMode();
        }

        public void requestRender() {
            this.mGLThread.requestRender();
        }

        public void onPause() {
            this.mGLThread.onPause();
        }

        public void onResume() {
            this.mGLThread.onResume();
        }

        public void queueEvent(Runnable r) {
            this.mGLThread.queueEvent(r);
        }

        private void checkRenderThreadState() {
            if (this.mGLThread != null) {
                throw new IllegalStateException("setRenderer has already been called for this instance.");
            }
        }
    }

    public Engine onCreateEngine() {
        return new GLEngine();
    }
}
