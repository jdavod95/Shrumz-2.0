package render2d.shapeNew.rectangle;

import static org.lwjgl.opengl.GL11.glColor4d;

import render2d.Color;
import render2d.shapeNew.Colorable;
import render2d.shapeNew.Point;

public class RectangleColor extends Rectangle implements Colorable{

	Color color;
	
	public RectangleColor(Point pos, int w, int h, Color c) {
		super(pos, w, h);
		color = c;
	}
	
	@Override
	protected void GlColor(){
		glColor4d(color.getRVal(),
				color.getGVal(),
				color.getBVal(),
				color.getOpacity());
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
	
}
