package elements;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.input.Mouse;

import render2d.Camera;
import render2d.drawable.Clickable;
import root.App;

public class Cursor {

	private static Clickable pressed = null;
	public static List<Clickable> clicks = new ArrayList<>();
	
	public static void addClickable(Clickable c){
		clicks.add(c);		
	}
	
	public static void addClickable(Clickable[] c){
		for(Clickable o : c)
			clicks.add(o);		
	}
	
	public static void check() {
		Point p = new Point(
				Camera.getCameraPos().getX() + Mouse.getX(), 
				Camera.getCameraPos().getY() + (App.H - Mouse.getY())
			);
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