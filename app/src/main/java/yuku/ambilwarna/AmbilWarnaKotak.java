package yuku.ambilwarna;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ComposeShader;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

import androidx.core.view.ViewCompat;

public class AmbilWarnaKotak extends View {
    final float[] color;
    Shader luar;
    Paint paint;

    public AmbilWarnaKotak(Context context) {
        this(context, null);
    }

    public AmbilWarnaKotak(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AmbilWarnaKotak(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.color = new float[]{1.0f, 1.0f, 1.0f};
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.paint == null) {
            this.paint = new Paint();
            this.luar = new LinearGradient(0.0f, 0.0f, 0.0f, (float) getMeasuredHeight(), -1, ViewCompat.MEASURED_STATE_MASK, Shader.TileMode.CLAMP);
        }
        this.paint.setShader(new ComposeShader(this.luar, new LinearGradient(0.0f, 0.0f, (float) getMeasuredWidth(), 0.0f, -1, Color.HSVToColor(this.color), Shader.TileMode.CLAMP), PorterDuff.Mode.MULTIPLY));
        canvas.drawRect(0.0f, 0.0f, (float) getMeasuredWidth(), (float) getMeasuredHeight(), this.paint);
    }

    /* access modifiers changed from: package-private */
    public void setHue(float hue) {
        this.color[0] = hue;
        invalidate();
    }
}
