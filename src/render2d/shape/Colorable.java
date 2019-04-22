package render2d.shape;

import static org.lwjgl.opengl.GL11.glColor4d;

import render2d.Color;

public interface Colorable extends Drawable{
	
	final Color color = new Color(1, 1, 1, 1);
	
	public default Color getColor() {
		return color;
	}

	public default void setColor(Color color) {
		getColor().setR(color.getR());
		getColor().setG(color.getG());
		getColor().setB(color.getB());
		getColor().setOpacity(color.getOpacity());
	}
	
	@Override
	default void GlColor(){
		Color c = getColor();
		glColor4d(c.getRVal(), c.getBVal(), c.getGVal(), c.getOpacity());
	}
}
