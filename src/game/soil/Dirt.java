package game.soil;

import game.Tile;
import render2d.shape.RectIsom;

public class Dirt extends Soil{
	
	/* fert col codes
	 * 160 128 64
	 * 160 96 32
	 * 96 64 32
	 * 64 32 0 
	 * */
	
	public Dirt(int x, int y, int w) {
		super(1, 3 ,new RectIsom(x,y,w,0,0,0));
		setTreshold(new int[]{
			1,1,1,1
		});
	}

	@Override
	public void setFert(int fert) {
		switch(fert){
			case 0: ((RectIsom) skin).setCol(160,128,64); break;
			case 1: ((RectIsom) skin).setCol(160,96,32); break;
			case 2: ((RectIsom) skin).setCol(96,64,32); break;
			case 3: ((RectIsom) skin).setCol(64,32,0); break;
		}
		this.fert = fert;
	}

}
