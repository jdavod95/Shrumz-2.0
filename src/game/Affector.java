package game;

import game.soil.SoilEffect;
import render2d.elements.Point;

public interface Affector {

	public SoilEffect[] getEffects();
	public Point[] getEffectRange();
}
