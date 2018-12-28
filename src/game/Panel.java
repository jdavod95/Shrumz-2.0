package game;

import elements.Button;
import elements.Cursor;
import render2d.Camera;
import render2d.Font;
import render2d.Render;

public class Panel {
	
	static final int mx = 5;
	static final int my = 5;
	static final int layer = 5;
	
	static int cx = Camera.getCX()+mx;
	static int cy = Camera.getCY()+my;
	
	static Button hidepanel = new Button(cx, cy, 25, 25, "",
			ButtonEvents.HIDEPANEL, true);

	static Button[] butts = new Button[8];
			
			
			
	public static void show() {
		cx = Camera.getCX()+mx;
		cy = Camera.getCY()+my;

		Cursor.addClck(hidepanel);
		Cursor.addClck(butts);
		
		Render.addShape(hidepanel, layer);
		if(butts[0].getVis())
			Render.addShape(butts, layer);
		
		//Font.writeString(cx+50, cy+30, "X", 32, false, 0, 0);
	}

	public static void load(){
		butts[0] =  new Button(cx+50, cy, 25, 25, "",
				ButtonEvents.GROWMAPX, true);
		
		butts[1] =  new Button(cx+50, cy+60, 25, 25, "",
				ButtonEvents.SHRINKMAPX, true);
		
		butts[2] =  new Button(cx+90, cy, 25, 25, "",
				ButtonEvents.GROWMAPY, true);
		
		butts[3] =  new Button(cx+90, cy+60, 25, 25, "",
				ButtonEvents.SHRINKMAPY, true);

		butts[4] =  new Button(cx+130, cy, 25, 25, "",
				ButtonEvents.SCALEUP, true);
		
		butts[5] =  new Button(cx+130, cy+60, 25, 25, "",
				ButtonEvents.SCALEDOWN, true);
		
		butts[6] =  new Button(cx+170, cy, 40, 40, "",
				ButtonEvents.REGENSOIL, true);
		
		butts[7] =  new Button(cx+170, cy+45, 40, 40, "",
				ButtonEvents.HIDESHRUM, true);
	}
}
