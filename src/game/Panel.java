package game;

import java.util.List;

import render2d.Camera;
import render2d.Color;
import render2d.Render;
import render2d.drawable.Rectangle;
import render2d.drawable.ShapeBuilder;
import render2d.elements.Button;
import render2d.elements.Cursor;
import render2d.elements.Point;
import render2d.write.Label;
import root.App;

public class Panel {

	private static final ShapeBuilder SHAPES = new ShapeBuilder();
	private static final int MARGIN_X = 5;
	private static final int MARGIN_Y = 5;
	
	private static Point camera = Camera.getCameraPos();
	
	private static List<Button> butts;

	private static Rectangle timerin;
	private static Rectangle timerbd;
	
	public static void show() {
		updateCamera();
		updateButts();
		toRender();
	}

	private static void toRender() {
		SHAPES.newRectangle(
				camera.getNew(),
				App.W, 100)
		.setColor(new Color(Color.GRAY, 0.2));
		Render.addUi(SHAPES.getShape(), 0);

		for(Button b : butts)
			Cursor.addClickable(b.getClickable());

		for(Button b : butts)
			b.toRender(1);
		
		Render.addUi(new Label(camera.getNew(43, 32), 20, "Row"), 1);
		Render.addUi(new Label(camera.getNew(90, 32), 20, "Col"), 1);
		Render.addUi(new Label(camera.getNew(125, 35), 17, "Scale"), 1);
		Render.addUi(new Label(camera.getNew(125, 35), 17, "Speed"), 1);

		SHAPES.newRectangle(
			camera.getNew(MARGIN_X + 400, MARGIN_Y),
			30, 90)
		.setColor(Color.WHITE);
		timerbd = (Rectangle) SHAPES.getShape();
		
		SHAPES.newRectangle(
			camera.getNew(MARGIN_X + 405, MARGIN_Y + 85),
			20, 0)
		.setColor(Color.BLACK);
		timerin = (Rectangle) SHAPES.getShape();

		
		Render.addUi(timerbd, 1);
		Render.addUi(timerin, 2);

		updateTimerBar();
	}

	private static void updateTimerBar() {
		int barHeight = (int)(Math.round(
				- (Screen.getTimer() + 1)
				* (80.0 / Screen.getCycleat()))
			);
		timerin.reScale(20, barHeight);
		timerin.setPos(camera.getNew(MARGIN_X + 405, MARGIN_Y + 85));
		timerbd.setPos(camera.getNew(MARGIN_X + 400, MARGIN_Y));
	}

	private static void updateCamera() {
		camera = Camera.getCameraPos();
	}

	private static void updateButts() {
		Point camPos = camera.getNew();
		camPos.add(MARGIN_X, MARGIN_Y);
		for(Button b : butts) {
			b.setPos(camPos.getNew(b.getRelativePosition()));
		}
	}
	
	public static void load(){
		butts = Buttons.getAll();
	}
	
}