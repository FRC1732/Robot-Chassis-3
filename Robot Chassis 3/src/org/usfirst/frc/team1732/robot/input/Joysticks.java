package org.usfirst.frc.team1732.robot.input;

import org.usfirst.frc.team1732.robot.commands.AimAtCube;
import org.usfirst.frc.team1732.robot.commands.ToggleLED;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class Joysticks {
	public final Joystick controller;

	public Joysticks() {
		controller = new Joystick(4);

		new JoystickButton(controller, 4).whenPressed(new ToggleLED());
		new JoystickButton(controller, 1).whileHeld(new AimAtCube());
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