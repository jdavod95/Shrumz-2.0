package render2d.shape.diamond;

import elements.Point;
import render2d.shape.Clickable;

public interface DiamondClick extends Clickable{

	public default boolean contains(Point mouse){
		
		int x = getShape().getPos().getX();
		int y = getShape().getPos().getY();

		int w = getShape().getW()/2;
		
		int mx = mouse.getX();
		int my = mouse.getY();

		if(mx < x || mx > x + getShape().getW())
			return false;
		if(my < y || my > y + getShape().getH())
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
