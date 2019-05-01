package game;

import java.util.ArrayList;
import java.util.List;

import elements.Button;
import elements.Cursor;
import elements.Point;
import render2d.Camera;
import render2d.Color;
import render2d.Render;
import render2d.shape.ShapeFactory;
import render2d.shape.rectangle.Rectangle;
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
		Rectangle timerbd = ShapeFactory.createRectCol(
				new Point(Camera.getCX()+mx+400,Camera.getCY()+my),
				30,90,
				Color.WHITE);
		
		Rectangle timerin = ShapeFactory.createRectCol(
				new Point(Camera.getCX()+mx+405,Camera.getCY()+my+85),
				20,0,
				Color.BLACK);

		timerin.reScale(0,(int)(Math.round(-(Screen.getTimer()+1)*(80.0/Screen.getCycleat()))));
		Render.addUi(ShapeFactory.createRectCol(new Point(Camera.getCX(),Camera.getCY()), App.W, 100, new Color(Color.GRAY, 0.2)),0);

		for(Button b : butts)
			Cursor.addClck(b.getClickable());

		for(Button b : butts)
			b.toRender(1);
		
		Render.addUi(new Label(new Point(cx+43,cy+32), 20, "Row"), 1);
		Render.addUi(new Label(new Point(cx+90,cy+32), 20, "Col"), 1);
		Render.addUi(new Label(new Point(cx+125,cy+35), 17, "Scale"), 1);
		Render.addUi(new Label(new Point(cx+350,cy+35), 17, "Speed"), 1);

		Render.addUi(timerbd, 1);
		Render.addUi(timerin, 1);
		
	}

	public static void load(){

		butts.add(new Button(new Point(cx+50, cy), 25, 25, "+",
				ButtonEvents.GROWMAPX));
		
		butts.add(new Button(new Point(cx+50, cy+60), 25, 25, "-",
				ButtonEvents.SHRINKMAPX));
		
		butts.add(new Button(new Point(cx+90, cy), 25, 25, "+",
				ButtonEvents.GROWMAPY));
		
		butts.add(new Button(new Point(cx+90, cy+60), 25, 25, "-",
				ButtonEvents.SHRINKMAPY));

		butts.add(new Button(new Point(cx+130, cy), 25, 25, "+",
				ButtonEvents.SCALEUP));
		
		butts.add(new Button(new Point(cx+130, cy+60), 25, 25, "-",
				ButtonEvents.SCALEDOWN));
		
//		butts.add(new Button(cx+170, cy, 40, 40, "Reg",
//				ButtonEvents.REGENSOIL, true));
		
		butts.add(new Button(new Point(cx+App.W-50, cy+45), 40, 40, "Del",
				ButtonEvents.BRPLNULL));
		
		butts.add(new Button(new Point(cx+App.W-95, cy+45), 40, 40, "txid2",
				ButtonEvents.BRPLSHRUM));
		
		butts.add(new Button(new Point(cx+App.W-140, cy+45), 40, 40, "txid5",
				ButtonEvents.BRPLBLUSHRUM));
		
		butts.add(new Button(new Point(cx+App.W-185, cy+45), 40, 40, "txid3",
				ButtonEvents.BRPLWEED));
		
		butts.add(new Button(new Point(cx+App.W-50, cy), 40, 40, "Off",
				ButtonEvents.BRSL_NO));
		
//		butts.add(new Button(cx+App.W-95, cy, 40, 40, "D0",
//				ButtonEvents.BRSL_0, true));
		
		butts.add(new Button(new Point(cx+App.W-140, cy), 40, 40, "Drt",
				ButtonEvents.BRSL_D));
		
		butts.add(new Button(new Point(cx+App.W-185, cy), 40, 40, "Wtr",
				ButtonEvents.BRSL_W));
		
		butts.add(new Button(new Point(cx+250, cy), 40, 40, "txid4",
				ButtonEvents.CTRLPLAY));
	
		butts.add(new Button(new Point(cx+295, cy), 40, 40, "txid4", 
				ButtonEvents.CTRLPAUSE));
		
//		butts.add(  new Button(cx+290, cy+45, 40, 40, 4, 3,
//				ButtonEvents.CTRLSTOP, true));
		
		butts.add(new Button(new Point(cx+250, cy+45), 40, 40, "txid4",
				ButtonEvents.CTRLSTEP));
		
		butts.add(new Button(new Point(cx+360, cy), 25, 25, "+",
				ButtonEvents.CTRLFRDOWN));
		
		butts.add(new Button(new Point(cx+360, cy+60), 25, 25, "-",
				ButtonEvents.CTRLFRUP));
		
	}
	
}