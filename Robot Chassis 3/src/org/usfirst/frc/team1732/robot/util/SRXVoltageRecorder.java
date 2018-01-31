package org.usfirst.frc.team1732.robot.util;

import java.util.Stack;

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
	}
	public void stopRecording() {
		recording = false;
	}
	private void record() {
		voltages.push(talon.getMotorOutputPercent());
	}
	public void update() {
		if (recording) record();
	}
	public double getLastVoltage() {
		return voltages.pop();
	}
	public boolean isFinished() {
		return voltages.empty();
	}
}
