package com.magicfluids;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.AssetManager;

import androidx.core.internal.view.SupportMenu;
import androidx.core.view.ViewCompat;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class SettingsStorage {
    private static final String ALLOW_MULTITHREADING = "ALLOW_MULTITHREADING";
    private static final String AUTO_ON_RESUME = "AUTO_ON_RESUME";
    private static final String BACKGROUND_COLOR = "BACKGROUND_COLOR";
    private static final String BORDER_MODE = "BORDER_MODE";
    private static final String CARTOON_COLORS = "CARTOON_COLORS";
    private static final String COLOR0 = "COLOR0";
    private static final String COLOR1 = "COLOR1";
    private static final String COLOR2 = "COLOR2";
    private static final String COLOR3 = "COLOR3";
    private static final String COLOR4 = "COLOR4";
    private static final String COLOR5 = "COLOR5";
    private static final String COLOR_ACTIVE0 = "COLOR_ACTIVE0";
    private static final String COLOR_ACTIVE1 = "COLOR_ACTIVE1";
    private static final String COLOR_ACTIVE2 = "COLOR_ACTIVE2";
    private static final String COLOR_ACTIVE3 = "COLOR_ACTIVE3";
    private static final String COLOR_ACTIVE4 = "COLOR_ACTIVE4";
    private static final String COLOR_ACTIVE5 = "COLOR_ACTIVE5";
    private static final String COLOR_CHANGE = "COLOR_CHANGE";
    private static final String COLOR_OPTION = "COLOR_OPTION";
    private static final String COLOR_RANDOMNESS = "COLOR_RANDOMNESS";
    private static final String DCOLOR0 = "DCOLOR0";
    private static final String DCOLOR1 = "DCOLOR1";
    private static final String DCOLOR2 = "DCOLOR2";
    private static final String DCOLOR_ACTIVE0 = "DCOLOR_ACTIVE0";
    private static final String DCOLOR_ACTIVE1 = "DCOLOR_ACTIVE1";
    private static final String DCOLOR_ACTIVE2 = "DCOLOR_ACTIVE2";
    private static final String DETAIL_HD = "DETAIL_HD";
    private static final String DETAIL_TEXTURE = "DETAIL_TEXTURE";
    private static final String DETAIL_UV_SCALE = "DETAIL_UV_SCALE";
    private static final String EFFECTS_QUALITY = "EFFECTS_QUALITY";
    private static final String FLUID_AMOUNT = "FLUID_AMOUNT";
    private static final String FLUID_LIFE_TIME = "FLUID_LIFE_TIME";
    private static final String FLUID_TYPE = "FLUID_TYPE";
    private static final String FORCE = "FORCE";
    private static final String FPS_LIMIT = "FPS_LIMIT";
    private static final String GLOW = "GLOW";
    private static final String GLOW_LEVEL_STRENGTH0 = "GLOW_LEVEL_STRENGTH0";
    private static final String GLOW_LEVEL_STRENGTH1 = "GLOW_LEVEL_STRENGTH1";
    private static final String GLOW_LEVEL_STRENGTH2 = "GLOW_LEVEL_STRENGTH2";
    private static final String GLOW_PARTICLE_INTENSITY = "GLOW_PARTICLE_INTENSITY";
    private static final String GLOW_THRESHOLD = "GLOW_THRESHOLD";
    private static final String GPU_ANIMATION = "GPU_ANIMATION";
    private static final String GRAVITY = "GRAVITY";
    private static final String INPUT_SIZE = "INPUT_SIZE";
    private static final String INPUT_SWIPE_CONSTANT = "INPUT_SWIPE_CONSTANT";
    private static final String INPUT_SWIPE_MODE = "INPUT_SWIPE_MODE";
    private static final String INPUT_TOUCH_MODE = "INPUT_TOUCH_MODE";
    private static final String INVERT_COLORS = "INVERT_COLORS";
    private static final String LIGHT_COLOR = "LIGHT_COLOR";
    private static final String LIGHT_INTENSITY = "LIGHT_INTENSITY";
    private static final String LIGHT_RADIUS = "LIGHT_RADIUS";
    private static final String LIGHT_SOURCE = "LIGHT_SOURCE";
    private static final String LIGHT_SOURCE_POSX = "LIGHT_SOURCE_POSX";
    private static final String LIGHT_SOURCE_POSY = "LIGHT_SOURCE_POSY";
    private static final String LIGHT_SOURCE_SPEED = "LIGHT_SOURCE_SPEED";
    private static final String MENU_BUTTON_VISIBILITY = "MENU_BUTTON_VISIBILITY";
    private static final String MULTI_COLOR0 = "MULTI_COLOR0";
    private static final String MULTI_COLOR1 = "MULTI_COLOR1";
    private static final String MULTI_COLOR2 = "MULTI_COLOR2";
    private static final String MULTI_COLOR3 = "MULTI_COLOR3";
    private static final String MULTI_COLOR4 = "MULTI_COLOR4";
    private static final String MULTI_COLOR5 = "MULTI_COLOR5";
    private static final String MULTI_COLOR6 = "MULTI_COLOR6";
    private static final String MULTI_COLOR7 = "MULTI_COLOR7";
    private static final String MULTI_COLOR8 = "MULTI_COLOR8";
    private static final String MULTI_COLOR_DOUBLE = "MULTI_COLOR_DOUBLE";
    private static final String NUM_SOURCES = "NUM_SOURCES";
    private static final String OVERBRIGHT_COLORS = "OVERBRIGHT_COLORS";
    private static final String PARTICLES_COLOR = "PARTICLES_COLOR";
    private static final String PARTICLES_ENABLED = "PARTICLES_ENABLED";
    private static final String PARTICLES_LIFE_TIME_SEC = "PARTICLES_LIFE_TIME_SEC";
    private static final String PARTICLES_MODE = "PARTICLES_MODE";
    private static final String PARTICLES_PER_SEC = "PARTICLES_PER_SEC";
    private static final String PARTICLES_SHAPE = "PARTICLES_SHAPE";
    private static final String PARTICLES_SIZE = "PARTICLES_SIZE";
    private static final String PARTICLES_USE_PAINT_COLOR = "PARTICLES_USE_PAINT_COLOR";
    private static final String PRESET_EXISTS = "PRESET_EXISTS";
    private static final String PRESET_NAME = "PRESET_NAME";
    private static final String QUALITY_BASE_VALUE = "QUALITY_BASE_VALUE";
    private static final String RANDOM_SATURATION = "RANDOM_SATURATION";
    public static final String SETTINGS_LWP_NAME = "LWPSettings";
    public static final String SETTINGS_NAME = "Settings";
    private static final String SHADING_BUMPINESS = "SHADING_BUMPINESS";
    private static final String SHADING_ENABLED = "SHADING_ENABLED";
    private static final String SHADING_FLUID_BRIGHTNESS = "SHADING_FLUID_BRIGHTNESS";
    private static final String SHADING_SPECULAR = "SHADING_SPECULAR";
    private static final String SHADING_SPEC_ONLY_GLOW = "SHADING_SPEC_ONLY_GLOW";
    private static final String SHADING_SPEC_POWER = "SHADING_SPEC_POWER";
    private static final String SHADING_SPEC_TYPE = "SHADING_SPEC_TYPE";
    private static final String SHADOW_FALLOFF_LENGTH = "SHADOW_FALLOFF_LENGTH";
    private static final String SHADOW_INTENSITY = "SHADOW_INTENSITY";
    private static final String SHADOW_INVERSE = "SHADOW_INVERSE";
    private static final String SHADOW_SELF = "SHADOW_SELF";
    private static final String SHADOW_SOURCE = "SHADOW_SOURCE";
    private static final String SOURCE_SPEED = "SOURCE_SPEED";
    private static final String SWIRLINESS = "SWIRLINESS";
    private static final String TOUCH_INPUT_FORCE = "TOUCH_INPUT_FORCE";
    private static final String TOUCH_INPUT_SIZE = "TOUCH_INPUT_SIZE";
    private static final String USE_DETAIL_TEXTURE = "USE_DETAIL_TEXTURE";
    private static final String VEL_LIFE_TIME = "VEL_LIFE_TIME";

    private static class SettingsFileMap implements SettingsMap {
        private Map<String, String> map = new HashMap();

        public SettingsFileMap(String filePath, AssetManager assetMgr) {
            String[] words = SettingsStorage.loadTextFileFromAssets(assetMgr, filePath).split("\\s+");
            if (words.length % 2 != 0) {
                LogUtil.e("SettingsFileMap", "Settings file " + filePath + " has incorrect format");
                return;
            }
            for (int i = 0; i < words.length; i += 2) {
                this.map.put(words[i], words[i + 1]);
            }
        }

        public float getFloat(String key, float defVal) {
            String val = this.map.get(key);
            if (val != null) {
                return Float.valueOf(val).floatValue();
            }
            return defVal;
        }

        public int getInt(String key, int defVal) {
            String val = this.map.get(key);
            if (val != null) {
                return Integer.valueOf(val).intValue();
            }
            return defVal;
        }

        public boolean getBoolean(String key, boolean defVal) {
            String val = this.map.get(key);
            if (val != null) {
                return Boolean.valueOf(val).booleanValue();
            }
            return defVal;
        }
    }

    private interface SettingsMap {
        boolean getBoolean(String str, boolean z);

        float getFloat(String str, float f);

        int getInt(String str, int i);
    }

    private static class SharedPrefsMap implements SettingsMap {
        private SharedPreferences prefs;

        public SharedPrefsMap(SharedPreferences preferences) {
            this.prefs = preferences;
        }

        public float getFloat(String key, float defVal) {
            return this.prefs.getFloat(key, defVal);
        }

        public int getInt(String key, int defVal) {
            return this.prefs.getInt(key, defVal);
        }

        public boolean getBoolean(String key, boolean defVal) {
            return this.prefs.getBoolean(key, defVal);
        }
    }

    static String loadTextFileFromAssets(AssetManager assetMgr, String filePath) {
        try {
            InputStream input = assetMgr.open(filePath);
            byte[] buffer = new byte[input.available()];
            input.read(buffer);
            input.close();
            return new String(buffer);
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static void printSettingsFileToLog(Settings set, String name) {
        LogUtil.i(name, String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(name) + " ") + "FLUID_TYPE " + set.FluidType + " ") + "FORCE " + set.Force + " ") + "NUM_SOURCES " + set.NumSources + " ") + "SOURCE_SPEED " + set.SourceSpeed + " ") + "FLUID_AMOUNT " + set.FluidAmount + " ") + "FLUID_LIFE_TIME " + set.FluidLifeTime + " ") + "COLOR_CHANGE " + set.ColorChange + " ") + "COLOR_OPTION " + set.ColorOption + " ") + "COLOR0 " + set.Colors[0] + " ") + "COLOR1 " + set.Colors[1] + " ") + "COLOR2 " + set.Colors[2] + " ") + "COLOR3 " + set.Colors[3] + " ") + "COLOR4 " + set.Colors[4] + " ") + "COLOR5 " + set.Colors[5] + " ") + "COLOR_ACTIVE0 " + set.ColorsActive[0] + " ") + "COLOR_ACTIVE1 " + set.ColorsActive[1] + " ") + "COLOR_ACTIVE2 " + set.ColorsActive[2] + " ") + "COLOR_ACTIVE3 " + set.ColorsActive[3] + " ") + "COLOR_ACTIVE4 " + set.ColorsActive[4] + " ") + "COLOR_ACTIVE5 " + set.ColorsActive[5] + " ") + "DCOLOR0 " + set.DColors[0] + " ") + "DCOLOR1 " + set.DColors[1] + " ") + "DCOLOR2 " + set.DColors[2] + " ") + "DCOLOR_ACTIVE0 " + set.DColorsActive[0] + " ") + "DCOLOR_ACTIVE1 " + set.DColorsActive[1] + " ") + "DCOLOR_ACTIVE2 " + set.DColorsActive[2] + " ") + "BACKGROUND_COLOR " + set.BackgroundColor + " ") + "INVERT_COLORS " + set.InvertColors + " ") + "PARTICLES_ENABLED " + set.ParticlesEnabled + " ") + "PARTICLES_SHAPE " + set.ParticlesShape + " ") + "PARTICLES_PER_SEC " + ((int) set.ParticlesPerSec) + " ") + "PARTICLES_LIFE_TIME_SEC " + set.ParticlesLifeTimeSec + " ") + "PARTICLES_SIZE " + set.ParticlesSize + " ") + "PARTICLES_COLOR " + set.ParticlesColor + " ") + "PARTICLES_USE_PAINT_COLOR " + set.ParticlesUsePaintColor + " ") + "BORDER_MODE " + set.BorderMode + " ") + "GRAVITY " + set.Gravity + " ") + "GLOW " + set.Glow + " ") + "GLOW_LEVEL_STRENGTH0 " + set.GlowLevelStrength0 + " ") + "GLOW_LEVEL_STRENGTH1 " + set.GlowLevelStrength1 + " ") + "GLOW_LEVEL_STRENGTH2 " + set.GlowLevelStrength2 + " ") + "USE_DETAIL_TEXTURE " + set.UseDetailTexture + " ") + "DETAIL_TEXTURE " + set.DetailTexture + " ") + "DETAIL_UV_SCALE " + set.DetailUVScale + " ");
    }

    private static void saveSettings(Context context, Settings set, String setName) {
        SharedPreferences.Editor editor = context.getSharedPreferences(setName, 0).edit();
        editor.putInt(FLUID_TYPE, set.FluidType);
        editor.putFloat(FORCE, set.Force);
        editor.putFloat(INPUT_SIZE, set.InputSize);
        editor.putFloat(TOUCH_INPUT_FORCE, set.TouchInputForce);
        editor.putFloat(TOUCH_INPUT_SIZE, set.TouchInputSize);
        editor.putInt(INPUT_SWIPE_MODE, set.InputSwipeMode);
        editor.putInt(INPUT_TOUCH_MODE, set.InputTouchMode);
        editor.putBoolean(INPUT_SWIPE_CONSTANT, set.InputSwipeConstant);
        editor.putFloat(VEL_LIFE_TIME, set.VelLifetime);
        editor.putFloat(SWIRLINESS, set.Swirliness);
        editor.putInt(NUM_SOURCES, set.NumSources);
        editor.putFloat(SOURCE_SPEED, set.SourceSpeed);
        editor.putBoolean(AUTO_ON_RESUME, set.AutoOnResume);
        editor.putInt(FPS_LIMIT, set.FPSLimit);
        editor.putInt(GPU_ANIMATION, set.GPUQuality);
        editor.putBoolean(ALLOW_MULTITHREADING, set.AllowMultithreading);
        editor.putFloat(FLUID_AMOUNT, set.FluidAmount);
        editor.putFloat(FLUID_LIFE_TIME, set.FluidLifeTime);
        editor.putInt(COLOR_CHANGE, set.ColorChange);
        editor.putInt(COLOR_OPTION, set.ColorOption);
        editor.putFloat(RANDOM_SATURATION, set.RandomSaturation);
        editor.putInt(COLOR0, set.Colors[0]);
        editor.putInt(COLOR1, set.Colors[1]);
        editor.putInt(COLOR2, set.Colors[2]);
        editor.putInt(COLOR3, set.Colors[3]);
        editor.putInt(COLOR4, set.Colors[4]);
        editor.putInt(COLOR5, set.Colors[5]);
        editor.putBoolean(COLOR_ACTIVE0, set.ColorsActive[0]);
        editor.putBoolean(COLOR_ACTIVE1, set.ColorsActive[1]);
        editor.putBoolean(COLOR_ACTIVE2, set.ColorsActive[2]);
        editor.putBoolean(COLOR_ACTIVE3, set.ColorsActive[3]);
        editor.putBoolean(COLOR_ACTIVE4, set.ColorsActive[4]);
        editor.putBoolean(COLOR_ACTIVE5, set.ColorsActive[5]);
        editor.putInt(DCOLOR0, set.DColors[0]);
        editor.putInt(DCOLOR1, set.DColors[1]);
        editor.putInt(DCOLOR2, set.DColors[2]);
        editor.putBoolean(DCOLOR_ACTIVE0, set.DColorsActive[0]);
        editor.putBoolean(DCOLOR_ACTIVE1, set.DColorsActive[1]);
        editor.putBoolean(DCOLOR_ACTIVE2, set.DColorsActive[2]);
        editor.putInt(BACKGROUND_COLOR, set.BackgroundColor);
        editor.putInt(MULTI_COLOR0, set.MultiColors[0]);
        editor.putInt(MULTI_COLOR1, set.MultiColors[1]);
        editor.putInt(MULTI_COLOR2, set.MultiColors[2]);
        editor.putInt(MULTI_COLOR3, set.MultiColors[3]);
        editor.putInt(MULTI_COLOR4, set.MultiColors[4]);
        editor.putInt(MULTI_COLOR5, set.MultiColors[5]);
        editor.putInt(MULTI_COLOR6, set.MultiColors[6]);
        editor.putInt(MULTI_COLOR7, set.MultiColors[7]);
        editor.putInt(MULTI_COLOR8, set.MultiColors[8]);
        editor.putBoolean(MULTI_COLOR_DOUBLE, set.MultiColorDouble);
        editor.putBoolean(CARTOON_COLORS, set.CartoonColors);
        editor.putBoolean(OVERBRIGHT_COLORS, set.OverbrightColors);
        editor.putBoolean(INVERT_COLORS, set.InvertColors);
        editor.putFloat(COLOR_RANDOMNESS, set.ColorRandomness);
        editor.putBoolean(PARTICLES_ENABLED, set.ParticlesEnabled);
        editor.putInt(PARTICLES_MODE, set.ParticlesMode);
        editor.putInt(PARTICLES_SHAPE, set.ParticlesShape);
        editor.putFloat(PARTICLES_PER_SEC, set.ParticlesPerSec);
        editor.putFloat(PARTICLES_LIFE_TIME_SEC, set.ParticlesLifeTimeSec);
        editor.putFloat(PARTICLES_SIZE, set.ParticlesSize);
        editor.putInt(PARTICLES_COLOR, set.ParticlesColor);
        editor.putBoolean(PARTICLES_USE_PAINT_COLOR, set.ParticlesUsePaintColor);
        editor.putInt(BORDER_MODE, set.BorderMode);
        editor.putFloat(GRAVITY, set.Gravity);
        editor.putBoolean(GLOW, set.Glow);
        editor.putFloat(GLOW_LEVEL_STRENGTH0, set.GlowLevelStrength0);
        editor.putFloat(GLOW_LEVEL_STRENGTH1, set.GlowLevelStrength1);
        editor.putFloat(GLOW_LEVEL_STRENGTH2, set.GlowLevelStrength2);
        editor.putFloat(GLOW_THRESHOLD, set.GlowThreshold);
        editor.putFloat(GLOW_PARTICLE_INTENSITY, set.GlowParticleIntensity);
        editor.putBoolean(LIGHT_SOURCE, set.LightSource);
        editor.putFloat(LIGHT_RADIUS, set.LightRadius);
        editor.putFloat(LIGHT_INTENSITY, set.LightIntensity);
        editor.putInt(LIGHT_COLOR, set.LightColor);
        editor.putFloat(LIGHT_SOURCE_SPEED, set.LightSourceSpeed);
        editor.putFloat(LIGHT_SOURCE_POSX, set.LightSourcePosX);
        editor.putFloat(LIGHT_SOURCE_POSY, set.LightSourcePosY);
        editor.putBoolean(SHADOW_SOURCE, set.ShadowSource);
        editor.putBoolean(SHADOW_SELF, set.ShadowSelf);
        editor.putBoolean(SHADOW_INVERSE, set.ShadowInverse);
        editor.putFloat(SHADOW_INTENSITY, set.ShadowIntensity);
        editor.putFloat(SHADOW_FALLOFF_LENGTH, set.ShadowFalloffLength);
        editor.putBoolean(SHADING_ENABLED, set.ShadingEnabled);
        editor.putFloat(SHADING_BUMPINESS, set.ShadingBumpiness);
        editor.putFloat(SHADING_FLUID_BRIGHTNESS, set.ShadingFluidBrightness);
        editor.putInt(SHADING_SPEC_TYPE, set.ShadingSpecType);
        editor.putFloat(SHADING_SPECULAR, set.ShadingSpecular);
        editor.putFloat(SHADING_SPEC_POWER, set.ShadingSpecPower);
        editor.putBoolean(SHADING_SPEC_ONLY_GLOW, set.ShadingSpecOnlyGlow);
        editor.putBoolean(USE_DETAIL_TEXTURE, set.UseDetailTexture);
        editor.putInt(DETAIL_TEXTURE, set.DetailTexture);
        editor.putFloat(DETAIL_UV_SCALE, set.DetailUVScale);
        editor.putInt(QUALITY_BASE_VALUE, set.QualityBaseValue);
        editor.putInt(EFFECTS_QUALITY, set.EffectsQuality);
        editor.putInt(MENU_BUTTON_VISIBILITY, set.MenuButtonVisibility);
        editor.commit();
    }

    public static boolean prefFileExist(Context context, String setName) {
        return context.getSharedPreferences(setName, 0).contains(FLUID_TYPE);
    }

    private static void loadSettingsFromMap(SettingsMap setMap, Settings set, boolean loadHW) {
        set.FluidType = setMap.getInt(FLUID_TYPE, 0);
        set.Force = setMap.getFloat(FORCE, 3.0E-4f);
        set.InputSize = setMap.getFloat(INPUT_SIZE, 0.03f);
        set.TouchInputForce = setMap.getFloat(TOUCH_INPUT_FORCE, 0.2f);
        set.TouchInputSize = setMap.getFloat(TOUCH_INPUT_SIZE, 0.25f);
        set.InputSwipeMode = setMap.getInt(INPUT_SWIPE_MODE, 0);
        set.InputTouchMode = setMap.getInt(INPUT_TOUCH_MODE, 4);
        set.InputSwipeConstant = setMap.getBoolean(INPUT_SWIPE_CONSTANT, false);
        set.VelLifetime = setMap.getFloat(VEL_LIFE_TIME, 1.0f);
        set.Swirliness = setMap.getFloat(SWIRLINESS, 0.0f);
        set.NumSources = setMap.getInt(NUM_SOURCES, 0);
        set.SourceSpeed = setMap.getFloat(SOURCE_SPEED, 6.0E-4f);
        set.AutoOnResume = setMap.getBoolean(AUTO_ON_RESUME, false);
        set.FluidAmount = setMap.getFloat(FLUID_AMOUNT, 0.002f);
        set.FluidLifeTime = setMap.getFloat(FLUID_LIFE_TIME, 5.0f);
        set.RandomSaturation = setMap.getFloat(RANDOM_SATURATION, 0.75f);
        set.ColorChange = setMap.getInt(COLOR_CHANGE, 0);
        set.ColorOption = setMap.getInt(COLOR_OPTION, 1);
        set.Colors[0] = setMap.getInt(COLOR0, SupportMenu.CATEGORY_MASK);
        set.Colors[1] = setMap.getInt(COLOR1, -16711936);
        set.Colors[2] = setMap.getInt(COLOR2, -16776961);
        set.Colors[3] = setMap.getInt(COLOR3, -1);
        set.Colors[4] = setMap.getInt(COLOR4, -1);
        set.Colors[5] = setMap.getInt(COLOR5, -1);
        set.ColorsActive[0] = setMap.getBoolean(COLOR_ACTIVE0, true);
        set.ColorsActive[1] = setMap.getBoolean(COLOR_ACTIVE1, true);
        set.ColorsActive[2] = setMap.getBoolean(COLOR_ACTIVE2, true);
        set.ColorsActive[3] = setMap.getBoolean(COLOR_ACTIVE3, false);
        set.ColorsActive[4] = setMap.getBoolean(COLOR_ACTIVE4, false);
        set.ColorsActive[5] = setMap.getBoolean(COLOR_ACTIVE5, false);
        set.DColors[0] = setMap.getInt(DCOLOR0, SupportMenu.CATEGORY_MASK);
        set.DColors[1] = setMap.getInt(DCOLOR1, -16711936);
        set.DColors[2] = setMap.getInt(DCOLOR2, -16711936);
        set.DColorsActive[0] = setMap.getBoolean(DCOLOR_ACTIVE0, true);
        set.DColorsActive[1] = setMap.getBoolean(DCOLOR_ACTIVE1, true);
        set.DColorsActive[2] = setMap.getBoolean(DCOLOR_ACTIVE2, true);
        set.BackgroundColor = setMap.getInt(BACKGROUND_COLOR, -1);
        set.MultiColors[0] = setMap.getInt(MULTI_COLOR0, 0);
        set.MultiColors[1] = setMap.getInt(MULTI_COLOR1, 54015);
        set.MultiColors[2] = setMap.getInt(MULTI_COLOR2, 14155520);
        set.MultiColors[3] = setMap.getInt(MULTI_COLOR3, 16711739);
        set.MultiColors[4] = setMap.getInt(MULTI_COLOR4, 0);
        set.MultiColors[5] = setMap.getInt(MULTI_COLOR5, 15116346);
        set.MultiColors[6] = setMap.getInt(MULTI_COLOR6, 1503083);
        set.MultiColors[7] = setMap.getInt(MULTI_COLOR7, 4211181);
        set.MultiColors[8] = setMap.getInt(MULTI_COLOR8, 0);
        set.MultiColorDouble = setMap.getBoolean(MULTI_COLOR_DOUBLE, true);
        set.CartoonColors = setMap.getBoolean(CARTOON_COLORS, false);
        set.OverbrightColors = setMap.getBoolean(OVERBRIGHT_COLORS, true);
        set.InvertColors = setMap.getBoolean(INVERT_COLORS, false);
        set.ColorRandomness = setMap.getFloat(COLOR_RANDOMNESS, 0.0f);
        set.ParticlesEnabled = setMap.getBoolean(PARTICLES_ENABLED, false);
        set.ParticlesMode = setMap.getInt(PARTICLES_MODE, 0);
        set.ParticlesShape = setMap.getInt(PARTICLES_SHAPE, 0);
        set.ParticlesPerSec = setMap.getFloat(PARTICLES_PER_SEC, 1000.0f);
        set.ParticlesLifeTimeSec = setMap.getFloat(PARTICLES_LIFE_TIME_SEC, 5.0f);
        set.ParticlesSize = setMap.getFloat(PARTICLES_SIZE, 15.0f);
        set.ParticlesColor = setMap.getInt(PARTICLES_COLOR, -3355444);
        set.ParticlesUsePaintColor = setMap.getBoolean(PARTICLES_USE_PAINT_COLOR, true);
        set.BorderMode = setMap.getInt(BORDER_MODE, 0);
        set.Gravity = setMap.getFloat(GRAVITY, 0.0f);
        set.Glow = setMap.getBoolean(GLOW, false);
        set.GlowLevelStrength0 = setMap.getFloat(GLOW_LEVEL_STRENGTH0, 0.3f);
        set.GlowLevelStrength1 = setMap.getFloat(GLOW_LEVEL_STRENGTH1, 0.3f);
        set.GlowLevelStrength2 = setMap.getFloat(GLOW_LEVEL_STRENGTH2, 0.3f);
        set.GlowThreshold = setMap.getFloat(GLOW_THRESHOLD, 0.0f);
        set.GlowParticleIntensity = setMap.getFloat(GLOW_PARTICLE_INTENSITY, 0.0f);
        set.LightSource = setMap.getBoolean(LIGHT_SOURCE, false);
        set.LightRadius = setMap.getFloat(LIGHT_RADIUS, 1.0f);
        set.LightIntensity = setMap.getFloat(LIGHT_INTENSITY, 1.0f);
        set.LightColor = setMap.getInt(LIGHT_COLOR, -1);
        set.LightSourceSpeed = setMap.getFloat(LIGHT_SOURCE_SPEED, 0.0f);
        set.LightSourcePosX = setMap.getFloat(LIGHT_SOURCE_POSX, 0.5f);
        set.LightSourcePosY = setMap.getFloat(LIGHT_SOURCE_POSY, 0.5f);
        set.ShadowSource = setMap.getBoolean(SHADOW_SOURCE, false);
        set.ShadowSelf = setMap.getBoolean(SHADOW_SELF, true);
        set.ShadowInverse = setMap.getBoolean(SHADOW_INVERSE, false);
        set.ShadowIntensity = setMap.getFloat(SHADOW_INTENSITY, 3.0f);
        set.ShadowFalloffLength = setMap.getFloat(SHADOW_FALLOFF_LENGTH, 0.25f);
        set.ShadingEnabled = setMap.getBoolean(SHADING_ENABLED, false);
        set.ShadingBumpiness = setMap.getFloat(SHADING_BUMPINESS, 0.75f);
        set.ShadingFluidBrightness = setMap.getFloat(SHADING_FLUID_BRIGHTNESS, 0.95f);
        set.ShadingSpecType = setMap.getInt(SHADING_SPEC_TYPE, 1);
        set.ShadingSpecular = setMap.getFloat(SHADING_SPECULAR, 1.3f);
        set.ShadingSpecPower = setMap.getFloat(SHADING_SPEC_POWER, 0.4f);
        set.ShadingSpecOnlyGlow = setMap.getBoolean(SHADING_SPEC_ONLY_GLOW, false);
        set.UseDetailTexture = setMap.getBoolean(USE_DETAIL_TEXTURE, false);
        set.DetailTexture = setMap.getInt(DETAIL_TEXTURE, 0);
        set.DetailUVScale = setMap.getFloat(DETAIL_UV_SCALE, 2.5f);
        if (loadHW) {
            set.QualityBaseValue = setMap.getInt(QUALITY_BASE_VALUE, 1);
            set.GPUQuality = setMap.getInt(GPU_ANIMATION, 2);
            set.EffectsQuality = setMap.getInt(EFFECTS_QUALITY, 1);
            set.MenuButtonVisibility = setMap.getInt(MENU_BUTTON_VISIBILITY, 0);
            if (set.QualityBaseValue > 10) {
                set.MenuButtonVisibility = 0;
                int oldValue = set.QualityBaseValue;
                int oldGpuValue = set.GPUQuality;
                if (oldValue <= 208) {
                    set.QualityBaseValue = 0;
                    set.EffectsQuality = 0;
                } else {
                    set.QualityBaseValue = 1;
                }
                if (oldGpuValue == 0) {
                    set.GPUQuality = 0;
                } else if (oldGpuValue == 1 && oldValue <= 208) {
                    set.GPUQuality = 1;
                } else if ((oldGpuValue != 1 || oldValue > 384) && oldValue > 208) {
                    set.EffectsQuality = 2;
                    if (oldGpuValue == 2 && oldValue == 576) {
                        set.GPUQuality = 4;
                        set.EffectsQuality = 3;
                    } else {
                        set.GPUQuality = 3;
                        set.EffectsQuality = 2;
                    }
                } else {
                    set.GPUQuality = 2;
                }
            }
            set.FPSLimit = setMap.getInt(FPS_LIMIT, 0);
            set.AllowMultithreading = setMap.getBoolean(ALLOW_MULTITHREADING, false);
        }
        for (int i = 0; i < 6; i++) {
            int[] iArr = set.Colors;
            iArr[i] = iArr[i] | ViewCompat.MEASURED_STATE_MASK;
        }
        for (int i2 = 0; i2 < 3; i2++) {
            int[] iArr2 = set.DColors;
            iArr2[i2] = iArr2[i2] | ViewCompat.MEASURED_STATE_MASK;
        }
        for (int i3 = 0; i3 < 9; i3++) {
            int[] iArr3 = set.MultiColors;
            iArr3[i3] = iArr3[i3] | ViewCompat.MEASURED_STATE_MASK;
        }
        set.BackgroundColor |= ViewCompat.MEASURED_STATE_MASK;
        set.ParticlesColor |= ViewCompat.MEASURED_STATE_MASK;
        set.LightColor |= ViewCompat.MEASURED_STATE_MASK;
    }

    private static void loadSettingsFromSharedPrefs(Context context, Settings set, String setName, boolean loadHW) {
        loadSettingsFromMap(new SharedPrefsMap(context.getSharedPreferences(setName, 0)), set, loadHW);
    }

    static void loadSessionSettings(Context context, Settings set, String name) {
        loadSettingsFromSharedPrefs(context, set, name, true);
    }

    static void saveSessionSettings(Context context, Settings set, String name) {
        saveSettings(context, set, name);
    }

    static void loadSettingsFromUserPreset(Context context, Settings set, int presetID) {
        loadSettingsFromSharedPrefs(context, set, "UserSettings" + presetID, false);
    }

    static void saveSettingsToUserPreset(Context context, Settings set, int presetID, String presetName) {
        saveSettings(context, set, "UserSettings" + presetID);
        SharedPreferences.Editor editor = context.getSharedPreferences("UserSettings" + presetID, 0).edit();
        editor.putString(PRESET_NAME, presetName);
        editor.putBoolean(PRESET_EXISTS, true);
        editor.commit();
    }

    static void loadInternalPresetFromFile(Preset preset, AssetManager assetMgr) {
        loadSettingsFromMap(new SettingsFileMap("presets/" + preset.Name.replaceAll("\\s+", ""), assetMgr), preset.Set, false);
    }

    static String getUserPresetName(Context context, int presetID) {
        return context.getSharedPreferences("UserSettings" + presetID, 0).getString(PRESET_NAME, "Empty");
    }

    static boolean isSavedUserPreset(Context context, int presetID) {
        return context.getSharedPreferences("UserSettings" + presetID, 0).getBoolean(PRESET_EXISTS, false);
    }
}
