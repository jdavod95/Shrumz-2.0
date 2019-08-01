package render2d.drawable;

import render2d.elements.CursorActions;
import render2d.elements.Point;

public final class RectangleClick extends ClickableShapeDummy{
	
	public RectangleClick(Shape shape, CursorActions action) {
		super(shape, action);
	}

	public boolean contains(Point m){
		if(
			m.getX() > shape.getPos().getX() &&
			m.getX() < shape.getPos().getX() + shape.getW())
			if(
				m.getY() > shape.getPos().getY() && 
				m.getY() < shape.getPos().getY() + shape.getH())
				return true;
		return false;
	}
}
