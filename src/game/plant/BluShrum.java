package game.plant;

import elements.IndexPair;

public class BluShrum extends Plant {

	public BluShrum() {
		super(5, 2, 3);
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
		return new BluShrum();
	}	

}
