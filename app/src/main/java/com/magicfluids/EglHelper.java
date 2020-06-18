package com.magicfluids;

import android.util.Log;
import android.view.SurfaceHolder;
import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.egl.EGLContext;
import javax.microedition.khronos.egl.EGLDisplay;
import javax.microedition.khronos.egl.EGLSurface;
import javax.microedition.khronos.opengles.GL;

/* compiled from: GLWallpaperServiceRBG */
class EglHelper {
    private EGLConfigChooser mEGLConfigChooser;
    private int mEGLContextClientVersion;
    private EGLContextFactory mEGLContextFactory;
    private EGLWindowSurfaceFactory mEGLWindowSurfaceFactory;
    private EGL10 mEgl;
    EGLConfig mEglConfig;
    private EGLContext mEglContext;
    private EGLDisplay mEglDisplay;
    private EGLSurface mEglSurface;
    private GLWrapper mGLWrapper;

    public EglHelper(EGLConfigChooser chooser, EGLContextFactory contextFactory, EGLWindowSurfaceFactory surfaceFactory, GLWrapper wrapper, int eglContextClientVersion) {
        this.mEGLConfigChooser = chooser;
        this.mEGLContextFactory = contextFactory;
        this.mEGLWindowSurfaceFactory = surfaceFactory;
        this.mGLWrapper = wrapper;
        this.mEGLContextClientVersion = eglContextClientVersion;
    }

    public boolean start() {
        boolean contextCreated = false;
        if (this.mEgl == null) {
            this.mEgl = (EGL10) EGLContext.getEGL();
        }
        if (this.mEglDisplay == null) {
            this.mEglDisplay = this.mEgl.eglGetDisplay(EGL10.EGL_DEFAULT_DISPLAY);
        }
        if (this.mEglConfig == null) {
            this.mEgl.eglInitialize(this.mEglDisplay, new int[2]);
            this.mEglConfig = this.mEGLConfigChooser.chooseConfig(this.mEgl, this.mEglDisplay);
        }
        if (this.mEglContext == null) {
            Log.d("EglHelper", "creating new context");
            this.mEglContext = this.mEGLContextFactory.createContext(this.mEgl, this.mEglDisplay, this.mEglConfig, this.mEGLContextClientVersion);
            if (this.mEglContext == null || this.mEglContext == EGL10.EGL_NO_CONTEXT) {
                throw new RuntimeException("createContext failed");
            }
            contextCreated = true;
        }
        this.mEglSurface = null;
        return contextCreated;
    }

    public GL createSurface(SurfaceHolder holder) {
        if (!(this.mEglSurface == null || this.mEglSurface == EGL10.EGL_NO_SURFACE)) {
            this.mEgl.eglMakeCurrent(this.mEglDisplay, EGL10.EGL_NO_SURFACE, EGL10.EGL_NO_SURFACE, EGL10.EGL_NO_CONTEXT);
            this.mEGLWindowSurfaceFactory.destroySurface(this.mEgl, this.mEglDisplay, this.mEglSurface);
        }
        this.mEglSurface = this.mEGLWindowSurfaceFactory.createWindowSurface(this.mEgl, this.mEglDisplay, this.mEglConfig, holder);
        if (this.mEglSurface == null) {
            throw new RuntimeException("createWindowSurface failed: mEglSurface is null");
        }
        if (this.mEglSurface == EGL10.EGL_NO_SURFACE) {
            this.mEglSurface = this.mEGLWindowSurfaceFactory.createWindowSurface(this.mEgl, this.mEglDisplay, this.mEglConfig, holder);
            if (this.mEglSurface == EGL10.EGL_NO_SURFACE) {
                throw new RuntimeException("createWindowSurface failed: mEglSurface is EGL10.EGL_NO_SURFACE");
            }
        }
        if (!this.mEgl.eglMakeCurrent(this.mEglDisplay, this.mEglSurface, this.mEglSurface, this.mEglContext)) {
            throw new RuntimeException("eglMakeCurrent failed.");
        }
        GL gl = this.mEglContext.getGL();
        return this.mGLWrapper != null ? this.mGLWrapper.wrap(gl) : gl;
    }

    public boolean swap() {
        this.mEgl.eglSwapBuffers(this.mEglDisplay, this.mEglSurface);
        return this.mEgl.eglGetError() != 12302;
    }

    public void destroySurface() {
        if (this.mEglSurface != null && this.mEglSurface != EGL10.EGL_NO_SURFACE) {
            this.mEgl.eglMakeCurrent(this.mEglDisplay, EGL10.EGL_NO_SURFACE, EGL10.EGL_NO_SURFACE, EGL10.EGL_NO_CONTEXT);
            this.mEGLWindowSurfaceFactory.destroySurface(this.mEgl, this.mEglDisplay, this.mEglSurface);
            this.mEglSurface = null;
        }
    }

    public void finish() {
        Log.e("GLThread", "finish()");
        if (this.mEglContext != null) {
            this.mEGLContextFactory.destroyContext(this.mEgl, this.mEglDisplay, this.mEglContext);
            this.mEglContext = null;
        }
        if (this.mEglDisplay != null) {
            this.mEgl.eglTerminate(this.mEglDisplay);
            this.mEglDisplay = null;
        }
    }
}
