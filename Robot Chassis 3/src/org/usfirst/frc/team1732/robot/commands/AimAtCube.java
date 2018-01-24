package org.usfirst.frc.team1732.robot.commands;

import org.usfirst.frc.team1732.robot.Robot;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AimAtCube extends Command {
	private PIDController p, q;

	public AimAtCube() {
		requires(Robot.drivetrain);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		p = new PIDController(0.95, 0, 0.1, new PIDSource() {
			public void setPIDSourceType(PIDSourceType pidSource) {}
			public PIDSourceType getPIDSourceType() {
				return PIDSourceType.kDisplacement;
			}
			public double pidGet() {
				double off = Robot.limelight.getNormalizedHorizontalOffset();
				return off == 0 ? 1 : off;
			}
		}, output -> {
			// Robot.drivetrain.setLeftMotors(output);
			// Robot.drivetrain.setRightMotors(-output);
		}, 0.02);
		p.setPercentTolerance(10);
		p.enable();
		q = new PIDController(1, 0, 0, new PIDSource() {
			public void setPIDSourceType(PIDSourceType pidSource) {}
			public PIDSourceType getPIDSourceType() {
				return PIDSourceType.kDisplacement;
			}
			public double pidGet() {
				double a = Robot.limelight.getNormalizedTargetArea();
				// TODO change to distance, not area
				return a == 0 ? 0 : 4 * (0.3 - a);
			}
		}, output -> {
			// Robot.drivetrain.setLeftMotors(output);
			// Robot.drivetrain.setRightMotors(-output);
		}, 0.02);
		q.setPercentTolerance(10);
		q.enable();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		Robot.drivetrain.setLeftMotors(p.get() + q.get());
		Robot.drivetrain.setRightMotors(-p.get() + q.get());
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;// p.onTarget();
	}

	// Called once after isFinished returns true
	protected void end() {
		p.disable();
		q.disable();
		Robot.drivetrain.setAllStop();
		System.out.println("TurnAtCube done");
	}
}
