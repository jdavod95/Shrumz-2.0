package game.plant;


import elements.IndexPair;

public class Weed extends Plant {


	public Weed(){
		super(3, 4, 6);
	}

	@Override
	public void grow() {
	}

	@Override
	public IndexPair[] spread() {
		
		int x = (int)Math.floor(Math.random()*3-1);
		int y = (int)Math.floor(Math.random()*3-1);
		while( x == 0 && y == 0 ){
			x = (int)Math.floor(Math.random()*3-1);
			y = (int)Math.floor(Math.random()*3-1);
		}
		return new IndexPair[]{
			new IndexPair(x, y)
			};
	}

	@Override
	public Plant getNew() {
		return new Weed();
	}


}
