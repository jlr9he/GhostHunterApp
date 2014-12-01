package com.example.ghosthunter;

import android.util.Log;

public class Explosion {

	private static final String TAG = MainGamePanel.class.getSimpleName();
	
	public static final int STATE_ALIVE 	= 0;	// at least 1 particle is alive
	public static final int STATE_DEAD 		= 1;	// all particles are dead

	private Particle[] particles;			// particles in the explosion
	private int x, y;						// the explosion's origin
	private int size;						// number of particles
	private int state;						// whether it's still active or not

	public Explosion(int particleNr, int x, int y) {
		Log.d(TAG, "Explosion created at " + x + "," + y);
		this.state = STATE_ALIVE;
		this.particles = new Particle[particleNr];
	 	for (int i = 0; i < this.particles.length; i++) {
			Particle p = new Particle(x, y);
			this.particles[i] = p;
		}
	 	this.size = particleNr;
	}
	
}