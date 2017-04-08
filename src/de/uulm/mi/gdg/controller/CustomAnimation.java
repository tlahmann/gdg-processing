package de.uulm.mi.gdg.controller;

/**
 * A class to hold all needed information for the variation of parameters in our animation
 */
class CustomAnimation {
    float start;
    float duration;
    int value;
    String params;

    /**
     * Constructor of the CustomAnimation
     *
     * @param start    start cue of the animation in seconds
     * @param duration the duration of the animation in seconds
     * @param params   what parameter should be changed
     * @param value    what the finish value of the animation should be
     */
    CustomAnimation(float start, float duration, String params, int value) {
        this.start = start;
        this.duration = duration;
        this.params = params;
        this.value = value;
    }
}
