package elements;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.input.Mouse;

import render2d.Camera;
import render2d.shape.Clickable;
import root.App;

public class Cursor {

	static Clickable pressed = null;
	static boolean down = false;
	public static List<Clickable> clicks = new ArrayList<>();
	
	public static void addClck(Clickable c){
		clicks.add(c);		
	}
	
	public static void addClck(Clickable[] c){
		for(Clickable o : c)
			clicks.add(o);		
	}
	
	public static void check() {
		Point p = new Point(Camera.getCX()+Mouse.getX(), 
				Camera.getCY()+App.H-Mouse.getY());
    	for(Clickable c : clicks){
    		if(c.contains(p)){
    			
    			c.hover();
    			if(Mouse.isButtonDown(0)){
    				c.click();
	    			if(pressed == null)
	    				pressed = c;
	    			else if(pressed != c){
	    				pressed.release();
	    				pressed = c;
	    			}
    			}
	    	 	if(!Mouse.isButtonDown(0) && pressed != null){
	    	 		pressed.release();
	    	 		pressed = null;
	    	 	}
	    	}
	    }
	 	clicks.clear();
	}

}