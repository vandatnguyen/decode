package com.magicfluids;

import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.egl.EGLContext;
import javax.microedition.khronos.egl.EGLDisplay;

/* compiled from: GLWallpaperServiceRBG */
class DefaultContextFactory implements EGLContextFactory {
    private int EGL_CONTEXT_CLIENT_VERSION = 12440;

    DefaultContextFactory() {
    }

    public EGLContext createContext(EGL10 egl, EGLDisplay display, EGLConfig config, int eglContextClientVersion) {
        int[] attrib_list = {this.EGL_CONTEXT_CLIENT_VERSION, eglContextClientVersion, 12344};
        EGLContext eGLContext = EGL10.EGL_NO_CONTEXT;
        if (eglContextClientVersion == 0) {
            attrib_list = null;
        }
        return egl.eglCreateContext(display, config, eGLContext, attrib_list);
    }

    public void destroyContext(EGL10 egl, EGLDisplay display, EGLContext context) {
        egl.eglDestroyContext(display, context);
    }
}
