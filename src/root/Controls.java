package root;



import org.lwjgl.input.Keyboard;

import game.ButtonEvents;
import render2d.Camera;

public class Controls {
	
	public static void navigate(){
		
		if(Keyboard.isKeyDown(Keyboard.KEY_LEFT))
			Camera.set(Camera.getCX()-15,Camera.getCY());
		else if(Keyboard.isKeyDown(Keyboard.KEY_RIGHT))
			Camera.set(Camera.getCX()+15,Camera.getCY());
		
		if(Keyboard.isKeyDown(Keyboard.KEY_DOWN))
			Camera.set(Camera.getCX(),Camera.getCY()+15);
		else if(Keyboard.isKeyDown(Keyboard.KEY_UP))
			Camera.set(Camera.getCX(),Camera.getCY()-15);
		
		if (Keyboard.isKeyDown(Keyboard.KEY_R))
			Camera.set(0,0);
		
		if(Keyboard.isKeyDown(Keyboard.KEY_SUBTRACT))
			ButtonEvents.SCALEDOWN.action();
		else if(Keyboard.isKeyDown(Keyboard.KEY_ADD))
			ButtonEvents.SCALEUP.action();
		
	}

}
