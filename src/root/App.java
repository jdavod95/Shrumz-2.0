package root;
import static org.lwjgl.opengl.GL13.GL_MULTISAMPLE;
import static org.lwjgl.opengl.GL11.GL_BLEND;
import static org.lwjgl.opengl.GL11.GL_LIGHTING;
import static org.lwjgl.opengl.GL11.GL_ONE_MINUS_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glBlendFunc;
import static org.lwjgl.opengl.GL11.glDisable;
import static org.lwjgl.opengl.GL11.glEnable;

import java.io.File;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import elements.Cursor;
import elements.Point;
import game.Screen;
import render2d.Camera;
import render2d.Color;
import render2d.Render;
import render2d.Texturing;
import render2d.shape.ShapeFactory;
import render2d.shape.rectangle.Rectangle;
import render2d.write.Label;

public class App {
	
	
	public final static int W = 1024;
	public final static int H = 768;
	static int ticks = 0;
	static int sec = 0;
	
	private static final Rectangle BGR = 
			ShapeFactory.createRectCol(
					new Point(Camera.getCX(),Camera.getCY()),
					W,H,
					Color.WHITE);

	public static void main(String[] args){
		initDisplay();
		while (!Display.isCloseRequested()) {
			BGR.setPos(new Point(Camera.getCX(),Camera.getCY()));
			Render.addBgr(BGR,0);
			
		    Controls.navigate();
		    Cursor.check();
		    Screen.show();
		    
		    Camera.create();
		    Render.drawFrame();	
		    tick();
		}
 
        Display.destroy();
        System.exit(0);
        
	}

	public static void preLoad(){ 
		Texturing.loadAll();
		Label.loadSymbols();
		Screen.load();
	}
	
	public static int getTicks(){
		return ticks;
	}
	
	private static void tick() {
		ticks ++;
		if(ticks == 60){
			ticks = 0;
			sec++;
		}
	}

	private static void initDisplay() {
		System.setProperty(
				"org.lwjgl.librarypath", 
				new File("lib/natives").getAbsolutePath()
				);
			
		try{
    	  	Display.setDisplayMode(new DisplayMode(W, H));
            Display.setTitle("Shrumz");
            Display.create();
            
		} catch (Exception e) {
            System.err.println("Display wasn't initialized correctly.");
            System.exit(1);
        }
		
		preLoad();
		
		glEnable(GL_MULTISAMPLE);  
        glEnable(GL_TEXTURE_2D);
        glEnable(GL_BLEND); 
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
        glDisable(GL_LIGHTING);

	}
}
