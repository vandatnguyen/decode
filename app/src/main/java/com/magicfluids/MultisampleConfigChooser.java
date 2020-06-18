package com.magicfluids;

import android.opengl.GLSurfaceView;
import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.egl.EGLDisplay;

public class MultisampleConfigChooser implements GLSurfaceView.EGLConfigChooser {
    private static final String kTag = "GDC11";
    private boolean mUsesCoverageAa;
    private int[] mValue;

    public EGLConfig chooseConfig(EGL10 egl, EGLDisplay display) {
        this.mValue = new int[1];
        int[] configSpec = {12340, 12344, 12324, 5, 12323, 6, 12322, 5, 12325, 16, 12352, 4, 12338, 1, 12337, 2, 12344};
        if (!egl.eglChooseConfig(display, configSpec, null, 0, this.mValue)) {
            LogUtil.i("configchooser", "msaa not found");
        }
        int numConfigs = this.mValue[0];
        if (numConfigs <= 0) {
            configSpec = new int[]{12340, 12344, 12324, 5, 12323, 6, 12322, 5, 12325, 16, 12352, 4, 12512, 1, 12513, 2, 12344};
            if (!egl.eglChooseConfig(display, configSpec, null, 0, this.mValue)) {
                LogUtil.i("configchooser", "csaa not found");
            }
            numConfigs = this.mValue[0];
            if (numConfigs <= 0) {
                configSpec = new int[]{12340, 12344, 12324, 5, 12323, 6, 12322, 5, 12325, 16, 12352, 4, 12344};
                if (!egl.eglChooseConfig(display, configSpec, null, 0, this.mValue)) {
                    LogUtil.i("configchooser", "is there ANY config?!");
                }
                numConfigs = this.mValue[0];
                if (numConfigs <= 0) {
                    LogUtil.i("configchooser", "Damn. There isn't.");
                }
            } else {
                this.mUsesCoverageAa = true;
                LogUtil.i("configchooser", "CSAA config found");
            }
        } else {
            LogUtil.i("configchooser", "MSAA config found");
        }
        EGLConfig[] configs = new EGLConfig[numConfigs];
        if (!egl.eglChooseConfig(display, configSpec, configs, numConfigs, this.mValue)) {
            LogUtil.i("configchooser", "rotfl. Don't know what happened");
        }
        LogUtil.i("configchooser", "num configs " + configs.length);
        int index = -1;
        int i = 0;
        while (true) {
            if (i >= configs.length) {
                break;
            }
            if (findConfigAttrib(egl, display, configs[i], 12324, 0) == 8) {
                index = i;
                break;
            }
            i++;
        }
        if (index == -1) {
            int i2 = 0;
            while (true) {
                if (i2 >= configs.length) {
                    break;
                }
                if (findConfigAttrib(egl, display, configs[i2], 12324, 0) == 5) {
                    index = i2;
                    break;
                }
                i2++;
            }
        }
        if (index != -1) {
            LogUtil.i("configchooser", "config index chosen: " + index + ", with red channel size: " + findConfigAttrib(egl, display, configs[index], 12324, 0));
        }
        if (index == -1) {
            LogUtil.w(kTag, "Did not find sane config, using first");
        }
        EGLConfig config = configs.length > 0 ? configs[index] : null;
        if (config != null) {
            return config;
        }
        throw new IllegalArgumentException("No config chosen");
    }

    private int findConfigAttrib(EGL10 egl, EGLDisplay display, EGLConfig config, int attribute, int defaultValue) {
        if (egl.eglGetConfigAttrib(display, config, attribute, this.mValue)) {
            return this.mValue[0];
        }
        return defaultValue;
    }

    public boolean usesCoverageAa() {
        return this.mUsesCoverageAa;
    }
}
