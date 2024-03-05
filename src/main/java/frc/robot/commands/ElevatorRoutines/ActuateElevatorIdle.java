// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.ElevatorRoutines;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Elevator.ElevatorState;

public class ActuateElevatorIdle extends Command {
  /** Creates a new ActuateElevatorIdle. */
  double tolerance = 0;
  public ActuateElevatorIdle(double tolerance) {
    addRequirements(RobotContainer.elevator); 
    this.tolerance = tolerance;
   }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    RobotContainer.elevator.setState(ElevatorState.IDLE);
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
    return RobotContainer.elevator.isAtSetpoint(tolerance);
  }
}