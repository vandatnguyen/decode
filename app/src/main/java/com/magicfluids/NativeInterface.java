package com.magicfluids;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLUtils;

import java.io.IOException;
import java.io.InputStream;

public class NativeInterface {
    public static boolean loadingFailed;
    public static Object nativeLock = new Object();
    public static Object nextIdLock = new Object();
    private static int nextID = 0;
    private static byte[] tmpBuf = new byte[2097152];

    static {
        loadingFailed = false;
        loadingFailed = false;
        try {
            System.loadLibrary("nativelib");
        } catch (UnsatisfiedLinkError e) {
            loadingFailed = true;
        }
    }

    private int ID;
    private AssetManager mAssetMgr;
    private volatile boolean mDrawOnly = false;
    private volatile boolean mPaused = true;

    public NativeInterface() {
        synchronized (nextIdLock) {
            int i = nextID;
            nextID = i + 1;
            this.ID = i;
        }
    }

    private native void clearScreenImpl(int i);

    private native void onCreateImpl(int i, int i2, int i3, boolean z);

    private native void onDestroyImpl(int i);

    private native void onGLContextRestartedImpl(int i);

    private native void onMotionEventImpl(int i, MotionEventWrapper motionEventWrapper);

    private native void onPauseImpl(int i);

    private native void onResumeImpl(int i);

    private native int perfHeuristicImpl(int i);

    private native void setAvailableGPUExtensionsImpl(int i, boolean z, boolean z2);

    private native void updateAppImpl(int i, boolean z, boolean z2, float f, float f2, int i2);

    private native void updateSettingsImpl(int i, Settings settings);

    private native void windowChangedImpl(int i, int i2, int i3);

    public int getID() {
        return this.ID;
    }

    public void windowChanged(int width, int height) {
        if (!loadingFailed) {
            if (!this.mPaused) {
                this.mPaused = true;
                synchronized (nativeLock) {
                    windowChangedImpl(this.ID, width, height);
                }
                this.mPaused = false;
                return;
            }
            synchronized (nativeLock) {
                windowChangedImpl(this.ID, width, height);
            }
        }
    }

    public void updateApp(boolean ignoreNextFrameTime, float accelX, float accelY, int orientation) {
        if (!loadingFailed && !this.mPaused) {
            synchronized (nativeLock) {
                updateAppImpl(this.ID, ignoreNextFrameTime, this.mDrawOnly, accelX, accelY, orientation);
            }
        }
    }

    public void onMotionEvent(MotionEventWrapper event) {
        if (!loadingFailed) {
            synchronized (nativeLock) {
                onMotionEventImpl(this.ID, event);
            }
        }
    }

    public void onCreate(int width, int height, boolean isLwp) {
        if (!loadingFailed) {
            if (!this.mPaused) {
                this.mPaused = true;
                synchronized (nativeLock) {
                    LogUtil.w("nativeint", "onCreate while not paused! " + this.ID);
                    onCreateImpl(this.ID, width, height, isLwp);
                }
                this.mPaused = false;
                return;
            }
            synchronized (nativeLock) {
                LogUtil.w("nativeint", "onCreate " + this.ID);
                onCreateImpl(this.ID, width, height, isLwp);
            }
        }
    }

    public void onDestroy() {
        if (!loadingFailed) {
            if (!this.mPaused) {
                this.mPaused = true;
                synchronized (nativeLock) {
                    LogUtil.w("nativeint", "onDestroy while not paused! " + this.ID);
                    onDestroyImpl(this.ID);
                }
                this.mPaused = false;
                return;
            }
            synchronized (nativeLock) {
                LogUtil.w("nativeint", "onDestroy " + this.ID);
                onDestroyImpl(this.ID);
            }
        }
    }

    public void onPause() {
        if (!loadingFailed) {
            if (!this.mPaused) {
                this.mPaused = true;
                synchronized (nativeLock) {
                    LogUtil.w("nativeint", "onPause " + this.ID);
                    onPauseImpl(this.ID);
                }
                return;
            }
            LogUtil.w("nativeint", "onPause no effect " + this.ID);
        }
    }

    public void onResume() {
        if (!loadingFailed) {
            if (this.mPaused) {
                this.mPaused = false;
                synchronized (nativeLock) {
                    LogUtil.w("nativeint", "onResume " + this.ID);
                    onResumeImpl(this.ID);
                }
                return;
            }
            LogUtil.w("nativeint", "onResume no effect " + this.ID);
        }
    }

