package org.usfirst.frc.team1732.robot.sensors;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

public class Limelight {
	private static final String LED_MODE = "ledMode";

	private final NetworkTable table;

	public Limelight() {
		table = NetworkTableInstance.getDefault().getTable("limelight");
	}
	public void setLEDMode(LEDMode mode) {
		table.getEntry(LED_MODE).setNumber(mode.getVal());
	}
	public void toggleLED() {
		NetworkTableEntry lights = table.getEntry(LED_MODE);
		lights.setNumber(lights.getNumber(1).intValue() == 0 ? 1 : 0);
	}

	public static enum LEDMode {
		ON(0), OFF(1), BLINK(2);
		private int val;

		private LEDMode(int n) {
			val = n;
		}
		public int getVal() {
			return val;
		}
	}
}
