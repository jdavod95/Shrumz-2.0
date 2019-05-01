package game;

import elements.Point;
import game.soil.SoilEffect;

public interface Affector {

	public SoilEffect[] getEffects();
	public Point[] getEffectRange();
}
