package de.uulm.mi.gdg;

import processing.core.PApplet;

/**
 * The Equalizer class is the main controller for the Animation.
 *
 * @author Tobias Lahmann
 */
public class Equalizer extends PApplet {
    /**
     * The settings() method runs before the sketch has been set up, so other Processing functions cannot be used at
     * this point.
     */
    public void settings() {
        setSize(1240, 720);
    }

    /**
     * The setup method is called before displaying any objects. The Method calls commands in the Processing API.
     */
    public void setup() {

    }

    /**
     * Draw method is responsible for displaying all objects.
     */
    public void draw() {

    }

    /**
     * Main method to instantiate the PApplet.
     *
     * @param args Arguments passed to the PApplet.
     */
    public static void main(String[] args) {
        PApplet.main(new String[]{Equalizer.class.getName()});
    }
}
