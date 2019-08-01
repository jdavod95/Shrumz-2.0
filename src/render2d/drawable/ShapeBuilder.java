package render2d.drawable;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import render2d.Color;
import render2d.elements.CursorActions;
import render2d.elements.Point;

public class ShapeBuilder {
	
	private Shape shape;
	
	public ShapeBuilder newShape(Class<? extends Shape> shape, Point pos, int w, int h) {
		Constructor<? extends Shape> constr;
			try {
				constr = shape.getConstructor(Point.class, Integer.TYPE, Integer.TYPE);
				this.shape = constr.newInstance(pos, w, h);
			} catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				System.out.println("ShapeBuilder.newShape() - error");
				e.printStackTrace();
			}
		return this;
	}
	
	public ShapeBuilder newDiamond(Point pos, int w, int h) {
		newShape(Diamond.class, pos, w, h);
		return this;
	}
	
	public ShapeBuilder newRectangle(Point pos, int w, int h) {
		newShape(Rectangle.class, pos, w, h);
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

	public ShapeBuilder setClickable(Class<? extends ClickableShapeDummy> clickable, CursorActions action) {
		Constructor<? extends ClickableShapeDummy> constr;
		try {
			constr = clickable.getConstructor(Shape.class, CursorActions.class);
			shape.setClickable(constr.newInstance(shape, action));
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
