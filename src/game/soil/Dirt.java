package game.soil;

import render2d.Color;

public class Dirt extends Soil{
	
	public static final Color f0 = new Color(160, 128, 64);
	public static final Color f1 = new Color(160, 96, 32);
	public static final Color f2 = new Color(96, 64, 32);
	public static final Color f3 = new Color(64, 32, 0);
								
	public Dirt() {
		super(2, 3);
		setTreshold(new int[]{
			5,5,5,5
		});
	}
/*
	@Override
	public void setFert(int fert) {
		switch(fert){
			case 0: ((RectIsom) skin).setCol(f0); break;
			case 1: ((RectIsom) skin).setCol(f1); break;
			case 2: ((RectIsom) skin).setCol(f2); break;
			case 3: ((RectIsom) skin).setCol(f3); break;
		}
		this.fert = fert;
	}
*/

	@Override
	public void setFert(int fert) {
		
	}
}
