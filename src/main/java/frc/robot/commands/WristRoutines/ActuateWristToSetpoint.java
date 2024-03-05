// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.WristRoutines;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Wrist.WristState;

public class ActuateWristToSetpoint extends Command {

  double setpointWrist = 0;
  double tolerance = 0;
  public ActuateWristToSetpoint(double setpoint, double tolerance) {
    addRequirements(RobotContainer.wrist);
    this.setpointWrist = setpoint;
    this.tolerance = tolerance;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    RobotContainer.wrist.setSetpointWrist(setpointWrist);
    RobotContainer.wrist.setState(WristState.POSITION);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return RobotContainer.wrist.isAtSetpoint(tolerance);
  }
}