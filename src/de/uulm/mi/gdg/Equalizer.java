package de.uulm.mi.gdg;

import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import ddf.minim.analysis.FFT;
import de.uulm.mi.gdg.objects.Circle;
import processing.core.PApplet;
import processing.core.PVector;

import java.util.ArrayList;

/**
 * The Equalizer class is the main controller for the Animation.
 *
 * @author Tobias Lahmann
 */
public class Equalizer extends PApplet {
    // A list of circles to hold the elements to display.
    private ArrayList<Circle> circles;
    private AudioPlayer player;
    private FFT fft;

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
        circles = new ArrayList<>();

        // Creates random circles to see what an animation could look like
        for (int i = 0; i < 10; i++) {
            PVector position = new PVector(this.width / 2, this.height / 2);
            float radius = random(10, this.width);
            float weight = random(10, 50);
            float alpha = random(50, 60);
            int color = color(0, 128, 128, alpha);
            circles.add(new Circle(this, position, radius, weight, color));
        }

        Minim minim = new Minim(this);
        player = minim.loadFile("./data/Kontinuum - First Rain.mp3");
        fft = new FFT(player.bufferSize(), player.sampleRate());

        // Start the song right away
        player.play();
    }

    /**
     * Draw method is responsible for displaying all objects.
     */
    public void draw() {
        background(0);

        fft.forward(player.mix);
        // Add a jitter variable from fft of the song to the objects
        float jitter = fft.getBand(0);
        // Display every circle available
        for (Circle c : circles) {
            c.update(jitter);
            c.display();
        }
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
