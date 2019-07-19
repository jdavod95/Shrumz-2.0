package render2d.drawable;

import elements.Action;
import elements.Point;

public class Rectangle extends Shape{

	Rectangle(Point pos, int w, int h) {
		super(pos, w, h);
	}

	Rectangle(Point pos, int w, int h, Action click, Action release, Action hover){
		super(pos, w, h);
		setClickable(new RectangleClick(getShape(), click, release, hover));
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
