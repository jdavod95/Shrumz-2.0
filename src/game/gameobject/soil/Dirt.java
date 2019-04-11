package game.gameobject.soil;

import render2d.Color;

public class Dirt extends Soil{
	
	private static final Color
		F0 = new Color(160, 128, 64),
		F1 = new Color(160, 96, 32),
		F2 = new Color(96, 64, 32),
		F3 = new Color(64, 32, 0);
								
	public Dirt() {
		super(4, 0.2, 0.5);
	}

	@Override
	protected void innerCycle() {
		addEffect(SoilEffect.WET);
		if(getFertility() == -1 && getWater() == -1){
			//set sand
		} else if ( getWater() == 10){
			//set swamp
		}
	}

	@Override
	public Color getColor() {
		if(getFertility() < 1)
			return F0;
		else if(getFertility() < 2)
			return F1;
		else if(getFertility() < 3)
			return F2;
		else
			return F3;
	}

	@Override
	public Soil getNew() {
		return new Dirt();
	}
	
}
