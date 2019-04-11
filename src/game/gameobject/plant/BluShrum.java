package game.gameobject.plant;

import elements.IndexPair;
import game.gameobject.soil.Soil;

public class BluShrum extends Plant {

	public BluShrum() {
		super(5, 3, 3);
	}

	@Override
	public IndexPair[] spread() {
		return new IndexPair[]{
				new IndexPair(2, 0),
				new IndexPair(-2, 0),
				new IndexPair(0, -2),
				new IndexPair(0, 2),

				new IndexPair(-1, -1),
				new IndexPair(1, -1),
				new IndexPair(-1, 1),
				new IndexPair(1, 1)
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
		return new BluShrum();
	}	

}
