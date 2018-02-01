/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team1732.robot.subsystems;

import org.usfirst.frc.team1732.robot.commands.drive.RyanDrive;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Drivetrain extends Subsystem {
	private final TalonSRX leftMaster = new TalonSRX(14), left1 = new TalonSRX(11), left2 = new TalonSRX(13);
	private final TalonSRX rightMaster = new TalonSRX(22), right1 = new TalonSRX(23), right2 = new TalonSRX(21);

	public Drivetrain() {
		initTalons();
		initEncoders();
	}
	private void initTalons() {
		leftMaster.setInverted(true);
		left1.follow(leftMaster);
		left2.follow(leftMaster);
		rightMaster.setInverted(true);
		right1.follow(rightMaster);
		right2.follow(rightMaster);
	}
	private void initEncoders() {

	}
	public void initDefaultCommand() {
		setDefaultCommand(new RyanDrive());
	}
	public void setLeftMotors(double val) {
		setLeftMotorsRaw(0.6 * constrain(val, -1, 1));
	}
	public void setLeftMotorsRaw(double val) {
		leftMaster.set(ControlMode.PercentOutput, -val);
	}
	public void setRightMotors(double val) {
		setRightMotorsRaw(0.6 * constrain(val, -1, 1));
	}
	public void setRightMotorsRaw(double val) {
		rightMaster.set(ControlMode.PercentOutput, val);
	}
	public void setMotors(double left, double right) {
		setLeftMotors(left);
		setRightMotors(right);
	}
	public void setMotorsRaw(double left, double right) {
		setLeftMotorsRaw(left);
		setRightMotorsRaw(right);
	}
	public void setMotors(double val) {
		setMotors(val, val);
	}
	public void setAllStop() {
		setMotors(0);
	}
	public double constrain(double n, double min, double max) {
		return (n < min ? min : (n > max ? max : n));
	}
	public TalonSRX getLeftTalon() {
		return leftMaster;
	}
	public TalonSRX getRightTalon() {
		return rightMaster;
	}
	public void setBrakeMode(boolean enabled) {
		NeutralMode mode = enabled ? NeutralMode.Brake : NeutralMode.Coast;
		leftMaster.setNeutralMode(mode);
		left1.setNeutralMode(mode);
		left2.setNeutralMode(mode);
		rightMaster.setNeutralMode(mode);
		right1.setNeutralMode(mode);
		right2.setNeutralMode(mode);
	}
}
