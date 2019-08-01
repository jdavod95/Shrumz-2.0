package render2d.drawable;

import render2d.elements.CursorActions;
import render2d.elements.Point;

public class ClickableShapeDummy extends Clickable{

	protected Shape shape;

	protected ClickableShapeDummy(Shape shape, CursorActions action) {
		super(action);
		this.shape = shape;
	}
	
	@Override
	public boolean contains(Point m) {
		return false;
	}
}
