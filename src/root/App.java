package root;
import static org.lwjgl.opengl.GL13.GL_MULTISAMPLE;
import static org.lwjgl.opengl.GL11.GL_BLEND;
import static org.lwjgl.opengl.GL11.GL_LIGHTING;
import static org.lwjgl.opengl.GL11.GL_ONE_MINUS_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glBindTexture;
import static org.lwjgl.opengl.GL11.glBlendFunc;
import static org.lwjgl.opengl.GL11.glDisable;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glEnd;

import java.io.File;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import elements.Cursor;
import game.Screen;
import render2d.Camera;
import render2d.Color;
import render2d.Render;
import render2d.TextureLoad;
import render2d.shape.Rect;
import render2d.write.Label;

public class App {
	
	public final static int W = 1024;
	public final static int H = 768;
	static int ticks = 0;
	static int sec = 0;
  	
	public static void main(String[] args){
		
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
        glBindTexture(GL_TEXTURE_2D,0);
        
        glEnable(GL_BLEND); 
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
        
        glDisable(GL_LIGHTING);
          
		while (!Display.isCloseRequested()) {
			Render.addBgr(new Rect(Camera.getCX(),Camera.getCY(),W,H,Color.WHITE),0);
		    Screen.show();
		    
		    Cursor.check();
		    
		    Camera.create();
		    Controls.navigate();

		    Render.drawFrame();	

		    glEnd();
    		
    		ticks ++;
    		if(ticks == 60){
    			ticks = 0;
    			sec++;
    		}

		   // System.out.println(ticks);
		}
 
        Display.destroy();
        System.exit(0);
        
	}
	
	public static void preLoad(){ 
		TextureLoad.loadAll();
		Label.loadSymbols();
		Screen.load();
		
	}
	
	public static int getTicks(){
		return ticks;
	}

}
