package game;

import elements.IndexPair;
import game.soil.SoilEffect;

public interface Affector {

	public SoilEffect[] getEffects();
	public IndexPair[] getEffectRange();
}
