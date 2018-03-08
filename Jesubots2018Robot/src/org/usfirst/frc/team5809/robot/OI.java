/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team5809.robot;

import org.usfirst.frc.team5809.lib.drivers.JesubotsButton;
import org.usfirst.frc.team5809.lib.drivers.JesubotsButton.LogitechButton;
import org.usfirst.frc.team5809.robot.commands.jaws.SpitJaws;
import org.usfirst.frc.team5809.robot.commands.jaws.StopJaws;
import org.usfirst.frc.team5809.robot.commands.jaws.StopWrist;
import org.usfirst.frc.team5809.robot.commands.jaws.TightenJaws;
import org.usfirst.frc.team5809.robot.commands.lift.MoveLift;
import org.usfirst.frc.team5809.robot.commands.lift.StartWinch;
import org.usfirst.frc.team5809.robot.commands.lift.StopLift;
import org.usfirst.frc.team5809.robot.RobotMap.StartPosition;
import org.usfirst.frc.team5809.robot.RobotMap.eLiftDistance;
import org.usfirst.frc.team5809.robot.commands.jaws.GrabJaws;
import org.usfirst.frc.team5809.robot.commands.jaws.LoosenJaws;
import org.usfirst.frc.team5809.robot.commands.jaws.MoveWrist;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

	public static OI instance;

	public static final Joystick driverStick = new Joystick(0);
	// public static final Joystick operatorStick = new Joystick(1);

	// jaw buttons
	public static JesubotsButton grabJawsButton = new JesubotsButton(OI.driverStick, LogitechButton.B);
	public static JesubotsButton spitJawsButton = new JesubotsButton(OI.driverStick, LogitechButton.A);
	public static JesubotsButton tightenJawsButton = new JesubotsButton(OI.driverStick, LogitechButton.X);
	public static JesubotsButton winchButton = new JesubotsButton(OI.driverStick, LogitechButton.Y);
	public static JesubotsButton wristUpButton = new JesubotsButton(OI.driverStick, LogitechButton.Start);
	public static JesubotsButton wristDownButton = new JesubotsButton(OI.driverStick, LogitechButton.Back);
	// lift buttons
	public static JesubotsButton lowerLiftHalfButton = new JesubotsButton(OI.driverStick, LogitechButton.BumperRight);
	public static JesubotsButton raiseLiftHalfButton = new JesubotsButton(OI.driverStick, LogitechButton.BumperLeft);
	public static JesubotsButton raiseLiftButton = new JesubotsButton(OI.driverStick, LogitechButton.TriggerLeft);
	public static JesubotsButton lowerLiftButton = new JesubotsButton(OI.driverStick, LogitechButton.TriggerRight);

	// private static double driveTime;
	private static Command autoCommand;

	private static double driveMag;
	private static double pivotTurnDegree;
	private static double driveTime;
	private static double encoderPosition;
	private static double side;
	private static boolean jawsTightened = false;
	private static StartPosition startPosition;
	private static eLiftDistance liftHeight = eLiftDistance.kUnknown;
	private static Destination destination = new Destination();
	public static Destination nearSwitch;
	public static Destination scale;
	public static Destination farSwitch;
	public static Destination nearSideOfSwitch;
	public static Destination defaultSwitch = new Destination();;

	public static StartPosition getStartPosition() {
		return startPosition;
	}

	public static void setStartPosition(StartPosition eStartPosition) {
		OI.startPosition = eStartPosition;
	}

	public static double getDriveMag() {
		return driveMag;
	}

	public static void setDriveMag(double driveMag) {
		OI.driveMag = driveMag;
	}

	public static double getPivotTurnDegree() {
		return pivotTurnDegree;
	}

	public static void setPivotTurnDegree(double pivotTurnDegree) {
		OI.pivotTurnDegree = pivotTurnDegree;
	}

	public OI() {
		initButtons();
	}

	public static double getEncoderPosition() {
		return encoderPosition;
	}

	public static void setEncoderPosition(double encoderPosition) {
		OI.encoderPosition = encoderPosition * RobotMap.rotationConstant;
	}

	public static double getDriveTime() {
		return driveTime;
	}

	public static void setDriveTime(double driveTime) {
		OI.driveTime = driveTime;
	}

	public void getPOVDirection() {

	}

	public static void setAutoInfo(String startPosition) {

		// convert string to enum
		StartPosition eStartPosition = StartPosition.valueOf(startPosition);
		setStartPosition(eStartPosition);

		String gameData;
		gameData = OI.getGameState();

		if (gameData.length() > 0) {

			nearSwitch = new Destination(gameData.charAt(0), Destination.eFieldDistance.kNear);
			scale = new Destination(gameData.charAt(1), Destination.eFieldDistance.kMiddle);
			farSwitch = new Destination(gameData.charAt(2), Destination.eFieldDistance.kFar);
			nearSideOfSwitch = new Destination(gameData.charAt(0), Destination.eFieldDistance.kNearSide);

		} else {
			// what if gameData does not exist? = default destinations to
			// unknown
			nearSwitch = new Destination();
			scale = new Destination();
			farSwitch = new Destination();
			nearSideOfSwitch = new Destination();
		}

		if (eStartPosition == StartPosition.LEFT) {
			if (nearSwitch.getFieldSide() == Destination.eFieldSide.kLeft) {
				setDestination(nearSwitch);
				setLiftDistance(eLiftDistance.kHigh);
			} else if (scale.getFieldSide() == Destination.eFieldSide.kLeft) {
				setDestination(scale);
				setLiftDistance(eLiftDistance.kLow);
			} else {
				setDestination(defaultSwitch);
				setLiftDistance(eLiftDistance.kUnknown);
			}
		} else if (eStartPosition == StartPosition.RIGHT) {
			if (nearSwitch.getFieldSide() == Destination.eFieldSide.kRight) {
				setDestination(nearSwitch);
				setLiftDistance(eLiftDistance.kHigh);
			} else if (scale.getFieldSide() == Destination.eFieldSide.kRight) {
				setDestination(scale);
				setLiftDistance(eLiftDistance.kLow);
			} else {
				setDestination(defaultSwitch);
				setLiftDistance(eLiftDistance.kUnknown);
			}
		} else if (eStartPosition == StartPosition.MIDDLE) {
			if (nearSwitch.getFieldSide() == Destination.eFieldSide.kRight) {
				setDestination(nearSideOfSwitch);
				setLiftDistance(eLiftDistance.kHigh);
			} else if (nearSwitch.getFieldSide() == Destination.eFieldSide.kLeft) {
				setDestination(nearSideOfSwitch);
				setLiftDistance(eLiftDistance.kHigh);
			} else {
				setDestination(defaultSwitch);
				setLiftDistance(eLiftDistance.kUnknown);
			}
		} else {
			setDestination(defaultSwitch);
			setLiftDistance(eLiftDistance.kUnknown);
		}

		SmartDashboard.putString("DestinationAuto Info", SmartDashboard.getString("DestinationAuto Info", "")
				+ "startPosition = " + startPosition + "  Destination = " + getDestination().toString());
		System.out.println(
				"SetAutoInfo startPosition = " + startPosition + "  Destination = " + getDestination().toString());
	}

	public static void setDestination(Destination destination) {
		OI.destination = destination;
	}

	public static Destination getDestination() {
		return OI.destination;
	}

	public static Command getGameAuto() {
		return OI.autoCommand;
	}

	public static void setSide(double side) {
		OI.side = side;
	}

	public static double getSide() {
		return OI.side;
	}

	public static void setLiftDistance(eLiftDistance distance) {
		OI.liftHeight = distance;
	}

	public static eLiftDistance getLiftDistance() {
		return OI.liftHeight;
	}

	public static void setJawsTightened() {
		OI.jawsTightened = !OI.jawsTightened;
	}

	public static String getGameState() {
		String gameData;
		gameData = DriverStation.getInstance().getGameSpecificMessage();
		return gameData;
	}

	public void initButtons() {
		OI.grabJawsButton.whenPressed(new GrabJaws());
		OI.spitJawsButton.whenPressed(new SpitJaws());
		OI.raiseLiftButton.whenPressed(new MoveLift(RobotMap.LiftHeightMap.kHighDistance));
		OI.lowerLiftButton.whenPressed(new MoveLift(-RobotMap.LiftHeightMap.kHighDistance));
		OI.raiseLiftHalfButton.whenPressed(new MoveLift(RobotMap.LiftHeightMap.kLowDistance));
		OI.lowerLiftHalfButton.whenPressed(new MoveLift(-RobotMap.LiftHeightMap.kLowDistance));
		OI.wristUpButton.whenPressed(new MoveWrist(RobotMap.defaultWristPower));
		OI.wristDownButton.whenPressed(new MoveWrist(-RobotMap.defaultWristPower));

		OI.winchButton.whileHeld(new StartWinch());

		if (jawsTightened) {
			OI.tightenJawsButton.whenPressed(new LoosenJaws());
		} else {
			OI.tightenJawsButton.whenPressed(new TightenJaws());
		}

		OI.wristUpButton.whenReleased(new StopWrist());
		OI.wristUpButton.whenReleased(new StopWrist());
		OI.grabJawsButton.whenReleased(new StopJaws());
		OI.spitJawsButton.whenReleased(new StopJaws());

	}
}
