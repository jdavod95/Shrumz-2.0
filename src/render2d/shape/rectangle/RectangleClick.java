package render2d.shape.rectangle;

import elements.Point;
import render2d.shape.Clickable;

public interface RectangleClick extends Clickable{
	
	public default boolean contains(Point m){
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