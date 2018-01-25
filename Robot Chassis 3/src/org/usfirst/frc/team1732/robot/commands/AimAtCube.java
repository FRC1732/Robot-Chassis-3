package org.usfirst.frc.team1732.robot.commands;

import static org.usfirst.frc.team1732.robot.Robot.drivetrain;
import static org.usfirst.frc.team1732.robot.Robot.limelight;

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
		requires(drivetrain);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		p = new PIDController(0.7, 0, 0.5, new PIDSource() {
			public void setPIDSourceType(PIDSourceType pidSource) {}
			public PIDSourceType getPIDSourceType() {
				return PIDSourceType.kDisplacement;
			}
			public double pidGet() {
				return limelight.hasValidTargets() ? limelight.getNormalizedHorizontalOffset() : 1;
			}
		}, output -> {
			// Robot.drivetrain.setLeftMotors(output);
			// Robot.drivetrain.setRightMotors(-output);
		}, 0.02);
		p.setAbsoluteTolerance(0.1);
		p.enable();
		q = new PIDController(0.5, 0, 3, new PIDSource() {
			public void setPIDSourceType(PIDSourceType pidSource) {}
			public PIDSourceType getPIDSourceType() {
				return PIDSourceType.kDisplacement;
			}
			public double pidGet() {
				return limelight.hasValidTargets() ? limelight.getDistanceToTarget() - 10 : 0;
			}
		}, output -> {
			// drivetrain.setLeftMotors(output);
			// drivetrain.setRightMotors(-output);
		}, 0.02);
		q.setAbsoluteTolerance(15);
		q.enable();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		drivetrain.setLeftMotors(p.get() + q.get());
		drivetrain.setRightMotors(-p.get() + q.get());
		System.out.println(limelight.getDistanceToTarget());
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return p.onTarget() && q.onTarget();
	}

	// Called once after isFinished returns true
	protected void end() {
		p.disable();
		q.disable();
		drivetrain.setAllStop();
		System.out.println("TurnAtCube done");
	}
}
