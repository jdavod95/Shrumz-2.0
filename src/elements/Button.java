package elements;

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

	private static final ShapeBuilder SHAPES = new ShapeBuilder();
	private final static String TEXTURE_NAME = "BUTTONCOL";
	private final static Point NUDGE = new Point(1, 1);
	private final Point relativePosition;
	
	Shape label;
	Rectangle skin;
	Action function;
	boolean down = false;
	
	Action click = new Action(){
		@Override
		public void run() {
			skin.setCurrentFrame(1);
			label.getPos().add(NUDGE);
			function.run();
			down = true;
		}
	};
	
	Action release = new Action(){
		@Override
		public void run() {
			skin.setCurrentFrame(0);
			label.getPos().subtract(NUDGE);
			down = false;
		}
	};
	
	public Button(Point pos, int w, int h, String label, Action function) {
		//possible bug
		this.function = function;
		this.relativePosition = pos;
		
		SHAPES.newRectangle(
				Camera.getCameraPos().getNew(pos),
				w, h);
		SHAPES.setTexture(TEXTURE_NAME);
		SHAPES.setClickable(RectangleClick.class, click, release, Action.EMPTY);
		skin = (Rectangle) SHAPES.getShape();
		
		Integer tex = Texturing.getTexIdFor(label);
		if(tex == 0)
			this.label = new Label(pos, w, label);
		else {
			SHAPES.newRectangle(pos, w, h);
			SHAPES.setTexture(label);
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
