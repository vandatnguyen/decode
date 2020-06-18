package com.magicfluids;

public class Settings {
    static final int BORDER_WALL = 0;
    static final int BORDER_WRAP = 1;
    static final int BORDER_WRAP_MIRROR = 2;
    static final int COLOR_CHG_TIME = 1;
    static final int COLOR_CHG_TOUCH = 0;
    static final int COLOR_DOUBLE_PALETTE = 2;
    static final int COLOR_MULTI = 4;
    static final int COLOR_PALETTE = 1;
    static final int COLOR_RANDOM = 0;
    static final int COLOR_TRIPPY = 3;
    static Settings Current = new Settings();
    static final float DETAIL_SCALE_MAX = 3.5f;
    static final float DETAIL_SCALE_MIN = 1.5f;
    static final float FLUID_AMOUNT_MAX = 0.003f;
    static final float FLUID_AMOUNT_MIN = 0.0f;
    static final float FLUID_LIFE_TIME_MAX = 51.0f;
    static final float FLUID_LIFE_TIME_MIN = 0.25f;
    static final int FLUID_TYPE_JELLO = 2;
    static final int FLUID_TYPE_SMOKE = 0;
    static final int FLUID_TYPE_WATER = 1;
    static final float FORCE_MAX = 0.001f;
    static final float FORCE_MIN = 0.0f;
    static final float GRAVITY_ENABLE_THRESHOLD = 3.0E-4f;
    static final float GRAVITY_MAX = 0.08f;
    static final float GRAVITY_MIN = 0.0f;
    static Settings LWPCurrent = new Settings();
    static final int MENU_BUTTON_DIMMED = 1;
    static final int MENU_BUTTON_HIDDEN = 2;
    static final int MENU_BUTTON_VISIBLE = 0;
    static final int NUM_COLORS = 6;
    static final int NUM_DCOLORS = 3;
    static final int NUM_MULTI_COLORS = 9;
    static final float PARTICLES_LIFE_TIME_MAX = 30.0f;
    static final float PARTICLES_LIFE_TIME_MIN = 0.25f;
    static final float PARTICLES_PER_SEC_MAX = 2000.0f;
    static final float PARTICLES_PER_SEC_MIN = 50.0f;
    static final float PARTICLES_SIZE_MAX = 100.0f;
    static final float PARTICLES_SIZE_MIN = 0.0f;
    static final int PARTICLE_MODE_FILL = 1;
    static final int PARTICLE_MODE_REGULAR = 0;
    static final int PARTICLE_SHAPE_DOTS = 0;
    static final int PARTICLE_SHAPE_LINES = 1;
    static final int PARTICLE_SHAPE_SOLID_DOTS = 3;
    static final int PARTICLE_SHAPE_STARS = 2;
    static final float SOURCE_SPEED_MAX = 0.0015f;
    static final float SOURCE_SPEED_MIN = 1.5E-4f;
    boolean AllowMultithreading;
    boolean AutoOnResume;
    int BackgroundColor;
    int BorderMode;
    boolean CartoonColors;
    int Color0;
    int Color1;
    int Color2;
    int Color3;
    int Color4;
    int Color5;
    int ColorChange;
    int ColorOption;
    float ColorRandomness;
    int[] Colors = new int[6];
    boolean[] ColorsActive = new boolean[6];
    int DColor0;
    int DColor1;
    int DColor2;
    int[] DColors = new int[3];
    boolean[] DColorsActive = new boolean[3];
    int DetailTexture;
    float DetailUVScale;
    int EffectsQuality;
    int FPSLimit;
    float FluidAmount;
    float FluidLifeTime;
    int FluidType;
    float Force;
    int GPUQuality;
    boolean Glow;
    float GlowLevelStrength0;
    float GlowLevelStrength1;
    float GlowLevelStrength2;
    float GlowParticleIntensity;
    float GlowThreshold;
    float Gravity;
    float InputSize;
    boolean InputSwipeConstant;
    int InputSwipeMode;
    int InputTouchMode;
    boolean InvertColors;
    int LightColor;
    float LightIntensity;
    float LightRadius;
    boolean LightSource;
    float LightSourcePosX;
    float LightSourcePosY;
    float LightSourceSpeed;
    int MColor0;
    int MColor1;
    int MColor2;
    int MColor3;
    int MColor4;
    int MColor5;
    int MColor6;
    int MColor7;
    int MColor8;
    int MenuButtonVisibility;
    boolean MultiColorDouble;
    int[] MultiColors = new int[9];
    int NumColors;
    int NumDColors;
    int NumSources;
    boolean OverbrightColors;
    int ParticlesColor;
    boolean ParticlesEnabled;
    float ParticlesLifeTimeSec;
    int ParticlesMode;
    float ParticlesPerSec;
    int ParticlesShape;
    float ParticlesSize;
    boolean ParticlesUsePaintColor;
    int QualityBaseValue;
    float RandomSaturation;
    boolean ReloadRequired = false;
    boolean ReloadRequiredPreview = false;
    float ShadingBumpiness;
    boolean ShadingEnabled;
    float ShadingFluidBrightness;
    boolean ShadingSpecOnlyGlow;
    float ShadingSpecPower;
    int ShadingSpecType;
    float ShadingSpecular;
    float ShadowFalloffLength;
    float ShadowIntensity;
    boolean ShadowInverse;
    boolean ShadowSelf;
    boolean ShadowSource;
    float SourceSpeed;
    float Swirliness;
    float TouchInputForce;
    float TouchInputSize;
    boolean UseDetailTexture;
    float VelLifetime;
    private int[] tmpColors = new int[6];

