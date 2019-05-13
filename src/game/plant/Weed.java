package game.plant;


import elements.Point;
import game.soil.Soil;

public class Weed extends Plant {

	private static int MAXSPRD = 2;
	private int spreadCount = 0;
	
	public Weed(){
		super("WEED", 4, 6);
	}

	@Override
	public void grow(Soil s) {
		
		if(s.getFertility() >= 0.5 && s.getWater() >= 0.5){
			if(stage == 5 && spreadCount < MAXSPRD)
				stage = 4;
			else
				incStage();
			s.useFertility(0.5);
			s.useWater(0.5);
		}
		else
			die();
	}

	@Override
	public Point[] spread() {
		
		int x = (int)Math.floor(Math.random()*3-1);
		int y = (int)Math.floor(Math.random()*3-1);
		while( x == 0 && y == 0 ){
			x = (int)Math.floor(Math.random()*3-1);
			y = (int)Math.floor(Math.random()*3-1);
		}
		return new Point[]{
			new Point(x, y)
		};
	}

	@Override
	public Plant getNew() {
		return new Weed();
	}

}
