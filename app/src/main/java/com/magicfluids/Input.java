package com.magicfluids;

public class Input {
    MotionEventWrapper[] Events = new MotionEventWrapper[1024];
    int NumEvents;

    Input() {
        for (int i = 0; i < 1024; i++) {
            this.Events[i] = new MotionEventWrapper();
        }
        this.NumEvents = 0;
    }
}
