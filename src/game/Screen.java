package game;

import root.Shrumz;

public class Screen {

	static String brushPlant = "";
	
	public static void show() {
		Panel.show();
		
		if(Shrumz.getTicks() % 5 == 0)
			Map.cycle();
		
		
		
		Map.toRender();
	}

	public static void load(){
		Map.load(16,16,32);
		Panel.load();
	}

	public static String getBrushPlant() {
		return brushPlant;
	}

	public static void setBrushPlant(String brushPlant) {
		Screen.brushPlant = brushPlant;
	}
	
	
}
