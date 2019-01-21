package game;

import java.util.ArrayList;
import java.util.List;

import elements.Button;
import elements.Cursor;
import render2d.write.Label;
import render2d.Camera;

import render2d.Render;
import render2d.shape.Rect;
import root.Shrumz;

public class Panel {
	
	static final int mx = 5;
	static final int my = 5;
	static final int layer = 5;
	
	static int cx = Camera.getCX()+mx;
	static int cy = Camera.getCY()+my;
	static Button hidepanel = new Button(cx, cy, 25, 25, "",
			ButtonEvents.HIDEPANEL, true);

	//static Button[] butts = new Button[11];
	static List<Button> butts = new ArrayList<>();		
	
	public static void show() {

			
		updButt();
		Render.addShape(new Rect(Camera.getCX(),Camera.getCY(), Shrumz.W, 100,128,128,128,0.5), 4);
		
//		Cursor.addClck(hidepanel);
		for(Button b : butts)
			Cursor.addClck(b);
		
	//	hidepanel.toRender(layer);
		if(butts.get(0).getVis())
			for(Button b : butts)
				b.toRender(layer);
		
		Render.addShape(new Label(cx+43,cy+32, 20, "Row"), 5);
		Render.addShape(new Label(cx+90,cy+32, 20, "Col"), 5);
		Render.addShape(new Label(cx+125,cy+35, 17, "Scale"), 5);
		Render.addShape(new Label(cx+350,cy+35, 17, "Speed"), 5);

		
	}

	public static void load(){

		butts.add(new Button(cx+50, cy, 25, 25, "+",
				ButtonEvents.GROWMAPX, true));
		
		butts.add(new Button(cx+50, cy+60, 25, 25, "-",
				ButtonEvents.SHRINKMAPX, true));
		
		butts.add(new Button(cx+90, cy, 25, 25, "+",
				ButtonEvents.GROWMAPY, true));
		
		butts.add(new Button(cx+90, cy+60, 25, 25, "-",
				ButtonEvents.SHRINKMAPY, true));

		butts.add(new Button(cx+130, cy, 25, 25, "+",
				ButtonEvents.SCALEUP, true));
		
		butts.add(new Button(cx+130, cy+60, 25, 25, "-",
				ButtonEvents.SCALEDOWN, true));
		
		butts.add(new Button(cx+170, cy, 40, 40, "Reg",
				ButtonEvents.REGENSOIL, true));
		
		butts.add(new Button(cx+170, cy+45, 40, 40, "Hide",
				ButtonEvents.HIDESHRUM, true));
		
		butts.add(new Button(cx+Shrumz.W-50, cy+45, 40, 40, "Del",
				ButtonEvents.BRPLNULL, true));
		
		butts.add(new Button(cx+Shrumz.W-95, cy+45, 40, 40, 2, 2,
				ButtonEvents.BRPLSHRUM, true));
		
		butts.add(new Button(cx+Shrumz.W-140, cy+45, 40, 40, 3, 4,
				ButtonEvents.BRPLWEED, true));
		
		butts.add(new Button(cx+Shrumz.W-50, cy, 40, 40, "Off",
				ButtonEvents.BRSL_NO, true));
		
		butts.add(new Button(cx+Shrumz.W-95, cy, 40, 40, "F0",
				ButtonEvents.BRSL_0, true));
		
		butts.add(new Button(cx+Shrumz.W-140, cy, 40, 40, "F1",
				ButtonEvents.BRSL_1, true));
		
		butts.add(new Button(cx+Shrumz.W-185, cy, 40, 40, "F2",
				ButtonEvents.BRSL_2, true));
		
		butts.add(new Button(cx+Shrumz.W-230, cy, 40, 40, "F3",
				ButtonEvents.BRSL_3, true));
		
		butts.add(new Button(cx+250, cy, 40, 40, 4, 1,
				ButtonEvents.CTRLPLAY, true));
	
		butts.add(new Button(cx+295, cy, 40, 40, 4, 0,
				ButtonEvents.CTRLPAUSE, true));
		
//		butts.add(  new Button(cx+290, cy+45, 40, 40, 4, 3,
//				ButtonEvents.CTRLSTOP, true));
		
		butts.add(new Button(cx+250, cy+45, 40, 40, 4, 2,
				ButtonEvents.CTRLSTEP, true));
		
		butts.add(new Button(cx+360, cy, 25, 25, "+",
				ButtonEvents.CTRLFRDOWN, true));
		
		butts.add(new Button(cx+360, cy+60, 25, 25, "-",
				ButtonEvents.CTRLFRUP, true));
		
	}
	
	static void updButt(){
		for(Button b : butts){
			b.setX(b.getX() - cx);
			b.setY(b.getY() - cy);
		}
		cx = Camera.getCX()+mx;
		cy = Camera.getCY()+my;
		for(Button b : butts){
			b.setX(b.getX() + cx);
			b.setY(b.getY() + cy);
		}
	}
}