    public void clearScreen() {
        if (!loadingFailed) {
            if (!this.mPaused) {
                this.mPaused = true;
                synchronized (nativeLock) {
                    clearScreenImpl(this.ID);
                }
                this.mPaused = false;
                return;
            }
            synchronized (nativeLock) {
                clearScreenImpl(this.ID);
            }
        }
    }

    public void onGLContextRestarted() {
        if (!loadingFailed) {
            synchronized (nativeLock) {
                LogUtil.w("nativeint", "onGLContextRestarted " + this.ID);
                onGLContextRestartedImpl(this.ID);
            }
        }
    }

    public void updateSettings(Settings settings) {
        if (!loadingFailed) {
            if (!this.mPaused) {
                this.mPaused = true;
                synchronized (nativeLock) {
                    updateSettingsImpl(this.ID, settings);
                }
                this.mPaused = false;
                return;
            }
            synchronized (nativeLock) {
                updateSettingsImpl(this.ID, settings);
            }
        }
    }

    public int perfHeuristic() {
        int perfValue;
        if (loadingFailed) {
            return 0;
        }
        synchronized (nativeLock) {
            perfValue = perfHeuristicImpl(this.ID);
        }
        return perfValue;
    }

    public void setAvailableGPUExtensions(boolean fpTex, boolean fpBuf) {
        if (!loadingFailed) {
            if (!this.mPaused) {
                this.mPaused = true;
                synchronized (nativeLock) {
                    setAvailableGPUExtensionsImpl(this.ID, fpTex, fpBuf);
                }
                this.mPaused = false;
                return;
            }
            synchronized (nativeLock) {
                setAvailableGPUExtensionsImpl(this.ID, fpTex, fpBuf);
            }
        }
    }

    public void onPauseAnim() {
        this.mDrawOnly = true;
    }

    public void onResumeAnim() {
        this.mDrawOnly = false;
    }

    public void setAssetManager(AssetManager ass) {
        this.mAssetMgr = ass;
    }

    public ByteArrayWithSize loadFileContentsFromAssets(String assetName) {
        ByteArrayWithSize array = new ByteArrayWithSize(null, 0);
        try {
            int bytesRead = this.mAssetMgr.open(assetName).read(tmpBuf);
            byte[] exactSizeArray = new byte[bytesRead];
            System.arraycopy(tmpBuf, 0, exactSizeArray, 0, bytesRead);
            return new ByteArrayWithSize(exactSizeArray, bytesRead);
        } catch (IOException e) {
            e.printStackTrace();
            return array;
        }
    }

    /* JADX WARNING: Unknown top exception splitter block from list: {B:18:0x0098=Splitter:B:18:0x0098, B:30:0x00cd=Splitter:B:30:0x00cd} */
    public boolean loadTexture2DFromAssets(String path) {
        InputStream is = null;
        try {
            LogUtil.i("ASSETS", "Reading texture file: textures/" + path);
            is = this.mAssetMgr.open("textures/" + path);
            if (path.contains("detail/")) {
                Bitmap bitmap = BitmapFactory.decodeStream(is);
                Bitmap bmp1Channel = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ALPHA_8);
                int w = bitmap.getWidth();
                int h = bitmap.getHeight();
                int[] data = new int[(w * h)];
                int wh = w * h;
                bitmap.getPixels(data, 0, w, 0, 0, w, h);
                for (int i = 0; i < wh; i++) {
                    data[i] = (data[i] & 16711680) << 8;
                }
                bmp1Channel.setPixels(data, 0, w, 0, 0, w, h);
                GLUtils.texImage2D(3553, 0, 6406, bmp1Channel, 0);
                bitmap.recycle();
                bmp1Channel.recycle();
                try {
                    is.close();
                } catch (IOException e) {
                }
            } else {
                Bitmap bitmap2 = BitmapFactory.decodeStream(is);
                GLUtils.texImage2D(3553, 0, bitmap2, 0);
                bitmap2.recycle();
                try {
                    is.close();
                } catch (IOException e2) {
                }
            }
            return true;
        } catch (IOException e1) {
            LogUtil.e("ASSETS", "Failed to read texture file: textures/" + path);
            e1.printStackTrace();
            return false;
        } catch (Throwable th) {
            try {
                is.close();
            } catch (IOException e3) {
            }
            throw th;
        }
    }
}
