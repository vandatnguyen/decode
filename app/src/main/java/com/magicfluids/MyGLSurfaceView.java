package com.magicfluids;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class MyGLSurfaceView extends GLSurfaceView {
    public MyGLSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyGLSurfaceView(Context context) {
        super(context);
    }

    public boolean onTouchEvent(MotionEvent event) {
        InputBuffer.Instance.addEvent(event);
        return true;
    }

    public void onPause() {
        super.onPause();
    }

    public void onResume() {
        super.onResume();
    }
}
