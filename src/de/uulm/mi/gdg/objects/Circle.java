package de.uulm.mi.gdg.objects;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PShape;
import processing.core.PVector;

/**
 * @author Tobias Lahmann
 */
public class Circle implements Entity {
    private static PApplet canvas;
    private PShape shape;
    private PVector position;
    private float angle;
    private float speed;
    private float weight;
    private float jitter;

    /**
     * Constructor for the Arc (Circle). Takes the parent (canvas) to draw on. Constructs random values for the diameter
     * of the shape, the rotation. The arc gets a randomly assigned direction as well as random speed to bring some
     * excitement to the animation.
     *
     * @param papa     The PApplet to draw on
     * @param position The position of the circle
     * @param radius   The radius of the circle
     * @param color    The color of the circles stroke
     */
    public Circle(PApplet papa, PVector position, float radius, float weight, int color) {
        canvas = papa;
        this.position = position;
        this.weight = weight;

        // Shapes bring the advantage of being easily modifiable and adjustable
        shape = canvas.createShape();
        shape.beginShape();

        shape.stroke(color);
        shape.noFill();

        // The arc itself. The span variable sets the dimension of the arc to a random value starting from quarter pi
        // because arcs that are too small are not pretty. I'm their arch nemesis
        float span = canvas.random(PConstants.QUARTER_PI, PConstants.TWO_PI);
        for (float a = -span; a < 0; a += 0.1) {
            shape.vertex(PApplet.cos(a) * radius / 2, PApplet.sin(a) * radius / 2);
        }

        this.angle = canvas.random(PConstants.TWO_PI);
        shape.rotate(angle);

        shape.endShape();

        int direction = -1 + (int) canvas.random(2) * 2; // results in either a 1 or -1
        this.speed = direction * canvas.random(0.003f, 0.007f);
    }

    /**
     * Displays the shape on the stored static canvas object.
     */
    public void display() {
        canvas.pushMatrix();

        // update the stroke weight
        shape.setStrokeWeight(weight + jitter);

        canvas.translate(position.x, position.y);
        canvas.rotate(angle);
        canvas.shape(shape);

        canvas.popMatrix();
    }

    public void update() {
        this.angle = (this.angle + this.speed) % PApplet.TWO_PI;
    }

    /**
     * Recalculates the angle of rotation depending on the direction of rotation and the speed (combined in speed) at
     * which the arc should be rotated.
     * Resets the jitter according to the jitter value recieved.
     *
     * @param jitter Value of change applied to the stroke width
     */
    public void update(float jitter) {
        this.jitter = jitter / 2;
        update();
    }
}
