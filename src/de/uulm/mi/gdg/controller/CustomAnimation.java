package de.uulm.mi.gdg.controller;

import de.looksgood.ani.easing.Easing;

/**
 * A class to hold all needed information for the variation of parameters in our animation
 */
class CustomAnimation {
    float start;
    float duration;
    int value;
    String params;
    Easing mode;

    /**
     * Constructor of the CustomAnimation
     *
     * @param start    start cue of the animation in seconds
     * @param duration the duration of the animation in seconds
     * @param params   what parameter should be changed
     * @param value    what the finish value of the animation should be
     * @param mode     the animation mode as Easing
     */
    CustomAnimation(float start, float duration, String params, int value, Easing mode) {
        this.start = start;
        this.duration = duration;
        this.params = params;
        this.value = value;
        this.mode = mode;
    }
}
