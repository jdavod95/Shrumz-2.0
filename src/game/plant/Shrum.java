package game.plant;

import elements.IndexPair;

public class Shrum extends Plant {
	
	public Shrum() {
		super(2, 2, 3);
	}
	
	@Override
	public IndexPair[] spread() {
		return new IndexPair[]{
			new IndexPair(1, 0),
			new IndexPair(0, 1),
			new IndexPair(-1, 0),
			new IndexPair(0, -1)
		};
	}

	@Override
	public void grow() {
		
	}

	@Override
	public Plant getNew() {
		return new Shrum();
	}
}