    /* access modifiers changed from: package-private */
    public void setFromInternalPreset(Settings s) {
        setFrom(s);
    }

    public void setEverythingFrom(Settings s) {
        setFrom(s);
        this.QualityBaseValue = s.QualityBaseValue;
        this.GPUQuality = s.GPUQuality;
        this.EffectsQuality = s.EffectsQuality;
        this.AllowMultithreading = s.AllowMultithreading;
        this.FPSLimit = s.FPSLimit;
        this.MenuButtonVisibility = s.MenuButtonVisibility;
    }

    private void setFrom(Settings s) {
        this.FluidType = s.FluidType;
        this.Force = s.Force;
        this.InputSize = s.InputSize;
        this.TouchInputForce = s.TouchInputForce;
        this.TouchInputSize = s.TouchInputSize;
        this.InputSwipeMode = s.InputSwipeMode;
        this.InputTouchMode = s.InputTouchMode;
        this.InputSwipeConstant = s.InputSwipeConstant;
        this.VelLifetime = s.VelLifetime;
        this.Swirliness = s.Swirliness;
        this.NumSources = s.NumSources;
        this.SourceSpeed = s.SourceSpeed;
        this.AutoOnResume = s.AutoOnResume;
        this.FluidAmount = s.FluidAmount;
        this.FluidLifeTime = s.FluidLifeTime;
        this.ShadingFluidBrightness = s.ShadingFluidBrightness;
        this.OverbrightColors = s.OverbrightColors;
        this.InvertColors = s.InvertColors;
        this.ColorRandomness = s.ColorRandomness;
        this.ParticlesEnabled = s.ParticlesEnabled;
        this.ParticlesShape = s.ParticlesShape;
        this.ParticlesMode = s.ParticlesMode;
        this.ParticlesPerSec = s.ParticlesPerSec;
        this.ParticlesLifeTimeSec = s.ParticlesLifeTimeSec;
        this.ParticlesSize = s.ParticlesSize;
        this.ColorChange = s.ColorChange;
        this.ColorOption = s.ColorOption;
        this.RandomSaturation = s.RandomSaturation;
        for (int i = 0; i < 6; i++) {
            this.Colors[i] = s.Colors[i];
            this.ColorsActive[i] = s.ColorsActive[i];
        }
        for (int i2 = 0; i2 < 3; i2++) {
            this.DColors[i2] = s.DColors[i2];
            this.DColorsActive[i2] = s.DColorsActive[i2];
        }
        this.BackgroundColor = s.BackgroundColor;
        for (int i3 = 0; i3 < 9; i3++) {
            this.MultiColors[i3] = s.MultiColors[i3];
        }
        this.MultiColorDouble = s.MultiColorDouble;
        this.CartoonColors = s.CartoonColors;
        this.ParticlesUsePaintColor = s.ParticlesUsePaintColor;
        this.ParticlesColor = s.ParticlesColor;
        this.Gravity = s.Gravity;
        this.BorderMode = s.BorderMode;
        this.Glow = s.Glow;
        this.GlowLevelStrength0 = s.GlowLevelStrength0;
        this.GlowLevelStrength1 = s.GlowLevelStrength1;
        this.GlowLevelStrength2 = s.GlowLevelStrength2;
        this.GlowThreshold = s.GlowThreshold;
        this.GlowParticleIntensity = s.GlowParticleIntensity;
        this.LightSource = s.LightSource;
        this.LightRadius = s.LightRadius;
        this.LightIntensity = s.LightIntensity;
        this.LightColor = s.LightColor;
        this.LightSourceSpeed = s.LightSourceSpeed;
        this.LightSourcePosX = s.LightSourcePosX;
        this.LightSourcePosY = s.LightSourcePosY;
        this.ShadowSource = s.ShadowSource;
        this.ShadowSelf = s.ShadowSelf;
        this.ShadowInverse = s.ShadowInverse;
        this.ShadowIntensity = s.ShadowIntensity;
        this.ShadowFalloffLength = s.ShadowFalloffLength;
        this.ShadingEnabled = s.ShadingEnabled;
        this.ShadingBumpiness = s.ShadingBumpiness;
        this.ShadingSpecType = s.ShadingSpecType;
        this.ShadingSpecular = s.ShadingSpecular;
        this.ShadingSpecPower = s.ShadingSpecPower;
        this.ShadingSpecOnlyGlow = s.ShadingSpecOnlyGlow;
        this.UseDetailTexture = s.UseDetailTexture;
        this.DetailTexture = s.DetailTexture;
        this.DetailUVScale = s.DetailUVScale;
    }

