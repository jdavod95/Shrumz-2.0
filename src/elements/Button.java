package elements;

import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.GL_TRIANGLES;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glBindTexture;
import static org.lwjgl.opengl.GL11.glColor4d;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glVertex2i;

import render2d.Color;
import render2d.Render;
import render2d.shape.Shape;

import render2d.shapeNew.Clickable;
import render2d.shapeNew.Point;
import render2d.shapeNew.rectangle.RectangleColor;
import render2d.shapeNew.rectangle.RectangleTextured;
import render2d.write.Label;

public class Button extends RectangleColor implements Clickable {

	static int COLBITS = 255;
	static Color col = new Color(224,224,224);
	int cDif = 16;
	int bd = 5;
	
	RectangleTextured lpic = null;
	Label label = null;
	
	boolean down = false;
	boolean type = true; //toggle true|| hold false 
	
	MyEvent e = new MyEvent(){
				@Override
				public void action() {
					System.out.println("No event specified");
				}
		};
	
	public Button(Point pos, int w, int h, String label, MyEvent e, boolean toggle) {
		super(pos, w, h, col);
		this.label = new Label(pos.getX()+bd, pos.getY()+bd, h-2*bd, label);
		this.type = toggle;
		if(e != null)
			this.e = e;
	}
	
	public Button(Point pos, int w, int h, int fid, int fcu, MyEvent e, boolean toggle) {
		super(pos, w, h, col);
		this.lpic = new RectangleTextured(new Point(pos.getX()+bd, pos.getY()+bd), w-2*bd, h-2*bd, fid ,fcu);
		this.type = toggle;
		if(e != null)
			this.e = e;
	}
	
	public void setPushType(boolean type){
		this.type = type;
	}
	
	public void setX(int x){
		getPos().setX(x);
		if(label != null)
			label.setX(x+bd);
		else if(lpic != null)
			lpic.getPos().setX(x+bd);
	}
	
	public void setY(int y){
		getPos().setY(y);
		if(label != null)
			label.setY(y+bd);
		else if(lpic != null)
			lpic.getPos().setY(y+bd);
	}
	@Override
	public void drawShape() {	
		glColor4d(
				(col.getR() + cDif)/COLBITS,
				(col.getG() + cDif)/COLBITS,
				(col.getB() + cDif)/COLBITS,
				1.0);
		
		drawTriangle(
				getPos(),
				new Point(getW(),0),
				new Point(getH(),getW()));
		
		glColor4d(
				(col.getR() - cDif)/COLBITS,
				(col.getG() - cDif)/COLBITS,
				(col.getB() - cDif)/COLBITS,
				1.0);
		
		drawTriangle(
				getPos(),
				new Point(0,getH()),
				new Point(getH(),getW()));
		
		int h = getH();
		int w = getW();
		int y = getPos().getY();
			
		if(down){
			h -= 1;
			w -= 1;
			y += 1;
			setColor(new Color(216,216,216));
		}
		
		glColor4d(
				col.getR()/COLBITS,
				col.getG()/COLBITS,
				col.getB()/COLBITS,
				1.0);
		
		drawTriangle(
				new Point(getPos().getX() + bd, y + bd), 
				new Point(w - bd,bd),
				new Point(w - bd,h - bd)
				);

		drawTriangle(
				new Point(getPos().getX() + bd, y + bd), 
				new Point(bd,h - bd),
				new Point(w - bd,h - bd)
				);

		glEnd();
	}
	
	public void toRender(int l){
		Render.addUi(this, l);
		if(label != null)
			Render.addUi(label, l);
		else if(lpic != null)
			Render.addUi(lpic, l);
	}
	
	void labelNudge(){
		int x = 1;
		if(!down)
			x *= -1;
		if(label != null){
			label.setH(label.getH()-x);
			label.setW(label.getW()-x);
			label.setY(label.getY()+x);
		}
		if(lpic != null){
			lpic.reScale(
					lpic.getW()-x,
					lpic.getH()-x);
			lpic.getPos().setY(lpic.getPos().getY()+x);
		}
			
	}
	
	// Clickable
	
	@Override
	public void release(){
		down = false;
		labelNudge();
	}
	
	@Override
	public void onClick() {		
		if(type){
			if(!down){
				down = true;
				e.action();
				labelNudge();
			}
		} 
		else{
			if(!down)
				labelNudge();
			down = true;
			e.action();
		}
	}
	
	@Override
	public boolean contains(Point m){
		if(
			m.getX() > getPos().getX() &&
			m.getX() < getPos().getX() + getW())
			if(
				m.getY() > getPos().getY() && 
				m.getY() < getPos().getY() + getH())
				return true;
		return false;
	}
	@Override
	public void hover() {}

}
