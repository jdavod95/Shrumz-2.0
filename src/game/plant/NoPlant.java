package game.plant;

import elements.IndexPair;

public class NoPlant extends Plant{
	
	public NoPlant() {
		super(1, 0, -1);
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