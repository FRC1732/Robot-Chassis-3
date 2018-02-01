package org.usfirst.frc.team1732.robot.util;

import java.util.Stack;

import org.usfirst.frc.team1732.robot.Robot;
import org.usfirst.frc.team1732.robot.sensors.encoders.EncoderReader;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class SRXVoltageRecorder {
	private TalonSRX talon;
	private EncoderReader encoder;
	private Stack<Moment> voltages;
	private boolean recording = false;

	public SRXVoltageRecorder(TalonSRX device, EncoderReader reader) {
		talon = device;
		encoder = reader;
		voltages = new Stack<>();
	}
	public void startRecording() {
		voltages.clear();
		recording = true;
		new Thread() {
			public void run() {
				while (recording && !Thread.interrupted()) {
					voltages.push(new Moment(talon.getMotorOutputPercent(), encoder.getPosition()));
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
		return isFinished() ? 0 : voltages.pop().voltage;
	}
	public boolean isFinished() {
		return voltages.empty();
	}

	private class Moment {
		public final double voltage;
		public final double encoderPos;

		public Moment(double v, double c) {
			voltage = v;
			encoderPos = c;
		}
	}
}
