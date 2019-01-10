package game;

import elements.MyEvent;

public class ButtonEvents {

	public final static MyEvent HIDEPANEL = new MyEvent(){
		@Override
		public void action(){
			if(Panel.butts.get(0).getVis())
				for(int i = 0;i<Panel.butts.size();i++)
					Panel.butts.get(i).setVis(false);
			else
				for(int i = 0;i<Panel.butts.size();i++)
					Panel.butts.get(i).setVis(true);
		}
	};
	
	public final static MyEvent GROWMAPX = new MyEvent(){
		@Override
		public void action(){
			if(Map.getX() < 128)
				Map.setTable(Map.getX()+8, Map.getY());	
		}
	};
	public final static MyEvent SHRINKMAPX = new MyEvent(){
		@Override
		public void action(){
			if(Map.getX() > 8)
				Map.setTable(Map.getX()-8, Map.getY());
		}
	};
	public final static MyEvent GROWMAPY = new MyEvent(){
		@Override
		public void action(){
			if(Map.getY() < 128)
				Map.setTable(Map.getX(), Map.getY()+8);
		}
	};
	public final static MyEvent SHRINKMAPY = new MyEvent(){
		@Override
		public void action(){
			if(Map.getY() > 8)
				Map.setTable(Map.getX(), Map.getY()-8);	
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
	public final static MyEvent BRPLSHRUM = new MyEvent(){
		@Override
		public void action(){
			Screen.setBrushPlant("Shrum");
		}
	};
	public final static MyEvent BRPLWEED = new MyEvent(){
		@Override
		public void action(){
			Screen.setBrushPlant("Weed");
		}
	};
	public final static MyEvent BRPLNULL = new MyEvent(){
		@Override
		public void action(){
			Screen.setBrushPlant("");
		}
	};
	
	public final static MyEvent BRSL_NO = new MyEvent(){
		@Override
		public void action(){
			Screen.setBrushFert(-1);
		}
	};
	
	public final static MyEvent BRSL_0 = new MyEvent(){
		@Override
		public void action(){
			Screen.setBrushFert(0);
		}
	};
	
	public final static MyEvent BRSL_1 = new MyEvent(){
		@Override
		public void action(){
			Screen.setBrushFert(1);
		}
	};
	
	public final static MyEvent BRSL_2 = new MyEvent(){
		@Override
		public void action(){
			Screen.setBrushFert(2);
		}
	};
	
	public final static MyEvent BRSL_3 = new MyEvent(){
		@Override
		public void action(){
			Screen.setBrushFert(3);
		}
	};
	
	public final static MyEvent CTRLPLAY = new MyEvent(){
		@Override
		public void action(){
			if(Screen.isPaused())
				Screen.setPaused(false);			
		}
	};
	
	public final static MyEvent CTRLPAUSE = new MyEvent(){
		@Override
		public void action(){
			if(!Screen.isPaused())
				Screen.setPaused(true);			
		}
	};
	
	public final static MyEvent CTRLSTOP = new MyEvent(){
		@Override
		public void action(){
			Screen.load();
		}
	};
	

	public final static MyEvent CTRLSTEP = new MyEvent(){
		@Override
		public void action(){
			Screen.setPaused(true);
			Map.cycle();		
		}
	};
	
	public final static MyEvent CTRLFRUP = new MyEvent(){
		@Override
		public void action(){
			if(Screen.getPerframe() > 20)
				Screen.setPerframe(Screen.getPerframe()+5);	
			else
				Screen.setPerframe(Screen.getPerframe()+1);
		}
	};
	
	public final static MyEvent CTRLFRDOWN = new MyEvent(){
		@Override
		public void action(){
			if(Screen.getPerframe() > 20)
				Screen.setPerframe(Screen.getPerframe()-5);	
			else
				Screen.setPerframe(Screen.getPerframe()-1);
		}
	};
}
