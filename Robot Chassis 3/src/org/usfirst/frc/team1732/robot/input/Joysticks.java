package org.usfirst.frc.team1732.robot.input;

import org.usfirst.frc.team1732.robot.Robot;
import org.usfirst.frc.team1732.robot.commands.DriveToCube;
import org.usfirst.frc.team1732.robot.commands.DriveToCube.TurnDirection;
import org.usfirst.frc.team1732.robot.commands.ReverseDrivetrainMovements;
import org.usfirst.frc.team1732.robot.commands.ToggleLED;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.InstantCommand;

public class Joysticks {
	public final Joystick controller;

	public Joysticks() {
		controller = new Joystick(4);

		new JoystickButton(controller, 4).whenPressed(new ToggleLED());
		new JoystickButton(controller, 1).whenPressed(new DriveToCube(TurnDirection.RIGHT));
		new JoystickButton(controller, 8).whenPressed(new InstantCommand() {
			protected void initialize() {
				Robot.leftRecorder.startRecording();
				Robot.rightRecorder.startRecording();
			}
		});
		new JoystickButton(controller, 7).whenPressed(new InstantCommand() {
			protected void initialize() {
				Robot.leftRecorder.stopRecording();
				Robot.rightRecorder.stopRecording();
			}
		});
		new JoystickButton(controller, 9).whenPressed(new ReverseDrivetrainMovements());

		new JoystickButton(controller, 2).whenPressed(new InstantCommand() {
			protected void initialize() {
				Robot.drivetrain.setBrakeMode(true);
			}
		});
		new JoystickButton(controller, 3).whenPressed(new InstantCommand() {
			protected void initialize() {
				Robot.drivetrain.setBrakeMode(false);
			}
		});
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