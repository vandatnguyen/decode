package yuku.ambilwarna;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.magicfluids.demo.R;

public class AmbilWarnaDialog {
    final float[] currentColorHsv = new float[3];
    final AlertDialog dialog;
    final OnAmbilWarnaListener listener;
    final ViewGroup viewContainer;
    final ImageView viewCursor;
    final View viewHue;
    final View viewNewColor;
    final View viewOldColor;
    final AmbilWarnaKotak viewSatVal;
    final ImageView viewTarget;

    public interface OnAmbilWarnaListener {
        void onCancel(AmbilWarnaDialog ambilWarnaDialog);

        void onOk(AmbilWarnaDialog ambilWarnaDialog, int i);
    }

    public void setCurrentColor(int color) {
        Color.colorToHSV(color, this.currentColorHsv);
        this.viewSatVal.setHue(getHue());
        this.viewOldColor.setBackgroundColor(color);
        this.viewNewColor.setBackgroundColor(color);
        moveCursor();
        moveTarget();
    }

    public AmbilWarnaDialog(Context context, int color, OnAmbilWarnaListener listener2) {
        this.listener = listener2;
        Color.colorToHSV(color, this.currentColorHsv);
        final View view = LayoutInflater.from(context).inflate(R.layout.ambilwarna_dialog, null);
        this.viewHue = view.findViewById(R.id.ambilwarna_viewHue);
        this.viewSatVal = (AmbilWarnaKotak) view.findViewById(R.id.ambilwarna_viewSatBri);
        this.viewCursor = (ImageView) view.findViewById(R.id.ambilwarna_cursor);
        this.viewOldColor = view.findViewById(R.id.ambilwarna_warnaLama);
        this.viewNewColor = view.findViewById(R.id.ambilwarna_warnaBaru);
        this.viewTarget = (ImageView) view.findViewById(R.id.ambilwarna_target);
        this.viewContainer = (ViewGroup) view.findViewById(R.id.ambilwarna_viewContainer);
        this.viewSatVal.setHue(getHue());
        this.viewOldColor.setBackgroundColor(color);
        this.viewNewColor.setBackgroundColor(color);
        this.viewHue.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() != 2 && event.getAction() != 0 && event.getAction() != 1) {
                    return false;
                }
                float y = event.getY();
                if (y < 0.0f) {
                    y = 0.0f;
                }
                if (y > ((float) AmbilWarnaDialog.this.viewHue.getMeasuredHeight())) {
                    y = ((float) AmbilWarnaDialog.this.viewHue.getMeasuredHeight()) - 0.001f;
                }
                float hue = 360.0f - ((360.0f / ((float) AmbilWarnaDialog.this.viewHue.getMeasuredHeight())) * y);
                if (hue == 360.0f) {
                    hue = 0.0f;
                }
                AmbilWarnaDialog.this.setHue(hue);
                AmbilWarnaDialog.this.viewSatVal.setHue(AmbilWarnaDialog.this.getHue());
                AmbilWarnaDialog.this.moveCursor();
                AmbilWarnaDialog.this.viewNewColor.setBackgroundColor(AmbilWarnaDialog.this.getColor());
                return true;
            }
        });
        this.viewSatVal.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() != 2 && event.getAction() != 0 && event.getAction() != 1) {
                    return false;
                }
                float x = event.getX();
                float y = event.getY();
                if (x < 0.0f) {
                    x = 0.0f;
                }
                if (x > ((float) AmbilWarnaDialog.this.viewSatVal.getMeasuredWidth())) {
                    x = (float) AmbilWarnaDialog.this.viewSatVal.getMeasuredWidth();
                }
                if (y < 0.0f) {
                    y = 0.0f;
                }
                if (y > ((float) AmbilWarnaDialog.this.viewSatVal.getMeasuredHeight())) {
                    y = (float) AmbilWarnaDialog.this.viewSatVal.getMeasuredHeight();
                }
                AmbilWarnaDialog.this.setSat((1.0f / ((float) AmbilWarnaDialog.this.viewSatVal.getMeasuredWidth())) * x);
                AmbilWarnaDialog.this.setVal(1.0f - ((1.0f / ((float) AmbilWarnaDialog.this.viewSatVal.getMeasuredHeight())) * y));
                AmbilWarnaDialog.this.moveTarget();
                AmbilWarnaDialog.this.viewNewColor.setBackgroundColor(AmbilWarnaDialog.this.getColor());
                return true;
            }
        });
        this.dialog = new AlertDialog.Builder(context).setPositiveButton(17039370, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                if (AmbilWarnaDialog.this.listener != null) {
                    AmbilWarnaDialog.this.listener.onOk(AmbilWarnaDialog.this, AmbilWarnaDialog.this.getColor());
                }
            }
        }).setNegativeButton(17039360, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                if (AmbilWarnaDialog.this.listener != null) {
                    AmbilWarnaDialog.this.listener.onCancel(AmbilWarnaDialog.this);
                }
            }
        }).setOnCancelListener(new DialogInterface.OnCancelListener() {
            public void onCancel(DialogInterface paramDialogInterface) {
                if (AmbilWarnaDialog.this.listener != null) {
                    AmbilWarnaDialog.this.listener.onCancel(AmbilWarnaDialog.this);
                }
            }
        }).create();
        this.dialog.setView(view, 0, 0, 0, 0);
        view.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            public void onGlobalLayout() {
                AmbilWarnaDialog.this.moveCursor();
                AmbilWarnaDialog.this.moveTarget();
                view.getViewTreeObserver().removeGlobalOnLayoutListener(this);
            }
        });
    }

    /* access modifiers changed from: protected */
    public void moveCursor() {
        float y = ((float) this.viewHue.getMeasuredHeight()) - ((getHue() * ((float) this.viewHue.getMeasuredHeight())) / 360.0f);
        if (y == ((float) this.viewHue.getMeasuredHeight())) {
            y = 0.0f;
        }
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.viewCursor.getLayoutParams();
        layoutParams.leftMargin = (int) ((((double) this.viewHue.getLeft()) - Math.floor((double) (this.viewCursor.getMeasuredWidth() / 2))) - ((double) this.viewContainer.getPaddingLeft()));
        layoutParams.topMargin = (int) ((((double) (((float) this.viewHue.getTop()) + y)) - Math.floor((double) (this.viewCursor.getMeasuredHeight() / 2))) - ((double) this.viewContainer.getPaddingTop()));
        this.viewCursor.setLayoutParams(layoutParams);
    }

    /* access modifiers changed from: protected */
    public void moveTarget() {
        float x = getSat() * ((float) this.viewSatVal.getMeasuredWidth());
        float y = (1.0f - getVal()) * ((float) this.viewSatVal.getMeasuredHeight());
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.viewTarget.getLayoutParams();
        layoutParams.leftMargin = (int) ((((double) (((float) this.viewSatVal.getLeft()) + x)) - Math.floor((double) (this.viewTarget.getMeasuredWidth() / 2))) - ((double) this.viewContainer.getPaddingLeft()));
        layoutParams.topMargin = (int) ((((double) (((float) this.viewSatVal.getTop()) + y)) - Math.floor((double) (this.viewTarget.getMeasuredHeight() / 2))) - ((double) this.viewContainer.getPaddingTop()));
        this.viewTarget.setLayoutParams(layoutParams);
    }

    /* access modifiers changed from: private */
    public int getColor() {
        return Color.HSVToColor(this.currentColorHsv);
    }

    /* access modifiers changed from: private */
    public float getHue() {
        return this.currentColorHsv[0];
    }

    private float getSat() {
        return this.currentColorHsv[1];
    }

    private float getVal() {
        return this.currentColorHsv[2];
    }

    /* access modifiers changed from: private */
    public void setHue(float hue) {
        this.currentColorHsv[0] = hue;
    }

    /* access modifiers changed from: private */
    public void setSat(float sat) {
        this.currentColorHsv[1] = sat;
    }

    /* access modifiers changed from: private */
    public void setVal(float val) {
        this.currentColorHsv[2] = val;
    }

    public void show() {
        this.dialog.show();
    }

    public AlertDialog getDialog() {
        return this.dialog;
    }
}
