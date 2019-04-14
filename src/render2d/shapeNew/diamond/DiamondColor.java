package render2d.shapeNew.diamond;

import static org.lwjgl.opengl.GL11.glColor4d;

import render2d.Color;
import render2d.shapeNew.Colorable;
import render2d.shapeNew.Point;

public class DiamondColor extends Diamond implements Colorable{

	Color color;
	
	protected DiamondColor(Point pos, int w, int h, Color color) {
		super(pos, w, h);
		this.color = color;
	}

	@Override
	public Color getColor() {
		return color;
	}

	@Override
	public void setColor(Color color) {
		this.color = color;
	}

	@Override
	protected void GlColor(){
		glColor4d(color.getRVal(),
				color.getGVal(),
				color.getBVal(),
				color.getOpacity());
	}
}
