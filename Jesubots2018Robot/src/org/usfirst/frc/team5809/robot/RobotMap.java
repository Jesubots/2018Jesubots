/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team5809.robot;

import java.util.Arrays;

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

	public static final double deadBand = .3;

	public static int frontLeftCAN = 5;
	public static int frontRightCAN = 7;
	public static int backLeftCAN = 0;
	public static int backRightCAN = 1;
	public static int midLeftCAN = 4;
	public static int midRightCAN = 3;

	public static int jawVictor = 0;
	public static int wristVictor = 1;
	public static double openJawsTimeout = 3.0;
	public static double closeJawsTimeout = 3.0;
	public static double stopJawsTimeout = .01;
	public static double defaultGrabJawsPower = .5;
	public static double defaultSpitJawsPower = .75;
	public static double defaultWristPower = .75;

	public static int leftLiftCAN = 2;
	public static int rightLiftCAN = 6;
	public static int winchVictor = 0;
	public static double defaultLiftTimeout = 5.0;
	public static double defaultWinchPower = .5;
	public static double defaultLiftPower = .5;

	public static double minMotorPower = 0.0;
	public static double tolerancePercent = 2;

	public static double defaultDriveMag = 0.5;
	public static double defaultDriveDistanceValue = 0;
	public static double defaultDriveTimeValue = 0;
	public static double defaultPivotTurn = 90.0;

	public static double gearRatio = 1.8125;
	public static double rotationConstant = 4096.0;

	public static double defaultEncoderDistance = 0.0;

	public static int defaultAutoSide = 1;
	public static double autoDistanceDifference = 47786.0;

	public static enum eLiftDistance {
		kHigh, kLow, kUnknown
	};

	public class PivotTurnPIDMap {
		public static final double kP = 0.028;
		public static final double kI = 0.0;
		public static final double kD = 0.52;
		public static final double kF = 0.0;
		public static final double kToleranceDegrees = 1.0f;

	}

	public class DriveStraightPIDMap {
		public static final double kP = 0.023;
		public static final double kI = 0.0026;
		public static final double kD = 0.25;
		public static final double kF = 0.1;
		public static final double kToleranceDegrees = 1.0f;
	}

	public class DriveEncoderPIDMap { // .028, .0025, .15, .1
		public static final double kP = 0.000042; // 0.0000468
		public static final double kI = 0.00000016; // 0.00000468
		public static final double kD = 0.00015; // 0.000234
		public static final double kF = 0.0;
		public static final double kToleranceDegrees = 1.0f;
	}

	public static class EncoderDistanceMap {
		public static final double kUnknownDistance = 0.0;
		public static final double kNearDistance = 101034.0;
		public static final double kMiddleDistance = 207530.0;
		public static final double kFarDistance = 30000.0;
		public static final double kNearSide = 27306.0;
		public static final double kNearSideSeg1 = 27306.0;
		public static final double kNearSideSeg2 = 27306.0 - 47786.0; // public
																		// static
																		// double
																		// autoDistanceDifference
																		// =
																		// 47786.0;
		public static final double kNearSideSeg3 = 2.0 * 27306.0;
	}

	public static class LiftHeightMap {
		public static final double kHighDistance = 8000.0;
		public static final double kLowDistance = 4000.0;
		public static final double kUnknownDistance = 0.0;
	}

	public enum StartPosition {
		LEFT, MIDDLE, RIGHT;

		public static String[] names() {
			String valueStr = Arrays.toString(StartPosition.values());
			return valueStr.substring(1, valueStr.length() - 1).replace(" ", "").split(",");

		}
	}
}
