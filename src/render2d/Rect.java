package render2d;

import static org.lwjgl.opengl.GL11.glColor4d;
import static org.lwjgl.opengl.GL11.glVertex2i;

public class Rect extends Shape {

	static protected final double COLBITS = 255; 
	protected int r,g,b;
	
	public Rect(int x, int y, int w, int h, int r, int g, int b) {
		super(x, y, w, h);
		this.r = r;
		this.g = g;
		this.b = b;
	}
	public void setCol(int r, int g, int b){
		this.r = r;
		this.g = g;
		this.b = b;
	}

	public void draw(){
		
		glColor4d(r/COLBITS,g/COLBITS,b/COLBITS,1.0);
				
		glVertex2i(x,y);
		glVertex2i(x+w,y);			
		glVertex2i(x+w,y+h);
		
		glVertex2i(x,y);
		glVertex2i(x,y+h);
		glVertex2i(x+w,y+h);
	}
	
}
