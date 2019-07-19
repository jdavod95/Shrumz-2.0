package game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import elements.Button;
import elements.Point;
import root.App;

public class Buttons {
	private static List<Button> buttons = new ArrayList<Button>(
		Arrays.asList(
			new Button(new Point(50, 0), 25, 25, "+",
					Actions.GROWMAPX),
			
			new Button(new Point(50, 60), 25, 25, "-",
					Actions.SHRINKMAPX),
			
			new Button(new Point(90, 0), 25, 25, "+",
					Actions.GROWMAPY),
			
			new Button(new Point(90, 60), 25, 25, "-",
					Actions.SHRINKMAPY),
	
			new Button(new Point(130, 0), 25, 25, "+",
					Actions.SCALEUP),
			
			new Button(new Point(130, 0), 25, 25, "-",
					Actions.SCALEDOWN),
			
			new Button(new Point(App.W-50, 45), 40, 40, "Del",
					Actions.BRPLNULL),
			
			new Button(new Point(App.W-95, 45), 40, 40, "SHRUM",
					Actions.BRPLSHRUM),
			
			new Button(new Point(App.W-140, 45), 40, 40, "BLUSHRUM",
					Actions.BRPLBLUSHRUM),
			
			new Button(new Point(App.W-185, 45), 40, 40, "WEED",
					Actions.BRPLWEED),
			
			new Button(new Point(App.W-50, 0), 40, 40, "Off",
					Actions.BRSL_NO),
			
			new Button(new Point(App.W-140, 0), 40, 40, "Drt",
					Actions.BRSL_D),
			
			new Button(new Point(App.W-185, 0), 40, 40, "Wtr",
					Actions.BRSL_W),
			
			new Button(new Point(250, 0), 40, 40, "CONTROLPLAY",
					Actions.CTRLPLAY),
			
			new Button(new Point(295, 0), 40, 40, "CONTROLPAUSE", 
					Actions.CTRLPAUSE),
			
			new Button(new Point(250, 45), 40, 40, "CONTROLSTEP",
					Actions.CTRLSTEP),
			
			new Button(new Point(360, 0), 25, 25, " +",
					Actions.CTRLFRDOWN),
			
			new Button(new Point(360, 60), 25, 25, " -",
					Actions.CTRLFRUP)
		)
	);

	public static List<Button> getAll() {
		return buttons;
	}
	
	
}
