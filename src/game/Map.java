package game;

import java.util.ArrayList;
import java.util.List;

import render2d.elements.Point;

public class Map {

	private static final int MARGIN_X = 5;
	private static final int MARGIN_Y = 95;
	
	private static int dx,dy;
	private static Tile[][] table;

	private static List<Tile> spreadingPlants = new ArrayList<>();
	private static List<Tile> dyingPlants = new ArrayList<>();
	private static List<Tile> affectors = new ArrayList<>();
	
	public static int getX(){
		return dx;
	}
	
	public static int getY(){
		return dy;
	}
	
	public static Tile[][] getTable() {
		return table;
	}
	
	public static Tile getTile(int x, int y){
		return table[x][y];
	}
	
	public static void setTable(int x, int y){
		table = new Tile[x][y];
		dx = x;
		dy = y;
		
		for(int i = 0; i < dx;i++)
			for(int j = 0; j < dy;j++)
				table[i][j] = new Tile(
					MARGIN_X + (16-i-1)*(Tile.getScale()) + j*Tile.getScale(),
					MARGIN_Y + (i+1)*Tile.getScale()/2 + j*Tile.getScale()/2,
					new Point(i, j)
				);
	}
	
	public static void load(int x, int y, int scale){
		Tile.setScale(scale);
		setTable(x,y);
	}
	
	public static void toRender(){
		for(Tile[] line : table)
			for(Tile field : line)
				field.toRender();
	}
	
	public static void toClick() {
		for(Tile[] line : table)
			for(Tile field : line)
				field.toClick();
	}
	
	public static void reScale(int scale){
		int scaledif = Tile.getScale() - scale;
		Tile.setScale(scale);
		for(int i = 0;i<dx;i++)
			for(int j = 0;j<dy;j++)
				table[i][j].reScale(
					-scaledif * j + scaledif * i,
					-scaledif * j / 2 - scaledif / 2 * i);
	}

	public static void cycle(){
		for(Tile[] tbl : table)
			for(Tile t : tbl)
				t.cycle();
				
		for(Tile t : spreadingPlants)
			spread(t);
		spreadingPlants.clear();
		
		for(Tile t : dyingPlants)
			t.killPlant();
		dyingPlants.clear();
		
		for(Tile t : affectors)
			applyEffects(t);
		affectors.clear();
	
	}

	public static void subSpread(Tile t){
		spreadingPlants.add(t);
	}
	
	public static void subDead(Tile t){
		dyingPlants.add(t);
	}
	
	public static void subAffect(Tile t){
		affectors.add(t);
	}
	
	public static void spread(Tile source){
		Point[] pairs = source.spreadPlant();
		Point sourcePos = source.getPos();
		Tile t;
		for(Point ip : pairs){
			try {
				t = getTile(
						sourcePos.getX() + ip.getX(),
						sourcePos.getY() + ip.getY());
				if (!t.hasPlant())
					t.setPlant(source.getNewPlant());
			} catch (Exception e) {}
		}
	}
	
	public static void applyEffects(Tile source){
		Point[] pairs = source.affectorRange();
		Point sourcePos = source.getPos();
		Tile t;
		for(Point ip : pairs){
			try {
				t = getTile(
						sourcePos.getX() + ip.getX(),
						sourcePos.getY() + ip.getY());
				t.applySoilEffect(source.affectorEffects());
			} catch (Exception e) {}
		}
	}
}
