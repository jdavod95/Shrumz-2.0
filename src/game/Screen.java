package game;

import org.lwjgl.input.Mouse;

import game.plant.Plant;
import game.plant.Shrum;
import render2d.Camera;
import render2d.Render;
import render2d.write.Label;
import root.App;

public class Screen {

	static boolean paused = false;
	static Plant brushPlant = new Shrum();
	static String brushSoil = "Dirt";
	
	static int brushFert = 3;
	
	static int timer = 0;
	static int cycleat = 35;
	
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
		
		Render.addUi(new Label(
				Camera.getCX()+Mouse.getX()+50,
				Camera.getCY()+App.H-Mouse.getY()+5,
				16,
				Integer.toString(Camera.getCX()+Mouse.getX()) +"          "+
				Integer.toString(Camera.getCY()+App.H-Mouse.getY())
				), 6);
		
		
		Map.toRender();

	}

	public static void load(){
		Map.load(16,16,32);
		Panel.load();
	}

	public static String getBrushSoil() {
		return brushSoil;
	}

	public static void setBrushSoil(String brushSoil) {
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

	public static int getBrushFert() {
		return brushFert;
	}

	public static void setBrushFert(int brushFert) {
		Screen.brushFert = brushFert;
	}
	
	
	
}
