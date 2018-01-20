package org.usfirst.frc.team1732.robot.input;

import edu.wpi.first.wpilibj.Joystick;

public class Joysticks {

	public final Joystick controller;

	public Joysticks() {
		controller = new Joystick(4);
	}

	public double getLeftY() {
		return controller.getRawAxis(1);
	}
	public double getLeftX() {
		return controller.getRawAxis(0);
	}
	public double getRightY() {
		return controller.getRawAxis(5);
	}
	public double getRightX() {
		return controller.getRawAxis(4);
	}
}