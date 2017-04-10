package de.uulm.mi.gdg;

import ddf.minim.analysis.FFT;
import de.looksgood.ani.Ani;
import de.uulm.mi.gdg.controller.Blinker;
import de.uulm.mi.gdg.controller.Player;
import de.uulm.mi.gdg.controller.Spawner;
import de.uulm.mi.gdg.objects.Circle;
import de.uulm.mi.gdg.objects.Spectrum;
import processing.core.PApplet;
import processing.core.PConstants;
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
    private ArrayList<Spectrum> spectra;
    private Blinker blinker;
    public static Spawner particleSystem;

    private static Player player;
    private static FFT fft;

    private float background = 0;

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

        PVector position = new PVector(this.width / 2, this.height / 2);
        // Creates random circles to see what an animation could look like
        for (int i = 0; i < 10; i++) {
            float radius = random(10, this.width);
            float weight = random(10, 50);
            float alpha = random(50, 60);
            int color = color(0, 128, 128, alpha);
            circles.add(new Circle(this, position, radius, weight, color));
        }

        player = new Player(this, "./data/Kontinuum - First Rain.mp3");
        fft = player.getFFT();

        spectra = new ArrayList<>();
        for (int i = 1; i < 7; i++) {
            float radius = i * 50;
            float weight = 2;
            int color = color(0, 128, 64, 100);
            int side = i % 2 == 0 ? 1 : -1;
            PVector orientation = new PVector(side * PConstants.HALF_PI, side);
            spectra.add(new Spectrum(this, position, radius, weight, color, fft.specSize(), orientation));
        }

        // Block of Elements that rely on animation.
        Ani.init(this);
        blinker = new Blinker(this);

        particleSystem = new Spawner(this);
    }

    /**
     * Draw method is responsible for displaying all objects.
     */
    public void draw() {
        background(background);

        fft.forward(player.getSong().mix);
        // Add a jitter variable from fft of the player to the objects
        float jitter = fft.getBand(1);
        // Display every circle available
        for (Circle c : circles) {
            c.update(jitter);
            c.display();
        }

        // update every spectral arc
        float val;
        for (int i = 0; i < fft.specSize(); i++) {
            // update the left spectral arcs
            val = player.getSong().left.get(i);
            for (int j = 1; j < spectra.size(); j += 2) {
                spectra.get(j).update(i, val * 40);
            }

            // update the right spectral arcs
            val = player.getSong().right.get(i);
            for (int j = 0; j < spectra.size(); j += 2) {
                spectra.get(j).update(i, val * 40);
            }
        }

        // display every spectrum available
        for (Spectrum s : spectra) {
            s.display();
        }

        blinker.update(player.getSong().position());
        blinker.display();

        particleSystem.update(player.getSong().position());
        particleSystem.display();
    }

    public void keyPressed() {
        if (key == 'P' || key == 'p') {
            player.TogglePlaying();
            blinker.startAnimation();
            particleSystem.startAnimation();
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
