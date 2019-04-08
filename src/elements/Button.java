package elements;

import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.GL_TRIANGLES;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glBindTexture;
import static org.lwjgl.opengl.GL11.glColor4d;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glVertex2i;

import org.lwjgl.util.Point;

import elements.clickable.Clickable;
import render2d.Color;
import render2d.Render;
import render2d.shape.RectIsomClickable;
import render2d.shape.RectTex;
import render2d.shape.Shape;
import render2d.write.Label;

public class Button extends RectIsomClickable implements Clickable {

	static Color col = new Color(224,224,224);
	int cDif = 16;
	int bd = 5;
	
	RectTex lpic = null;
	Label label = null;
	
	boolean visible;
	boolean down = false;
	boolean type = true; //toggle true|| hold false 
	
	MyEvent e = new MyEvent(){
				@Override
				public void action() {
					System.out.println("No event specified");
				}
		};
	
	public Button(int x, int y, int w, int h, String label, MyEvent e, boolean toggle) {
		super(x,y,w,h,col);
		this.label = new Label(x+bd, y+bd, h-2*bd, label);
		this.type = toggle;
		visible = true;
		if(e != null)
			this.e = e;
	}
	
	public Button(int x, int y, int w, int h, int fid, int fcu, MyEvent e, boolean toggle) {
		super(x,y,w,h,col);
		this.lpic = new RectTex(x+bd ,y+bd, w-2*bd, h-2*bd, fid ,fcu);
		this.type = toggle;
		visible = true;
		if(e != null)
			this.e = e;
	}
	
	public void setVis(boolean b){
		visible = b;
	}
	
	public void setType(boolean type){
		this.type = type;
	}
	
	public void setBd(int bd){
		this.bd = bd;
	}
	@Override
	public void setX(int x){
		this.x = x;
		if(label != null)
			label.setX(x+bd);
		else if(lpic != null)
			lpic.setX(x+bd);
	}
	@Override
	public void setY(int y){
		this.y = y;
		if(label != null)
			label.setY(y+bd);
		else if(lpic != null)
			lpic.setY(y+bd);
	}
	@Override
	public void draw() {	
		glBindTexture(GL_TEXTURE_2D,0);
		glBegin(GL_TRIANGLES);
		
		glColor4d((col.getR()+cDif)/COLBITS,(col.getG()+cDif)/COLBITS,(col.getB()+cDif)/COLBITS,1.0);
		
		glVertex2i(x,y);
		glVertex2i(x+w,y);			
		glVertex2i(x+w,y+h);
		
		glColor4d((col.getR()-cDif)/COLBITS,(col.getG()-cDif)/COLBITS,(col.getB()-cDif)/COLBITS,1.0);
		
		glVertex2i(x,y);
		glVertex2i(x,y+h);
		glVertex2i(x+w,y+h);
		
		int h = this.h;
		int w = this.w;
		int y = this.y;
			
		if(down){
			h -= 1;
			w -= 1;
			y += 1;
			setCol(new Color(216,216,216));

		}
		
		glColor4d(
				col.getR()/COLBITS,
				col.getG()/COLBITS,
				col.getB()/COLBITS,
				1d);
		
		glVertex2i(x+bd,y+bd);
		glVertex2i(x+w-bd,y+bd);			
		glVertex2i(x+w-bd,y+h-bd);
		
		glVertex2i(x+bd,y+bd);
		glVertex2i(x+bd,y+h-bd);
		glVertex2i(x+w-bd,y+h-bd);
		
		setCol(col);
		

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
			lpic.setH(lpic.getH()-x);
			lpic.setW(lpic.getW()-x);
			lpic.setY(lpic.getY()+x);
		}
			
	}
	
	// Clickable
	
	@Override
	public void release(){
		down = false;
		labelNudge();
	}
	
	@Override
	public boolean getVis(){
		return visible;
	}
	
	@Override
	public Shape getShape(){
		return this;
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
		if(m.getX() > x && m.getX() < x + w)
			if(m.getY() > y && m.getY() < y + h)
					return true;
		return false;
	}
	@Override
	public void hover() {}
	
}
