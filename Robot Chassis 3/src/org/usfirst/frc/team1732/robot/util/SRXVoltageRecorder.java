package org.usfirst.frc.team1732.robot.util;

import java.util.Stack;

import org.usfirst.frc.team1732.robot.Robot;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class SRXVoltageRecorder {
	private TalonSRX talon;
	private Stack<Double> voltages;
	private boolean recording = false;

	public SRXVoltageRecorder(TalonSRX device) {
		talon = device;
		voltages = new Stack<>();
	}
	public void startRecording() {
		voltages.clear();
		recording = true;
		new Thread() {
			public void run() {
				while (recording && !Thread.interrupted()) {
					voltages.push(talon.getMotorOutputPercent());
					try {
						// no idea what this does, but it has to be there to make the wait work
						synchronized (this) {
							this.wait((long) (Robot.DEFAULT_PERIOD * 1000));
						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}.start();
	}
	public void stopRecording() {
		recording = false;
	}
	public double getLastVoltage() {
		return voltages.empty() ? 0 : voltages.pop();
	}
	public boolean isFinished() {
		return voltages.empty();
	}

	private class Moment {
		double voltage;
	}
}
