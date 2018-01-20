package org.usfirst.frc.team1732.robot.sensors;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;

public class Limelight {
	NetworkTable table;

	public Limelight() {
		table = NetworkTableInstance.getDefault().getTable("limelight");
	}
	public void setLEDMode(LEDMode mode) {
		table.getEntry("ledMode").setNumber(mode.getVal());
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