    /* access modifiers changed from: package-private */
    public void forceParticlesLifeTime() {
        if (this.ParticlesPerSec * this.ParticlesLifeTimeSec > 16000.0f) {
            this.ParticlesLifeTimeSec = (float) Math.floor((double) (16000.0f / this.ParticlesPerSec));
        }
    }

    /* access modifiers changed from: package-private */
    public void forceParticlesPerSec() {
        if (this.ParticlesPerSec * this.ParticlesLifeTimeSec > 16000.0f) {
            this.ParticlesPerSec = (float) Math.floor((double) (16000.0f / this.ParticlesLifeTimeSec));
        }
    }

    Settings() {
        initDefault();
    }

    /* access modifiers changed from: package-private */
    public void initDefault() {
        this.FluidType = 0;
        this.Force = SOURCE_SPEED_MIN;
        this.VelLifetime = 1.0f;
        this.Swirliness = 0.0f;
        this.NumSources = 0;
        this.SourceSpeed = 5.0E-4f;
        this.AutoOnResume = false;
        this.FluidAmount = 0.0013f;
        this.FluidLifeTime = 40.0f;
        this.ColorOption = 0;
        this.RandomSaturation = 0.75f;
        for (int i = 0; i < 3; i++) {
            this.ColorsActive[i] = true;
        }
        for (int i2 = 3; i2 < 6; i2++) {
            this.ColorsActive[i2] = false;
        }
        this.Colors[0] = -48060;
        this.Colors[1] = -12255420;
        this.Colors[2] = -12303105;
        this.Colors[3] = -1118482;
        this.Colors[4] = -1118482;
        this.Colors[5] = -1118482;
        for (int i3 = 0; i3 < 3; i3++) {
            this.DColorsActive[i3] = true;
        }
        this.DColors[0] = -48060;
        this.DColors[1] = -12255420;
        this.DColors[2] = -12303105;
        this.BackgroundColor = Global.TEXT_COLOR;
        this.MultiColors[0] = -16777216;
        for (int i4 = 1; i4 < 5; i4++) {
            this.MultiColors[i4] = -48060;
        }
        for (int i5 = 5; i5 < 9; i5++) {
            this.MultiColors[i5] = -12303105;
        }
        this.MultiColorDouble = true;
        this.CartoonColors = false;
        this.ShadingFluidBrightness = 0.95f;
        this.OverbrightColors = true;
        this.InvertColors = false;
        this.ColorRandomness = 0.0f;
        this.ColorChange = 0;
        this.ParticlesEnabled = false;
        this.ParticlesMode = 0;
        this.ParticlesShape = 0;
        this.ParticlesPerSec = 650.0f;
        this.ParticlesLifeTimeSec = 15.0f;
        this.ParticlesSize = 10.0f;
        this.ParticlesUsePaintColor = false;
        this.ParticlesColor = -5855578;
        this.BorderMode = 0;
        this.Gravity = 0.0f;
        this.Glow = false;
        this.GlowLevelStrength0 = 0.3f;
        this.GlowLevelStrength1 = 0.3f;
        this.GlowLevelStrength2 = 0.3f;
        this.GlowThreshold = 0.1f;
        this.GlowParticleIntensity = 0.5f;
        this.LightSource = false;
        this.LightRadius = 1.0f;
        this.LightIntensity = 1.0f;
        this.LightColor = -1;
        this.LightSourceSpeed = 0.0f;
        this.LightSourcePosX = 0.5f;
        this.LightSourcePosY = 0.5f;
        this.ShadowSource = false;
        this.ShadowSelf = true;
        this.ShadowInverse = false;
        this.ShadowIntensity = 3.0f;
        this.ShadowFalloffLength = 0.5f;
        this.ShadingEnabled = false;
        this.ShadingBumpiness = 0.75f;
        this.ShadingSpecType = 1;
        this.ShadingSpecular = 1.3f;
        this.ShadingSpecPower = 0.4f;
        this.ShadingSpecOnlyGlow = false;
        this.UseDetailTexture = false;
        this.DetailTexture = 0;
        this.DetailUVScale = 2.5f;
        this.QualityBaseValue = 1;
        this.GPUQuality = 4;
        this.EffectsQuality = 1;
        this.FPSLimit = 0;
        this.AllowMultithreading = true;
        this.MenuButtonVisibility = 0;
    }

