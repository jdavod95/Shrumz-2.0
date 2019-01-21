package game.plant;

import org.lwjgl.util.Point;

import game.Tile;

public class Weed extends Plant {


	public Weed(int x, int y){
		super(x, y, 3, 4, 6);
	}

	@Override
	public void grow(Tile t) {
		switch(stage){
		case 6: break;
		case 5: 
			spreading = false;
			
			if(getAge() % 10 == 0){
				if(getAge() <= 90){
					incStage(false);
				}
				else
					incStage(true);
			}
			break;
		
		default:
			spreading = true;
			if(t.getSoil().getFert() > 0 && getAge() % 10 == 0){
				t.getSoil().incTire();
				incStage(true);
			}
			else if(t.getSoil().getFert() < 1){
				t.setPlant(null);
				if(stage > 0)
					for(int i = 0;i<stage;i++)
					t.getSoil().incFert(true);
			}
		break;	
		}
	}

	@Override
	public Plant[] spread(Point[][] n) {
		
		int x = (int)Math.floor(Math.random()*3);
		int y = (int)Math.floor(Math.random()*3);
		while( x == 1 && y == 1 ){
			x = (int)Math.floor(Math.random()*3);
			y = (int)Math.floor(Math.random()*3);
		}
		return new Plant[]{
				new Weed(
					n[x][y].getX(),
					n[x][y].getY()
					)
			};
	}


}