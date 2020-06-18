package com.magicfluids;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.WallpaperInfo;
import android.app.WallpaperManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.text.InputFilter;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.view.MotionEventCompat;
import androidx.core.view.ViewCompat;

import com.magicfluids.demo.R;

import yuku.ambilwarna.AmbilWarnaDialog;

public class SettingsController {
    private static final int NUM_COLORS = 6;
    private static final int NUM_DCOLORS = 3;
    private static final int NUM_MCOLORS = 9;
    private static final int NUM_USER_PRESETS = 10;
    /* access modifiers changed from: private */
    public SettingsActivity activity;
    /* access modifiers changed from: private */
    /* access modifiers changed from: private */
    public Button buttonBackgrColor;
    /* access modifiers changed from: private */
    public Button[] buttonColor = new Button[6];
    /* access modifiers changed from: private */
    public Button[] buttonDColor = new Button[3];
    private Button buttonEffectsQualInfo;
    /* access modifiers changed from: private */
    public Button buttonLightColor;
    /* access modifiers changed from: private */
    public Button[] buttonLoadPreset = new Button[10];
    /* access modifiers changed from: private */
    public Button[] buttonMColor = new Button[9];
    /* access modifiers changed from: private */
    public Button buttonParticleColor;
    /* access modifiers changed from: private */
    public Button[] buttonSavePreset = new Button[10];
    private Button buttonSimQualInfo;
    private CheckBox checkAutoOnResume;
    private CheckBox checkCartoonColors;
    /* access modifiers changed from: private */
    public CheckBox[] checkColor = new CheckBox[6];
    /* access modifiers changed from: private */
    public CheckBox[] checkDColor = new CheckBox[3];
    private CheckBox checkDetailTexture;
    private CheckBox checkGlow;
    private CheckBox checkInputSwipeConstant;
    private CheckBox checkInvertColors;
    private CheckBox checkLightSource;
    private CheckBox checkOverbrightColors;
    private CheckBox checkParticles;
    private CheckBox checkShadingEnabled;
    private CheckBox checkShadingSpecOnlyGlow;
    private CheckBox checkShadowInverse;
    private CheckBox checkShadowSelf;
    private CheckBox checkShadowSource;
    /* access modifiers changed from: private */
    public int chosenButtonColorID = 0;
    /* access modifiers changed from: private */
    public int chosenButtonDColorID = 0;
    /* access modifiers changed from: private */
    public int chosenButtonMColorID = 0;
    /* access modifiers changed from: private */
    public AmbilWarnaDialog dialogBackgrColorPicker;
    /* access modifiers changed from: private */
    public AlertDialog.Builder dialogChoosePreset;
    /* access modifiers changed from: private */
    public AmbilWarnaDialog dialogColorPicker;
    /* access modifiers changed from: private */
    public AmbilWarnaDialog dialogDColorPicker;
    /* access modifiers changed from: private */
    public AmbilWarnaDialog dialogLightColorPicker;
    /* access modifiers changed from: private */
    public AmbilWarnaDialog dialogMColorPicker;
    /* access modifiers changed from: private */
    public AmbilWarnaDialog dialogParticleColorPicker;
    /* access modifiers changed from: private */
    public boolean isLiveWallpaper;
    /* access modifiers changed from: private */
    public int lastWantedPreset = 0;
    /* access modifiers changed from: private */
    public NativeInterface ntv;
    private RadioGroup radioGroupBorder;
    private RadioGroup radioGroupColorChange;
    private RadioGroup radioGroupColorOption;
    private RadioGroup radioGroupFluidType;
    private RadioGroup radioGroupInputSwipeMode;
    private RadioGroup radioGroupInputTouchMode;
    private RadioGroup radioGroupMultiColorDouble;
    private RadioGroup radioGroupParticleColor;
    private RadioGroup radioGroupParticlesMode;
    private RadioGroup radioGroupParticlesShape;
    private SeekBar seekColorAddSaturation;
    private SeekBar seekDetailUVScale;
    private SeekBar seekFluidAmount;
    private SeekBar seekFluidLifeTime;
    private SeekBar seekGlowPartIntens;
    private SeekBar seekGlowStr0;
    private SeekBar seekGlowStr1;
    private SeekBar seekGlowStr2;
    private SeekBar seekGlowThreshold;
    private SeekBar seekGravity;
    private SeekBar seekInputForce;
    private SeekBar seekInputSize;
    private SeekBar seekLightIntensity;
    private SeekBar seekLightRadius;
    private SeekBar seekLightSourcePosX;
    private SeekBar seekLightSourcePosY;
    private SeekBar seekLightSourceSpeed;
    /* access modifiers changed from: private */
    public SeekBar seekParticlesLifeTimeMs;
    /* access modifiers changed from: private */
    public SeekBar seekParticlesPerSec;
    private SeekBar seekParticlesSize;
    private SeekBar seekRandomSaturation;
    private SeekBar seekShadingBumpiness;
    private SeekBar seekShadingSpecPower;
    private SeekBar seekShadingSpecular;
    private SeekBar seekShadowFalloff;
    private SeekBar seekShadowIntensity;
    private SeekBar seekSourceSpeed;
    private SeekBar seekSwirliness;
    private SeekBar seekTouchInputForce;
    private SeekBar seekTouchInputSize;
    private SeekBar seekVelLifetime;
    /* access modifiers changed from: private */
    public Settings settings;
    private Spinner spinnerDetailTexture;
    private Spinner spinnerEffectsQual;
    private Spinner spinnerNumSources;
    private Spinner spinnerPaintRes;
    private Spinner spinnerQuality;
    /* access modifiers changed from: private */
    public TextView[] textPreset = new TextView[10];

    /* access modifiers changed from: package-private */
    public int getObjectIndex(Object obj, Object[] objects) {
        for (int i = 0; i < objects.length; i++) {
            if (obj == objects[i]) {
                return i;
            }
        }
        return -1;
    }

    /* access modifiers changed from: package-private */
    public void setColorModeUIVisibility() {
        int i;
        int i2;
        int i3;
        int i4 = 8;
        this.activity.findViewById(R.id.layoutPaintColorRandom).setVisibility(this.settings.ColorOption == 0 ? View.VISIBLE : View.GONE);
        View findViewById = this.activity.findViewById(R.id.layoutPaintColorPalette);
        if (this.settings.ColorOption == 1) {
            i = 0;
        } else {
            i = 8;
        }
        findViewById.setVisibility(i);
        View findViewById2 = this.activity.findViewById(R.id.layoutPaintDoublePalette);
        if (this.settings.ColorOption == 2) {
            i2 = 0;
        } else {
            i2 = 8;
        }
        findViewById2.setVisibility(i2);
        View findViewById3 = this.activity.findViewById(R.id.layoutPaintMultiColors);
        if (this.settings.ColorOption == 4) {
            i3 = 0;
        } else {
            i3 = 8;
        }
        findViewById3.setVisibility(i3);
        View findViewById4 = this.activity.findViewById(R.id.layoutOverbrightColors);
        if (this.settings.ColorOption == 0 || this.settings.ColorOption == 1) {
            i4 = 0;
        }
        findViewById4.setVisibility(i4);
    }

