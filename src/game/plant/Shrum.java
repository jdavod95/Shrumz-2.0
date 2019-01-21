package game.plant;

import org.lwjgl.util.Point;

import game.Tile;

public class Shrum extends Plant {
	static final double RNDFACT = 0.0;
	
	public Shrum(int x, int y) {
		super(x, y, 2, 2, 3);
	}
	
	@Override
	public Plant[] spread(Point[][] n) {
		Plant[] s = new Plant[4];
		if(Math.random() > RNDFACT){
			s[0] = new Shrum(
					n[0][1].getX(), 
					n[0][1].getY()
				);
			s[1] = new Shrum(
					n[1][0].getX(), 
					n[1][0].getY()
				);
			s[2] = new Shrum(
					n[1][2].getX(), 
					n[1][2].getY()
				);
			s[3] = new Shrum(
					n[2][1].getX(), 
					n[2][1].getY()
			);
		}
		else {
			s[0] = new Shrum(
					n[0][0].getX(), 
					n[0][0].getY()
				);
			s[1] = new Shrum(
					n[0][2].getX(), 
					n[0][2].getY()
				);
			s[2] = new Shrum(
					n[2][0].getX(), 
					n[2][0].getY()
				);
			s[3] = new Shrum(
					n[2][2].getX(), 
					n[2][2].getY()
			);
		}
		return s;
	}

	@Override
	public void grow(Tile t) {
		switch(stage){
		case 0:
			if(nCount(2, t) == 2 && nCount(8, t) < 6 
				&& t.getSoil().getFert() > 0){
				incStage(true);
				t.getSoil().incTire();
			}
			else
				t.setPlant(null);
			break;
		case 1: 
			if(nCount(1, t) == 1 && nCount(8, t) < 4 
				&& t.getSoil().getFert() > 0){
				incStage(true);
				t.getSoil().incTire();
			}
			else{
				t.setPlant(null);
				t.getSoil().incFert(true);
			}
			break;
		case 2:
			incStage(true);
		}
	}	
			
}
