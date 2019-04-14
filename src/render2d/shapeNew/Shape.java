package render2d.shapeNew;

import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.GL_TRIANGLES;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glBindTexture;
import static org.lwjgl.opengl.GL11.glColor4d;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glVertex2i;

public abstract class Shape {
	
	private Point pos;
	private int w, h;
	
	protected Shape(Point pos, int w, int h){
		this.pos = pos;
		this.w = w;
		this.h = h;
	}

	public int getW() {
		return w;
	}

	protected void setW(int w) {
		this.w = w;
	}

	public int getH() {
		return h;
	}

	protected void setH(int h) {
		this.h = h;
	}

	public Point getPos() {
		return pos;
	}

	public void setPos(Point pos) {
		this.pos = pos;
	}

	public Shape getShape(){
		return this;
	}
	
	public final void draw(){
		GlBindTex();
		GlTriangles();
		GlColor();
		drawShape();
		glEnd();
	}
	
	protected void GlBindTex(){
		glBindTexture(GL_TEXTURE_2D, 0);
	}
	
	protected void GlColor(){
		glColor4d(1, 1, 1, 1);
	}

	private void GlTriangles(){
		glBegin(GL_TRIANGLES);
	}

	protected void drawTriangle(Point base, Point vector1, Point vector2){
		glVertex2i(base.getX(),base.getY());
	
		glVertex2i(
				base.getX()+vector1.getX(),
				base.getY()+vector1.getY());
		
		glVertex2i(
				base.getX()+vector2.getX(),
				base.getY()+vector2.getY());		
	}
	
	public abstract void reScale(int w, int h);
	// implement with calling drawTriangle()
	protected abstract void drawShape();

}
