package game.plants;

import org.lwjgl.util.Point;

import game.Map;
import game.Tile;

public class Shrum extends Plant {
// TODO az egészet újra
	public Shrum(int x, int y, int scale) {
		super(x, y, scale, 2, 2, 2);
	}

	@Override
	public void setStage() {
		stage = age;
		getSkin().setFcu(stage+1);
		
	}

	@Override
	public void grow() {
		// TODO ki is kell találni h itt mivan
		
	}

	@Override
	public Plant[] spread(Point[][] n, Tile t) {
		// TODO szépen csináld már meg
		t.setPlant(null);
		Plant[] s = new Plant[4];
		s[0] = new Shrum(
				n[0][1].getX(), 
				n[0][1].getY(), 
				Tile.getScale()
			);
		s[1] = new Shrum(
				n[1][0].getX(), 
				n[1][0].getY(), 
				Tile.getScale()
			);
		s[2] = new Shrum(
				n[1][2].getX(), 
				n[1][2].getY(), 
				Tile.getScale()
			);
		s[3] = new Shrum(
				n[2][1].getX(), 
				n[2][1].getY(), 
				Tile.getScale()
			);
			
		return s;
	}

	@Override
	public boolean cycle(int fert, Tile t) {
		incAge();
		switch(stage){
			case 0:
				if(nCount(2, t) == 2 && nCount(8, t) < 6 
					&& fert > 0){
					setStage();
					t.setFert(fert-1);
				}
				else
					t.setPlant(null);
			break;
			case 1: 
				if(nCount(1, t) == 1 && nCount(8, t) < 4 
					&& fert > 0){
					setStage();
					t.setFert(fert-1);
				}
				else{
					t.setPlant(null);
					t.setFert(fert+1);
				}
			break;
			case 2:
				//setStage();
				return true;
		}
		
		
		return false;
	}

	int nCount(int c, Tile t){

		int count = 0;
		int i = 0;
		while(count < c && i < 3){
			int j = 0;
			while(count < c && j < 3){
				Point p = t.getNeigh()[i][j];
				try{
					if(Map.getTable()[p.getX()][p.getY()].getPlant() != null)
						count++;
				} catch(Exception e){}
				j++;
			}
			i++;
		}
		return count;
	}	
			
	

}
