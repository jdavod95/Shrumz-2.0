package render2d.shapeNew.diamond;

import render2d.shapeNew.Point;
import render2d.shapeNew.Shape;

public abstract class Diamond extends Shape{

	protected Diamond(Point pos, int w, int h) {
		super(pos, w, h);
	}

	@Override
	public void reScale(int w, int h) {
		
	}

	@Override
	protected void drawShape() {
		Point base = new Point(
				getPos().getX(),
				getPos().getY()+getH()/2);
		
		drawTriangle(base, new Point(getW()/2, -getH()/2), new Point(getW(), 0));
		drawTriangle(base, new Point(getW()/2, +getH()/2), new Point(getW(), 0));
	}

}