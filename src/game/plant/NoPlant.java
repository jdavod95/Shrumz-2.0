package game.plant;

import elements.IndexPair;

public class NoPlant extends Plant{
	
	public NoPlant() {
		super(0, 0, 0);
	}

	@Override
	public void grow() {
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