    /* access modifiers changed from: package-private */
    public float setValueFromInt(float min, float max, float intVal) {
        return ((intVal / PARTICLES_SIZE_MAX) * (max - min)) + min;
    }

    /* access modifiers changed from: package-private */
    public int getIntValue(float min, float max, float val) {
        return (int) ((PARTICLES_SIZE_MAX * (val - min)) / (max - min));
    }

    /* access modifiers changed from: package-private */
    public float set01ValueFromInt(float intVal) {
        return setValueFromInt(0.0f, 1.0f, intVal);
    }

    /* access modifiers changed from: package-private */
    public int getIntFrom01Value(float val) {
        return getIntValue(0.0f, 1.0f, val);
    }

    /* access modifiers changed from: package-private */
    public void setForceInt(int value) {
        this.Force = setValueFromInt(0.0f, FORCE_MAX, (float) value);
    }

    /* access modifiers changed from: package-private */
    public int getForceInt() {
        return getIntValue(0.0f, FORCE_MAX, this.Force);
    }

    /* access modifiers changed from: package-private */
    public void setInputSizeInt(int value) {
        this.InputSize = setValueFromInt(0.015f, 0.125f, (float) value);
    }

    /* access modifiers changed from: package-private */
    public int getInputSizeInt() {
        return getIntValue(0.015f, 0.125f, this.InputSize);
    }

    /* access modifiers changed from: package-private */
    public void setTouchInputForceInt(int value) {
        this.TouchInputForce = set01ValueFromInt((float) value);
    }

    /* access modifiers changed from: package-private */
    public int getTouchInputForceInt() {
        return getIntFrom01Value(this.TouchInputForce);
    }

    /* access modifiers changed from: package-private */
    public void setTouchInputSizeInt(int value) {
        this.TouchInputSize = set01ValueFromInt((float) value);
    }

    /* access modifiers changed from: package-private */
    public int getTouchInputSizeInt() {
        return getIntFrom01Value(this.TouchInputSize);
    }

    /* access modifiers changed from: package-private */
    public void setVelLifetimeInt(int value) {
        this.VelLifetime = setValueFromInt(-1.0f, 1.0f, (float) value);
    }

    /* access modifiers changed from: package-private */
    public int getVelLifetimeInt() {
        return getIntValue(-1.0f, 1.0f, this.VelLifetime);
    }

    /* access modifiers changed from: package-private */
    public void setSwirlinessInt(int value) {
        this.Swirliness = setValueFromInt(0.0f, 3.0f, (float) value);
    }

    /* access modifiers changed from: package-private */
    public int getSwirlinessInt() {
        return getIntValue(0.0f, 3.0f, this.Swirliness);
    }

    /* access modifiers changed from: package-private */
    public void setFluidAmountInt(int value) {
        this.FluidAmount = setValueFromInt(0.0f, FLUID_AMOUNT_MAX, (float) value);
        if (this.FluidAmount < 1.0E-5f) {
            this.FluidAmount = 0.0f;
        }
    }

