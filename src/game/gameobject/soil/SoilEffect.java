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
	},
	INFERTILE(){
		@Override
		public void action(Soil s) {
			s.decFertility();
		}
	},
	FERTILE(){
		@Override
		public void action(Soil s) {
			s.incFertility();
		}
	}
	;
	public abstract void action(Soil s);
}

