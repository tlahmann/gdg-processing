package de.uulm.mi.gdg.objects;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PShape;
import processing.core.PVector;

/**
 * @author Tobias Lahmann
 */
public class Circle {
    private static PApplet canvas;
    private PShape shape;
    private PVector position;

    /**
     * Constructor for the Circle. Creates a PShape.
     *
     * @param papa   The PApplet to draw on
     * @param position The position of the circle
     * @param radius   The radius of the circle
     * @param color    The color of the circles stroke
     */
    public Circle(PApplet papa, PVector position, float radius, int color) {
        canvas = papa;
        this.position = position;

        // Shapes bring the advantage of being easily modifiable and adjustable
        shape = canvas.createShape(PConstants.ELLIPSE, 0, 0, radius, radius);
        shape.setStroke(color);
    }

    /**
     * Displays the shape on the stored static canvas object.
     */
    public void display() {
        canvas.pushMatrix();

        canvas.translate(position.x, position.y);
        canvas.shape(shape);

        canvas.popMatrix();
    }
}
