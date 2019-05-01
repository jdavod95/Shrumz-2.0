package game.plant;

import elements.Point;
import game.soil.Soil;

public class BluShrum extends Plant {

	public BluShrum() {
		super(5, 3, 3);
	}

	@Override
	public Point[] spread() {
		return new Point[]{
				new Point(2, 0),
				new Point(-2, 0),
				new Point(0, -2),
				new Point(0, 2),

				new Point(-1, -1),
				new Point(1, -1),
				new Point(-1, 1),
				new Point(1, 1)
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
