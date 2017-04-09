package de.uulm.mi.gdg.controller;

import de.looksgood.ani.Ani;
import de.looksgood.ani.AniConstants;
import de.looksgood.ani.easing.Easing;
import de.uulm.mi.gdg.objects.Entity;
import processing.core.PApplet;
import processing.data.JSONArray;
import processing.data.JSONObject;

import java.util.ArrayList;

public class Blinker implements Entity {
    private static PApplet aniObject;
    private volatile ArrayList<CustomAnimation> anis;
    private CustomAnimation ani;

    /**
     * Blinker constructor.
     *
     * @param papa The object to execute the animation on.
     */
    public Blinker(PApplet papa) {
        aniObject = papa;

        startAnimation();
    }

    /**
     * Initializes the animations into the anis-list to get a whole new start even if the song restarts.
     */
    public void startAnimation() {
        anis = AniImporter.importAnimation(aniObject, "./data/timing/timing.json", "background");
    }

    /**
     * Starts the animation if one is present.
     */
    public void display() {
        if (ani == null) {
            return;
        }
        Ani.to(aniObject, ani.duration, ani.params, ani.value, ani.mode);
        ani = null;

    }

    public void update() {

    }

    /**
     * Takes a value of the position of the playhead from the equalizer. It is intended to start animations just when
     * the cue-position is greater than the start position of the animation.
     * <p>A possible error could be that if the animation is not gone through linearly all animations that have a
     * smaller start value than the cue they get all started simultaneously.</p>
     *
     * @param val the cue position of the song
     */
    public void update(float val) {
        if (anis.size() == 0) {
            return;
        }
        if (val / 1000 < anis.get(0).start) {
            return;
        }

        ani = anis.remove(0);
    }
}
