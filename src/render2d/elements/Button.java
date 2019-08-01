package render2d.elements;

import render2d.Camera;
import render2d.Render;
import render2d.Texturing;
import render2d.drawable.Clickable;
import render2d.drawable.Rectangle;
import render2d.drawable.RectangleClick;
import render2d.drawable.Shape;
import render2d.drawable.ShapeBuilder;
import render2d.write.Label;

public class Button{
	final static Point NUDGE = new Point(1, 1);
	private static final ShapeBuilder SHAPES = new ShapeBuilder();
	private final static String TEXTURE_NAME = "BUTTONCOL";
	private final Point relativePosition;
	
	Shape label;
	Rectangle skin;
	boolean down = false;
	
	public Button(Point pos, int w, int h, String label, Action function) {
		this.relativePosition = pos;
		
		SHAPES.newRectangle(
				Camera.getCameraPos().getNew(pos),
				w, h)
			.setTexture(TEXTURE_NAME)
			.setClickable(RectangleClick.class, new ButtonActions(this, function));
		skin = (Rectangle) SHAPES.getShape();
		
		Integer tex = Texturing.getTexIdFor(label);
		if(tex == 0)
			this.label = new Label(pos, w, label);
		else {
			SHAPES.newRectangle(pos, w, h)
				.setTexture(label);
			this.label = SHAPES.getShape();
		}
	}
	
	public void toRender(int l){
		Render.addUi(skin, l);
		Render.addUi(label, l);
	} 

	public Clickable getClickable() {
		return skin.getClickable();
	}

	public Point getPos() {
		return skin.getPos().getNew();
	}

	public void setPos(Point pos) {
		skin.setPos(pos);
		if(!down)
			label.setPos(pos);
		else
			label.setPos(pos.getNew(NUDGE));
	}
	
	public Point getRelativePosition() {
		return relativePosition;
	}
	
}
