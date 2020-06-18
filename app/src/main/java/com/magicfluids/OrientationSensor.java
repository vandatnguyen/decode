package com.magicfluids;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.view.WindowManager;

import static android.content.Context.SENSOR_SERVICE;

public class OrientationSensor implements SensorEventListener {
    public float AccelerationX;
    public float AccelerationY;
    public int Orientation;
    private float[] acceleration;
    private Sensor accelerometer;
    private Application application;
    private boolean registered;
    private SensorManager sensorManager;

    public boolean isRegistered() {
        return this.registered;
    }

    public OrientationSensor(Context context, Application app) {
        this.sensorManager = (SensorManager)context.getSystemService(SENSOR_SERVICE);
        this.accelerometer = this.sensorManager.getDefaultSensor(1);
        this.application = app;
        this.registered = false;
    }

    public void register() {
        if (!this.registered) {
            this.sensorManager.registerListener(this, this.accelerometer, 3);
            this.registered = true;
        }
    }

    public void unregister() {
        if (this.registered) {
            this.sensorManager.unregisterListener(this);
            this.registered = false;
        }
    }

    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    @SuppressLint("WrongConstant")
    public void onSensorChanged(SensorEvent event) {
        this.acceleration = (float[]) event.values.clone();
        this.AccelerationX = this.acceleration[1];
        this.AccelerationY = this.acceleration[0];
        this.Orientation = ((WindowManager) this.application.getSystemService("window")).getDefaultDisplay().getRotation();
    }
}
