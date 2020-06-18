package com.magicfluids;

import android.os.Build;
import java.util.ArrayList;

public class QualitySetting {
    public static ArrayList<String> EffectsSettings = null;
    public static ArrayList<String> PaintResSettings = null;
    public static ArrayList<String> SimResSettings = null;

    public static void init() {
        if (SimResSettings == null) {
            SimResSettings = new ArrayList<>();
            SimResSettings.add("Low");
            SimResSettings.add("Medium (recommended)");
            SimResSettings.add("High");
            PaintResSettings = new ArrayList<>();
            PaintResSettings.add("Lowest (no GPU support)");
            PaintResSettings.add("Low");
            PaintResSettings.add("Medium");
            PaintResSettings.add("High");
            PaintResSettings.add("Very high");
            PaintResSettings.add("Best");
            EffectsSettings = new ArrayList<>();
            EffectsSettings.add("Low");
            EffectsSettings.add("Medium");
            EffectsSettings.add("High");
            EffectsSettings.add("Best");
        }
    }

    public static void setQualitySettingsToDefault(Settings settings) {
        settings.QualityBaseValue = 1;
        settings.GPUQuality = 2;
        settings.EffectsQuality = 1;
        if (Build.VERSION.SDK_INT <= 19) {
            settings.QualityBaseValue = 0;
        }
    }

    private static int getNumberOfCores() {
        if (Build.VERSION.SDK_INT >= 17) {
            return Runtime.getRuntime().availableProcessors();
        }
        return 1;
    }

    public static void setQualitySettingsFromPerf(Settings settings, NativeInterface ntv) {
        settings.QualityBaseValue = 1;
        int perfHeuristic = ntv.perfHeuristic();
        int numCores = getNumberOfCores();
        if (numCores <= 2) {
            settings.GPUQuality = 2;
            settings.EffectsQuality = 1;
            settings.QualityBaseValue = 0;
        } else if (numCores <= 4) {
            settings.GPUQuality = 3;
            settings.EffectsQuality = 1;
        } else {
            settings.GPUQuality = 3;
            settings.EffectsQuality = 2;
        }
        if (Build.VERSION.SDK_INT <= 19) {
            settings.QualityBaseValue = 0;
            settings.GPUQuality = 0;
            settings.EffectsQuality = 0;
        }
    }
}
