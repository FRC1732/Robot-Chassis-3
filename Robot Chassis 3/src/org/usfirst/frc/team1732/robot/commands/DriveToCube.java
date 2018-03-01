package org.usfirst.frc.team1732.robot.commands;

import static org.usfirst.frc.team1732.robot.Robot.drivetrain;
import static org.usfirst.frc.team1732.robot.Robot.limelight;

import org.usfirst.frc.team1732.robot.Robot;
import org.usfirst.frc.team1732.robot.util.DisplacementPIDSource;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveToCube extends Command {
	private PIDController rotation, translation;
	private final double turn;

	public DriveToCube(TurnDirection direction) {
		this(direction, 1);
	}
	public DriveToCube(TurnDirection direction, double speed) {
		requires(drivetrain);
		turn = direction.getValue() * speed;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		rotation = new PIDController(0.7, 0, 0.5, new DisplacementPIDSource() {
			public double pidGet() {
				return limelight.getNormalizedHorizontalOffset(turn);
			}
		}, this::u, Robot.DEFAULT_PERIOD);
		// within 10 percent
		rotation.setAbsoluteTolerance(0.1);
		rotation.enable();
		translation = new PIDController(0.5, 0, 3, new DisplacementPIDSource() {
			public double pidGet() {
				// if it can't find a cube, will just output 0
				return limelight.getDistanceToTarget(10) - 10;
			}
		}, this::u, Robot.DEFAULT_PERIOD);
		// within 15 inches
		translation.setAbsoluteTolerance(15);
		translation.enable();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		drivetrain.setMotors(rotation.get() + translation.get(), -rotation.get() + translation.get());
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return rotation.onTarget() && translation.onTarget();
	}

	// Called once after isFinished returns true
	protected void end() {
		rotation.disable();
		translation.disable();
		drivetrain.setAllStop();
		System.out.println("DriveToCube: Ended");
	}

	public static enum TurnDirection {
		LEFT(-1), RIGHT(1), NONE(0);
		private int val;

		private TurnDirection(int n) {
			val = n;
		}
		public int getValue() {
			return val;
		}
	}

	// useless method, called in PIDOutput, shortens code
	private void u(double d) {}
}
