package game;

import root.Shrumz;

public class Screen {

	public static void show() {
		Panel.show();
		//if(Shrumz.getTicks()%3 == 0)
			Map.cycle();
		
		
		
		Map.toRender();
	}

	public static void load(){
	
		Map.load(64,64,8);
		Panel.load();
	}
}
