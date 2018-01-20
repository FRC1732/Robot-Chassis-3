package org.usfirst.frc.team1732.robot.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Grabber extends Subsystem {

	private final Solenoid solenoid;

	public Grabber() {
		solenoid = new Solenoid(4);
	}

	public final boolean GRAB = true;
	public final boolean RELEASE = !GRAB;

	public void grab() {
		solenoid.set(GRAB);
	}

	public void release() {
		solenoid.set(RELEASE);
	}

	@Override
	protected void initDefaultCommand() {
	}

}