package render2d.shapeNew.diamond;

import render2d.shapeNew.Clickable;
import render2d.shapeNew.Point;
import render2d.shapeNew.Shape;

public interface DiamondClick extends Clickable{

	public default boolean contains(Shape shape, Point mouse){
		
		int x = shape.getPos().getX();
		int y = shape.getPos().getY();

		int w = shape.getW()/2;
		
		int mx = mouse.getX();
		int my = mouse.getY();

		if(mx < x || mx > x + shape.getW())
			return false;
		if(my < y || my > y + shape.getH())
			return false;

		if(mx < x+w){
			if(my < (mx-x+w)/2+y && my > (x-mx+w)/2+y)
				return true;
		} else
			if(my < (x-mx+w)/2+w+y && my > (mx-x-w)/2+y)
				return true;
		
		return false;
	}
	
	
}
