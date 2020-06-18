package com.magicfluids;

import android.view.SurfaceHolder;
import java.util.ArrayList;
import javax.microedition.khronos.opengles.GL10;

/* compiled from: GLWallpaperServiceRBG */
class GLThread extends Thread {
    public static final int DEBUG_CHECK_GL_ERROR = 1;
    public static final int DEBUG_LOG_GL_CALLS = 2;
    private static final boolean LOG_THREADS = false;
    public boolean mDone = false;
    private EGLConfigChooser mEGLConfigChooser;
    private int mEGLContextClientVersion;
    private EGLContextFactory mEGLContextFactory;
    private EGLWindowSurfaceFactory mEGLWindowSurfaceFactory;
    private EglHelper mEglHelper;
    /* access modifiers changed from: private */
    public GLThread mEglOwner;
    private ArrayList<Runnable> mEventQueue = new ArrayList<>();
    private boolean mEventsWaiting;
    private GLWrapper mGLWrapper;
    private boolean mHasSurface;
    private boolean mHaveEgl;
    private int mHeight = 0;
    public SurfaceHolder mHolder;
    private boolean mPaused;
    private int mRenderMode = 1;
    private GLES20Renderer mRenderer;
    private boolean mRequestRender = true;
    private boolean mSizeChanged = true;
    private boolean mWaitingForSurface;
    private int mWidth = 0;
    private final GLThreadManager sGLThreadManager = new GLThreadManager(this, null);

    /* compiled from: GLWallpaperServiceRBG */
    private class GLThreadManager {
        private GLThreadManager() {
        }

        /* synthetic */ GLThreadManager(GLThread gLThread, GLThreadManager gLThreadManager) {
            this();
        }

        public synchronized void threadExiting(GLThread thread) {
            thread.mDone = true;
            if (GLThread.this.mEglOwner == thread) {
                GLThread.this.mEglOwner = null;
            }
            notifyAll();
        }

        public synchronized boolean tryAcquireEglSurface(GLThread thread) {
            boolean z;
            if (GLThread.this.mEglOwner == thread || GLThread.this.mEglOwner == null) {
                GLThread.this.mEglOwner = thread;
                notifyAll();
                z = true;
            } else {
                z = false;
            }
            return z;
        }

        public synchronized void releaseEglSurface(GLThread thread) {
            if (GLThread.this.mEglOwner == thread) {
                GLThread.this.mEglOwner = null;
            }
            notifyAll();
        }
    }

    GLThread(GLES20Renderer renderer, EGLConfigChooser chooser, EGLContextFactory contextFactory, EGLWindowSurfaceFactory surfaceFactory, GLWrapper wrapper, int eglContextClientVersion) {
        this.mRenderer = renderer;
        this.mEGLConfigChooser = chooser;
        this.mEGLContextFactory = contextFactory;
        this.mEGLWindowSurfaceFactory = surfaceFactory;
        this.mGLWrapper = wrapper;
        this.mEGLContextClientVersion = eglContextClientVersion;
    }

    public void run() {
        setName("GLThread " + getId());
        try {
            guardedRun();
        } catch (InterruptedException e) {
        } finally {
            this.sGLThreadManager.threadExiting(this);
        }
    }

    private void stopEglLocked() {
        if (this.mHaveEgl) {
            this.mHaveEgl = false;
            this.mEglHelper.destroySurface();
            this.sGLThreadManager.releaseEglSurface(this);
        }
    }

