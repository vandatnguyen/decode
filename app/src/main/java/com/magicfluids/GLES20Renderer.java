package com.magicfluids;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.os.Environment;
import android.text.format.Time;
import android.widget.Toast;

import androidx.core.view.ViewCompat;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

class GLES20Renderer implements GLSurfaceView.Renderer {
    /* access modifiers changed from: private */
    public MainActivity activity;
    Input input;
    private int avgTimeNumSamples = 0;
    private long avgTimeSamplesSum = 0;
    private boolean ignoreNextFrameTime = false;
    private long lastNanoTime = 0;
    private long maxTime = 0;
    private NativeInterface ntv;
    private OrientationSensor orientationSensor;
    private int screenHeight;
    private int screenWidth;
    private Object screenshotLock = new Object();
    private Settings settings;
    private boolean takeScreenshot = false;

    public GLES20Renderer(MainActivity act, NativeInterface nativ, OrientationSensor orientSensor, Settings set) {
        this.activity = act;
        this.ntv = nativ;
        this.orientationSensor = orientSensor;
        this.settings = set;
        this.input = new Input();
    }

    static int byteToUint(byte b) {
        if (b < 0) {
            return b + 256;
        }
        return b;
    }

    /* access modifiers changed from: package-private */
    public void setInitialScreenSize(int width, int height) {
        this.screenWidth = width;
        this.screenHeight = height;
    }

    /* access modifiers changed from: package-private */
    public void orderScreenshot() {
        synchronized (this.screenshotLock) {
            this.takeScreenshot = true;
        }
    }

    /* access modifiers changed from: package-private */
    public boolean checkScreenshotOrder() {
        synchronized (this.screenshotLock) {
            if (!this.takeScreenshot) {
                return false;
            }
            this.takeScreenshot = false;
            return true;
        }
    }

    public void saveBitmap(Bitmap bmp) {
        File dir = null;
        Object obj;
        try {
            LogUtil.i("saveBitmap()", "mkdirs result: " + Boolean.toString(new File(String.valueOf(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getAbsolutePath()) + "/MagicFluids").mkdirs()));
            Time now = new Time();
            now.setToNow();
            int month = now.month + 1;
            StringBuilder append = new StringBuilder().append(now.year).append("-").append(month >= 10 ? Integer.valueOf(month) : "0" + month).append("-").append(now.monthDay >= 10 ? Integer.valueOf(now.monthDay) : "0" + now.monthDay).append("-").append(now.hour >= 10 ? Integer.valueOf(now.hour) : "0" + now.hour).append("-").append(now.minute >= 10 ? Integer.valueOf(now.minute) : "0" + now.minute).append("-");
            if (now.second >= 10) {
                obj = Integer.valueOf(now.second);
            } else {
                obj = "0" + now.second;
            }
            File file = new File(dir, "MagicFluids" + append.append(obj).toString() + ".jpg");
            FileOutputStream fOut = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.JPEG, 100, fOut);
            fOut.flush();
            fOut.close();
            final File filetmp = file;
            this.activity.runOnUiThread(new Runnable() {
                public void run() {
                    Intent intent = new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE");
                    intent.setData(Uri.fromFile(filetmp));
                    GLES20Renderer.this.activity.sendBroadcast(intent);
                    Toast.makeText(GLES20Renderer.this.activity, "Saved to /Pictures/MagicFluids", Toast.LENGTH_SHORT).show();
                }
            });
        } catch (Exception e) {
            this.activity.runOnUiThread(new Runnable() {
                public void run() {
                    Toast.makeText(GLES20Renderer.this.activity, "External storage unavailable! Please put in SD Card or disconnect device from computer", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    public void screenshot() {
        int[] data = new int[(this.screenWidth * this.screenHeight)];
        ByteBuffer buffer = ByteBuffer.allocate(this.screenWidth * this.screenHeight * 4);
        GLES20.glGetError();
        GLES20.glPixelStorei(3333, 1);
        LogUtil.i("screenshot()", "glPixelStorei result: " + GLES20.glGetError());
        GLES20.glReadPixels(0, 0, this.screenWidth, this.screenHeight, 6408, 5121, buffer);
        LogUtil.i("screenshot()", "glReadPixels result: " + GLES20.glGetError());
        byte[] byteData = buffer.array();
        boolean wtf = false;
        for (int y = 0; y < this.screenHeight; y++) {
            for (int x = 0; x < this.screenWidth; x++) {
                byte r = byteData[((((this.screenHeight - 1) - y) * this.screenWidth) + x) * 4];
                byte g = byteData[(((((this.screenHeight - 1) - y) * this.screenWidth) + x) * 4) + 1];
                byte b = byteData[(((((this.screenHeight - 1) - y) * this.screenWidth) + x) * 4) + 2];
                if (r < 0 || g < 0 || b < 0) {
                    wtf = true;
                }
                data[(this.screenWidth * y) + x] = ViewCompat.MEASURED_STATE_MASK + (byteToUint(r) << 16) + (byteToUint(g) << 8) + byteToUint(b);
            }
        }
        saveBitmap(Bitmap.createBitmap(data, this.screenWidth, this.screenHeight, Bitmap.Config.ARGB_8888));
    }

    public void onDrawFrame(GL10 glUnused) {
        long newTime = System.nanoTime();
        long diff = newTime - this.lastNanoTime;
        this.avgTimeSamplesSum += diff;
        this.avgTimeNumSamples++;
        if (diff > this.maxTime) {
            this.maxTime = diff;
        }
        this.lastNanoTime = newTime;
        if (this.avgTimeNumSamples == 25) {
            if (this.activity != null) {
                this.activity.updateFrameTime(String.valueOf(String.valueOf((((float) this.avgTimeSamplesSum) / 25.0f) / 1000000.0f)) + " Max: " + String.valueOf(((float) this.maxTime) / 1000000.0f));
            }
            this.avgTimeSamplesSum = 0;
            this.avgTimeNumSamples = 0;
            this.maxTime = 0;
        }
        InputBuffer.Instance.getCurrentInputState(this.input);
        for (int i = 0; i < this.input.NumEvents; i++) {
            this.ntv.onMotionEvent(this.input.Events[i]);
        }
        this.ntv.updateApp(this.ignoreNextFrameTime, this.orientationSensor.AccelerationX, this.orientationSensor.AccelerationY, this.orientationSensor.Orientation);
        this.ignoreNextFrameTime = false;
        if (checkScreenshotOrder()) {
            screenshot();
            this.ignoreNextFrameTime = true;
        }
    }

    public void onSurfaceChanged(GL10 glUnused, int width, int height) {
        this.screenWidth = width;
        this.screenHeight = height;
        this.ntv.windowChanged(width, height);
    }

    public void checkGPUExtensions() {
        String ext = GLES20.glGetString(7939);
        this.ntv.setAvailableGPUExtensions(ext.contains("GL_OES_texture_half_float") && ext.contains("GL_OES_texture_half_float_linear"), ext.contains("GL_EXT_color_buffer_half_float"));
    }

    public void onSurfaceCreated(GL10 glUnused, EGLConfig config) {
        checkGPUExtensions();
        if (!isLWP()) {
            onEGLContextCreated();
        }
    }

    private boolean isLWP() {
        return this.activity == null;
    }

    public void onEGLContextCreated() {
        try {
            this.ntv.onGLContextRestarted();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
