package render2d.shape;

import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.GL_TRIANGLES;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glBindTexture;
import static org.lwjgl.opengl.GL11.glColor4d;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glVertex2i;

public interface Drawable {
	
	 public default void draw(){
		GlBindTex();
		GlTriangles();
		GlColor();
		drawShape();
		glEnd();
	}
	
	default void GlBindTex(){
		glBindTexture(GL_TEXTURE_2D, 0);
	}
	
	default void GlColor(){
		glColor4d(1, 1, 1, 1);
	}

	default void GlTriangles(){
		glBegin(GL_TRIANGLES);
	}

	default void drawTriangle(Point base, Point vector1, Point vector2){
		glVertex2i(base.getX(),base.getY());
	
		glVertex2i(
				base.getX()+vector1.getX(),
				base.getY()+vector1.getY());
		
		glVertex2i(
				base.getX()+vector2.getX(),
				base.getY()+vector2.getY());		
	}
		
	void drawShape();
}
