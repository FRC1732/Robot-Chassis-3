package org.usfirst.frc.team1732.robot.commands;

import static org.usfirst.frc.team1732.robot.Robot.drivetrain;
import static org.usfirst.frc.team1732.robot.Robot.leftRecorder;
import static org.usfirst.frc.team1732.robot.Robot.rightRecorder;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ReverseDrivetrainMovements extends Command {

	public ReverseDrivetrainMovements() {
		requires(drivetrain);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		leftRecorder.stopRecording();
		rightRecorder.stopRecording();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		drivetrain.setMotorsRaw(leftRecorder.getLastVoltage(), -rightRecorder.getLastVoltage());
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return leftRecorder.isFinished() && rightRecorder.isFinished();
	}

	// Called once after isFinished returns true
	protected void end() {
		drivetrain.setAllStop();
	}
}
