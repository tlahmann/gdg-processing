package de.uulm.mi.gdg.objects;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PShape;
import processing.core.PVector;

/**
 * @author Tobias Lahmann
 */
public class Spectrum implements Entity {
    private static PApplet canvas;
    private int bands;
    private PShape shape;
    private PVector position;
    private float radius;

    /**
     * Constructor for the spectrum arc. Takes the parent (canvas) to draw on. Also gets a position and orientation to
     * determine the look of the arc
     *
     * @param papa        The PApplet to draw on
     * @param bands       Number of bands to display
     * @param position    The position of the arc
     * @param radius      Radius of the arc
     * @param orientation PVector containing two components: the rotation and the orientation of the arc
     */
    public Spectrum(PApplet papa, PVector position, float radius, float weight, int color, int bands, PVector orientation) {
        canvas = papa;
        this.bands = bands;
        this.position = position;
        this.radius = radius;

        // Shapes bring the advantage of being easily modifiable and adjustable
        shape = canvas.createShape();
        shape.beginShape();

        shape.stroke(color);
        shape.strokeWeight(weight);
        shape.noFill();

        shape.rotate(orientation.x);
        shape.scale(orientation.y, 1);

        float step = PConstants.PI / bands;
        for (float a = 0; a < PConstants.PI; a += step) {
            shape.vertex(PApplet.cos(a) * radius, PApplet.sin(a) * radius);
        }

        shape.endShape();
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

    public void update() {

    }

    /**
     * Recalculates the position of the vertex representing its frequency band
     *
     * @param band  Audio frequency band to apply the value to
     * @param value The value to apply to the frequency band
     */
    public void update(int band, float value) {
        PVector vertex = shape.getVertex(band);
        vertex.x = PApplet.cos((band * (PConstants.PI / bands))) * (radius + value);
        vertex.y = PApplet.sin((band * (PConstants.PI / bands))) * (radius + value);
        shape.setVertex(band, vertex);
    }
}
