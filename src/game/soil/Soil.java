package game.soil;

import java.util.HashSet;
import java.util.Set;

import render2d.Color;

public abstract class Soil {

	private final int MAXFRT;
	private final Set<SoilEffect> effects = new HashSet<>();
	
	private double
		water,
		fertility,
		waterFactor,
		fertilityFactor
		;
	
	public Soil(int MAXFRT, double fertilityFactor, double waterFactor) {
		this.MAXFRT = MAXFRT;
		fertility = 0;
		water = 0;
		this.waterFactor = waterFactor;
		this.fertilityFactor = fertilityFactor;
	}

	void incWater(){
		water += waterFactor;
	}
	
	void decWater(){
		water -= waterFactor;
	}
	
	void incFertility(){
		if(fertility < MAXFRT)
			fertility += fertilityFactor;
	}
	
	void decFertility(){
		fertility -= fertilityFactor;
	}
	
	public final void cycle(boolean hasPlant){
	//	if(!hasPlant)
	//		incFertility();
		applyEffects();
		removeEffects();
		innerCycle();
	}
	
	public double getWater() {
		return water;
	}

	public double getFertility() {
		return fertility;
	}

	public void useWater(double amount){
		water -= amount;
	}
	
	public void useFertility(double amount){
		fertility -= amount;
	}
	
	private void applyEffects(){
		for(SoilEffect se : effects){
			se.action(this);
		}
	}
	
	private void removeEffects(){
		effects.clear();
	}

	public void addEffect(SoilEffect se){
		effects.add(se);
	}

	public abstract Color getColor();
	
	public abstract Soil getNew();
	protected abstract void innerCycle();
}
