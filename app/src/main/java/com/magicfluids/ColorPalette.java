package com.magicfluids;

import androidx.core.view.ViewCompat;

import java.util.ArrayList;

public class ColorPalette {
    static ArrayList<ColorPalette> Palettes;
    int[] Colors = new int[4];
    String Name;
    int NumColors;

    ColorPalette(int numColors, int c0, int c1, int c2, int c3, String name) {
        this.NumColors = numColors;
        this.Colors[0] = c0 | ViewCompat.MEASURED_STATE_MASK;
        this.Colors[1] = c1 | ViewCompat.MEASURED_STATE_MASK;
        this.Colors[2] = c2 | ViewCompat.MEASURED_STATE_MASK;
        this.Colors[3] = c3 | ViewCompat.MEASURED_STATE_MASK;
        this.Name = name;
    }

    static void init() {
        Palettes = new ArrayList<>();
        Palettes.add(new ColorPalette(2, 16724736, 22015, ViewCompat.MEASURED_SIZE_MASK, ViewCompat.MEASURED_SIZE_MASK, "Hot and cold"));
        Palettes.add(new ColorPalette(3, 14496546, 2276351, 12320614, ViewCompat.MEASURED_SIZE_MASK, "RGB"));
        Palettes.add(new ColorPalette(3, 5575031, 14527231, 16716151, ViewCompat.MEASURED_SIZE_MASK, "Purple sunset"));
        Palettes.add(new ColorPalette(3, 14048017, 1149081, 16776836, ViewCompat.MEASURED_SIZE_MASK, "Salon"));
        Palettes.add(new ColorPalette(2, 16610627, 1930868, ViewCompat.MEASURED_SIZE_MASK, ViewCompat.MEASURED_SIZE_MASK, "Orange and deep sea"));
        Palettes.add(new ColorPalette(2, 12346998, 16244659, ViewCompat.MEASURED_SIZE_MASK, ViewCompat.MEASURED_SIZE_MASK, "Pink and grey"));
    }
}
