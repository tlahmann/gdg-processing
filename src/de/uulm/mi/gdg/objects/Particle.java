package de.uulm.mi.gdg.objects;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PShape;
import processing.core.PVector;

public class Particle implements Entity {
    private static PApplet canvas;
    private PShape self;
    private PVector position;
    private PVector velocity;

    /**
     * Particle element constructor
     *
     * @param papa     parent object
     * @param position the position of the particle
     * @param weight   the weight of the particle
     * @param color    the color of the particle
     */
    public Particle(PApplet papa, PVector position, float weight, int color) {
        canvas = papa;
        // if we give the position in the constructor it gets called by reference
        // this was also done before but we havent noticed it yet because all other elements where rather static.
        this.position = position.copy();

        float orientation = canvas.random(0, PConstants.TWO_PI);
        velocity = new PVector(20 * PApplet.sin(orientation), 20 * PApplet.cos(orientation));

        self = canvas.createShape();
        self.beginShape();

        self.stroke(color);
        self.strokeWeight(weight);

        self.vertex(0, 0);
        self.vertex(1, 0);

        self.endShape();
    }

    /**
     * Displays the shape onto the stored static canvas object.
     */
    public void display() {
        // Locating and drawing the shape
        canvas.pushMatrix();
        canvas.translate(position.x, position.y);
        canvas.shape(self);
        canvas.popMatrix();
    }

    /**
     * update a particles position according to its velocity
     */
    public void update() {
        // Location changes by velocity
        position.add(velocity);
    }

    /**
     * kill particle if it is outside of the visible area
     */
    public boolean checkEdges() {
        if (position.y >= canvas.height && velocity.y > 0) {
            return true;
        } else if (position.y <= 0 && velocity.y < 0) {
            return true;
        } else if (position.x >= canvas.width && velocity.x > 0) {
            return true;
        } else if (position.x <= 0 && velocity.x < 0) {
            return true;
        }
        return false;
    }
}
