package render2d.drawable;

import render2d.elements.CursorActions;
import render2d.elements.Point;

public class Rectangle extends Shape{

	public Rectangle(Point pos, int w, int h) {
		super(pos, w, h);
	}

	Rectangle(Point pos, int w, int h, CursorActions action){
		super(pos, w, h);
		setClickable(new RectangleClick(getShape(), action));
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
