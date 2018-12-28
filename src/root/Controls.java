package root;



import org.lwjgl.input.Keyboard;

import render2d.Camera;

public class Controls {
	
	public static void navigate(){
		
		if(Keyboard.isKeyDown(Keyboard.KEY_LEFT))
			Camera.set(Camera.getCX()-20,Camera.getCY());
		else if(Keyboard.isKeyDown(Keyboard.KEY_RIGHT))
			Camera.set(Camera.getCX()+20,Camera.getCY());
		
		if(Keyboard.isKeyDown(Keyboard.KEY_DOWN))
			Camera.set(Camera.getCX(),Camera.getCY()+20);
		else if(Keyboard.isKeyDown(Keyboard.KEY_UP))
			Camera.set(Camera.getCX(),Camera.getCY()-20);
		
		if (Keyboard.isKeyDown(Keyboard.KEY_R))
			Camera.set(0,0);
		
				
	}

}
