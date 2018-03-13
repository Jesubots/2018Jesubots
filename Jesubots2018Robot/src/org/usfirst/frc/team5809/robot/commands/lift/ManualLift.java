package org.usfirst.frc.team5809.robot.commands.lift;

import org.usfirst.frc.team5809.robot.Robot;
import org.usfirst.frc.team5809.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ManualLift extends Command {
	
	
	private double power;
	public ManualLift(double input) {
		requires(Robot.lift);
		power = input;
		setTimeout(0.1);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		Robot.lift.moveLift(power);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return isTimedOut();
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.lift.stopLift();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		Robot.lift.stopLift();
	}
}
