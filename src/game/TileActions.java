package game;

import render2d.Color;
import render2d.Render;
import render2d.drawable.Diamond;
import render2d.drawable.Shape;
import render2d.drawable.ShapeBuilder;
import render2d.elements.CursorActions;
import render2d.elements.Point;

public final class TileActions extends CursorActions{
	
	private Tile ownerTile;
	private Shape soilSkin;
	
	public TileActions(Tile tile, Diamond soilSkin) {
		this.ownerTile = tile;
		this.soilSkin = soilSkin;
	}
	
	@Override
	protected void actionClick() {
		if(Screen.getBrushPlant() != null)
			ownerTile.setPlant(Screen.getBrushPlant().getNew());
		
		if(Screen.getBrushSoil() != null)
			ownerTile.setSoil(Screen.getBrushSoil().getNew());
	}
	
	@Override
	protected void actionHover() {
		Render.addScn(
				new ShapeBuilder().newDiamond(
					new Point(
						soilSkin.getPos().getX(),
						soilSkin.getPos().getY()),
					soilSkin.getW(),
					soilSkin.getW()/2)
				.setColor(new Color(Color.WHITE, 0.5))
				.getShape(),
			4);
	}
}
