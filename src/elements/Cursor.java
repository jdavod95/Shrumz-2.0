package elements;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.input.Mouse;
import org.lwjgl.util.Point;

import render2d.Camera;
import root.Shrumz;

public class Cursor {

	static Clickable pressed = null;
	static boolean down = false;
	public static List<Clickable> clicks = new ArrayList<>();
	
	public static void addClck(Clickable c){
		if(c.getVis())
			clicks.add(c);		
	}
	
	public static void addClck(Clickable[] c){
		for(Clickable o : c)
			if(o.getVis())
				clicks.add(o);		
	}
	
	public static void check() {
		Point p = new Point(Camera.getCX()+Mouse.getX(), 
				Camera.getCY()+Shrumz.H-Mouse.getY());
    	for(Clickable c : clicks){
    		if(c.contains(p)){
    			
    			c.hover();
    			if(Mouse.isButtonDown(0)){
    				c.action();
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