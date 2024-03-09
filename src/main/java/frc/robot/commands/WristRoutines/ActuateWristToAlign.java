// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.WristRoutines;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Wrist.WristState;

public class ActuateWristToAlign extends Command {
  /** Creates a new ActuateElevatorIdle. */
  double toleranceWrist = 0;
  public ActuateWristToAlign(double toleranceWrist) {
    addRequirements(RobotContainer.wrist); 
    this.toleranceWrist = toleranceWrist;
   }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    RobotContainer.wrist.setState(WristState.ALIGN);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return RobotContainer.wrist.isAtSetpoint(toleranceWrist);
  }
}
