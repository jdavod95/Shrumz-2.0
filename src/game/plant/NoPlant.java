package game.plant;

import elements.Point;
import game.soil.Soil;

public class NoPlant extends Plant{
	
	public NoPlant() {
		super(1, -1, -1);
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