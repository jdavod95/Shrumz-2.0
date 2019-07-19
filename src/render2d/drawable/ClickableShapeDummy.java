package render2d.drawable;

import elements.Action;
import elements.Point;

public class ClickableShapeDummy extends Clickable{

	protected Shape shape;

	protected ClickableShapeDummy(Shape shape, Action click, Action release, Action hover) {
		super(click, release, hover);
		this.shape = shape;
	}

	public ClickableShapeDummy() {
		super();
	}
	
	@Override
	public boolean contains(Point m) {
		return false;
	}
}
