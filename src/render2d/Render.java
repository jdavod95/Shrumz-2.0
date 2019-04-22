package render2d;

import render2d.shape.Shape;

public class Render {
	
	public static final Renderer BGR = new Renderer();	// background
	public static final Renderer SCN = new Renderer();	// scene
	public static final Renderer UI = new Renderer();	// foreground
	
	public static void drawFrame(){
		BGR.drawFrame();
		SCN.drawFrame();
		UI.drawFrame();
	}
	 
	public static void addBgr(Shape s, int l){
		BGR.addShape(s, l);
	}
	
	public static void addScn(Shape s, int l){
		SCN.addShape(s, l);
	}
	
	public static void addUi(Shape s, int l){
		UI.addShape(s, l);
	}
	
}
