package render2d;

import java.util.List;

import render2d.drawable.Drawable;

public class Render {
	
	public static final Renderer BGR = new Renderer();	// background
	public static final Renderer SCN = new Renderer();	// scene
	public static final Renderer UI = new Renderer();	// foreground
	
	public static void drawFrame(){
		BGR.drawFrame();
		SCN.drawFrame();
		UI.drawFrame();
	}
	 
	public static void addBgr(Drawable d, int l){
		BGR.addDrawable(d, l);
	}
	
	public static void addBgr(Drawable[] d, int l){
		BGR.addDrawable(d, l);
	}
	
	public static void addBgr(List<? extends Drawable> d, int l){
		BGR.addDrawable(d, l);
	}
	
	public static void addScn(Drawable d, int l){
		SCN.addDrawable(d, l);
	}
	
	public static void addScn(Drawable[] d, int l){
		SCN.addDrawable(d, l);
	}
	
	public static void addScn(List<? extends Drawable> d, int l){
		SCN.addDrawable(d, l);
	}
	
	public static void addUi(Drawable d, int l){
		UI.addDrawable(d, l);
	}

	public static void addUi(Drawable[] d, int l){
		UI.addDrawable(d, l);
	}
	
	public static void addUi(List<? extends Drawable> d, int l){
		UI.addDrawable(d, l);
	}
}
