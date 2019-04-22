package elements;

import static org.lwjgl.opengl.GL11.glColor4d;
import static org.lwjgl.opengl.GL11.glEnd;

import render2d.Color;
import render2d.Render;
import render2d.shape.Clickable;
import render2d.shape.Point;
import render2d.shape.Shape;
import render2d.shape.ShapeFactory;
import render2d.shape.rectangle.Rectangle;
import render2d.write.Label;

public class Button extends Shape implements Clickable{

	static int COLBITS = 255;
	static Color col = new Color(224,224,224);
	int cDif = 16;
	int bd = 5;
	
	Rectangle lpic = null;
	Label label = null;
	
	boolean down = false;
	boolean type = true; //toggle true|| hold false 
	MyEvent e;

	MyEvent release = new MyEvent(){
		@Override
		public void action() {
			down = false;
			labelNudge();
		}
	};
	
	MyEvent onClick = new MyEvent(){	
		@Override
		public void action() {
			labelNudge();
			e.action();		
		}
	};
		
	public Button(Point pos, int w, int h, String label, MyEvent e, boolean toggle) {
		super(pos, w, h);
		this.label = new Label(new Point(pos.getX()+bd, pos.getY()+bd), h-2*bd, label);
		this.type = toggle;
		if(e != null)
			this.e = e;
	}
	
	public Button(Point pos, int w, int h, int fid, int fcu, MyEvent e, boolean toggle) {
		super(pos, w, h);
		this.lpic = ShapeFactory.createRectTex(new Point(pos.getX()+bd, pos.getY()+bd), w-2*bd, h-2*bd, fid);
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
			col = new Color(216,216,216);
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
			label.reScale((label.getH()-x), (label.getW()-x));
			label.setY(label.getPos().getY()+x);
		}
		if(lpic != null){
			lpic.reScale(
					lpic.getW()-x,
					lpic.getH()-x);
			lpic.getPos().setY(lpic.getPos().getY()+x);
		}
			
	}

	@Override
	public void reScale(int w, int h) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public MyEvent getRelease() {
		return release;
	}

	@Override
	public MyEvent getHover() {
		return MyEvent.EMPTY;
	}

	@Override
	public MyEvent getOnClick() {
		return onClick;
	}

	@Override
	public boolean isDown() {
		return down;
	}

	@Override
	public void setDown(boolean b) {
		down = b;
	}

	@Override
	public boolean contains(Point m) {
		if(
				m.getX() > getShape().getPos().getX() &&
				m.getX() < getShape().getPos().getX() + getShape().getW())
				if(
					m.getY() > getShape().getPos().getY() && 
					m.getY() < getShape().getPos().getY() + getShape().getH())
					return true;
			
			return false;
	}
	
	

}
