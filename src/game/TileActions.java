package game;

import render2d.Color;
import render2d.Render;
import render2d.drawable.Diamond;
import render2d.drawable.ShapeBuilder;
import render2d.elements.Action;
import render2d.elements.CursorActions;
import render2d.elements.Point;

public final class TileActions extends CursorActions{
	
	public TileActions(Tile tile, Diamond soilSkin) {
		
		setClick(()->{
			if(Screen.getBrushPlant() != null)
				tile.setPlant(Screen.getBrushPlant().getNew());
			
			if(Screen.getBrushSoil() != null)
				tile.setSoil(Screen.getBrushSoil().getNew());
		});
		
		setHover(()->{
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
		});
	}
}
