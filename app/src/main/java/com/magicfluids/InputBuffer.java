package com.magicfluids;

import android.view.MotionEvent;
import java.util.concurrent.ArrayBlockingQueue;

public class InputBuffer {
    static InputBuffer Instance = new InputBuffer();
    static final int MAX_EVENTS = 1024;
    private ArrayBlockingQueue<MotionEventWrapper> eventPool;
    private ArrayBlockingQueue<MotionEventWrapper> newEvents;

    InputBuffer() {
        init();
    }

    /* access modifiers changed from: package-private */
    public void init() {
        this.eventPool = new ArrayBlockingQueue<>(1024);
        this.newEvents = new ArrayBlockingQueue<>(1024);
        for (int i = 0; i < 1024; i++) {
            this.eventPool.add(new MotionEventWrapper());
        }
    }

    /* access modifiers changed from: package-private */
    public boolean addNewEvent(int action, int id, float x, float y) {
        MotionEventWrapper eventWrapper = this.eventPool.poll();
        if (eventWrapper == null) {
            return false;
        }
        eventWrapper.Type = action;
        eventWrapper.PosX = x;
        eventWrapper.PosY = y;
        eventWrapper.ID = id;
        this.newEvents.add(eventWrapper);
        return true;
    }

    /* access modifiers changed from: package-private */
    public void addEvent(MotionEvent event) {
        int action = event.getActionMasked();
        if (action == 0) {
            int i = 0;
            while (i < event.getPointerCount() && addNewEvent(0, event.getPointerId(i), event.getX(i), event.getY(i))) {
                i++;
            }
        } else if (action == 1 || action == 3) {
            int i2 = 0;
            while (i2 < event.getPointerCount() && addNewEvent(1, event.getPointerId(i2), event.getX(i2), event.getY(i2))) {
                i2++;
            }
        } else {
            if (action == 5) {
                int index = event.getActionIndex();
                if (addNewEvent(5, event.getPointerId(index), event.getX(index), event.getY(index))) {
                    int pointerIndexToOmit = index;
                } else {
                    return;
                }
            }
            if (action == 6) {
                int index2 = event.getActionIndex();
                if (addNewEvent(6, event.getPointerId(index2), event.getX(index2), event.getY(index2))) {
                    int pointerIndexToOmit2 = index2;
                } else {
                    return;
                }
            }
            if (action == 2) {
                int i3 = 0;
                while (i3 < event.getPointerCount() && addNewEvent(2, event.getPointerId(i3), event.getX(i3), event.getY(i3))) {
                    i3++;
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void getCurrentInputState(Input in) {
        in.NumEvents = 0;
        while (!this.newEvents.isEmpty()) {
            MotionEventWrapper event = this.newEvents.poll();
            if (event != null) {
                MotionEventWrapper[] motionEventWrapperArr = in.Events;
                int i = in.NumEvents;
                in.NumEvents = i + 1;
                motionEventWrapperArr[i].set(event);
                this.eventPool.add(event);
            }
        }
    }
}
