package game;

import elements.MyEvent;

public class ButtonEvents {

	public final static MyEvent HIDEPANEL = new MyEvent(){
		@Override
		public void action(){
			if(Panel.butts[0].getVis())
				for(int i = 0;i<Panel.butts.length;i++)
					Panel.butts[i].setVis(false);
			else
				for(int i = 0;i<Panel.butts.length;i++)
					Panel.butts[i].setVis(true);
		}
	};
	
	public final static MyEvent GROWMAPX = new MyEvent(){
		@Override
		public void action(){
			if(Map.getX() < 128)
				Map.set(Map.getX()+8, Map.getY());
				
		}
	};
	public final static MyEvent SHRINKMAPX = new MyEvent(){
		@Override
		public void action(){
			if(Map.getX() > 8)
				Map.set(Map.getX()-8, Map.getY());
				
		}
	};
	
	public final static MyEvent GROWMAPY = new MyEvent(){
		@Override
		public void action(){
			if(Map.getY() < 128)
				Map.set(Map.getX(), Map.getY()+8);
		}
	};
	public final static MyEvent SHRINKMAPY = new MyEvent(){
		@Override
		public void action(){
			if(Map.getY() > 8)
				Map.set(Map.getX(), Map.getY()-8);	
		}
	};
	public final static MyEvent SCALEUP = new MyEvent(){
		@Override
		public void action(){
			if(Tile.getScale() < 64){
				Map.reScale(Tile.getScale()+2);
			}
		}
	};
	public final static MyEvent SCALEDOWN = new MyEvent(){
		@Override
		public void action(){
			if(Tile.getScale() > 2){
				Map.reScale(Tile.getScale()-2);
			}
		}
	};
	public final static MyEvent REGENSOIL = new MyEvent(){
		@Override
		public void action(){
			if(Tile.isRegen())
				Tile.setRegen(false);
			else
				Tile.setRegen(true);
		}
	};
	public final static MyEvent HIDESHRUM = new MyEvent(){
		@Override
		public void action(){
			if(Tile.isHidden())
				Tile.setHidden(false);
			else
				Tile.setHidden(true);
		}
	};

}
