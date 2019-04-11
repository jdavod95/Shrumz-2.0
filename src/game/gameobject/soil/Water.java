package game.gameobject.soil;

import render2d.Color;

public class Water extends Soil{

	public static final Color COLOR = new Color(64,200,240);
			
	public Water() {
		super(0, 0, 0);
	}

	@Override
	protected void innerCycle() {
	}

	@Override
	public Color getColor() {
		return COLOR;
	}

	@Override
	public Soil getNew() {
		return new Water();
	}

}
