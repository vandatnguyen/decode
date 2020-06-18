package com.magicfluids;

import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.egl.EGLDisplay;

/* compiled from: GLWallpaperServiceRBG */
interface EGLConfigChooser {
    EGLConfig chooseConfig(EGL10 egl10, EGLDisplay eGLDisplay);
}
