package render2d.drawable;

import elements.Action;
import elements.Point;

public final class RectangleClick extends ClickableShapeDummy{
	
	public RectangleClick(Shape shape, Action click, Action release, Action hover) {
		super(shape, click, release, hover);
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
