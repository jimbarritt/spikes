package com.jimbarritt.spikes.stringtemplate.solarsystem;

import java.util.ArrayList;
import java.util.List;

public class TheSolarSystem {
	private List<Planet> planets;
	private String description;

	public TheSolarSystem() {
		planets = new ArrayList<Planet>();
		planets.add(new Planet("Mercury"));
		planets.add(new Planet("Venus"));
		planets.add(new Planet("Earth"));
		planets.add(new Planet("Mars"));
		planets.add(new Planet("Jupiter"));
		planets.add(new Planet("Saturn"));
		planets.add(new Planet("Uranus"));
		planets.add(new Planet("Neptune"));
		planets.add(new Planet("Pluto"));

	}

	public List<Planet> getPlanets() {
		return planets;
	}

	public int getNumberOfPlanets() {
		return planets.size();
	}

	public  void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return this.description;
	}
}
