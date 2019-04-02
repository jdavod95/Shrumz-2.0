package game.soil;

import render2d.Color;
import render2d.shape.RectIsom;

public class Dirt extends Soil{
	
	private static final Color f0 = new Color(160, 128, 64);
	private static final Color f1 = new Color(160, 96, 32);
	private static final Color f2 = new Color(96, 64, 32);
	private static final Color f3 = new Color(64, 32, 0);
								
	public Dirt(int x, int y, int w) {
		super(1, 3 ,new RectIsom(x,y,w,Color.BLACK));
		setTreshold(new int[]{
			5,5,5,5
		});
	}

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

}