    /* access modifiers changed from: package-private */
    public int getFluidAmountInt() {
        return getIntValue(0.0f, FLUID_AMOUNT_MAX, this.FluidAmount);
    }

    /* access modifiers changed from: package-private */
    public void setFluidLifeTimeInt(int value) {
        this.FluidLifeTime = setValueFromInt(0.25f, FLUID_LIFE_TIME_MAX, (float) value);
    }

    /* access modifiers changed from: package-private */
    public int getFluidLifeTimeInt() {
        return getIntValue(0.25f, FLUID_LIFE_TIME_MAX, this.FluidLifeTime);
    }

    /* access modifiers changed from: package-private */
    public void setColorRandomnessInt(int value) {
        this.ColorRandomness = setValueFromInt(0.0f, 1.0f, (float) value);
    }

    /* access modifiers changed from: package-private */
    public int getColorRandomnessInt() {
        return getIntValue(0.0f, 1.0f, this.ColorRandomness);
    }

    /* access modifiers changed from: package-private */
    public void setParticlesPerSecInt(int value) {
        this.ParticlesPerSec = setValueFromInt(PARTICLES_PER_SEC_MIN, PARTICLES_PER_SEC_MAX, (float) value);
    }

    /* access modifiers changed from: package-private */
    public int getParticlesPerSecInt() {
        return getIntValue(PARTICLES_PER_SEC_MIN, PARTICLES_PER_SEC_MAX, this.ParticlesPerSec);
    }

    /* access modifiers changed from: package-private */
    public void setParticlesLifeTimeMsInt(int value) {
        this.ParticlesLifeTimeSec = setValueFromInt(0.25f, 30.0f, (float) value);
    }

    /* access modifiers changed from: package-private */
    public int getParticlesLifeTimeMsInt() {
        return getIntValue(0.25f, 30.0f, this.ParticlesLifeTimeSec);
    }

    /* access modifiers changed from: package-private */
    public void setParticlesSizeInt(int value) {
        this.ParticlesSize = setValueFromInt(0.0f, PARTICLES_SIZE_MAX, (float) value);
    }

    /* access modifiers changed from: package-private */
    public int getParticlesSizeInt() {
        return getIntValue(0.0f, PARTICLES_SIZE_MAX, this.ParticlesSize);
    }

    /* access modifiers changed from: package-private */
    public void setRandomSaturationInt(int value) {
        this.RandomSaturation = setValueFromInt(0.0f, 1.0f, (float) value);
    }

    /* access modifiers changed from: package-private */
    public int getRandomSaturationInt() {
        return getIntValue(0.0f, 1.0f, this.RandomSaturation);
    }

    /* access modifiers changed from: package-private */
    public int getColor(int index) {
        return this.Colors[index];
    }

    /* access modifiers changed from: package-private */
    public void setColor(int color, int index) {
        this.Colors[index] = color;
    }

    /* access modifiers changed from: package-private */
    public boolean getColorsActive(int index) {
        return this.ColorsActive[index];
    }

    /* access modifiers changed from: package-private */
    public void setColorsActive(int index, boolean value) {
        this.ColorsActive[index] = value;
    }

    /* access modifiers changed from: package-private */
    public int getNumColorsActive() {
        int sum = 0;
        for (int i = 0; i < 6; i++) {
            if (this.ColorsActive[i]) {
                sum++;
            }
        }
        return sum;
    }

    /* access modifiers changed from: package-private */
    public int getDColor(int index) {
        return this.DColors[index];
    }

    /* access modifiers changed from: package-private */
    public void setDColor(int color, int index) {
        this.DColors[index] = color;
    }

    /* access modifiers changed from: package-private */
    public boolean getDColorsActive(int index) {
        return this.DColorsActive[index];
    }

    /* access modifiers changed from: package-private */
    public void setDColorsActive(int index, boolean value) {
        this.DColorsActive[index] = value;
    }

    /* access modifiers changed from: package-private */
    public int getNumDColorsActive() {
        int sum = 0;
        for (int i = 0; i < 3; i++) {
            if (this.DColorsActive[i]) {
                sum++;
            }
        }
        return sum;
    }

    /* access modifiers changed from: package-private */
    public int getMultiColor(int index) {
        return this.MultiColors[index];
    }

    /* access modifiers changed from: package-private */
    public void setMultiColor(int color, int index) {
        this.MultiColors[index] = color;
    }

