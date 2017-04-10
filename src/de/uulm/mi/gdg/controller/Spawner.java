package de.uulm.mi.gdg.controller;

import de.looksgood.ani.Ani;
import de.uulm.mi.gdg.objects.Entity;
import de.uulm.mi.gdg.objects.Particle;
import processing.core.PApplet;
import processing.core.PVector;

import java.util.ArrayList;

public class Spawner implements Entity {
    private static PApplet canvas;

    private static ArrayList<Particle> particles;
    private static ArrayList<Particle> deadParticles;

    private volatile ArrayList<CustomAnimation> anis;
    private CustomAnimation ani;
    private int particlesToSpawn = 0;

    public Spawner(PApplet _parent) {
        canvas = _parent;

        particles = new ArrayList<Particle>();
        deadParticles = new ArrayList<Particle>();

        startAnimation();
    }

    /**
     * Initializes the animations into the anis-list to get a whole new start even if the song restarts.
     */
    public void startAnimation() {
        anis = AniImporter.importAnimation(canvas, "./data/timing/timing.json", "particlesToSpawn");
    }

    /**
     * If particles are to be created this adds them to the list of livin particles.
     */
    private void spawn() {
        PVector position = new PVector(canvas.width / 2, canvas.height / 2);
        float weight = 5.0f;
        int color = canvas.color(128, 128, 64);

        for (; particlesToSpawn > 0; particlesToSpawn--) {
            particles.add(new Particle(canvas, position, weight, color));
        }
    }

    /**
     * display all living particles
     */
    public void display() {
        for (Particle p : particles) {
            p.display();
        }
    }

    /**
     * check if particles go out of bounds and, if confirmed, kill them... with fire
     */
    public void update() {
        for (Particle p : particles) {
            p.update();
            if (p.checkEdges()) {
                deadParticles.add(p);
            }
        }

        particles.removeAll(deadParticles);
        deadParticles.clear();
    }

    /**
     * Takes a value of the position of the playhead from the equalizer. It is intended to start animations just when
     * the cue-position is greater than the start position of the animation.
     * <p>A possible error could be that if the animation is not gone through linearly all animations that have a
     * smaller start value than the cue get all started simultaneously.</p>
     *
     * @param val the cue position of the song
     */
    public void update(float val) {
        if (anis.size() != 0) {
            if (val / 1000 > anis.get(0).start) {
                ani = anis.remove(0);
            }
        }

        if (ani != null) {
            Ani.to(this, ani.duration, ani.params, ani.value, ani.mode);
            ani = null;
        }

        spawn();

        update();
    }
}
