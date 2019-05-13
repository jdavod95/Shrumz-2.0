package game;

import game.plant.Plant;
import game.plant.Shrum;
import game.soil.Soil;

public class Screen {

	static boolean paused = false;
	static Plant brushPlant = new Shrum();
	static Soil brushSoil = null;

	static int timer = 0;
	static int cycleat = 16;
	
	public static void show() {
		Panel.show();
		
		if(!paused){
			timer++;
			if(!paused && timer >= cycleat){
				Map.cycle();
				timer = 0;
			}
		} else
			timer = 0;
		
		Map.toRender();
		Map.toClick();
	}

	public static void load(){
		Map.load(16,16,32);
		Panel.load();
	}

	public static Soil getBrushSoil() {
		return brushSoil;
	}

	public static void setBrushSoil(Soil brushSoil) {
		Screen.brushSoil = brushSoil;
	}

	public static Plant getBrushPlant() {
		return brushPlant;
	}

	public static void setBrushPlant(Plant brushPlant) {
		Screen.brushPlant = brushPlant;
	}

	public static boolean isPaused() {
		return paused;
	}

	public static void setPaused(boolean paused) {
		Screen.paused = paused;
	}

	public static int getCycleat() {
		return cycleat;
	}

	public static void setCycleat(int perframe) {
		if(perframe > 120)
			perframe = 120;
		else if(perframe < 1)
			perframe = 1;
		Screen.cycleat = perframe;
	}
	
	public static int getTimer() {
		return timer;
	}

	public static void setTimer(int timer) {
		Screen.timer = timer;
	}

}
