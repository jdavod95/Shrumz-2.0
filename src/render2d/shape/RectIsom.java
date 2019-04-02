package render2d.shape;

import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.GL_TRIANGLES;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glBindTexture;
import static org.lwjgl.opengl.GL11.glColor4d;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glVertex2i;

import render2d.Color;

public class RectIsom extends Rect{

	public RectIsom(int x, int y, int w, Color c) {
		super(x, y, w*2, w, c);
	}
	
	public RectIsom(int x, int y, int w, Color c, double op) {
		super(x, y, w*2, w, c, op);
	}
	
	@Override
	public void draw(){

		glBindTexture(GL_TEXTURE_2D,0);
	    glBegin(GL_TRIANGLES);	
		glColor4d(
				col.getR()/COLBITS,
				col.getG()/COLBITS,
				col.getB()/COLBITS,
				op);
		
		glVertex2i(x+w/2,y);
		glVertex2i(x+w,y+h/2);			
		glVertex2i(x,y+h/2);
		
		glVertex2i(x+w/2,y+h);
		glVertex2i(x,y+h/2);
		glVertex2i(x+w,y+h/2);
	
		glEnd();
	}
	@Override
	public void setH(int h) {
		this.h = getW()/2;
	}
	
	@Override
	public void setW(int w) {
		this.w = w*2;
	}
}
