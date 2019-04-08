package game;

import java.util.ArrayList;
import java.util.List;

import elements.IndexPair;

public class Map {

	static final int mx = 5;
	static final int my = 95;
	
	static int dx,dy;
	static Tile[][] table;

	static List<Tile> spreadingPlants = new ArrayList<>();
	static List<Tile> dyingPlants = new ArrayList<>();
	static List<Tile> enhancers = new ArrayList<>();
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
		
		for(int i = 0;i<dx;i++)
			for(int j = 0;j<dy;j++)
				table[i][j] = new Tile(
						mx + (16-i-1)*(Tile.getScale()) + j*Tile.getScale(),
						my + (i+1)*Tile.getScale()/2 + j*Tile.getScale()/2,
						new IndexPair(i, j)
					);
	}
	
	public static void load(int x, int y, int scale){
		Tile.setScale(scale);
		setTable(x,y);
	}
	
	public static void toRender(){
		for(int i = 0;i<dx;i++)
			for(int j = 0;j<dy;j++){
				table[i][j].toRender();
				table[i][j].toClick();
		}
	}
	
	public static void reScale(int scale){
		int scaledif = Tile.getScale() - scale;
		Tile.setScale(scale);
		for(int i = 0;i<dx;i++)
			for(int j = 0;j<dy;j++)
				table[i][j].reScale(-scaledif*j+scaledif*i, -scaledif*j/2-scaledif/2*i);
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
		
	/*	for(Tile t : enhancers)
			applyEffects(t);
		enhancers.clear();*/
		
	}

	public static void subSpread(Tile t){
		spreadingPlants.add(t);
	}
	
	public static void subDead(Tile t){
		dyingPlants.add(t);
	}

	public static void spread(Tile source){
		IndexPair[] pairs = source.spreadPlant();
		Tile t;
		for(IndexPair ip : pairs){
			try {
				t = getTile(
						source.getPos().getX() + ip.getX(),
						source.getPos().getY() + ip.getY());
				if (!t.hasPlant())
					t.setPlant(source.getNewPlant());
			} catch (Exception e) {}
		}
	}
	/*
	public static void applyEffects(Tile source){
		IndexPair[] pairs = source.spreadPlant();
		Tile t;
		for(IndexPair ip : pairs){
			try {
				t = getTile(
						source.getPos().getX() + ip.getX(),
						source.getPos().getY() + ip.getY());
				t.applySoilEffect();
			} catch (Exception e) {}
		}
	}*/
}