    /* access modifiers changed from: package-private */
    public void initUserPresets() {
        for (int i = 0; i < 10; i++) {
            try {
                this.textPreset[i] = (TextView) this.activity.findViewById(R.id.textPreset0 + (i * 3));
                this.buttonLoadPreset[i] = (Button) this.activity.findViewById(R.id.buttonLoadPreset0 + (i * 3));
                this.buttonSavePreset[i] = (Button) this.activity.findViewById(R.id.buttonSavePreset0 + (i * 3));
                this.buttonLoadPreset[i].setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        final int index = SettingsController.this.getObjectIndex(v, SettingsController.this.buttonLoadPreset);
                        AlertDialog.Builder builder = new AlertDialog.Builder(SettingsController.this.activity);
                        builder.setTitle("Load " + SettingsStorage.getUserPresetName(SettingsController.this.activity, index) + " preset?");
                        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                SettingsStorage.loadSettingsFromUserPreset(SettingsController.this.activity, SettingsController.this.settings, index);
                                SettingsController.this.setEverythingFromSettingsCurrent();
                                SettingsController.this.activity.onSettingsChanged();
                                if (SettingsController.this.ntv != null) {
                                    SettingsController.this.ntv.clearScreen();
                                }
                                if (SettingsController.this.isLiveWallpaper) {
                                    SettingsController.this.settings.ReloadRequired = true;
                                }
                            }
                        });
                        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                            }
                        });
                        builder.show();
                    }
                });
                this.buttonSavePreset[i].setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(SettingsController.this.activity);
                        builder.setTitle("Choose preset name");
                        final int index = SettingsController.this.getObjectIndex(v, SettingsController.this.buttonSavePreset);
                        final EditText input = new EditText(SettingsController.this.activity);
                        input.setFilters(new InputFilter[]{new InputFilter.LengthFilter(16)});
                        builder.setView(input);
                        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                SettingsController.this.textPreset[index].setText(input.getText().toString());
                                SettingsStorage.saveSettingsToUserPreset(SettingsController.this.activity, SettingsController.this.settings, index, input.getText().toString());
                                SettingsController.this.buttonLoadPreset[index].setEnabled(true);
                            }
                        });
                        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                            }
                        });
                        builder.show();
                    }
                });
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void initTabs() {
        TabHost tabHost = (TabHost) this.activity.findViewById(R.id.tabHost);
        tabHost.setup();
        tabHost.addTab(tabHost.newTabSpec("tab1").setIndicator("PRESETS").setContent(R.id.tabPresets));
        tabHost.addTab(tabHost.newTabSpec("tab2").setIndicator("ANIMATION").setContent(R.id.tabSimulation));
        if (this.isLiveWallpaper) {
            tabHost.addTab(tabHost.newTabSpec("tab3").setIndicator("INPUT").setContent(R.id.tabInputParent));
        }
        tabHost.addTab(tabHost.newTabSpec("tab4").setIndicator("PAINT").setContent(R.id.tabPaint));
        tabHost.addTab(tabHost.newTabSpec("tab5").setIndicator("PARTICLES").setContent(R.id.tabParticles));
        tabHost.addTab(tabHost.newTabSpec("tab6").setIndicator("EFFECTS").setContent(R.id.tabEffects));
        tabHost.addTab(tabHost.newTabSpec("tab7").setIndicator("INFO").setContent(R.id.tabAbout));
        tabHost.getTabWidget().setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
        for (int i = 0; i < 6; i++) {
            View tabView = tabHost.getTabWidget().getChildTabViewAt(i);
            tabView.setPadding(5, 5, 5, 5);
            TextView tv = (TextView) tabView.findViewById(16908310);
            tv.setSingleLine();
            tv.setTextSize(12.0f);
        }
        TabHost tabHost2 = (TabHost) this.activity.findViewById(R.id.tabHostInput);
        tabHost2.setup();
        tabHost2.addTab(tabHost2.newTabSpec("tab1").setIndicator("", this.activity.getResources().getDrawable(R.drawable.but_hand_swipe)).setContent(R.id.tabInput));
        tabHost2.addTab(tabHost2.newTabSpec("tab2").setIndicator("", this.activity.getResources().getDrawable(R.drawable.but_hand_tap)).setContent(R.id.tabInput2));
        tabHost2.getTabWidget().setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
        for (int i2 = 0; i2 < 2; i2++) {
            View tabView2 = tabHost2.getTabWidget().getChildTabViewAt(i2);
            tabView2.setPadding(5, 5, 5, 5);
            @SuppressLint("ResourceType") TextView tv2 = (TextView) tabView2.findViewById(16908310);
            tv2.setSingleLine();
            tv2.setTextSize(12.0f);
        }
    }

    /* access modifiers changed from: package-private */
    public void updateShadingUI() {
        int textColor;
        boolean z = this.settings.ShadingEnabled;
        if (0 != 0) {
            textColor = Global.TEXT_COLOR;
        } else {
            textColor = -12303292;
        }
        this.checkShadingEnabled.setEnabled(false);
        this.checkShadingEnabled.setTextColor(Global.TEXT_COLOR_DISABLED);
        this.seekShadingBumpiness.setEnabled(false);
        this.seekColorAddSaturation.setEnabled(false);
        this.seekShadingSpecular.setEnabled(false);
        this.seekShadingSpecPower.setEnabled(false);
        ((TextView) this.activity.findViewById(R.id.textShading1)).setTextColor(textColor);
        ((TextView) this.activity.findViewById(R.id.textShadingSat)).setTextColor(textColor);
        ((TextView) this.activity.findViewById(R.id.textShading3)).setTextColor(textColor);
        ((TextView) this.activity.findViewById(R.id.textShading4)).setTextColor(textColor);
        ((TextView) this.activity.findViewById(R.id.textShading5)).setTextColor(textColor);
        this.checkShadingSpecOnlyGlow.setEnabled(false);
        this.checkShadingSpecOnlyGlow.setTextColor(textColor);
    }

    /* access modifiers changed from: package-private */
    public void updateGlowUI() {
        int textColor;
        boolean lightEnabled;
        int i;
        int i2;
        boolean shadowEnabled;
        int i3;
        int i4;
        int i5;
        int i6 = Global.TEXT_COLOR;
        boolean z = this.settings.Glow;
        if (0 != 0) {
            textColor = -1118482;
        } else {
            textColor = -12303292;
        }
        this.seekGlowStr0.setEnabled(false);
        this.seekGlowStr1.setEnabled(false);
        this.seekGlowStr2.setEnabled(false);
        this.seekGlowPartIntens.setEnabled(false);
        this.seekGlowThreshold.setEnabled(false);
        ((TextView) this.activity.findViewById(R.id.textGlowIntensity)).setTextColor(textColor);
        ((TextView) this.activity.findViewById(R.id.textGlowStr0)).setTextColor(textColor);
        ((TextView) this.activity.findViewById(R.id.textGlowStr1)).setTextColor(textColor);
        ((TextView) this.activity.findViewById(R.id.textGlowStr2)).setTextColor(textColor);
        ((TextView) this.activity.findViewById(R.id.textGlowThreshold)).setTextColor(textColor);
        ((TextView) this.activity.findViewById(R.id.textGlowThresholdTitle)).setTextColor(textColor);
        ((TextView) this.activity.findViewById(R.id.textGlowPartIntens)).setTextColor(textColor);
        ((TextView) this.activity.findViewById(R.id.textGlowPartIntensTitle)).setTextColor(textColor);
        this.checkLightSource.setEnabled(false);
        this.checkLightSource.setTextColor(textColor);
        this.checkShadowSource.setEnabled(false);
        this.checkShadowSource.setTextColor(textColor);
        this.checkShadowSelf.setEnabled(false);
        this.checkShadowSelf.setTextColor(textColor);
        this.checkShadowInverse.setEnabled(false);
        this.checkShadowInverse.setTextColor(textColor);
        ((TextView) this.activity.findViewById(R.id.textLightSourceSpeed)).setTextColor(textColor);
        this.seekLightSourceSpeed.setEnabled(false);
        ((TextView) this.activity.findViewById(R.id.textLightPosX)).setTextColor(textColor);
        ((TextView) this.activity.findViewById(R.id.textLightPosY)).setTextColor(textColor);
        this.seekLightSourcePosX.setEnabled(false);
        this.seekLightSourcePosY.setEnabled(false);
        if (0 == 0 || !this.settings.LightSource) {
            lightEnabled = false;
        } else {
            lightEnabled = true;
        }
        this.buttonLightColor.setEnabled(lightEnabled);
        this.seekLightIntensity.setEnabled(lightEnabled);
        this.seekLightRadius.setEnabled(lightEnabled);
        TextView textView = (TextView) this.activity.findViewById(R.id.textLightIntens);
        if (lightEnabled) {
            i = -1118482;
        } else {
            i = -12303292;
        }
        textView.setTextColor(i);
        TextView textView2 = (TextView) this.activity.findViewById(R.id.textLightRadius);
        if (lightEnabled) {
            i2 = -1118482;
        } else {
            i2 = -12303292;
        }
        textView2.setTextColor(i2);
        if (0 == 0 || !this.settings.ShadowSource) {
            shadowEnabled = false;
        } else {
            shadowEnabled = true;
        }
        this.checkShadowSelf.setEnabled(shadowEnabled);
        this.checkShadowInverse.setEnabled(shadowEnabled);
        this.seekShadowIntensity.setEnabled(shadowEnabled);
        this.seekShadowFalloff.setEnabled(shadowEnabled);
        TextView textView3 = (TextView) this.activity.findViewById(R.id.textShadowIntens);
        if (shadowEnabled) {
            i3 = -1118482;
        } else {
            i3 = -12303292;
        }
        textView3.setTextColor(i3);
        TextView textView4 = (TextView) this.activity.findViewById(R.id.textShadowFalloff);
        if (shadowEnabled) {
            i4 = -1118482;
        } else {
            i4 = -12303292;
        }
        textView4.setTextColor(i4);
        CheckBox checkBox = this.checkShadowSelf;
        if (shadowEnabled) {
            i5 = -1118482;
        } else {
            i5 = -12303292;
        }
        checkBox.setTextColor(i5);
        CheckBox checkBox2 = this.checkShadowInverse;
        if (!shadowEnabled) {
            i6 = -12303292;
        }
        checkBox2.setTextColor(i6);
    }

    /* access modifiers changed from: package-private */
    public void updateDetailRelatedUI() {
        int textColor;
        int borderTextColor = Global.TEXT_COLOR;
        boolean detailEnabled = this.settings.UseDetailTexture;
        if (detailEnabled) {
            textColor = -1118482;
        } else {
            textColor = -12303292;
        }
        boolean borderEnabled = !detailEnabled;
        if (!borderEnabled) {
            borderTextColor = -12303292;
        }
        if (!borderEnabled) {
            this.radioGroupBorder.check(R.id.radioBorderWall);
        }
        ((TextView) this.activity.findViewById(R.id.textDemoEff1)).setText(borderEnabled ? "Screen edge mode" : "Screen edge mode (DISABLED WHEN USING TEXTURES)");
        this.radioGroupBorder.setEnabled(borderEnabled);
        ((RadioButton) this.activity.findViewById(R.id.radioBorderWall)).setEnabled(borderEnabled);
        ((RadioButton) this.activity.findViewById(R.id.radioBorderWrap)).setEnabled(borderEnabled);
        ((RadioButton) this.activity.findViewById(R.id.radioBorderWrapMirror)).setEnabled(borderEnabled);
        ((RadioButton) this.activity.findViewById(R.id.radioBorderWall)).setTextColor(borderTextColor);
        ((RadioButton) this.activity.findViewById(R.id.radioBorderWrap)).setTextColor(borderTextColor);
        ((RadioButton) this.activity.findViewById(R.id.radioBorderWrapMirror)).setTextColor(borderTextColor);
        ((TextView) this.activity.findViewById(R.id.textDemoEff1)).setTextColor(borderTextColor);
        ((TextView) this.activity.findViewById(R.id.textDemoEff2)).setTextColor(borderTextColor);
        this.seekDetailUVScale.setEnabled(detailEnabled);
        ((TextView) this.activity.findViewById(R.id.textDetailScale)).setTextColor(textColor);
    }

    /* access modifiers changed from: package-private */
    public void setEverythingFromSettingsCurrent() {
        updateColorChecksAndButtons();
        this.spinnerPaintRes.setSelection(this.settings.GPUQuality);
        this.spinnerEffectsQual.setSelection(this.settings.EffectsQuality);
        this.spinnerQuality.setSelection(this.settings.QualityBaseValue);
        if (this.settings.FluidType == 0) {
            this.radioGroupFluidType.check(R.id.radioFluidSmoke);
        } else if (this.settings.FluidType == 1) {
            this.radioGroupFluidType.check(R.id.radioFluidWater);
        } else {
            this.radioGroupFluidType.check(R.id.radioFluidJello);
        }
        this.seekInputForce.setProgress(this.settings.getForceInt());
        this.seekInputSize.setProgress(this.settings.getInputSizeInt());
        this.seekTouchInputForce.setProgress(this.settings.getTouchInputForceInt());
        this.seekTouchInputSize.setProgress(this.settings.getTouchInputSizeInt());
        if (this.settings.InputSwipeMode == 0) {
            this.radioGroupInputSwipeMode.check(R.id.radioSwipeStream);
        } else {
            this.radioGroupInputSwipeMode.check(R.id.radioSwipeInverseStream);
        }
        switch (this.settings.InputTouchMode) {
            case 0:
                this.radioGroupInputTouchMode.check(R.id.radioTouchStreamSource);
                break;
            case 1:
                this.radioGroupInputTouchMode.check(R.id.radioTouch2Stream);
                break;
            case 2:
                this.radioGroupInputTouchMode.check(R.id.radioTouch2StreamAlt);
                break;
            case 4:
                this.radioGroupInputTouchMode.check(R.id.radioTouchVortex1);
                break;
            case 5:
                this.radioGroupInputTouchMode.check(R.id.radioTouchSource);
                break;
            case 6:
                this.radioGroupInputTouchMode.check(R.id.radioTouchSink);
                break;
            case 7:
                this.radioGroupInputTouchMode.check(R.id.radioTouchSourceSink);
                break;
            case 8:
                this.radioGroupInputTouchMode.check(R.id.radioTouchNone);
                break;
        }
        this.checkInputSwipeConstant.setChecked(this.settings.InputSwipeConstant);
        this.seekVelLifetime.setProgress(this.settings.getVelLifetimeInt());
        this.seekSwirliness.setProgress(this.settings.getSwirlinessInt());
        this.seekSourceSpeed.setProgress(this.settings.getSourceSpeedInt());
        this.spinnerNumSources.setSelection(this.settings.NumSources);
        this.checkAutoOnResume.setChecked(this.settings.AutoOnResume);
        this.seekFluidLifeTime.setProgress(this.settings.getFluidLifeTimeInt());
        this.seekFluidAmount.setProgress(this.settings.getFluidAmountInt());
        if (this.settings.ColorChange == 0) {
            this.radioGroupColorChange.check(R.id.radioColorChgTouch);
        } else {
            this.radioGroupColorChange.check(R.id.radioColorChgTime);
        }
        if (this.settings.ColorOption == 0) {
            this.radioGroupColorOption.check(R.id.radioColorsRandom);
        } else if (this.settings.ColorOption == 1) {
            this.radioGroupColorOption.check(R.id.radioColorPalette);
        } else if (this.settings.ColorOption == 2) {
            this.radioGroupColorOption.check(R.id.radioColorDoublePalette);
        } else if (this.settings.ColorOption == 3) {
            this.radioGroupColorOption.check(R.id.radioColorTrippy);
        } else {
            this.radioGroupColorOption.check(R.id.radioColorMulti);
        }
        this.seekRandomSaturation.setProgress(this.settings.getRandomSaturationInt());
        if (this.settings.MultiColorDouble) {
            this.radioGroupMultiColorDouble.check(R.id.radioMultiColorsTwo);
        } else {
            this.radioGroupMultiColorDouble.check(R.id.radioMultiColorsOne);
        }
        this.checkCartoonColors.setChecked(this.settings.CartoonColors);
        this.seekColorAddSaturation.setProgress(this.settings.getShadingFluidBrightnessInt());
        this.checkOverbrightColors.setChecked(this.settings.OverbrightColors);
        this.checkInvertColors.setChecked(this.settings.InvertColors);
        setColorModeUIVisibility();
        this.checkParticles.setChecked(this.settings.ParticlesEnabled);
        if (this.settings.ParticlesShape == 0) {
            this.radioGroupParticlesShape.check(R.id.radioParticleDots);
        } else if (this.settings.ParticlesShape == 1) {
            this.radioGroupParticlesShape.check(R.id.radioParticleLines);
        } else if (this.settings.ParticlesShape == 2) {
            this.radioGroupParticlesShape.check(R.id.radioParticleStars);
        } else {
            this.radioGroupParticlesShape.check(R.id.radioParticleSolidDots);
        }
        if (this.settings.ParticlesMode == 0) {
            this.radioGroupParticlesMode.check(R.id.radioParticleModeRegular);
        } else {
            this.radioGroupParticlesMode.check(R.id.radioParticleModeFill);
        }
        this.seekParticlesPerSec.setProgress(this.settings.getParticlesPerSecInt());
        this.seekParticlesLifeTimeMs.setProgress(this.settings.getParticlesLifeTimeMsInt());
        this.seekParticlesSize.setProgress(this.settings.getParticlesSizeInt());
        if (this.settings.ParticlesUsePaintColor) {
            this.radioGroupParticleColor.check(R.id.radioParticleColorsPaint);
        } else {
            this.radioGroupParticleColor.check(R.id.radioParticleColorsOther);
        }
        updateButtonColor(this.buttonParticleColor, this.settings.ParticlesColor);
        if (this.settings.BorderMode == 0) {
            this.radioGroupBorder.check(R.id.radioBorderWall);
        } else if (this.settings.BorderMode == 1) {
            this.radioGroupBorder.check(R.id.radioBorderWrap);
        } else {
            this.radioGroupBorder.check(R.id.radioBorderWrapMirror);
        }
        this.seekGravity.setProgress(this.settings.getGravityInt());
        this.checkGlow.setChecked(this.settings.Glow);
        this.seekGlowStr0.setProgress(this.settings.getGlowLevelStrength0Int());
        this.seekGlowStr1.setProgress(this.settings.getGlowLevelStrength1Int());
        this.seekGlowStr2.setProgress(this.settings.getGlowLevelStrength2Int());
        this.seekGlowThreshold.setProgress(this.settings.getGlowThresholdInt());
        this.seekGlowPartIntens.setProgress(this.settings.getIntFrom01Value(this.settings.GlowParticleIntensity));
        this.checkLightSource.setChecked(this.settings.LightSource);
        updateButtonColor(this.buttonLightColor, this.settings.LightColor);
        this.seekLightIntensity.setProgress(this.settings.getLightIntensityInt());
        this.seekLightRadius.setProgress(this.settings.getLightRadiusInt());
        this.seekLightSourceSpeed.setProgress(this.settings.getLightSourceSpeedInt());
        this.seekLightSourcePosX.setProgress(this.settings.getLightSourcePosXInt());
        this.seekLightSourcePosY.setProgress(this.settings.getLightSourcePosYInt());
        this.checkShadowSource.setChecked(this.settings.ShadowSource);
        this.checkShadowSelf.setChecked(this.settings.ShadowSelf);
        this.checkShadowInverse.setChecked(this.settings.ShadowInverse);
        this.seekShadowIntensity.setProgress(this.settings.getShadowIntensityInt());
        this.seekShadowFalloff.setProgress(this.settings.getShadowFalloffLengthInt());
        this.checkShadingEnabled.setChecked(this.settings.ShadingEnabled);
        this.seekShadingBumpiness.setProgress(this.settings.getShadingBumpinessInt());
        this.seekShadingSpecular.setProgress(this.settings.getShadingSpecularInt());
        this.seekShadingSpecPower.setProgress(this.settings.getShadingSpecPowerInt());
        this.checkShadingSpecOnlyGlow.setChecked(this.settings.ShadingSpecOnlyGlow);
        this.spinnerDetailTexture.setSelection(this.settings.DetailTexture);
        this.seekDetailUVScale.setProgress(this.settings.getDetailUVScaleInt());
        this.checkDetailTexture.setChecked(this.settings.UseDetailTexture);
        updateDetailRelatedUI();
        updateGlowUI();
        updateShadingUI();
    }

    /* access modifiers changed from: package-private */
    public void updateColorChecksAndButtons() {
        int i;
        int i2;
        int i3;
        for (int i4 = 0; i4 < 6; i4++) {
            boolean active = this.settings.getColorsActive(i4);
            updateButtonColor(this.buttonColor[i4], this.settings.getColor(i4));
            Button button = this.buttonColor[i4];
            if (active) {
                i3 = 0;
            } else {
                i3 = 4;
            }
            button.setVisibility(i3);
            this.checkColor[i4].setChecked(active);
            if (this.settings.getNumColorsActive() == 1 && active) {
                this.checkColor[i4].setEnabled(false);
            }
        }
        for (int i5 = 0; i5 < 3; i5++) {
            boolean active2 = this.settings.getDColorsActive(i5);
            updateButtonColor(this.buttonDColor[i5], this.settings.getDColor(i5));
            Button button2 = this.buttonDColor[i5];
            if (active2) {
                i2 = 0;
            } else {
                i2 = 4;
            }
            button2.setVisibility(i2);
            this.checkDColor[i5].setChecked(active2);
            if (this.settings.getNumDColorsActive() == 1 && active2) {
                this.checkDColor[i5].setEnabled(false);
            }
        }
        updateButtonColor(this.buttonBackgrColor, this.settings.BackgroundColor);
        for (int i6 = 0; i6 < 9; i6++) {
            updateButtonColor(this.buttonMColor[i6], this.settings.getMultiColor(i6));
            if (i6 >= 5) {
                Button button3 = this.buttonMColor[i6];
                if (this.settings.MultiColorDouble) {
                    i = 0;
                } else {
                    i = 4;
                }
                button3.setVisibility(i);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void tryLoadPreset(int which, boolean askForConfirm) {
        Preset.Status status = Preset.List.get(which).Stat;
        if (status != Preset.Status.FREE) {
            if (status != Preset.Status.UNLOCKABLE) {
                Toast.makeText(this.activity, "This preset is available in full version", Toast.LENGTH_SHORT).show();
            } else if (this.isLiveWallpaper) {
                AlertDialog.Builder askDialog = new AlertDialog.Builder(this.activity);
                askDialog.setTitle("This preset is locked");
                askDialog.setMessage("You can watch a video ad to load this preset, but you need to do it in the Magic Fluids app (not in the live wallpaper).");
                askDialog.setPositiveButton("Open the app", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                        Intent myIntent = new Intent(SettingsController.this.activity, MainActivity.class);
                        myIntent.putExtra("openSettings", true);
                        SettingsController.this.activity.startActivity(myIntent);
                    }
                });
                askDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });
                askDialog.show();
            } else {
                AlertDialog.Builder askDialog2 = new AlertDialog.Builder(this.activity);
                askDialog2.setTitle("This preset is locked: " + Preset.List.get(which).Name);
                askDialog2.setMessage("You can watch a video ad to load this preset as your current configuration.\n\nYou could also buy the full version and get access to all the presets.\n\nNOTE: Video ads may not work on some devices.");
                final int presetNum = which;
                askDialog2.setNeutralButton("Watch video ad", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        SettingsController.this.lastWantedPreset = presetNum;
                        dialog.dismiss();
                    }
                });
                askDialog2.setPositiveButton("See full version", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        SettingsController.this.activity.startActivity(new Intent("android.intent.action.VIEW").setData(Uri.parse("market://details?id=com.magicfluids")));
                        dialog.dismiss();
                    }
                });
                askDialog2.setNegativeButton("No, thanks", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });
                askDialog2.show();
            }
        } else if (askForConfirm) {
            AlertDialog.Builder askDialog3 = new AlertDialog.Builder(this.activity);
            askDialog3.setTitle("Load preset?");
            askDialog3.setMessage("Are you sure you want to load " + Preset.List.get(which).Name + "?");
            final int whichFinal = which;
            askDialog3.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    dialog.dismiss();
                    SettingsController.this.loadPredefPreset(whichFinal);
                }
            });
            askDialog3.setNegativeButton("No", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    dialog.dismiss();
                }
            });
            askDialog3.show();
        } else {
            loadPredefPreset(which);
            if (Preset.getPresetIndexByName("Gravity Game") == which || Preset.getPresetIndexByName("Weird Water") == which) {
                CommonAlerts.showMessageOk(this.activity, "Disable autorotate", "When using the gravity setting, you should disable your device's autorotate option.");
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void initPresetDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this.activity);
        builder.setTitle("Choose preset");
        String[] items = new String[Preset.List.size()];
        for (int i = 0; i < Preset.List.size(); i++) {
            Preset p = Preset.List.get(i);
            items[i] = p.Name.toUpperCase();
            if (p.New) {
                items[i] = String.valueOf(items[i]) + "     (NEW!)";
            }
            if (p.Stat == Preset.Status.UNLOCKABLE) {
                items[i] = String.valueOf(items[i]) + "\n      (watch ad to load)";
            } else if (p.Stat == Preset.Status.LOCKED) {
                items[i] = String.valueOf(items[i]) + "\n            (full version only)";
            }
        }
        builder.setItems(items, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                SettingsController.this.tryLoadPreset(which, false);
            }
        });
        this.dialogChoosePreset = builder;
    }

    /* access modifiers changed from: package-private */
    public void loadPredefPreset(int num) {
        this.settings.setFromInternalPreset(Preset.List.get(num).Set);
        setEverythingFromSettingsCurrent();
        this.activity.onSettingsChanged();
        if (this.ntv != null) {
            this.ntv.clearScreen();
        }
        if (this.isLiveWallpaper) {
            this.settings.ReloadRequired = true;
        }
    }

    /* access modifiers changed from: package-private */
    public void updateButtonColor(Button b, int backgrColor) {
        b.setBackgroundColor(backgrColor);
        if (((16711680 & backgrColor) >> 16) + ((65280 & backgrColor) >> 8) + (backgrColor & MotionEventCompat.ACTION_MASK) < 200) {
            b.setTextColor(-1);
        } else {
            b.setTextColor(ViewCompat.MEASURED_STATE_MASK);
        }
    }

    /* access modifiers changed from: package-private */
    public void initColorPickerDialogs() {
        this.dialogColorPicker = new AmbilWarnaDialog(this.activity, 0, new AmbilWarnaDialog.OnAmbilWarnaListener() {
            public void onOk(AmbilWarnaDialog dialog, int color) {
                SettingsController.this.settings.setColor(color, SettingsController.this.chosenButtonColorID);
                SettingsController.this.updateButtonColor(SettingsController.this.buttonColor[SettingsController.this.chosenButtonColorID], color);
                SettingsController.this.activity.onSettingsChanged();
            }

            public void onCancel(AmbilWarnaDialog dialog) {
            }
        });
        this.dialogDColorPicker = new AmbilWarnaDialog(this.activity, 0, new AmbilWarnaDialog.OnAmbilWarnaListener() {
            public void onOk(AmbilWarnaDialog dialog, int color) {
                SettingsController.this.settings.setDColor(color, SettingsController.this.chosenButtonDColorID);
                SettingsController.this.updateButtonColor(SettingsController.this.buttonDColor[SettingsController.this.chosenButtonDColorID], color);
                SettingsController.this.activity.onSettingsChanged();
            }

            public void onCancel(AmbilWarnaDialog dialog) {
            }
        });
        this.dialogBackgrColorPicker = new AmbilWarnaDialog(this.activity, 0, new AmbilWarnaDialog.OnAmbilWarnaListener() {
            public void onOk(AmbilWarnaDialog dialog, int color) {
                SettingsController.this.settings.BackgroundColor = color;
                SettingsController.this.updateButtonColor(SettingsController.this.buttonBackgrColor, color);
                SettingsController.this.activity.onSettingsChanged();
            }

            public void onCancel(AmbilWarnaDialog dialog) {
            }
        });
        this.dialogMColorPicker = new AmbilWarnaDialog(this.activity, 0, new AmbilWarnaDialog.OnAmbilWarnaListener() {
            public void onOk(AmbilWarnaDialog dialog, int color) {
                SettingsController.this.settings.setMultiColor(color, SettingsController.this.chosenButtonMColorID);
                SettingsController.this.updateButtonColor(SettingsController.this.buttonMColor[SettingsController.this.chosenButtonMColorID], color);
                SettingsController.this.activity.onSettingsChanged();
            }

            public void onCancel(AmbilWarnaDialog dialog) {
            }
        });
        this.dialogParticleColorPicker = new AmbilWarnaDialog(this.activity, 0, new AmbilWarnaDialog.OnAmbilWarnaListener() {
            public void onOk(AmbilWarnaDialog dialog, int color) {
                SettingsController.this.settings.ParticlesColor = color;
                SettingsController.this.updateButtonColor(SettingsController.this.buttonParticleColor, color);
                SettingsController.this.activity.onSettingsChanged();
            }

            public void onCancel(AmbilWarnaDialog dialog) {
            }
        });
        this.dialogLightColorPicker = new AmbilWarnaDialog(this.activity, 0, new AmbilWarnaDialog.OnAmbilWarnaListener() {
            public void onOk(AmbilWarnaDialog dialog, int color) {
                SettingsController.this.settings.LightColor = color;
                SettingsController.this.updateButtonColor(SettingsController.this.buttonLightColor, color);
                SettingsController.this.activity.onSettingsChanged();
            }

            public void onCancel(AmbilWarnaDialog dialog) {
            }
        });
    }

    /* access modifiers changed from: private */
    public void applyToLwpDialog() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this.activity);
        dialog.setTitle("Apply settings to live wallpaper?");
        String msg = "Are you sure you want to use current settings as the live wallpaper settings? The previous configuration will be overwritten.";
        WallpaperInfo info = WallpaperManager.getInstance(this.activity).getWallpaperInfo();
        if (info == null || !info.getPackageName().equals(this.activity.getPackageName())) {
            msg = String.valueOf(msg) + "\n\nNOTE: Magic Fluids is NOT currently set as your live wallpaper!";
            dialog.setPositiveButton("Apply and set as live wallpaper", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    SettingsController.this.applyCurrentSettingsToLwp();
                    SettingsController.this.activity.setLiveWallpaper();
                    dialog.dismiss();
                }
            });
        }
        dialog.setMessage(msg);
        dialog.setNeutralButton("Apply", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                SettingsController.this.applyCurrentSettingsToLwp();
                dialog.dismiss();
            }
        });
        dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    /* access modifiers changed from: private */
    public void applySettingsToLwp(boolean useCurrent, int preset) {
        if (useCurrent) {
            Settings.LWPCurrent.setEverythingFrom(this.settings);
        } else {
            if (RunManager.getNumLwpRuns(this.activity) == 0) {
                Settings.LWPCurrent.setEverythingFrom(this.settings);
            }
            Settings.LWPCurrent.setFromInternalPreset(Preset.List.get(preset).Set);
        }
        SettingsStorage.saveSessionSettings(this.activity, Settings.LWPCurrent, SettingsStorage.SETTINGS_LWP_NAME);
        Settings.LWPCurrent.ReloadRequired = true;
        Settings.LWPCurrent.ReloadRequiredPreview = true;
        if (RunManager.getNumLwpRuns(this.activity) == 0) {
            RunManager.newLWPSettingsScreenRun(this.activity);
        }
    }

    /* access modifiers changed from: private */
    public void applyCurrentSettingsToLwp() {
        applySettingsToLwp(true, -1);
    }

    /* access modifiers changed from: package-private */
    public void setPresetLoadImageButton(int buttonID, final String presetName, int drawableID, int drawableFreeID) {
        ImageButton button = (ImageButton) this.activity.findViewById(buttonID);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                SettingsController.this.tryLoadPreset(Preset.getPresetIndexByName(presetName), true);
            }
        });
        if (drawableFreeID == -1) {
            button.setImageBitmap(BitmapUtil.getPresetImage(this.activity, drawableID, this.isLiveWallpaper));
        } else {
            button.setImageBitmap(BitmapUtil.getPresetImage(this.activity, drawableFreeID, this.isLiveWallpaper));
        }
    }

    /* access modifiers changed from: package-private */
    public void setFrameTime(String frameTime) {
    }

    /* access modifiers changed from: package-private */
    @SuppressLint("ResourceType")
    public void initControls(SettingsActivity act, NativeInterface ntvInterface, Settings set, boolean isLwp) {
        ColorPalette.init();
        this.activity = act;
        this.ntv = ntvInterface;
        this.settings = set;
        this.isLiveWallpaper = isLwp;
        initTabs();
        initColorPickerDialogs();
        initPresetDialog();
        ((Button) act.findViewById(R.id.buttonChoosePreset)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                SettingsController.this.dialogChoosePreset.show();
            }
        });
        setPresetLoadImageButton(R.id.buttonFeatPreset00, "Super Smoke", R.drawable.super_smoke, -1);
        setPresetLoadImageButton(R.id.buttonFeatPreset01, "Dimension of Depth", R.drawable.dimension_of_depth, -1);
        setPresetLoadImageButton(R.id.buttonFeatPreset02, "Flashy Fluids", R.drawable.flashy_fluids, -1);
        setPresetLoadImageButton(R.id.buttonFeatPreset03, "Gleeful Glimmers", R.drawable.gleeful_glimmers, -1);
        setPresetLoadImageButton(R.id.buttonFeatPreset10, "Blinding Bliss", R.drawable.blinding_bliss, R.drawable.blinding_bliss_ad);
        setPresetLoadImageButton(R.id.buttonFeatPreset11, "Fire and Flame", R.drawable.fire_and_flame, R.drawable.fire_and_flame_ad);
        setPresetLoadImageButton(R.id.buttonFeatPreset12, "Strange Substance", R.drawable.strange_substance, R.drawable.strange_substance_ad);
        setPresetLoadImageButton(R.id.buttonFeatPreset13, "Molten Metal", R.drawable.molten_metal, R.drawable.molten_metal_ad);
        setPresetLoadImageButton(R.id.buttonFeatPreset20, "Cosmic Charm", R.drawable.cosmic_charm, R.drawable.cosmic_charm_ad);
        setPresetLoadImageButton(R.id.buttonFeatPreset21, "Silky Smooth", R.drawable.silky_smooth, R.drawable.silky_smooth_ad);
        setPresetLoadImageButton(R.id.buttonFeatPreset22, "Life of Lights", R.drawable.life_of_lights, R.drawable.life_of_lights_ad);
        setPresetLoadImageButton(R.id.buttonFeatPreset23, "Jittery Jello", R.drawable.jittery_jello, R.drawable.jittery_jello_ad);
        setPresetLoadImageButton(R.id.buttonFeatPreset30, "Weird Water", R.drawable.weird_water, R.drawable.weird_water_ad);
        setPresetLoadImageButton(R.id.buttonFeatPreset31, "Incredible Ink", R.drawable.incredible_ink, R.drawable.incredible_ink_ad);
        setPresetLoadImageButton(R.id.buttonFeatPreset32, "Disturbing Details", R.drawable.disturbing_details, R.drawable.disturbing_details_ad);
        ImageButton button = (ImageButton) this.activity.findViewById(R.id.buttonFeatPreset33);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                CommonAlerts.showKolazBuy(SettingsController.this.activity);
            }
        });
        button.setImageBitmap(BitmapUtil.getPresetImage(this.activity, R.drawable.kolaz, this.isLiveWallpaper));
        if (this.isLiveWallpaper) {
            act.findViewById(R.id.groupApplyToLwp).setVisibility(8);
        }
        ((Button) act.findViewById(R.id.buttonApplyToLwp)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                SettingsController.this.applyToLwpDialog();
            }
        });
        this.spinnerPaintRes = (Spinner) act.findViewById(R.id.spinnerPaintRes);
        ArrayAdapter<CharSequence> paintResAdapter = new ArrayAdapter<>(act, 17367048);
        paintResAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        for (int i = 0; i < QualitySetting.PaintResSettings.size(); i++) {
            paintResAdapter.add(QualitySetting.PaintResSettings.get(i));
        }
        this.spinnerPaintRes.setAdapter(paintResAdapter);
        this.spinnerPaintRes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                SettingsController.this.settings.GPUQuality = (int) id;
                SettingsController.this.activity.onSettingsChanged();
            }

            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        this.spinnerEffectsQual = (Spinner) act.findViewById(R.id.spinnerEffectsQual);
        ArrayAdapter<CharSequence> effectsQualAdapter = new ArrayAdapter<>(act, 17367048);
        effectsQualAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        for (int i2 = 0; i2 < QualitySetting.EffectsSettings.size(); i2++) {
            effectsQualAdapter.add(QualitySetting.EffectsSettings.get(i2));
        }
        this.spinnerEffectsQual.setAdapter(effectsQualAdapter);
        this.spinnerEffectsQual.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                SettingsController.this.settings.EffectsQuality = (int) id;
                SettingsController.this.activity.onSettingsChanged();
            }

            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        this.spinnerQuality = (Spinner) act.findViewById(R.id.spinnerQuality);
        ArrayAdapter<CharSequence> qualityAdapter = new ArrayAdapter<>(act, 17367048);
        qualityAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        for (int i3 = 0; i3 < QualitySetting.SimResSettings.size(); i3++) {
            qualityAdapter.add(QualitySetting.SimResSettings.get(i3));
        }
        this.spinnerQuality.setAdapter(qualityAdapter);
        this.spinnerQuality.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                SettingsController.this.settings.QualityBaseValue = (int) id;
                SettingsController.this.activity.onSettingsChanged();
            }

            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        this.radioGroupFluidType = (RadioGroup) act.findViewById(R.id.radioGroupFluidType);
        this.radioGroupFluidType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radioFluidSmoke) {
                    SettingsController.this.settings.FluidType = 0;
                } else if (checkedId == R.id.radioFluidWater) {
                    SettingsController.this.settings.FluidType = 1;
                } else {
                    SettingsController.this.settings.FluidType = 2;
                }
                SettingsController.this.activity.onSettingsChanged();
            }
        });
        this.seekInputForce = (SeekBar) act.findViewById(R.id.seekInputForce);
        this.seekInputForce.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onStopTrackingTouch(SeekBar seekBar) {
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                SettingsController.this.settings.setForceInt(progress);
                SettingsController.this.activity.onSettingsChanged();
            }
        });
        this.seekInputSize = (SeekBar) act.findViewById(R.id.seekInputSize);
        this.seekInputSize.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onStopTrackingTouch(SeekBar seekBar) {
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                SettingsController.this.settings.setInputSizeInt(progress);
                SettingsController.this.activity.onSettingsChanged();
            }
        });
        this.seekTouchInputForce = (SeekBar) act.findViewById(R.id.seekTouchInputForce);
        this.seekTouchInputForce.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onStopTrackingTouch(SeekBar seekBar) {
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                SettingsController.this.settings.setTouchInputForceInt(progress);
                SettingsController.this.activity.onSettingsChanged();
            }
        });
        this.seekTouchInputSize = (SeekBar) act.findViewById(R.id.seekTouchInputSize);
        this.seekTouchInputSize.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onStopTrackingTouch(SeekBar seekBar) {
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                SettingsController.this.settings.setTouchInputSizeInt(progress);
                SettingsController.this.activity.onSettingsChanged();
            }
        });
        this.radioGroupInputSwipeMode = (RadioGroup) act.findViewById(R.id.radioGroupInputSwipeMode);
        this.radioGroupInputSwipeMode.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radioSwipeStream) {
                    SettingsController.this.settings.InputSwipeMode = 0;
                } else {
                    SettingsController.this.settings.InputSwipeMode = 1;
                }
                SettingsController.this.activity.onSettingsChanged();
            }
        });
        this.radioGroupInputTouchMode = (RadioGroup) act.findViewById(R.id.radioGroupInputTouchMode);
        this.radioGroupInputTouchMode.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radioTouchStreamSource /*2131296401*/:
                        SettingsController.this.settings.InputTouchMode = 0;
                        break;
                    case R.id.radioTouchVortex1 /*2131296402*/:
                        SettingsController.this.settings.InputTouchMode = 4;
                        break;
                    case R.id.radioTouch2Stream /*2131296403*/:
                        SettingsController.this.settings.InputTouchMode = 1;
                        break;
                    case R.id.radioTouch2StreamAlt /*2131296404*/:
                        SettingsController.this.settings.InputTouchMode = 2;
                        break;
                    case R.id.radioTouchSource /*2131296405*/:
                        SettingsController.this.settings.InputTouchMode = 5;
                        break;
                    case R.id.radioTouchSink /*2131296406*/:
                        SettingsController.this.settings.InputTouchMode = 6;
                        break;
                    case R.id.radioTouchSourceSink /*2131296407*/:
                        SettingsController.this.settings.InputTouchMode = 7;
                        break;
                    case R.id.radioTouchNone /*2131296408*/:
                        SettingsController.this.settings.InputTouchMode = 8;
                        break;
                }
                SettingsController.this.activity.onSettingsChanged();
            }
        });
        this.checkInputSwipeConstant = (CheckBox) act.findViewById(R.id.checkInputSwipeConstant);
        this.checkInputSwipeConstant.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SettingsController.this.settings.InputSwipeConstant = isChecked;
                SettingsController.this.activity.onSettingsChanged();
            }
        });
        this.seekVelLifetime = (SeekBar) act.findViewById(R.id.seekVelLifetime);
        this.seekVelLifetime.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onStopTrackingTouch(SeekBar seekBar) {
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                SettingsController.this.settings.setVelLifetimeInt(progress);
                SettingsController.this.activity.onSettingsChanged();
            }
        });
        this.seekSwirliness = (SeekBar) act.findViewById(R.id.seekSwirliness);
        this.seekSwirliness.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onStopTrackingTouch(SeekBar seekBar) {
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                SettingsController.this.settings.setSwirlinessInt(progress);
                SettingsController.this.activity.onSettingsChanged();
            }
        });
        this.seekSourceSpeed = (SeekBar) act.findViewById(R.id.seekSourceSpeed);
        this.seekSourceSpeed.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onStopTrackingTouch(SeekBar seekBar) {
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                SettingsController.this.settings.setSourceSpeedInt(progress);
                SettingsController.this.activity.onSettingsChanged();
            }
        });
        this.spinnerNumSources = (Spinner) act.findViewById(R.id.spinnerSources);
        ArrayAdapter<CharSequence> sourceAdapter = new ArrayAdapter<>(act, 17367048);
        sourceAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        sourceAdapter.add("No sources");
        sourceAdapter.add("1 source");
        sourceAdapter.add("2 sources");
        sourceAdapter.add("3 sources");
        sourceAdapter.add("4 sources");
        sourceAdapter.add("5 sources");
        this.spinnerNumSources.setAdapter(sourceAdapter);
        this.spinnerNumSources.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                SettingsController.this.settings.NumSources = (int) id;
                SettingsController.this.activity.onSettingsChanged();
                LogUtil.i("SettingsController", "Num sources " + ((int) id));
            }

            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        this.checkAutoOnResume = (CheckBox) act.findViewById(R.id.checkAutoOnResume);
        this.checkAutoOnResume.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SettingsController.this.settings.AutoOnResume = isChecked;
                SettingsController.this.activity.onSettingsChanged();
            }
        });
        this.seekFluidAmount = (SeekBar) act.findViewById(R.id.SeekFluidAmount);
        this.seekFluidAmount.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onStopTrackingTouch(SeekBar seekBar) {
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                SettingsController.this.settings.setFluidAmountInt(progress);
                SettingsController.this.activity.onSettingsChanged();
            }
        });
        this.seekFluidLifeTime = (SeekBar) act.findViewById(R.id.SeekFluidLifeTime);
        this.seekFluidLifeTime.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onStopTrackingTouch(SeekBar seekBar) {
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                SettingsController.this.settings.setFluidLifeTimeInt(progress);
                SettingsController.this.activity.onSettingsChanged();
            }
        });
        this.radioGroupColorChange = (RadioGroup) act.findViewById(R.id.radioGroupColorChange);
        this.radioGroupColorChange.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radioColorChgTouch) {
                    SettingsController.this.settings.ColorChange = 0;
                } else {
                    SettingsController.this.settings.ColorChange = 1;
                }
                SettingsController.this.activity.onSettingsChanged();
            }
        });
        this.radioGroupColorOption = (RadioGroup) act.findViewById(R.id.radioGroupColors);
        this.radioGroupColorOption.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radioColorsRandom) {
                    SettingsController.this.settings.ColorOption = 0;
                } else if (checkedId == R.id.radioColorPalette) {
                    SettingsController.this.settings.ColorOption = 1;
                } else if (checkedId == R.id.radioColorDoublePalette) {
                    SettingsController.this.settings.ColorOption = 2;
                } else if (checkedId == R.id.radioColorTrippy) {
                    SettingsController.this.settings.ColorOption = 3;
                } else {
                    SettingsController.this.settings.ColorOption = 4;
                }
                SettingsController.this.activity.onSettingsChanged();
                SettingsController.this.setColorModeUIVisibility();
            }
        });
        this.seekRandomSaturation = (SeekBar) act.findViewById(R.id.seekRandomSaturation);
        this.seekRandomSaturation.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onStopTrackingTouch(SeekBar seekBar) {
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                SettingsController.this.settings.setRandomSaturationInt(progress);
                SettingsController.this.activity.onSettingsChanged();
            }
        });
        this.buttonColor[0] = (Button) act.findViewById(R.id.buttonColor0);
        this.buttonColor[1] = (Button) act.findViewById(R.id.buttonColor1);
        this.buttonColor[2] = (Button) act.findViewById(R.id.buttonColor2);
        this.buttonColor[3] = (Button) act.findViewById(R.id.buttonColor3);
        this.buttonColor[4] = (Button) act.findViewById(R.id.buttonColor4);
        this.buttonColor[5] = (Button) act.findViewById(R.id.buttonColor5);
        this.checkColor[0] = (CheckBox) act.findViewById(R.id.checkColor0);
        this.checkColor[1] = (CheckBox) act.findViewById(R.id.checkColor1);
        this.checkColor[2] = (CheckBox) act.findViewById(R.id.checkColor2);
        this.checkColor[3] = (CheckBox) act.findViewById(R.id.checkColor3);
        this.checkColor[4] = (CheckBox) act.findViewById(R.id.checkColor4);
        this.checkColor[5] = (CheckBox) act.findViewById(R.id.checkColor5);
        for (int i4 = 0; i4 < 6; i4++) {
            this.buttonColor[i4].setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    int index = SettingsController.this.getObjectIndex(v, SettingsController.this.buttonColor);
                    SettingsController.this.chosenButtonColorID = index;
                    SettingsController.this.dialogColorPicker.setCurrentColor(SettingsController.this.settings.getColor(index));
                    SettingsController.this.dialogColorPicker.show();
                }
            });
            this.checkColor[i4].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    SettingsController.this.settings.setColorsActive(SettingsController.this.getObjectIndex(buttonView, SettingsController.this.checkColor), isChecked);
                    SettingsController.this.activity.onSettingsChanged();
                    SettingsController.this.updateColorChecksAndButtons();
                }
            });
        }
        this.buttonDColor[0] = (Button) act.findViewById(R.id.buttonDColor0);
        this.buttonDColor[1] = (Button) act.findViewById(R.id.buttonDColor1);
        this.buttonDColor[2] = (Button) act.findViewById(R.id.buttonDColor2);
        this.checkDColor[0] = (CheckBox) act.findViewById(R.id.checkDColor0);
        this.checkDColor[1] = (CheckBox) act.findViewById(R.id.checkDColor1);
        this.checkDColor[2] = (CheckBox) act.findViewById(R.id.checkDColor2);
        for (int i5 = 0; i5 < 3; i5++) {
            this.buttonDColor[i5].setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    int index = SettingsController.this.getObjectIndex(v, SettingsController.this.buttonDColor);
                    SettingsController.this.chosenButtonDColorID = index;
                    SettingsController.this.dialogDColorPicker.setCurrentColor(SettingsController.this.settings.getDColor(index));
                    SettingsController.this.dialogDColorPicker.show();
                }
            });
            this.checkDColor[i5].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    SettingsController.this.settings.setDColorsActive(SettingsController.this.getObjectIndex(buttonView, SettingsController.this.checkDColor), isChecked);
                    SettingsController.this.activity.onSettingsChanged();
                    SettingsController.this.updateColorChecksAndButtons();
                }
            });
        }
        this.buttonBackgrColor = (Button) act.findViewById(R.id.buttonBackgroundColor);
        this.buttonBackgrColor.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                SettingsController.this.dialogBackgrColorPicker.setCurrentColor(SettingsController.this.settings.BackgroundColor);
                SettingsController.this.dialogBackgrColorPicker.show();
            }
        });
        this.buttonMColor[0] = (Button) act.findViewById(R.id.buttonMColor0);
        this.buttonMColor[1] = (Button) act.findViewById(R.id.buttonMColor1);
        this.buttonMColor[2] = (Button) act.findViewById(R.id.buttonMColor2);
        this.buttonMColor[3] = (Button) act.findViewById(R.id.buttonMColor3);
        this.buttonMColor[4] = (Button) act.findViewById(R.id.buttonMColor4);
        this.buttonMColor[5] = (Button) act.findViewById(R.id.buttonMColor5);
        this.buttonMColor[6] = (Button) act.findViewById(R.id.buttonMColor6);
        this.buttonMColor[7] = (Button) act.findViewById(R.id.buttonMColor7);
        this.buttonMColor[8] = (Button) act.findViewById(R.id.buttonMColor8);
        for (int i6 = 0; i6 < 9; i6++) {
            this.buttonMColor[i6].setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    int index = SettingsController.this.getObjectIndex(v, SettingsController.this.buttonMColor);
                    SettingsController.this.chosenButtonMColorID = index;
                    SettingsController.this.dialogMColorPicker.setCurrentColor(SettingsController.this.settings.getMultiColor(index));
                    SettingsController.this.dialogMColorPicker.show();
                }
            });
        }
        this.radioGroupMultiColorDouble = (RadioGroup) act.findViewById(R.id.radioGroupMultiColorsDouble);
        this.radioGroupMultiColorDouble.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                SettingsController.this.settings.MultiColorDouble = checkedId == R.id.radioMultiColorsTwo;
                SettingsController.this.activity.onSettingsChanged();
                SettingsController.this.updateColorChecksAndButtons();
            }
        });
        this.checkCartoonColors = (CheckBox) act.findViewById(R.id.checkCartoonColors);
        this.checkCartoonColors.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SettingsController.this.settings.CartoonColors = isChecked;
                SettingsController.this.activity.onSettingsChanged();
            }
        });
        this.seekColorAddSaturation = (SeekBar) act.findViewById(R.id.seekShadingFluidBrightness);
        this.seekColorAddSaturation.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onStopTrackingTouch(SeekBar seekBar) {
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                SettingsController.this.settings.setShadingFluidBrightnessInt(progress);
                SettingsController.this.activity.onSettingsChanged();
            }
        });
        this.checkOverbrightColors = (CheckBox) act.findViewById(R.id.checkOverbrightColors);
        this.checkOverbrightColors.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SettingsController.this.settings.OverbrightColors = isChecked;
                SettingsController.this.activity.onSettingsChanged();
            }
        });
        this.checkInvertColors = (CheckBox) act.findViewById(R.id.checkInvertColors);
        this.checkInvertColors.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SettingsController.this.settings.InvertColors = isChecked;
                SettingsController.this.activity.onSettingsChanged();
            }
        });
        this.checkParticles = (CheckBox) act.findViewById(R.id.checkParticles);
        this.checkParticles.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SettingsController.this.settings.ParticlesEnabled = isChecked;
                SettingsController.this.activity.onSettingsChanged();
            }
        });
        this.radioGroupParticlesShape = (RadioGroup) act.findViewById(R.id.radioGroupParticleShapes);
        this.radioGroupParticlesShape.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radioParticleDots) {
                    SettingsController.this.settings.ParticlesShape = 0;
                } else if (checkedId == R.id.radioParticleLines) {
                    SettingsController.this.settings.ParticlesShape = 1;
                } else if (checkedId == R.id.radioParticleStars) {
                    SettingsController.this.settings.ParticlesShape = 2;
                } else {
                    SettingsController.this.settings.ParticlesShape = 3;
                }
                SettingsController.this.activity.onSettingsChanged();
            }
        });
        this.radioGroupParticlesMode = (RadioGroup) act.findViewById(R.id.radioGroupParticleMode);
        this.radioGroupParticlesMode.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radioParticleModeRegular) {
                    SettingsController.this.settings.ParticlesMode = 0;
                } else {
                    SettingsController.this.settings.ParticlesMode = 1;
                }
                SettingsController.this.activity.onSettingsChanged();
            }
        });
        this.seekParticlesPerSec = (SeekBar) act.findViewById(R.id.SeekParticlesAmount);
        this.seekParticlesPerSec.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
            }

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    SettingsController.this.settings.setParticlesPerSecInt(progress);
                    SettingsController.this.settings.forceParticlesLifeTime();
                    SettingsController.this.seekParticlesLifeTimeMs.setProgress(SettingsController.this.settings.getParticlesLifeTimeMsInt());
                    SettingsController.this.activity.onSettingsChanged();
                }
            }
        });
        this.seekParticlesLifeTimeMs = (SeekBar) act.findViewById(R.id.SeekParticlesLifeTime);
        this.seekParticlesLifeTimeMs.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
            }

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    SettingsController.this.settings.setParticlesLifeTimeMsInt(progress);
                    SettingsController.this.settings.forceParticlesPerSec();
                    SettingsController.this.seekParticlesPerSec.setProgress(SettingsController.this.settings.getParticlesPerSecInt());
                    SettingsController.this.activity.onSettingsChanged();
                }
            }
        });
        this.seekParticlesSize = (SeekBar) act.findViewById(R.id.SeekParticlesSize);
        this.seekParticlesSize.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
            }

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                SettingsController.this.settings.setParticlesSizeInt(progress);
                SettingsController.this.activity.onSettingsChanged();
            }
        });
        this.radioGroupParticleColor = (RadioGroup) act.findViewById(R.id.radioGroupParticleColors);
        this.radioGroupParticleColor.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radioParticleColorsPaint) {
                    SettingsController.this.settings.ParticlesUsePaintColor = true;
                } else {
                    SettingsController.this.settings.ParticlesUsePaintColor = false;
                }
                SettingsController.this.activity.onSettingsChanged();
            }
        });
        this.buttonParticleColor = (Button) act.findViewById(R.id.buttonParticleColor);
        this.buttonParticleColor.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                SettingsController.this.dialogParticleColorPicker.setCurrentColor(SettingsController.this.settings.ParticlesColor);
                SettingsController.this.dialogParticleColorPicker.show();
            }
        });
        this.radioGroupBorder = (RadioGroup) act.findViewById(R.id.radioGroupBorder);
        this.radioGroupBorder.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radioBorderWall) {
                    SettingsController.this.settings.BorderMode = 0;
                } else if (checkedId == R.id.radioBorderWrap) {
                    SettingsController.this.settings.BorderMode = 1;
                } else {
                    SettingsController.this.settings.BorderMode = 2;
                }
                SettingsController.this.activity.onSettingsChanged();
            }
        });
        this.seekGravity = (SeekBar) act.findViewById(R.id.SeekGravity);
        this.seekGravity.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onStopTrackingTouch(SeekBar seekBar) {
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                SettingsController.this.settings.setGravityInt(progress);
                SettingsController.this.activity.onSettingsChanged();
            }
        });
        this.checkGlow = (CheckBox) act.findViewById(R.id.checkGlow);
        this.checkGlow.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SettingsController.this.settings.Glow = isChecked;
                SettingsController.this.activity.onSettingsChanged();
                SettingsController.this.updateGlowUI();
            }
        });
        this.seekGlowStr0 = (SeekBar) act.findViewById(R.id.seekGlowStr0);
        this.seekGlowStr0.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onStopTrackingTouch(SeekBar seekBar) {
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                SettingsController.this.settings.setGlowLevelStrength0Int(progress);
                SettingsController.this.activity.onSettingsChanged();
            }
        });
        this.seekGlowStr1 = (SeekBar) act.findViewById(R.id.seekGlowStr1);
        this.seekGlowStr1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onStopTrackingTouch(SeekBar seekBar) {
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                SettingsController.this.settings.setGlowLevelStrength1Int(progress);
                SettingsController.this.activity.onSettingsChanged();
            }
        });
        this.seekGlowStr2 = (SeekBar) act.findViewById(R.id.seekGlowStr2);
        this.seekGlowStr2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onStopTrackingTouch(SeekBar seekBar) {
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                SettingsController.this.settings.setGlowLevelStrength2Int(progress);
                SettingsController.this.activity.onSettingsChanged();
            }
        });
        this.seekGlowThreshold = (SeekBar) act.findViewById(R.id.seekGlowThreshold);
        this.seekGlowThreshold.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onStopTrackingTouch(SeekBar seekBar) {
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                SettingsController.this.settings.setGlowThresholdInt(progress);
                SettingsController.this.activity.onSettingsChanged();
            }
        });
        this.seekGlowPartIntens = (SeekBar) act.findViewById(R.id.seekGlowPartIntens);
        this.seekGlowPartIntens.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onStopTrackingTouch(SeekBar seekBar) {
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                SettingsController.this.settings.GlowParticleIntensity = SettingsController.this.settings.set01ValueFromInt((float) progress);
                SettingsController.this.activity.onSettingsChanged();
            }
        });
        this.checkLightSource = (CheckBox) act.findViewById(R.id.checkLightSource);
        this.checkLightSource.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SettingsController.this.settings.LightSource = isChecked;
                SettingsController.this.activity.onSettingsChanged();
                SettingsController.this.updateGlowUI();
            }
        });
        this.buttonLightColor = (Button) act.findViewById(R.id.buttonLightColor);
        this.buttonLightColor.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                SettingsController.this.dialogLightColorPicker.setCurrentColor(SettingsController.this.settings.LightColor);
                SettingsController.this.dialogLightColorPicker.show();
            }
        });
        this.seekLightIntensity = (SeekBar) act.findViewById(R.id.seekLightIntens);
        this.seekLightIntensity.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onStopTrackingTouch(SeekBar seekBar) {
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                SettingsController.this.settings.setLightIntensityInt(progress);
                SettingsController.this.activity.onSettingsChanged();
            }
        });
        this.seekLightRadius = (SeekBar) act.findViewById(R.id.seekLightRadius);
        this.seekLightRadius.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onStopTrackingTouch(SeekBar seekBar) {
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                SettingsController.this.settings.setLightRadiusInt(progress);
                SettingsController.this.activity.onSettingsChanged();
            }
        });
        this.seekLightSourceSpeed = (SeekBar) act.findViewById(R.id.seekLightSourceSpeed);
        this.seekLightSourceSpeed.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onStopTrackingTouch(SeekBar seekBar) {
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                SettingsController.this.settings.setLightSourceSpeedInt(progress);
                SettingsController.this.activity.onSettingsChanged();
            }
        });
        this.seekLightSourcePosX = (SeekBar) act.findViewById(R.id.seekLightSourcePosX);
        this.seekLightSourcePosX.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onStopTrackingTouch(SeekBar seekBar) {
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                SettingsController.this.settings.setLightSourcePosXInt(progress);
                SettingsController.this.activity.onSettingsChanged();
            }
        });
        this.seekLightSourcePosY = (SeekBar) act.findViewById(R.id.seekLightSourcePosY);
        this.seekLightSourcePosY.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onStopTrackingTouch(SeekBar seekBar) {
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                SettingsController.this.settings.setLightSourcePosYInt(progress);
                SettingsController.this.activity.onSettingsChanged();
            }
        });
        this.checkShadowSource = (CheckBox) act.findViewById(R.id.checkShadowSource);
        this.checkShadowSource.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SettingsController.this.settings.ShadowSource = isChecked;
                SettingsController.this.activity.onSettingsChanged();
                SettingsController.this.updateGlowUI();
            }
        });
        this.checkShadowSelf = (CheckBox) act.findViewById(R.id.checkShadowSelf);
        this.checkShadowSelf.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SettingsController.this.settings.ShadowSelf = isChecked;
                SettingsController.this.activity.onSettingsChanged();
            }
        });
        this.checkShadowInverse = (CheckBox) act.findViewById(R.id.checkShadowInverse);
        this.checkShadowInverse.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SettingsController.this.settings.ShadowInverse = isChecked;
                SettingsController.this.activity.onSettingsChanged();
            }
        });
        this.seekShadowIntensity = (SeekBar) act.findViewById(R.id.seekShadowIntens);
        this.seekShadowIntensity.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onStopTrackingTouch(SeekBar seekBar) {
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                SettingsController.this.settings.setShadowIntensityInt(progress);
                SettingsController.this.activity.onSettingsChanged();
            }
        });
        this.seekShadowFalloff = (SeekBar) act.findViewById(R.id.seekShadowFalloff);
        this.seekShadowFalloff.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onStopTrackingTouch(SeekBar seekBar) {
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                SettingsController.this.settings.setShadowFalloffLengthInt(progress);
                SettingsController.this.activity.onSettingsChanged();
            }
        });
        this.checkShadingEnabled = (CheckBox) act.findViewById(R.id.checkShadingEnabled);
        this.checkShadingEnabled.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SettingsController.this.settings.ShadingEnabled = isChecked;
                SettingsController.this.activity.onSettingsChanged();
                SettingsController.this.updateShadingUI();
            }
        });
        this.seekShadingBumpiness = (SeekBar) act.findViewById(R.id.seekShadingBumpiness);
        this.seekShadingBumpiness.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onStopTrackingTouch(SeekBar seekBar) {
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                SettingsController.this.settings.setShadingBumpinessInt(progress);
                SettingsController.this.activity.onSettingsChanged();
            }
        });
        this.seekShadingSpecular = (SeekBar) act.findViewById(R.id.seekShadingSpecular);
        this.seekShadingSpecular.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onStopTrackingTouch(SeekBar seekBar) {
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                SettingsController.this.settings.setShadingSpecularInt(progress);
                SettingsController.this.activity.onSettingsChanged();
            }
        });
        this.seekShadingSpecPower = (SeekBar) act.findViewById(R.id.seekShadingSpecPower);
        this.seekShadingSpecPower.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onStopTrackingTouch(SeekBar seekBar) {
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                SettingsController.this.settings.setShadingSpecPowerInt(progress);
                SettingsController.this.activity.onSettingsChanged();
            }
        });
        this.checkShadingSpecOnlyGlow = (CheckBox) act.findViewById(R.id.checkShadingSpecOnlyGlow);
        this.checkShadingSpecOnlyGlow.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SettingsController.this.settings.ShadingSpecOnlyGlow = isChecked;
                SettingsController.this.activity.onSettingsChanged();
            }
        });
        this.checkDetailTexture = (CheckBox) act.findViewById(R.id.checkDetailTexture);
        this.checkDetailTexture.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SettingsController.this.settings.UseDetailTexture = isChecked;
                SettingsController.this.activity.onSettingsChanged();
                SettingsController.this.updateDetailRelatedUI();
            }
        });
        this.seekDetailUVScale = (SeekBar) act.findViewById(R.id.seekDetailUVScale);
        this.seekDetailUVScale.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onStopTrackingTouch(SeekBar seekBar) {
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                SettingsController.this.settings.setDetailUVScaleInt(progress);
                SettingsController.this.activity.onSettingsChanged();
            }
        });
        this.spinnerDetailTexture = (Spinner) act.findViewById(R.id.spinnerDetailTexture);
        ArrayAdapter<CharSequence> detailAdapter = new ArrayAdapter<>(act, 17367048);
        detailAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        detailAdapter.add("Water");
        detailAdapter.add("Fire");
        detailAdapter.add("Ice");
        detailAdapter.add("Cloud 1");
        detailAdapter.add("Cloud 2");
        detailAdapter.add("Veins");
        detailAdapter.add("Something 1");
        detailAdapter.add("Something 2");
        detailAdapter.add("Something 3");
        detailAdapter.add("Something 4");
        this.spinnerDetailTexture.setAdapter(detailAdapter);
        this.spinnerDetailTexture.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                SettingsController.this.settings.DetailTexture = (int) id;
                SettingsController.this.activity.onSettingsChanged();
            }

            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        ((TextView) act.findViewById(R.id.textApache)).setMovementMethod(LinkMovementMethod.getInstance());
        ((ImageButton) act.findViewById(R.id.buttonByMadSci)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                SettingsController.this.activity.startActivity(new Intent("android.intent.action.VIEW").setData(Uri.parse("http://www.madscientist.pl")));
            }
        });
        ((ImageButton) act.findViewById(R.id.buttonByMadSci)).setImageBitmap(BitmapUtil.decodeSampledBitmapFromResource(act.getResources(), R.drawable.by_ms, 215, 100));
        ((Button) act.findViewById(R.id.buttonBuy)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                SettingsController.this.activity.startActivity(new Intent("android.intent.action.VIEW").setData(Uri.parse("market://details?id=com.magicfluids")));
            }
        });
        ((Button) act.findViewById(R.id.buttonRate)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                SettingsController.this.activity.startActivity(new Intent("android.intent.action.VIEW").setData(Uri.parse("market://details?id=com.magicfluids.demo")));
            }
        });
        ((ImageButton) act.findViewById(R.id.buttonFacebook)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                SettingsController.this.activity.startActivity(new Intent("android.intent.action.VIEW").setData(Uri.parse("https://www.facebook.com/madscientistgames")));
            }
        });
        ((ImageButton) act.findViewById(R.id.buttonInstagram)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                SettingsController.this.activity.startActivity(new Intent("android.intent.action.VIEW").setData(Uri.parse("https://www.instagram.com/magicfluidsapp")));
            }
        });
        ((ImageButton) act.findViewById(R.id.buttonYoutube)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                SettingsController.this.activity.startActivity(new Intent("android.intent.action.VIEW").setData(Uri.parse("https://youtube.com/user/MadScientistPL")));
            }
        });
        ((Button) act.findViewById(R.id.buttonMoreApps)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                SettingsController.this.activity.startActivity(new Intent("android.intent.action.VIEW").setData(Uri.parse("market://search?q=pub:Mad Scientist")));
            }
        });
        ((Button) act.findViewById(R.id.buttonPrivacyPolicy)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                SettingsController.this.activity.startActivity(new Intent("android.intent.action.VIEW").setData(Uri.parse("https://madscientist.pl/magicfluids/policy.html")));
            }
        });
        ((Button) act.findViewById(R.id.buttonNewsletter)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                SettingsController.this.activity.startActivity(new Intent("android.intent.action.VIEW").setData(Uri.parse("https://lb.benchmarkemail.com//listbuilder/signupnew?om9I%252B75JX6HIbjW6bhqeAv5pwVnAjsSISlu1Syc0Xk%252FtO5iNRn8gS049TyW7spdJ")));
            }
        });
        ((Button) act.findViewById(R.id.buttonEmail)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent emailIntent = new Intent("android.intent.action.SEND");
                emailIntent.setType("text/plain");
                emailIntent.putExtra("android.intent.extra.EMAIL", new String[]{"magicfluids@madscientist.pl"});
                SettingsController.this.activity.startActivity(Intent.createChooser(emailIntent, "Send email"));
            }
        });
        ((Button) act.findViewById(R.id.buttonHelp)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                CommonAlerts.showHelp(SettingsController.this.activity);
            }
        });
        initUserPresets();
        setEverythingFromSettingsCurrent();
        reloadPresets();
        disableForDemo();
    }

    public void reloadEverything() {
        setEverythingFromSettingsCurrent();
        reloadPresets();
    }

    public void reloadPresets() {
        for (int i = 0; i < 10; i++) {
            try {
                this.textPreset[i].setText(SettingsStorage.getUserPresetName(this.activity, i));
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public void disableForDemo() {
        for (int i = 0; i < 10; i++) {
            try {
                this.buttonLoadPreset[i].setEnabled(false);
                this.buttonSavePreset[i].setEnabled(false);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        this.spinnerNumSources.setEnabled(false);
        this.seekSourceSpeed.setEnabled(false);
        this.seekVelLifetime.setEnabled(false);
        ((TextView) this.activity.findViewById(R.id.textVelLifetime)).setTextColor(Global.TEXT_COLOR_DISABLED);
        ((TextView) this.activity.findViewById(R.id.textVelLifetimeDescr)).setTextColor(Global.TEXT_COLOR_DISABLED);
        this.seekSwirliness.setEnabled(false);
        ((TextView) this.activity.findViewById(R.id.textSwirliness)).setTextColor(Global.TEXT_COLOR_DISABLED);
        ((TextView) this.activity.findViewById(R.id.textSwirlinessDescr)).setTextColor(Global.TEXT_COLOR_DISABLED);
        this.radioGroupFluidType.setEnabled(false);
        ((RadioButton) this.activity.findViewById(R.id.radioFluidSmoke)).setEnabled(false);
        ((RadioButton) this.activity.findViewById(R.id.radioFluidWater)).setEnabled(false);
        ((RadioButton) this.activity.findViewById(R.id.radioFluidJello)).setEnabled(false);
        this.seekFluidLifeTime.setEnabled(false);
        this.radioGroupColorOption.setEnabled(false);
        ((RadioButton) this.activity.findViewById(R.id.radioColorPalette)).setEnabled(false);
        ((RadioButton) this.activity.findViewById(R.id.radioColorsRandom)).setEnabled(false);
        ((RadioButton) this.activity.findViewById(R.id.radioColorDoublePalette)).setEnabled(false);
        ((RadioButton) this.activity.findViewById(R.id.radioColorTrippy)).setEnabled(false);
        ((RadioButton) this.activity.findViewById(R.id.radioColorMulti)).setEnabled(false);
        ((TextView) this.activity.findViewById(R.id.textPaintRandomSat)).setTextColor(Global.TEXT_COLOR_DISABLED);
        this.seekColorAddSaturation.setEnabled(false);
        for (int i2 = 0; i2 < 6; i2++) {
            this.buttonColor[i2].setEnabled(false);
            this.checkColor[i2].setEnabled(false);
        }
        for (int i3 = 0; i3 < 3; i3++) {
            this.buttonDColor[i3].setEnabled(false);
            this.checkDColor[i3].setEnabled(false);
        }
        this.buttonBackgrColor.setEnabled(false);
        for (int i4 = 0; i4 < 9; i4++) {
            this.buttonMColor[i4].setEnabled(false);
        }
        this.radioGroupMultiColorDouble.setEnabled(false);
        ((RadioButton) this.activity.findViewById(R.id.radioMultiColorsOne)).setEnabled(false);
        ((RadioButton) this.activity.findViewById(R.id.radioMultiColorsTwo)).setEnabled(false);
        ((RadioButton) this.activity.findViewById(R.id.radioMultiColorsOne)).setTextColor(Global.TEXT_COLOR_DISABLED);
        ((RadioButton) this.activity.findViewById(R.id.radioMultiColorsTwo)).setTextColor(Global.TEXT_COLOR_DISABLED);
        this.checkCartoonColors.setEnabled(false);
        this.checkCartoonColors.setTextColor(Global.TEXT_COLOR_DISABLED);
        this.checkOverbrightColors.setEnabled(false);
        this.checkOverbrightColors.setTextColor(Global.TEXT_COLOR_DISABLED);
        this.checkInvertColors.setEnabled(false);
        this.checkInvertColors.setTextColor(Global.TEXT_COLOR_DISABLED);
        ((TextView) this.activity.findViewById(R.id.textParticleMode)).setTextColor(Global.TEXT_COLOR_DISABLED);
        this.radioGroupParticlesMode.setEnabled(false);
        ((RadioButton) this.activity.findViewById(R.id.radioParticleModeRegular)).setEnabled(false);
        ((RadioButton) this.activity.findViewById(R.id.radioParticleModeFill)).setEnabled(false);
        this.seekParticlesPerSec.setEnabled(false);
        this.seekParticlesLifeTimeMs.setEnabled(false);
        this.radioGroupParticleColor.setEnabled(false);
        ((RadioButton) this.activity.findViewById(R.id.radioParticleColorsOther)).setEnabled(false);
        ((RadioButton) this.activity.findViewById(R.id.radioParticleColorsPaint)).setEnabled(false);
        this.buttonParticleColor.setEnabled(false);
        this.checkGlow.setEnabled(false);
        this.checkGlow.setTextColor(Global.TEXT_COLOR_DISABLED);
        this.seekGlowStr0.setEnabled(false);
        this.seekGlowStr1.setEnabled(false);
        this.seekGlowStr2.setEnabled(false);
        this.seekGlowThreshold.setEnabled(false);
        this.seekGlowPartIntens.setEnabled(false);
        ((TextView) this.activity.findViewById(R.id.textGlowIntensity)).setTextColor(Global.TEXT_COLOR_DISABLED);
        ((TextView) this.activity.findViewById(R.id.textGlowStr0)).setTextColor(Global.TEXT_COLOR_DISABLED);
        ((TextView) this.activity.findViewById(R.id.textGlowStr1)).setTextColor(Global.TEXT_COLOR_DISABLED);
        ((TextView) this.activity.findViewById(R.id.textGlowStr2)).setTextColor(Global.TEXT_COLOR_DISABLED);
        ((TextView) this.activity.findViewById(R.id.textGlowPartIntens)).setTextColor(Global.TEXT_COLOR_DISABLED);
        ((TextView) this.activity.findViewById(R.id.textGlowPartIntensTitle)).setTextColor(Global.TEXT_COLOR_DISABLED);
        ((TextView) this.activity.findViewById(R.id.textGlowThreshold)).setTextColor(Global.TEXT_COLOR_DISABLED);
        ((TextView) this.activity.findViewById(R.id.textGlowThresholdTitle)).setTextColor(Global.TEXT_COLOR_DISABLED);
        this.checkLightSource.setEnabled(false);
        this.checkLightSource.setTextColor(Global.TEXT_COLOR_DISABLED);
        this.checkShadowSource.setEnabled(false);
        this.checkShadowSource.setTextColor(Global.TEXT_COLOR_DISABLED);
        this.checkShadowSource.setEnabled(false);
        this.checkShadowSource.setTextColor(Global.TEXT_COLOR_DISABLED);
        this.buttonLightColor.setEnabled(false);
        this.seekLightIntensity.setEnabled(false);
        this.seekShadowIntensity.setEnabled(false);
        this.seekShadowFalloff.setEnabled(false);
        ((TextView) this.activity.findViewById(R.id.textLightIntens)).setTextColor(Global.TEXT_COLOR_DISABLED);
        ((TextView) this.activity.findViewById(R.id.textShadowIntens)).setTextColor(Global.TEXT_COLOR_DISABLED);
        ((TextView) this.activity.findViewById(R.id.textShadowFalloff)).setTextColor(Global.TEXT_COLOR_DISABLED);
        ((TextView) this.activity.findViewById(R.id.textLightSourceSpeed)).setTextColor(Global.TEXT_COLOR_DISABLED);
        this.seekLightSourceSpeed.setEnabled(false);
        ((TextView) this.activity.findViewById(R.id.textLightPosX)).setTextColor(Global.TEXT_COLOR_DISABLED);
        ((TextView) this.activity.findViewById(R.id.textLightPosY)).setTextColor(Global.TEXT_COLOR_DISABLED);
        this.seekLightSourcePosX.setEnabled(false);
        this.seekLightSourcePosY.setEnabled(false);
        this.checkDetailTexture.setEnabled(false);
        this.checkDetailTexture.setTextColor(Global.TEXT_COLOR_DISABLED);
        ((TextView) this.activity.findViewById(R.id.textDemoEff6)).setTextColor(Global.TEXT_COLOR_DISABLED);
        this.spinnerDetailTexture.setEnabled(false);
        ((TextView) this.activity.findViewById(R.id.textDemoPre1)).setTextColor(Global.TEXT_COLOR_DISABLED);
        ((TextView) this.activity.findViewById(R.id.textDemoPre2)).setTextColor(Global.TEXT_COLOR_DISABLED);
        for (int i5 = 0; i5 < 10; i5++) {
            try {
                this.textPreset[i5].setTextColor(Global.TEXT_COLOR_DISABLED);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        ((TextView) this.activity.findViewById(R.id.textDemoAnim1)).setTextColor(Global.TEXT_COLOR_DISABLED);
        ((TextView) this.activity.findViewById(R.id.textDemoAnim2)).setTextColor(Global.TEXT_COLOR_DISABLED);
        ((TextView) this.activity.findViewById(R.id.textDemoAnim5)).setTextColor(Global.TEXT_COLOR_DISABLED);
        ((TextView) this.activity.findViewById(R.id.textDemoAnim6)).setTextColor(Global.TEXT_COLOR_DISABLED);
        ((TextView) this.activity.findViewById(R.id.textDemoAnim7)).setTextColor(Global.TEXT_COLOR_DISABLED);
        ((RadioButton) this.activity.findViewById(R.id.radioFluidSmoke)).setTextColor(Global.TEXT_COLOR_DISABLED);
        ((RadioButton) this.activity.findViewById(R.id.radioFluidWater)).setTextColor(Global.TEXT_COLOR_DISABLED);
        ((RadioButton) this.activity.findViewById(R.id.radioFluidJello)).setTextColor(Global.TEXT_COLOR_DISABLED);
        ((TextView) this.activity.findViewById(R.id.textDemoPaint1)).setTextColor(Global.TEXT_COLOR_DISABLED);
        ((TextView) this.activity.findViewById(R.id.textDemoPaint2)).setTextColor(Global.TEXT_COLOR_DISABLED);
        ((TextView) this.activity.findViewById(R.id.textDemoPaint3)).setTextColor(Global.TEXT_COLOR_DISABLED);
        ((TextView) this.activity.findViewById(R.id.textDemoPaint5)).setTextColor(Global.TEXT_COLOR_DISABLED);
        ((RadioButton) this.activity.findViewById(R.id.radioColorPalette)).setTextColor(Global.TEXT_COLOR_DISABLED);
        ((RadioButton) this.activity.findViewById(R.id.radioColorsRandom)).setTextColor(Global.TEXT_COLOR_DISABLED);
        ((RadioButton) this.activity.findViewById(R.id.radioColorDoublePalette)).setTextColor(Global.TEXT_COLOR_DISABLED);
        ((RadioButton) this.activity.findViewById(R.id.radioColorTrippy)).setTextColor(Global.TEXT_COLOR_DISABLED);
        ((RadioButton) this.activity.findViewById(R.id.radioColorMulti)).setTextColor(Global.TEXT_COLOR_DISABLED);
        ((TextView) this.activity.findViewById(R.id.textDemoPart2)).setTextColor(Global.TEXT_COLOR_DISABLED);
        ((TextView) this.activity.findViewById(R.id.textDemoPart3)).setTextColor(Global.TEXT_COLOR_DISABLED);
        ((TextView) this.activity.findViewById(R.id.textDemoPart4)).setTextColor(Global.TEXT_COLOR_DISABLED);
        ((TextView) this.activity.findViewById(R.id.textDemoPart5)).setTextColor(Global.TEXT_COLOR_DISABLED);
        ((TextView) this.activity.findViewById(R.id.textDemoPart6)).setTextColor(Global.TEXT_COLOR_DISABLED);
        ((RadioButton) this.activity.findViewById(R.id.radioParticleColorsOther)).setTextColor(Global.TEXT_COLOR_DISABLED);
        ((RadioButton) this.activity.findViewById(R.id.radioParticleColorsPaint)).setTextColor(Global.TEXT_COLOR_DISABLED);
    }

    private void showDialogPresetLoaded() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this.activity);
        dialog.setCancelable(false);
        dialog.setTitle("Preset rewarded!");
        dialog.setMessage("Where do you want to apply " + Preset.List.get(this.lastWantedPreset).Name + "?");
        dialog.setNegativeButton(" App ", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
                SettingsController.this.loadPredefPreset(SettingsController.this.lastWantedPreset);
            }
        });
        dialog.setNeutralButton(" Live wallpaper ", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
                SettingsController.this.applySettingsToLwp(false, SettingsController.this.lastWantedPreset);
            }
        });
        dialog.setPositiveButton(" Both ", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
                SettingsController.this.loadPredefPreset(SettingsController.this.lastWantedPreset);
                SettingsController.this.applySettingsToLwp(false, SettingsController.this.lastWantedPreset);
            }
        });
        dialog.show();
    }

    public void rewardGranted() {
        showDialogPresetLoaded();
    }
}
