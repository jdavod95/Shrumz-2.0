package render2d.drawable;

import render2d.elements.Point;

public class ClickableDummy extends Clickable{
	
	@Override
	public boolean contains(Point m) {
		return false;
	}

}
