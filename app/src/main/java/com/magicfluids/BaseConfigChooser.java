package com.magicfluids;

import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.egl.EGLDisplay;

/* compiled from: GLWallpaperServiceRBG */
abstract class BaseConfigChooser implements EGLConfigChooser {
    protected int[] mConfigSpec;
    protected int mEGLContextClientVersion;

    /* compiled from: GLWallpaperServiceRBG */
    public static class ComponentSizeChooser extends BaseConfigChooser {
        protected int mAlphaSize;
        protected int mBlueSize;
        protected int mDepthSize;
        protected int mGreenSize;
        protected int mRedSize;
        protected int mStencilSize;
        private int[] mValue = new int[1];

        public EGLConfig chooseConfig(EGL10 egl10, EGLDisplay eGLDisplay) {
            return super.chooseConfig(egl10, eGLDisplay);
        }

        public ComponentSizeChooser(int redSize, int greenSize, int blueSize, int alphaSize, int depthSize, int stencilSize, int eglContextClientVersion) {
            super(new int[]{12324, redSize, 12323, greenSize, 12322, blueSize, 12321, alphaSize, 12325, depthSize, 12326, stencilSize, 12344}, eglContextClientVersion);
            this.mRedSize = redSize;
            this.mGreenSize = greenSize;
            this.mBlueSize = blueSize;
            this.mAlphaSize = alphaSize;
            this.mDepthSize = depthSize;
            this.mStencilSize = stencilSize;
        }

        public EGLConfig chooseConfig(EGL10 egl, EGLDisplay display, EGLConfig[] configs) {
            EGLConfig closestConfig = null;
            int closestDistance = 1000;
            int length = configs.length;
            int i = 0;
            while (true) {
                int i2 = i;
                if (i2 >= length) {
                    return closestConfig;
                }
                EGLConfig config = configs[i2];
                int d = findConfigAttrib(egl, display, config, 12325, 0);
                int s = findConfigAttrib(egl, display, config, 12326, 0);
                if (d >= this.mDepthSize && s >= this.mStencilSize) {
                    int distance = Math.abs(findConfigAttrib(egl, display, config, 12324, 0) - this.mRedSize) + Math.abs(findConfigAttrib(egl, display, config, 12323, 0) - this.mGreenSize) + Math.abs(findConfigAttrib(egl, display, config, 12322, 0) - this.mBlueSize) + Math.abs(findConfigAttrib(egl, display, config, 12321, 0) - this.mAlphaSize);
                    if (distance < closestDistance) {
                        closestDistance = distance;
                        closestConfig = config;
                    }
                }
                i = i2 + 1;
            }
        }

        private int findConfigAttrib(EGL10 egl, EGLDisplay display, EGLConfig config, int attribute, int defaultValue) {
            if (egl.eglGetConfigAttrib(display, config, attribute, this.mValue)) {
                return this.mValue[0];
            }
            return defaultValue;
        }
    }

    /* compiled from: GLWallpaperServiceRBG */
    public static class SimpleEGLConfigChooser extends ComponentSizeChooser {
        public /* bridge */ /* synthetic */ EGLConfig chooseConfig(EGL10 egl10, EGLDisplay eGLDisplay) {
            return super.chooseConfig(egl10, eGLDisplay);
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public SimpleEGLConfigChooser(boolean withDepthBuffer, int eglContextClientVersion) {
            super(4, 4, 4, 0, withDepthBuffer ? 16 : 0, 0, eglContextClientVersion);
            this.mRedSize = 8;
            this.mGreenSize = 8;
            this.mBlueSize = 8;
        }
    }

    /* access modifiers changed from: package-private */
    public abstract EGLConfig chooseConfig(EGL10 egl10, EGLDisplay eGLDisplay, EGLConfig[] eGLConfigArr);

    public BaseConfigChooser(int[] configSpec, int eglContextClientVersion) {
        this.mEGLContextClientVersion = eglContextClientVersion;
        this.mConfigSpec = filterConfigSpec(configSpec);
    }

    public EGLConfig chooseConfig(EGL10 egl, EGLDisplay display) {
        int[] num_config = new int[1];
        egl.eglChooseConfig(display, this.mConfigSpec, null, 0, num_config);
        int numConfigs = num_config[0];
        if (numConfigs <= 0) {
            throw new IllegalArgumentException("No configs match configSpec");
        }
        EGLConfig[] configs = new EGLConfig[numConfigs];
        egl.eglChooseConfig(display, this.mConfigSpec, configs, numConfigs, num_config);
        EGLConfig config = chooseConfig(egl, display, configs);
        if (config != null) {
            return config;
        }
        throw new IllegalArgumentException("No config chosen");
    }

    private int[] filterConfigSpec(int[] configSpec) {
        if (this.mEGLContextClientVersion != 2) {
            return configSpec;
        }
        int len = configSpec.length;
        int[] newConfigSpec = new int[(len + 2)];
        System.arraycopy(configSpec, 0, newConfigSpec, 0, len - 1);
        newConfigSpec[len - 1] = 12352;
        newConfigSpec[len] = 4;
        newConfigSpec[len + 1] = 12344;
        return newConfigSpec;
    }
}
