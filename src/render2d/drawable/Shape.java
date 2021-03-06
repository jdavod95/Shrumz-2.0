package render2d.drawable;

import render2d.elements.Point;

public abstract class Shape extends Drawable{
	
	private Point pos;
	private int w, h;
	
	protected Shape(Point pos, int w, int h){
		super();
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
	
	public void setPos(int x, int y) {
		pos.setX(x);
		pos.setY(y);
	}

	public Shape getShape(){
		return this;
	}

	public abstract void reScale(int w, int h);

}
