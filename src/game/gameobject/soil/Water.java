package game.gameobject.soil;

import elements.IndexPair;
import game.Affector;
import render2d.Color;

public class Water extends Soil implements Affector{

	public static final Color COLOR = new Color(64,200,240);
			
	public Water() {
		super(0, 0, 0);
	}

	@Override
	protected void innerCycle() {
	}

	@Override
	public Color getColor() {
		return COLOR;
	}

	@Override
	public SoilEffect[] getEffects() {
		return new SoilEffect[]{
			SoilEffect.FERTILE
		};
	}

	@Override
	public IndexPair[] getEffectRange() {
		return new IndexPair[] {
				new IndexPair(-1, -1),
				new IndexPair(-1, 0),
				new IndexPair(-1, 1),
				new IndexPair(0, -1),
				new IndexPair(0, 1),
				new IndexPair(1, -1),
				new IndexPair(1, 0),
				new IndexPair(1, 1)
		};
	}

	@Override
	public Soil getNew() {
		return new Water();
	}
}
