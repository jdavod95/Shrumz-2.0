package game.gameobject.soil;

public enum SoilEffect{
	
	WET() {
		@Override
		public void action(Soil s) {
			s.incWater();	
		}
	},
	DRY()
	{
		@Override
		public void action(Soil s) {
			s.decWater();
		}
	}
	;
	public abstract void action(Soil s);
}