    /* access modifiers changed from: package-private */
    public void process() {
        int index;
        int index2;
        int index3 = 0;
        int i = 0;
        while (true) {
            index = index3;
            if (i >= 6) {
                break;
            }
            if (this.ColorsActive[i]) {
                index3 = index + 1;
                this.tmpColors[index] = this.Colors[i];
            } else {
                index3 = index;
            }
            i++;
        }
        this.Color0 = this.tmpColors[0];
        this.Color1 = this.tmpColors[1];
        this.Color2 = this.tmpColors[2];
        this.Color3 = this.tmpColors[3];
        this.Color4 = this.tmpColors[4];
        this.Color5 = this.tmpColors[5];
        this.NumColors = index;
        int i2 = 0;
        int index4 = 0;
        while (i2 < 3) {
            if (this.DColorsActive[i2]) {
                index2 = index4 + 1;
                this.tmpColors[index4] = this.DColors[i2];
            } else {
                index2 = index4;
            }
            i2++;
            index4 = index2;
        }
        this.DColor0 = this.tmpColors[0];
        this.DColor1 = this.tmpColors[1];
        this.DColor2 = this.tmpColors[2];
        this.NumDColors = index4;
        this.MColor0 = this.MultiColors[0];
        this.MColor1 = this.MultiColors[1];
        this.MColor2 = this.MultiColors[2];
        this.MColor3 = this.MultiColors[3];
        this.MColor4 = this.MultiColors[4];
        this.MColor5 = this.MultiColors[5];
        this.MColor6 = this.MultiColors[6];
        this.MColor7 = this.MultiColors[7];
        this.MColor8 = this.MultiColors[8];
        if (this.DetailUVScale < DETAIL_SCALE_MIN) {
            this.DetailUVScale = DETAIL_SCALE_MIN;
        }
        if (this.DetailUVScale > DETAIL_SCALE_MAX) {
            this.DetailUVScale = DETAIL_SCALE_MAX;
        }
    }

    /* access modifiers changed from: package-private */
    public void setSourceSpeedInt(int value) {
        this.SourceSpeed = setValueFromInt(SOURCE_SPEED_MIN, SOURCE_SPEED_MAX, (float) value);
    }

    /* access modifiers changed from: package-private */
    public int getSourceSpeedInt() {
        return getIntValue(SOURCE_SPEED_MIN, SOURCE_SPEED_MAX, this.SourceSpeed);
    }

    /* access modifiers changed from: package-private */
    public void setGravityInt(int value) {
        this.Gravity = setValueFromInt(0.0f, GRAVITY_MAX, (float) value);
    }

    /* access modifiers changed from: package-private */
    public int getGravityInt() {
        return getIntValue(0.0f, GRAVITY_MAX, this.Gravity);
    }

    /* access modifiers changed from: package-private */
    public void setGlowLevelStrength0Int(int value) {
        this.GlowLevelStrength0 = setValueFromInt(0.0f, 4.0f, (float) value);
    }

    /* access modifiers changed from: package-private */
    public int getGlowLevelStrength0Int() {
        return getIntValue(0.0f, 4.0f, this.GlowLevelStrength0);
    }

    /* access modifiers changed from: package-private */
    public void setGlowLevelStrength1Int(int value) {
        this.GlowLevelStrength1 = setValueFromInt(0.0f, 3.0f, (float) value);
    }

    /* access modifiers changed from: package-private */
    public int getGlowLevelStrength1Int() {
        return getIntValue(0.0f, 3.0f, this.GlowLevelStrength1);
    }

    /* access modifiers changed from: package-private */
    public void setGlowLevelStrength2Int(int value) {
        this.GlowLevelStrength2 = setValueFromInt(0.0f, 2.0f, (float) value);
    }

    /* access modifiers changed from: package-private */
    public int getGlowLevelStrength2Int() {
        return getIntValue(0.0f, 2.0f, this.GlowLevelStrength2);
    }

    /* access modifiers changed from: package-private */
    public void setGlowThresholdInt(int value) {
        this.GlowThreshold = setValueFromInt(0.0f, DETAIL_SCALE_MIN, (float) value);
    }

    /* access modifiers changed from: package-private */
    public int getGlowThresholdInt() {
        return getIntValue(0.0f, DETAIL_SCALE_MIN, this.GlowThreshold);
    }

    /* access modifiers changed from: package-private */
    public void setLightRadiusInt(int value) {
        this.LightRadius = setValueFromInt(0.15f, 2.0f, (float) value);
    }

