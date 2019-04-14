package game;

import java.util.ArrayList;
import java.util.List;

import elements.Button;
import elements.Cursor;
import render2d.Camera;
import render2d.Color;
import render2d.Render;
import render2d.shape.RectIsomClickable;
import render2d.shapeNew.Point;
import render2d.shapeNew.rectangle.RectangleColor;
import render2d.write.Label;
import root.App;

public class Panel {
	
	static final int mx = 5;
	static final int my = 5;
	static final int layer = 5;
	
	static int cx = Camera.getCX()+mx;
	static int cy = Camera.getCY()+my;
/*	static Button hidepanel = new Button(cx, cy, 25, 25, "",
			ButtonEvents.HIDEPANEL, true);*/

	static List<Button> butts = new ArrayList<>();		

	public static void show() {
		RectangleColor timerbd = new RectangleColor(new Point(Camera.getCX()+mx+400,Camera.getCY()+my),30,90,Color.GRAY);
		RectangleColor timerin = new RectangleColor(new Point(Camera.getCX()+mx+405,Camera.getCY()+my+85),20,0,Color.BLACK);

		timerin.reScale(0,(int)(Math.round(-(Screen.getTimer()+1)*(80.0/Screen.getCycleat()))));
		upd();
		Render.addUi(new RectangleColor(new Point(Camera.getCX(),Camera.getCY()), App.W, 100,Color.GRAY), 0);

		for(Button b : butts)
			Cursor.addClck(b);

		for(Button b : butts)
			b.toRender(1);
		
		Render.addUi(new Label(cx+43,cy+32, 20, "Row"), 1);
		Render.addUi(new Label(cx+90,cy+32, 20, "Col"), 1);
		Render.addUi(new Label(cx+125,cy+35, 17, "Scale"), 1);
		Render.addUi(new Label(cx+350,cy+35, 17, "Speed"), 1);

		Render.addUi(timerbd, 1);
		Render.addUi(timerin, 1);
		
	}

	public static void load(){

		butts.add(new Button(new Point(cx+50, cy), 25, 25, "+",
				ButtonEvents.GROWMAPX, true));
		
		butts.add(new Button(new Point(cx+50, cy+60), 25, 25, "-",
				ButtonEvents.SHRINKMAPX, true));
		
		butts.add(new Button(new Point(cx+90, cy), 25, 25, "+",
				ButtonEvents.GROWMAPY, true));
		
		butts.add(new Button(new Point(cx+90, cy+60), 25, 25, "-",
				ButtonEvents.SHRINKMAPY, true));

		butts.add(new Button(new Point(cx+130, cy), 25, 25, "+",
				ButtonEvents.SCALEUP, true));
		
		butts.add(new Button(new Point(cx+130, cy+60), 25, 25, "-",
				ButtonEvents.SCALEDOWN, true));
		
//		butts.add(new Button(cx+170, cy, 40, 40, "Reg",
//				ButtonEvents.REGENSOIL, true));
		
		butts.add(new Button(new Point(cx+App.W-50, cy+45), 40, 40, "Del",
				ButtonEvents.BRPLNULL, true));
		
		butts.add(new Button(new Point(cx+App.W-95, cy+45), 40, 40, 2, 2,
				ButtonEvents.BRPLSHRUM, true));
		
		butts.add(new Button(new Point(cx+App.W-140, cy+45), 40, 40, 5, 2,
				ButtonEvents.BRPLBLUSHRUM, true));
		
		butts.add(new Button(new Point(cx+App.W-185, cy+45), 40, 40, 3, 4,
				ButtonEvents.BRPLWEED, true));
		
		butts.add(new Button(new Point(cx+App.W-50, cy), 40, 40, "Off",
				ButtonEvents.BRSL_NO, true));
		
//		butts.add(new Button(cx+App.W-95, cy, 40, 40, "D0",
//				ButtonEvents.BRSL_0, true));
		
		butts.add(new Button(new Point(cx+App.W-140, cy), 40, 40, "Drt",
				ButtonEvents.BRSL_D, true));
		
		butts.add(new Button(new Point(cx+App.W-185, cy), 40, 40, "Wtr",
				ButtonEvents.BRSL_W, true));
		
		butts.add(new Button(new Point(cx+250, cy), 40, 40, 4, 1,
				ButtonEvents.CTRLPLAY, true));
	
		butts.add(new Button(new Point(cx+295, cy), 40, 40, 4, 0,
				ButtonEvents.CTRLPAUSE, true));
		
//		butts.add(  new Button(cx+290, cy+45, 40, 40, 4, 3,
//				ButtonEvents.CTRLSTOP, true));
		
		butts.add(new Button(new Point(cx+250, cy+45), 40, 40, 4, 2,
				ButtonEvents.CTRLSTEP, true));
		
		butts.add(new Button(new Point(cx+360, cy), 25, 25, "+",
				ButtonEvents.CTRLFRDOWN, true));
		
		butts.add(new Button(new Point(cx+360, cy+60), 25, 25, "-",
				ButtonEvents.CTRLFRUP, true));
		
	}
	
	static void upd(){
		for(Button b : butts){
			b.setPos(
					b.getPos().getX() - cx,
					b.getPos().getY() - cy
					);
		}
		
		cx = Camera.getCX()+mx;
		cy = Camera.getCY()+my;
		for(Button b : butts){
			b.setPos(
					b.getPos().getX() - cx,
					b.getPos().getY() - cy
					);
		}
	}
}
