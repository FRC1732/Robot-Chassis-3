package org.usfirst.frc.team1732.robot.util;

import java.util.Stack;

import org.usfirst.frc.team1732.robot.Robot;
import org.usfirst.frc.team1732.robot.sensors.encoders.EncoderReader;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class SRXMomentRecorder {
	private TalonSRX talon;
	private EncoderReader encoder;
	private Stack<Moment> moments;
	private boolean recording = false;

	public SRXMomentRecorder(TalonSRX device, EncoderReader reader) {
		talon = device;
		encoder = reader;
		moments = new Stack<>();
	}
	public void startRecording() {
		moments.clear();
		recording = true;
		new Thread() {
			public void run() {
				while (recording && !Thread.interrupted()) {
					moments.push(new Moment(talon.getMotorOutputPercent(), encoder.getPosition()));
					try {
						// no idea what this does, but it has to be there to make the wait work
						synchronized (this) {
							this.wait((long) Robot.PERIOD_MS);
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
		if (isFinished())
			return 0;
		else {
			Moment pres = moments.pop();
			return pres.voltage + 0.1 * (encoder.getPosition() - pres.encoderPos);
		}
	}
	public boolean isFinished() {
		return moments.empty();
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
