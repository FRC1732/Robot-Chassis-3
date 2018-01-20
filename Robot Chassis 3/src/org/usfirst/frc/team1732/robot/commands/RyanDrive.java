package org.usfirst.frc.team1732.robot.commands;

import static org.usfirst.frc.team1732.robot.Robot.drivetrain;
import static org.usfirst.frc.team1732.robot.Robot.joysticks;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class RyanDrive extends Command {

	public RyanDrive() {
		requires(drivetrain);
	}

	// Called just before this Command runs the first time
	protected void initialize() {}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		double drive = joysticks.getLeftY(), turn = joysticks.getRightX();
		double left = drive - turn, right = drive + turn;
		drivetrain.setLeftMotors(left);
		drivetrain.setRightMotors(right);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {}
}
