/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team1732.robot.commands.drive;

import static org.usfirst.frc.team1732.robot.Robot.drivetrain;
import static org.usfirst.frc.team1732.robot.Robot.joysticks;

import edu.wpi.first.wpilibj.command.Command;

/**
 * An example command. You can replace me with your own command.
 */
public class TankDrive extends Command {
	public TankDrive() {
		requires(drivetrain);
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {

	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		drivetrain.setLeftMotors(joysticks.getLeftY());
		drivetrain.setRightMotors(joysticks.getRightY());
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {}
}
