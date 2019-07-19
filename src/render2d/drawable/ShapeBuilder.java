package render2d.drawable;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import elements.Action;
import elements.Point;
import render2d.Color;

public class ShapeBuilder {
	
	private Shape shape;
	
	public ShapeBuilder newDiamond(Point pos, int w, int h) {
		shape = new Diamond(pos, w, h);
		return this;
	}
	
	public ShapeBuilder newRectangle(Point pos, int w, int h) {
		shape = new Rectangle(pos, w, h);
		return this;
	}
	
	public ShapeBuilder setColor(Color color) {
		shape.setColor(color);
		return this;
	}
	
	public ShapeBuilder setTexture(String texName) {
		shape.setTexName(texName);
		return this;
	}

	public ShapeBuilder setClickable(Class<? extends ClickableShapeDummy> clickable, Action click, Action release, Action hover) {
		Constructor<? extends ClickableShapeDummy> constr;
		try {
			constr = clickable.getConstructor(Shape.class, Action.class, Action.class, Action.class);
			shape.setClickable(constr.newInstance(shape, click, release, hover));
		} catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			System.out.println("ShapeBuilder.setClickable() - error");
			e.printStackTrace();
		}
		return this;
	}
	
	public Shape getShape() {
		return shape;
	}
}
