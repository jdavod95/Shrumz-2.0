package game.soil;

import java.util.ArrayList;
import java.util.List;

import elements.IndexPair;
import game.Affector;
import render2d.Color;

public class Water extends Soil implements Affector{

	private static final int EFFECT_RANGE = 2;
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
		IndexPair[] ip = new IndexPair[(int) Math.pow(EFFECT_RANGE*2+1, 2)];
		for (int i =0; i <= EFFECT_RANGE*2; i++) 
			for (int j = 0; j <= EFFECT_RANGE*2; j++) 
				ip[i*EFFECT_RANGE+EFFECT_RANGE+j] = new IndexPair(i-EFFECT_RANGE, j-EFFECT_RANGE);
		return ip;
	}

	@Override
	public Soil getNew() {
		return new Water();
	}
}
