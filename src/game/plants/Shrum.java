package game.plants;

import org.lwjgl.util.Point;

import game.Tile;

public class Shrum extends Plant {
// TODO az egészet újra
	public Shrum(int x, int y, int scale) {
		super(x, y, scale, 1, 2, 2);
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
	public Tile[] spread(Point[][] n) {
		// TODO szépen csináld már meg
		Tile[] s = new Tile[4];
		
		if(n[0][1] != null)
			s[0] = new Tile(
					n[0][1].getX(), 
					n[0][1].getY(), 
					new Shrum(
							n[0][1].getX(), 
							n[0][1].getY(), 
							Tile.getScale()
							)
					);
		if(n[1][0] != null)
			s[1] = new Tile(
					n[1][0].getX(), 
					n[1][0].getY(), 
					new Shrum(
							n[1][0].getX(), 
							n[1][0].getY(), 
							Tile.getScale()
							)
					);
		if(n[1][2] != null)
			s[2] = new Tile(
					n[1][2].getX(), 
					n[1][2].getY(), 
					new Shrum(
							n[1][2].getX(), 
							n[1][2].getY(), 
							Tile.getScale()
							)
					);
		if(n[2][1] != null)
			s[3] = new Tile(
					n[2][1].getX(), 
					n[2][1].getY(), 
					new Shrum(
							n[2][1].getX(), 
							n[2][1].getY(), 
							Tile.getScale()
							)
					);
		
		return s;
	}

	@Override
	public boolean cycle(int fert, Tile t) {
		incAge();
		setStage();
		switch(stage){
			case 0: 
				if(nCount(2, t) != 2 || fert == 0)
					t.setPlant(null);
				else
					t.setFert(fert-1);
				break;
			case 1: 
				if(nCount(1, t) != 1 || fert == 0){
					t.setPlant(null);
					t.setFert(fert+1);
				}
				else
					t.setFert(fert-1);
				break;
			case 2:
				t.setPlant(null);
			break;
		}
		
		
		return stage == SPRSTAGE;
	}

	int nCount(int c, Tile t){

		int count = 0;
		while(count < c)
			for(int i = 0;i < 3;i++)
				for(int j = 0;j < 3;j++)
					if(t.getNeigh()[i][j] != null)
						count++;
		return count;
	}	
			
	

}
