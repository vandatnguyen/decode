package com.magicfluids;

import android.content.res.AssetManager;
import java.util.ArrayList;

public class Preset {
    static ArrayList<Preset> List = null;
    static final int NUM = 2;
    static AssetManager sAssetMgr = null;
    String Name;
    boolean New;
    Settings Set;
    Status Stat;

    enum Status {
        FREE,
        LOCKED,
        UNLOCKABLE
    }

    Preset(Settings s, String name, Status status, boolean isNew) {
        this.Set = s;
        this.Name = name;
        this.Stat = status;
        this.New = isNew;
    }

    static void addPreset(String name, Status status, boolean isNew) {
        Preset p = new Preset(new Settings(), name, status, isNew);
        SettingsStorage.loadInternalPresetFromFile(p, sAssetMgr);
        List.add(p);
    }

    public static int getPresetIndexByName(String name) {
        for (int i = 0; i < List.size(); i++) {
            if (List.get(i).Name.equals(name)) {
                return i;
            }
        }
        return 0;
    }

    static void init(AssetManager assetMgr) {
        if (List == null) {
            sAssetMgr = assetMgr;
            List = new ArrayList<>();
            addPreset("Super Smoke", Status.FREE, true);
            addPreset("Flashy Fluids", Status.FREE, false);
            addPreset("Dimension of Depth", Status.FREE, true);
            addPreset("Gleeful Glimmers", Status.FREE, false);
            addPreset("Fire and Flame", Status.UNLOCKABLE, true);
            addPreset("Molten Metal", Status.UNLOCKABLE, true);
            addPreset("Weird Water", Status.UNLOCKABLE, true);
            addPreset("Silky Smooth", Status.UNLOCKABLE, true);
            addPreset("Disturbing Details", Status.UNLOCKABLE, true);
            addPreset("Uniquely Unreal", Status.LOCKED, true);
            addPreset("Blinding Bliss", Status.UNLOCKABLE, false);
            addPreset("Life of Lights", Status.UNLOCKABLE, false);
            addPreset("Cosmic Charm", Status.UNLOCKABLE, false);
            addPreset("Strange Substance", Status.UNLOCKABLE, false);
            addPreset("Jittery Jello", Status.UNLOCKABLE, false);
            addPreset("Radioactive Rumble", Status.LOCKED, false);
            addPreset("Wizard Wand", Status.LOCKED, false);
            addPreset("Dancing in the Dark", Status.LOCKED, false);
            addPreset("Blinking Beauty", Status.LOCKED, false);
            addPreset("Earthly Elements", Status.FREE, false);
            addPreset("Crazy Colors", Status.FREE, false);
            addPreset("Random Remarkability", Status.FREE, false);
            addPreset("Gravity Game", Status.FREE, false);
            addPreset("Wonderful Whirls", Status.UNLOCKABLE, false);
            addPreset("Fading Forms", Status.LOCKED, false);
            addPreset("Wavy Winter", Status.UNLOCKABLE, false);
            addPreset("Bloody Blue", Status.FREE, false);
            addPreset("Lake of Lava", Status.FREE, false);
            addPreset("Steady Sea", Status.UNLOCKABLE, false);
            addPreset("Freaky Fun", Status.UNLOCKABLE, false);
            addPreset("Incredible Ink", Status.UNLOCKABLE, false);
            addPreset("Gentle Glow", Status.UNLOCKABLE, false);
            addPreset("Transient Thoughts", Status.LOCKED, false);
            addPreset("Glorious Galaxies", Status.LOCKED, false);
            addPreset("Something Strange", Status.LOCKED, false);
            addPreset("Cloudy Composition", Status.LOCKED, false);
            addPreset("Glowing Glare", Status.LOCKED, false);
            addPreset("Trippy Tint", Status.UNLOCKABLE, false);
            addPreset("Girls Game", Status.UNLOCKABLE, false);
            addPreset("Particle Party", Status.UNLOCKABLE, false);
            addPreset("Busy Brilliance", Status.UNLOCKABLE, false);
            addPreset("Grainy Greatness", Status.LOCKED, false);
            addPreset("Magic Trail by Toni", Status.LOCKED, false);
            addPreset("Lovely Liquid", Status.LOCKED, false);
            addPreset("Floating Flames", Status.LOCKED, false);
            addPreset("Glimming Glow", Status.LOCKED, false);
            addPreset("Subtle Setting", Status.LOCKED, false);
            addPreset("Calm Christmas", Status.LOCKED, false);
            addPreset("Bouncing Beach", Status.LOCKED, false);
            addPreset("Classy Combination", Status.LOCKED, false);
            addPreset("Swirly Sparkles", Status.LOCKED, false);
        }
    }
}