    // TODO: 6/8/2020
    private void guardedRun() throws InterruptedException {
        this.mEglHelper = new EglHelper(this.mEGLConfigChooser, this.mEGLContextFactory, this.mEGLWindowSurfaceFactory, this.mGLWrapper, this.mEGLContextClientVersion);
        GL10 gl = null;
        boolean tellRendererSurfaceCreated = true;
        boolean tellRendererSurfaceChanged = true;
        loop0:
        while (!isDone()) {
            try {
                int w = 0;
                int h = 0;
                boolean changed = false;
                boolean needStart = false;
                boolean eventsWaiting = false;
                boolean contextCreated = false;
                synchronized (this.sGLThreadManager) {
                    while (true) {
                        if (this.mPaused) {
                            stopEglLocked();
                        }
                        if (!this.mHasSurface) {
                            if (!this.mWaitingForSurface) {
                                stopEglLocked();
                                this.mWaitingForSurface = true;
                                this.sGLThreadManager.notifyAll();
                            }
                        } else if (!this.mHaveEgl && this.sGLThreadManager.tryAcquireEglSurface(this)) {
                            this.mHaveEgl = true;
                            contextCreated = this.mEglHelper.start();
                            this.mRequestRender = true;
                            needStart = true;
                        }
                        if (this.mDone) {
                            synchronized (this.sGLThreadManager) {
                                stopEglLocked();
                                this.mEglHelper.finish();
                            }
                            return;
                        } else if (this.mEventsWaiting) {
                            eventsWaiting = true;
                            this.mEventsWaiting = false;
                            break;
                        } else if (this.mPaused || !this.mHasSurface || !this.mHaveEgl || this.mWidth <= 0 || this.mHeight <= 0 || (!this.mRequestRender && this.mRenderMode != 1)) {
                            this.sGLThreadManager.wait();
                        }
                    }
                    changed = this.mSizeChanged;
                    w = this.mWidth;
                    h = this.mHeight;
                    this.mSizeChanged = false;
                    this.mRequestRender = false;
                    if (this.mHasSurface && this.mWaitingForSurface) {
                        changed = true;
                        this.mWaitingForSurface = false;
                        this.sGLThreadManager.notifyAll();
                    }
                }
            } catch (Throwable th) {
                synchronized (this.sGLThreadManager) {
                    stopEglLocked();
                    this.mEglHelper.finish();
                    throw th;
                }
            }
        }
        synchronized (this.sGLThreadManager) {
            stopEglLocked();
            this.mEglHelper.finish();
        }
    }

    private boolean isDone() {
        boolean z;
        synchronized (this.sGLThreadManager) {
            z = this.mDone;
        }
        return z;
    }

    public void setRenderMode(int renderMode) {
        if (renderMode < 0 || renderMode > 1) {
            throw new IllegalArgumentException("renderMode");
        }
        synchronized (this.sGLThreadManager) {
            this.mRenderMode = renderMode;
            if (renderMode == 1) {
                this.sGLThreadManager.notifyAll();
            }
        }
    }

    public int getRenderMode() {
        int i;
        synchronized (this.sGLThreadManager) {
            i = this.mRenderMode;
        }
        return i;
    }

    public void requestRender() {
        synchronized (this.sGLThreadManager) {
            this.mRequestRender = true;
            this.sGLThreadManager.notifyAll();
        }
    }

    public void surfaceCreated(SurfaceHolder holder) {
        this.mHolder = holder;
        synchronized (this.sGLThreadManager) {
            this.mHasSurface = true;
            this.sGLThreadManager.notifyAll();
        }
    }

    public void surfaceDestroyed() {
        synchronized (this.sGLThreadManager) {
            this.mHasSurface = false;
            this.sGLThreadManager.notifyAll();
            while (!this.mWaitingForSurface && isAlive() && !this.mDone) {
                try {
                    this.sGLThreadManager.wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    public void onPause() {
        synchronized (this.sGLThreadManager) {
            this.mPaused = true;
            this.sGLThreadManager.notifyAll();
        }
    }

    public void onResume() {
        synchronized (this.sGLThreadManager) {
            this.mPaused = false;
            this.mRequestRender = true;
            this.sGLThreadManager.notifyAll();
        }
    }

    public void onWindowResize(int w, int h) {
        synchronized (this.sGLThreadManager) {
            this.mWidth = w;
            this.mHeight = h;
            this.mSizeChanged = true;
            this.sGLThreadManager.notifyAll();
        }
    }

    public void requestExitAndWait() {
        synchronized (this.sGLThreadManager) {
            this.mDone = true;
            this.sGLThreadManager.notifyAll();
        }
        try {
            join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void queueEvent(Runnable r) {
        synchronized (this) {
            this.mEventQueue.add(r);
            synchronized (this.sGLThreadManager) {
                this.mEventsWaiting = true;
                this.sGLThreadManager.notifyAll();
            }
        }
    }

    private Runnable getEvent() {
        synchronized (this) {
            if (this.mEventQueue.size() <= 0) {
                return null;
            }
            Runnable remove = this.mEventQueue.remove(0);
            return remove;
        }
    }
}
