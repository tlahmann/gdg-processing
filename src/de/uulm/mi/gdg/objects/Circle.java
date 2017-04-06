package de.uulm.mi.gdg.objects;

import processing.core.PVector;

/**
 * @author Tobias Lahmann
 */
public class Circle {
    public PVector position;
    public float radius;
    public int color;

    /**
     * Constructor for the Circle.
     *
     * @param position The position of the circle
     * @param radius   The radius of the circle
     * @param color    The color of the circles stroke
     */
    public Circle(PVector position, float radius, int color) {
        this.position = position;
        this.radius = radius;
        this.color = color;
    }
}
