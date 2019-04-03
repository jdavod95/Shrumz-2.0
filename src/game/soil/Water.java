package game.soil;

import org.lwjgl.util.Point;

import render2d.Color;
import render2d.shape.RectIsom;

public class Water extends Soil{
	
	private static final Color WATER = new Color(64,200,240);
			
	Point poz;
	static int range = 3;
	
	public Water(Point poz) {
		super(0,1);
		this.poz = poz;
		setTreshold(new int[]{
				10
		});
	}

	@Override
	public void setFert(int fert) {
		for(int r = 1;r <= range;r++)
			for(int i = -r;i<=r;i++){
				//if(Map.getTable()[poz.getX()+i][poz.getY()+j].getSoil() instanceof Water)
				//	continue;
				for(int j = -r;j<r;j++){
			
				}
			}
	}

}
