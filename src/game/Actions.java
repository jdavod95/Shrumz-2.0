package game;

import game.plant.BluShrum;
import game.plant.Shrum;
import game.plant.Weed;
import game.soil.Dirt;
import game.soil.Water;
import render2d.elements.Action;

public class Actions {

	public final static Action GROWMAPX = new Action(){
		@Override
		public void run(){
			if(Map.getX() < 128)
				Map.setTable(Map.getX()+8, Map.getY());	
		}
	};
	public final static Action SHRINKMAPX = new Action(){
		@Override
		public void run(){
			if(Map.getX() > 8)
				Map.setTable(Map.getX()-8, Map.getY());
		}
	};
	public final static Action GROWMAPY = new Action(){
		@Override
		public void run(){
			if(Map.getY() < 128)
				Map.setTable(Map.getX(), Map.getY()+8);
		}
	};
	public final static Action SHRINKMAPY = new Action(){
		@Override
		public void run(){
			if(Map.getY() > 8)
				Map.setTable(Map.getX(), Map.getY()-8);	
		}
	};
	public final static Action SCALEUP = new Action(){
		@Override
		public void run(){
			if(Tile.getScale() < 64){
				Map.reScale(Tile.getScale()+2);
			}
		}
	};
	public final static Action SCALEDOWN = new Action(){
		@Override
		public void run(){
			if(Tile.getScale() > 2){
				Map.reScale(Tile.getScale()-2);
			}
		}
	};

	public final static Action BRPLSHRUM = new Action(){
		@Override
		public void run(){
			Screen.setBrushPlant(new Shrum());
		}
	};
	public final static Action BRPLBLUSHRUM = new Action(){
		@Override
		public void run(){
			Screen.setBrushPlant(new BluShrum());
		}
	};
	public final static Action BRPLWEED = new Action(){
		@Override
		public void run(){
			Screen.setBrushPlant(new Weed());
		}
	};
	public final static Action BRPLNULL = new Action(){
		@Override
		public void run(){
			Screen.setBrushPlant(null);
		}
	};
	
	public final static Action BRSL_NO = new Action(){
		@Override
		public void run(){
			Screen.brushSoil = null;
		}
	};
	
	public final static Action BRSL_0 = new Action(){
		@Override
		public void run(){
			Screen.brushSoil = new Dirt();
		}
	};
	
	public final static Action BRSL_D = new Action(){
		@Override
		public void run(){
			Screen.brushSoil = new Dirt();
		}
	};
	
	public final static Action BRSL_W = new Action(){
		@Override
		public void run(){
			Screen.brushSoil = new Water();
		}
	};
	
	public final static Action CTRLPLAY = new Action(){
		@Override
		public void run(){
			if(Screen.isPaused())
				Screen.setPaused(false);			
		}
	};
	
	public final static Action CTRLPAUSE = new Action(){
		@Override
		public void run(){
			if(!Screen.isPaused())
				Screen.setPaused(true);			
		}
	};
	
	public final static Action CTRLSTOP = new Action(){
		@Override
		public void run(){
			Screen.load();
		}
	};

	public final static Action CTRLSTEP = new Action(){
		@Override
		public void run(){
			Screen.setPaused(true);
			Map.cycle();		
		}
	};
	
	public final static Action CTRLFRUP = new Action(){
		@Override
		public void run(){
			if(Screen.getCycleat() > 20)
				Screen.setCycleat(Screen.getCycleat()+5);	
			else
				Screen.setCycleat(Screen.getCycleat()+1);
		}
	};
	
	public final static Action CTRLFRDOWN = new Action(){
		@Override
		public void run(){
			if(Screen.getCycleat() > 20)
				Screen.setCycleat(Screen.getCycleat()-5);	
			else
				Screen.setCycleat(Screen.getCycleat()-1);
		}
	};
}
