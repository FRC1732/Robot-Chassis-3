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
	// ----- SETTERS -----
	public void setLEDMode(LEDMode mode) {
		table.getEntry(LED_MODE).setNumber(mode.getVal());
	}
	// ----- GETTERS -----
	// returns the horizonatal offset of the target (between -27 and 27 degrees)
	public double getHorizontalOffset() {
		return table.getEntry("tx").getNumber(0).doubleValue();
	}
	// returns the vertical offset of the target (between -20.5 and 20.5 degrees)
	public double getVerticalOffset() {
		return table.getEntry("ty").getNumber(0).doubleValue();
	}
	// returns true if it is tracking a target
	public boolean hasValidTargets() {
		return table.getEntry("tv").getNumber(0).intValue() == 1;
	}
	// returns percent of screen area target takes up (between 0 and 100)
	public double getTargetArea() {
		return table.getEntry("ta").getNumber(0).doubleValue();
	}
	// rotation of target (between -90 and 0 degrees)
	public double getTargetSkew() {
		return table.getEntry("ts").getNumber(0).doubleValue();
	}
	// returns pipeline latency in milliseconds
	// doesnt take image capture latency into account (website says add 11 ms)
	public double getLatency() {
		return table.getEntry("tl").getNumber(0).doubleValue();
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
