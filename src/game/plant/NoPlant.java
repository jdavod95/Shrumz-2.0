package game.plant;

import game.soil.Soil;
import render2d.elements.Point;

public class NoPlant extends Plant{
	
	public NoPlant() {
		super("no", -1, -1);
	}

	@Override
	public void grow(Soil s) {
	}
 
	@Override
	public Point[] spread() {
		return new Point[0];
	}

	@Override
	public Plant getNew() {
		return new NoPlant();
	}
	
}