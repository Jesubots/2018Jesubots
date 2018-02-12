/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team5809.robot;

import org.usfirst.frc.team5809.lib.drivers.JesubotsButton;

import edu.wpi.first.wpilibj.Joystick;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	// For example to map the left and right motors, you could define the
	// following variables to use with your drivetrain subsystem.
	// public static int leftMotor = 1;
	// public static int rightMotor = 2;

	// If you are using multiple modules, make sure to define both the port
	// number and the module. For example you with a rangefinder:
	// public static int rangefinderPort = 1;
	// public static int rangefinderModule = 1;
	public static final Joystick driverStick = new Joystick(0);
	public static final Joystick operatorStick = new Joystick(1);
	
	public static final double deadBand = .3;

	public static int frontLeftCAN = 1;
	public static int frontRightCAN = 4;
	public static int backLeftCAN = 0;
	public static int backRightCAN = 3;

	public static int jawLeftCAN = 2;
	public static int jawRightCAN = 0;
	public static double openJawsTimeout = 3.0;
	public static double closeJawsTimeout = 3.0;
	public static double defaultJawsPower = .25;
	
	public static int leftLiftCAN = 0;
	public static int rightLiftCAN = 1;
	public static double defaultLiftPower = .5;

	public static double minMotorPower = 0.0;
	public static double tolerancePercent = 2;

	public static int backLeftEncoder = 0;
	public static int backRightEncoder = 0;
	public static int frontLeftEncoder = 0;
	public static int frontRightEncoder = 0;

	public class RotateToAnglePIDMap {
		public static final double kP = 0.026;
		public static final double kI = 0.0026;
		public static final double kD = 0.13;
		public static final double kF = 0.1;
		public static final double kToleranceDegrees = 1.0f;
	}
}