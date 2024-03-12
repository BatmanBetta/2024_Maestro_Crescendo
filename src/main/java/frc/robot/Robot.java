// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.ctre.phoenix6.SignalLogger;
import com.ctre.phoenix6.configs.CurrentLimitsConfigs;
import com.pathplanner.lib.pathfinding.LocalADStar;
import com.pathplanner.lib.pathfinding.Pathfinding;

import edu.wpi.first.math.geometry.Pose3d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.RunCommand;
import frc.robot.commands.ActuateIntake;
import frc.robot.subsystems.Drive;
import frc.robot.subsystems.Elevator.ElevatorState;
import frc.robot.subsystems.Indexer.IndexerState;
import frc.robot.subsystems.Intake.IntakeState;
// import frc.robot.subsystems.Orchestrator.OrchestratorState;
import frc.robot.subsystems.Shooter.ShooterState;
import frc.robot.subsystems.Vision;
import frc.robot.subsystems.Wrist.WristState;
import frc.util.InterpolatingDouble;

public class Robot extends TimedRobot {
  private Command autoCommand;

  private static RobotContainer robotContainer;

  private final SendableChooser<Command> autoChooser = new SendableChooser<Command>();

  CurrentLimitsConfigs configs = new CurrentLimitsConfigs().withStatorCurrentLimit(50);
  public Robot() {
    // addPeriodic(() -> {
    // }, 0.1);
    // addPeriodic(()->{
    //   if (RobotContainer.vision.resultLeftShooter != null){
    //     Pose3d photonPose =
    //   RobotContainer.vision.photonPoseEstimatorLeftShoot.getReferencePose();
    //   RobotContainer.drivetrain.addVisionMeasurement(photonPose.toPose2d(),
    //   Timer.getFPGATimestamp());}
    //   else if(RobotContainer.vision.resultRightShooter != null){
    //     Pose3d photonPose = RobotContainer.vision.photonPoseEstimatorRightShoot.getReferencePose();
    //     RobotContainer.drivetrain.addVisionMeasurement(photonPose.toPose2d(), Timer.getFPGATimestamp());
    //   }
    // },.140);
}


  @Override
  public void robotInit() {
    // Pathfinding.setPathfinder(new LocalADStar());
    robotContainer = new RobotContainer();

    // PortForwarder.add(1181, "photonvision.local", 1182);
    // PortForwarder.add(1183, "photonvision.local", 1184);
    RobotContainer.drivetrain.getDaqThread().setThreadPriority(99);
    SmartDashboard.putData("Auto Selector", autoChooser);
    for (int module = 0; module < 3; module++) {
      RobotContainer.drivetrain.getModule(module).getDriveMotor().getConfigurator().apply(configs);
  }
   
  }

  @Override
  public void robotPeriodic() {
    CommandScheduler.getInstance().run();
   
  }

  @Override
  public void disabledInit() {
    SignalLogger.stop();
    RobotContainer.shooter.setState(ShooterState.IDLE);
    RobotContainer.indexer.setState(IndexerState.IDLE);
    RobotContainer.intake.setState(IntakeState.IDLE);
    RobotContainer.elevator.setState(ElevatorState.IDLE);
    // RobotContainer.orchestrator.setState(OrchestratorState.SILENT);
  }

  @Override
  public void disabledPeriodic() {

  }

  @Override
  public void disabledExit() {
  }

  @Override
  public void autonomousInit() {
    SignalLogger.start();
    autoCommand = robotContainer.getAutonomousCommand();
    RobotContainer.drivetrain.setOperatorPerspectiveForward(
        Rotation2d.fromDegrees(DriverStation.getAlliance().orElse(Alliance.Red) == Alliance.Red ? 0 : 180));

    if (autoCommand != null) {
      autoCommand.schedule();
    }
  }

  @Override
  public void autonomousPeriodic() {

  }

  @Override
  public void autonomousExit() {
  }

  @Override
  public void teleopInit() {
    if (autoCommand != null) {
      autoCommand.cancel();
    }
    RobotContainer.shooter.setState(ShooterState.IDLE);
    RobotContainer.indexer.setState(IndexerState.IDLE);
    RobotContainer.intake.setState(IntakeState.IDLE);
    RobotContainer.wrist.setState(WristState.ALIGN);
  }

  @Override
  public void teleopPeriodic() {
    // Pose3d llPose =
    // RobotContainer.vision.photonPoseEstimatorshoot.getReferencePose();
    // RobotContainer.drivetrain.addVisionMeasurement(llPose.toPose2d(),
    // kDefaultPeriod, Constants.Vision.kDefaultStdDevs);

    // if (RobotContainer.driverPad.getTriangleButton()){
    // RobotContainer.intake.setState(IntakeState.INTAKING);
    // };
    // RobotContainer.orchestrator.setState(OrchestratorState.MARIOTIME);
    // System.out.println(RobotContainer.driverPad.getRightX());

    // if (RobotContainer.driverPad.getCrossButton()){
    // // RobotContainer.intake.setState(IntakeState.INTAKING);
    // RobotContainer.indexer.setState(IndexerState.SHOOTING);
    // }
    // if(RobotContainer.indexer.getNoteDetected()){
    // // RobotContainer.intake.setState(IntakeState.IDLE);
    // RobotContainer.indexer.setState(IndexerState.IDLE);
    // }
    // if (RobotContainer.driverPad.getCircleButton()){
    // RobotContainer.indexer.setState(IndexerState.INDEXING);
    // }
    // if (Pathfinding.isNewPathAvailable()){
    // Pathfinding.setStartPosition(RobotContainer.drivetrain.getState().Pose.getTranslation());
    // Pathfinding.setGoalPosition(pose);
    // }
    // RobotContainer.drivetrain.redAmpFPH.onlyWhile(() ->
    // RobotContainer.driverPad.getCircleButton());
    // RobotContainer.drivetrain.redTrapClose.onlyWhile(() ->
    // RobotContainer.driverPad.getTriangleButton());

    // if(RobotContainer.driverPad.getSquareButton()){
    // RobotContainer.intake.setState(IntakeState.IDLE);
    // }

  }

  @Override
  public void teleopExit() {
  }

  @Override
  public void testInit() {
    CommandScheduler.getInstance().cancelAll();
  }

  @Override
  public void testPeriodic() {
  }

  @Override
  public void testExit() {
  }

  @Override
  public void simulationPeriodic() {
  }
}
