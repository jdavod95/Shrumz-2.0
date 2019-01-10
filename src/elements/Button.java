package elements;

import static org.lwjgl.opengl.GL11.glColor4d;
import static org.lwjgl.opengl.GL11.glVertex2i;

import render2d.Render;
import render2d.shape.Rect;
import render2d.shape.RectTex;
import render2d.shape.Shape;
import render2d.write.Word;

public class Button extends Rect implements Clickable {

	static int col = 224;
	int cDif = 16;
	int bd = 5;

	RectTex lpic = null;
	String label = null;
	
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
		this.label = label;
		this.type = toggle;
		visible = true;
		if(e != null)
			this.e = e;
	}
	
	public Button(int x, int y, int w, int h, int fid, int fcu, MyEvent e, boolean toggle) {
		super(x,y,w,h,col,col,col);
		this.lpic = new RectTex(x ,y, w, h, fid ,fcu);
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
			setCol(col-4,col-4,col-4);
		}
		
		glColor4d(r/COLBITS,g/COLBITS,b/COLBITS,1.0);
		
		glVertex2i(x+bd,y+bd);
		glVertex2i(x+w-bd,y+bd);			
		glVertex2i(x+w-bd,y+h-bd);
		
		glVertex2i(x+bd,y+bd);
		glVertex2i(x+bd,y+h-bd);
		glVertex2i(x+w-bd,y+h-bd);
		
		setCol(col,col,col);
	
		if(label != null)
			Render.drawWord(new Word(x,y,32,label));
		
	}
	
	public void toRender(int l){
		Render.addShape(this, l);
		if(lpic != null)
			Render.addShape(lpic, l);
	}
	// Clickable
	
	@Override
	public void release(){
		down = false;
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
			}
		} 
		else{
			down = true;
			e.action();
		}
	}
	
}
