package game.plant;

import elements.IndexPair;
import game.soil.Soil;

public class NoPlant extends Plant{
	
	public NoPlant() {
		super(1, -1, -1);
	}

	@Override
	public void grow(Soil s) {
	}

	@Override
	public IndexPair[] spread() {
		return new IndexPair[0];
	}

	@Override
	public Plant getNew() {
		return new NoPlant();
	}
	
}