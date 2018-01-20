package org.usfirst.frc.team1732.robot.commands;

import org.usfirst.frc.team1732.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AimAtCube extends Command {
	private double error;

	public AimAtCube() {
		requires(Robot.drivetrain);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		error = Robot.limelight.getHorizontalOffset();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		error = Robot.limelight.getHorizontalOffset();
		double tx = error / 27;
		Robot.drivetrain.setLeftMotors(tx);
		Robot.drivetrain.setRightMotors(-tx);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return Math.abs(error) < 1;
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.drivetrain.setAllStop();
	}
}
