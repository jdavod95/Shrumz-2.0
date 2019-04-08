package render2d.shape;

import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.GL_TRIANGLES;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glBindTexture;
import static org.lwjgl.opengl.GL11.glColor4d;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glVertex2i;

import render2d.Color;

public class RectIsomClickable extends Shape {

	static protected final double COLBITS = 255; 
	protected Color col;
	protected double op;
	
	public RectIsomClickable(int x, int y, int w, int h, Color c) {
		this(x, y, w, h, c, 1.0);
	}
	
	public RectIsomClickable(int x, int y, int w, int h, Color c, double op) {
		super(x, y, w, h);
		this.col = c;
		this.op = op;

	}
	public void setCol(Color c){
		col = c;
	}

	public void draw(){
		
		glBindTexture(GL_TEXTURE_2D,0);
	    glBegin(GL_TRIANGLES);	
		glColor4d(
			col.getR()/COLBITS,
			col.getG()/COLBITS,
			col.getB()/COLBITS,
			op);
				
		glVertex2i(x,y);
		glVertex2i(x+w,y);			
		glVertex2i(x+w,y+h);
		
		glVertex2i(x,y);
		glVertex2i(x,y+h);
		glVertex2i(x+w,y+h);
		
		glEnd();
	}
	
}
