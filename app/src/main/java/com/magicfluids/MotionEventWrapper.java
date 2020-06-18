package com.magicfluids;

public class MotionEventWrapper {
    static final int EVENT_DOWN = 0;
    static final int EVENT_MOVE = 2;
    static final int EVENT_POINTER_DOWN = 5;
    static final int EVENT_POINTER_UP = 6;
    static final int EVENT_UP = 1;
    int ID;
    float PosX;
    float PosY;
    int Type;

    /* access modifiers changed from: package-private */
    public void set(MotionEventWrapper other) {
        this.Type = other.Type;
        this.PosX = other.PosX;
        this.PosY = other.PosY;
        this.ID = other.ID;
    }
}
