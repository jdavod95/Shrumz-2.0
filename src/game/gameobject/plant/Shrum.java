package game.gameobject.plant;

import elements.IndexPair;
import game.gameobject.soil.Soil;

public class Shrum extends Plant {
	
	public Shrum() {
		super(2, 3, 3);
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
	public void grow(Soil s) {
		if(inSpreadStage())
			return;
		if(s.getFertility() >= 1.0 && s.getWater() >= 1.0){
			incStage();
			s.useFertility(1.0);
			s.useWater(1.0);
		}
		else
			die();
	}

	@Override
	public Plant getNew() {
		return new Shrum();
	}
}
