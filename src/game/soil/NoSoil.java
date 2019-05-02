package game.soil;

import render2d.Color;

public class NoSoil extends Soil{

	public static final Color COLOR = new Color(1,1,1);
	
	public NoSoil() {
		super(0, 0 ,0);
	}

	@Override
	protected void innerCycle() {
	}

	@Override
	public Color pickColor() {
		return COLOR;
	}

	@Override
	public Soil getNew() {
		return new NoSoil();
	}

}
