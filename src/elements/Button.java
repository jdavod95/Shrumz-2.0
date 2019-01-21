package elements;

import static org.lwjgl.opengl.GL11.glColor4d;
import static org.lwjgl.opengl.GL11.glVertex2i;

import org.lwjgl.util.Point;

import render2d.Render;
import render2d.shape.Rect;
import render2d.shape.RectTex;
import render2d.shape.Shape;
import render2d.write.Label;

public class Button extends Rect implements Clickable {

	static int col = 224;
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
		super(x,y,w,h,col,col,col);
		this.label = new Label(x+bd, y+bd, h-2*bd, label);
		this.type = toggle;
		visible = true;
		if(e != null)
			this.e = e;
	}
	
	public Button(int x, int y, int w, int h, int fid, int fcu, MyEvent e, boolean toggle) {
		super(x,y,w,h,col,col,col);
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

		glColor4d((r+cDif)/COLBITS,(g+cDif)/COLBITS,(b+cDif)/COLBITS,1.0);
		
		glVertex2i(x,y);
		glVertex2i(x+w,y);			
		glVertex2i(x+w,y+h);
		
		glColor4d((r-cDif)/COLBITS,(g-cDif)/COLBITS,(b-cDif)/COLBITS,1.0);
		
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
			setCol(col-8,col-8,col-8);

		}
		
		glColor4d(r/COLBITS,g/COLBITS,b/COLBITS,1.0);
		
		glVertex2i(x+bd,y+bd);
		glVertex2i(x+w-bd,y+bd);			
		glVertex2i(x+w-bd,y+h-bd);
		
		glVertex2i(x+bd,y+bd);
		glVertex2i(x+bd,y+h-bd);
		glVertex2i(x+w-bd,y+h-bd);
		
		setCol(col,col,col);
		
		
	}
	
	public void toRender(int l){
		Render.addShape(this, l);
		if(label != null)
			Render.addShape(label, l);
		else if(lpic != null)
			Render.addShape(lpic, l);
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
	public void action() {		
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
