package render2d.shape;

import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glBindTexture;
import static org.lwjgl.opengl.GL11.glVertex2i;

import elements.Point;
import render2d.Texturing;

public interface Textureable extends Drawable{

	String getTexName();
	void setTexName(String texName);
	int getCurrentFrame();
	void setCurrentFrame(int frame);
	
	@Override
	default void drawTriangle(Point base, Point vector1, Point vector2){
		Texturing.setTexture(0, 0, getTexName(), getCurrentFrame());
		glVertex2i(
				base.getX(),
				base.getY());
		
		Texturing.setTexture(
				vector1.getX() == 0 ? 0 : 1,
				vector1.getY() == 0 ? 0 : 1,
				getTexName(), getCurrentFrame());
		glVertex2i(
				base.getX()+vector1.getX(),
				base.getY()+vector1.getY());
		
		Texturing.setTexture(1, 1, getTexName(), getCurrentFrame());
		glVertex2i(
				base.getX()+vector2.getX(),
				base.getY()+vector2.getY());	
	}
	
	@Override
	default void GlBindTex(){
		glBindTexture(GL_TEXTURE_2D, Texturing.getTexIdFor(getTexName()));
	}
	
	
	
}
