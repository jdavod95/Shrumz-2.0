package render2d.shapeNew;

import static org.lwjgl.opengl.GL11.glColor4d;

import render2d.Color;

public interface Colorable extends Drawable{

	public Color getColor();
	public void setColor(Color color);
	
	@Override
	default void GlColor(){
		Color c = getColor();
		glColor4d(c.getRVal(), c.getBVal(), c.getGVal(), c.getOpacity());
	}
}
