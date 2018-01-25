/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team1732.robot.subsystems;

import org.usfirst.frc.team1732.robot.commands.RyanDrive;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Drivetrain extends Subsystem {
	private final TalonSRX leftMaster = new TalonSRX(14);
	private final TalonSRX left1 = new TalonSRX(11);
	private final TalonSRX left2 = new TalonSRX(13);
	private final TalonSRX rightMaster = new TalonSRX(22);
	private final TalonSRX right1 = new TalonSRX(23);
	private final TalonSRX right2 = new TalonSRX(21);

	public Drivetrain() {
		initTalons();
	}
	public void initTalons() {
		leftMaster.setInverted(true);
		left1.follow(leftMaster);
		left2.follow(leftMaster);
		rightMaster.setInverted(true);
		right1.follow(rightMaster);
		right2.follow(rightMaster);
	}
	public void initDefaultCommand() {
		setDefaultCommand(new RyanDrive());
	}
	public void setLeftMotors(double val) {
		leftMaster.set(ControlMode.PercentOutput, 0.6 * constrain(-val, -1, 1));
	}
	public void setRightMotors(double val) {
		rightMaster.set(ControlMode.PercentOutput, 0.6 * constrain(val, -1, 1));
	}
	public void setAllStop() {
		setLeftMotors(0);
		setRightMotors(0);
	}
	public double constrain(double n, double min, double max) {
		return (n < min ? min : (n > max ? max : n));
	}
}
