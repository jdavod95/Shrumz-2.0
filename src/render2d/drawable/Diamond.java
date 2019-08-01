package render2d.drawable;

import render2d.elements.CursorActions;
import render2d.elements.Point;

public class Diamond extends Shape{

	public Diamond(Point pos, int w, int h) {
		super(pos, w, h);
	}

	Diamond(Point pos, int w, int h, CursorActions action){
		super(pos, w, h);
		setClickable(new DiamondClick(getShape(), action));
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
		Point base = new Point(
				getPos().getX(),
				getPos().getY()+getH()/2);
		
		drawTriangle(base, new Point(getW()/2, -getH()/2), new Point(getW(), 0));
		drawTriangle(base, new Point(getW()/2, +getH()/2), new Point(getW(), 0));
	}

}