    /* access modifiers changed from: package-private */
    public int getLightRadiusInt() {
        return getIntValue(0.15f, 2.0f, this.LightRadius);
    }

    /* access modifiers changed from: package-private */
    public void setLightIntensityInt(int value) {
        this.LightIntensity = setValueFromInt(0.25f, 2.0f, (float) value);
    }

    /* access modifiers changed from: package-private */
    public int getLightIntensityInt() {
        return getIntValue(0.25f, 2.0f, this.LightIntensity);
    }

    /* access modifiers changed from: package-private */
    public void setLightSourceSpeedInt(int value) {
        this.LightSourceSpeed = setValueFromInt(0.0f, 2.0f, (float) value);
    }

    /* access modifiers changed from: package-private */
    public int getLightSourceSpeedInt() {
        return getIntValue(0.0f, 2.0f, this.LightSourceSpeed);
    }

    /* access modifiers changed from: package-private */
    public void setLightSourcePosXInt(int value) {
        this.LightSourcePosX = setValueFromInt(-0.1f, 1.1f, (float) value);
    }

    /* access modifiers changed from: package-private */
    public int getLightSourcePosXInt() {
        return getIntValue(-0.1f, 1.1f, this.LightSourcePosX);
    }

    /* access modifiers changed from: package-private */
    public void setLightSourcePosYInt(int value) {
        this.LightSourcePosY = setValueFromInt(-0.1f, 1.1f, (float) value);
    }

    /* access modifiers changed from: package-private */
    public int getLightSourcePosYInt() {
        return getIntValue(-0.1f, 1.1f, this.LightSourcePosY);
    }

    /* access modifiers changed from: package-private */
    public void setShadowIntensityInt(int value) {
        this.ShadowIntensity = setValueFromInt(0.5f, 8.0f, (float) value);
    }

    /* access modifiers changed from: package-private */
    public int getShadowIntensityInt() {
        return getIntValue(0.5f, 8.0f, this.ShadowIntensity);
    }

    /* access modifiers changed from: package-private */
    public void setShadowFalloffLengthInt(int value) {
        this.ShadowFalloffLength = setValueFromInt(0.025f, 1.0f, (float) value);
    }

    /* access modifiers changed from: package-private */
    public int getShadowFalloffLengthInt() {
        return getIntValue(0.025f, 1.0f, this.ShadowFalloffLength);
    }

    /* access modifiers changed from: package-private */
    public void setShadingBumpinessInt(int value) {
        this.ShadingBumpiness = setValueFromInt(0.0f, 1.0f, (float) value);
    }

    /* access modifiers changed from: package-private */
    public int getShadingBumpinessInt() {
        return getIntValue(0.0f, 1.0f, this.ShadingBumpiness);
    }

    /* access modifiers changed from: package-private */
    public void setShadingFluidBrightnessInt(int value) {
        this.ShadingFluidBrightness = setValueFromInt(0.4f, 1.0f, (float) value);
    }

    /* access modifiers changed from: package-private */
    public int getShadingFluidBrightnessInt() {
        return getIntValue(0.4f, 1.0f, this.ShadingFluidBrightness);
    }

    /* access modifiers changed from: package-private */
    public void setShadingSpecularInt(int value) {
        this.ShadingSpecular = setValueFromInt(0.0f, 2.0f, (float) value);
    }

    /* access modifiers changed from: package-private */
    public int getShadingSpecularInt() {
        return getIntValue(0.0f, 2.0f, this.ShadingSpecular);
    }

    /* access modifiers changed from: package-private */
    public void setShadingSpecPowerInt(int value) {
        this.ShadingSpecPower = setValueFromInt(0.0f, 1.0f, (float) value);
    }

    /* access modifiers changed from: package-private */
    public int getShadingSpecPowerInt() {
        return getIntValue(0.0f, 1.0f, this.ShadingSpecPower);
    }

    /* access modifiers changed from: package-private */
    public void setDetailUVScaleInt(int value) {
        this.DetailUVScale = setValueFromInt(DETAIL_SCALE_MIN, DETAIL_SCALE_MAX, (float) value);
    }

    /* access modifiers changed from: package-private */
    public int getDetailUVScaleInt() {
        return getIntValue(DETAIL_SCALE_MIN, DETAIL_SCALE_MAX, this.DetailUVScale);
    }
}
