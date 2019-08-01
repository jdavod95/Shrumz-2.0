package game.soil;

import java.util.ArrayList;
import java.util.List;

import game.Affector;
import render2d.Color;
import render2d.elements.Point;

public class Water extends Soil implements Affector{

	private static final int EFFECT_RANGE = 3;
	public static final Color COLOR = new Color(64,200,240);
			
	public Water() {
		super(0, 0, 0);
	}

	@Override
	protected void innerCycle() {
	}

	@Override
	public Color pickColor() {
		return COLOR;
	}

	@Override
	public SoilEffect[] getEffects() {
		return new SoilEffect[]{
			SoilEffect.FERTILE
		};
	}

	@Override
	public Point[] getEffectRange() {
		List<Point> affectedList = new ArrayList<>();
		for (int i = 0; i <= EFFECT_RANGE * 2; i++) 
			for (int j = 0; j <= EFFECT_RANGE * 2;  j++) 
				affectedList.add(new Point(i-EFFECT_RANGE, j-EFFECT_RANGE));
		return affectedList.toArray(new Point[affectedList.size()]);
	}

	@Override
	public Soil getNew() {
		return new Water();
	}

}
