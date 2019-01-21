package game;

import org.lwjgl.input.Mouse;

import render2d.Camera;
import render2d.Render;
import render2d.write.Label;
import root.Shrumz;

public class Screen {

	static int perframe = 35;
	static boolean paused = false;
	static String brushPlant = "Shrum";
	static int brushFert = 3;

	public static void show() {
		Panel.show();
		
		if(!paused && Shrumz.getTicks() % perframe == 0)
			Map.cycle();

		Render.addShape(new Label(
				Camera.getCX()+Mouse.getX()+50,
				Camera.getCY()+Shrumz.H-Mouse.getY()+5,
				16,
				Integer.toString(Camera.getCX()+Mouse.getX()) +"          "+
				Integer.toString(Camera.getCY()+Shrumz.H-Mouse.getY())
				), 6);
		
		
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

	public static boolean isPaused() {
		return paused;
	}

	public static void setPaused(boolean paused) {
		Screen.paused = paused;
	}

	public static int getPerframe() {
		return perframe;
	}

	public static void setPerframe(int perframe) {
		if(perframe > 60)
			perframe = 60;
		else if(perframe < 1)
			perframe =  1;
		Screen.perframe = perframe;
	}

	public static int getBrushFert() {
		return brushFert;
	}

	public static void setBrushFert(int brushFert) {
		Screen.brushFert = brushFert;
	}
	
	
	
}
