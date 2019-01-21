package render2d.shape;

import static org.lwjgl.opengl.GL11.glColor4d;
import static org.lwjgl.opengl.GL11.glVertex2i;

public class RectIsom extends Rect{

	public RectIsom(int x, int y, int w, int r, int g, int b) {
		super(x, y, w*2, w, r, g, b);
		// TODO Auto-generated constructor stub
	}
	public RectIsom(int x, int y, int w, int r, int g, int b, double op) {
		super(x, y, w*2, w, r, g, b, op);
		// TODO Auto-generated constructor stub
	}
	@Override
	public void draw(){
		
		glColor4d(r/COLBITS,g/COLBITS,b/COLBITS,op);
		
		glVertex2i(x+w/2,y);
		glVertex2i(x+w,y+h/2);			
		glVertex2i(x,y+h/2);
		
		
		glVertex2i(x+w/2,y+h);
		glVertex2i(x,y+h/2);
		glVertex2i(x+w,y+h/2);
	
		
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
