package com.magicfluids;

import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.egl.EGLContext;
import javax.microedition.khronos.egl.EGLDisplay;

/* compiled from: GLWallpaperServiceRBG */
interface EGLContextFactory {
    EGLContext createContext(EGL10 egl10, EGLDisplay eGLDisplay, EGLConfig eGLConfig, int i);

    void destroyContext(EGL10 egl10, EGLDisplay eGLDisplay, EGLContext eGLContext);
}
