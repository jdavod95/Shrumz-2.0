package render2d.shape.rectangle;

import render2d.shape.Point;
import render2d.shape.Shape;

public abstract class Rectangle extends Shape{

	protected Rectangle(Point pos, int w, int h) {
		super(pos, w, h);
	}

	@Override
	public void reScale(int w, int h) {
		Point pos = getPos();
		pos.setXY(
				pos.getX()+(getW() - w)/2,
				pos.getY()+(getH() - h)/2);
		setW(w);
		setH(h);
	}

	@Override
	public void drawShape() {
		drawTriangle(getPos(), new Point(getW(), 0), new Point(getW(), getH()));
		drawTriangle(getPos(), new Point(0, getH()), new Point(getW(), getH()));
	}

}
