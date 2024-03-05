// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;


import com.ctre.phoenix6.mechanisms.swerve.SwerveModule.DriveRequestType;
import com.pathplanner.lib.auto.AutoBuilder;
import com.pathplanner.lib.auto.NamedCommands;
import com.ctre.phoenix6.mechanisms.swerve.SwerveRequest;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj.PS4Controller;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.PrintCommand;
import edu.wpi.first.wpilibj2.command.SwerveControllerCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.auto.PPTrajectoryGenerator;
import frc.robot.commands.ActuateIntake;
import frc.robot.commands.ElevatorRoutines.SetElevatorManualOverride;
import frc.robot.commands.WristRoutines.SetWristManualOverride;
import frc.robot.controllers.InterpolatedPS4Gamepad;
import frc.robot.generated.TunerConstants;
import frc.robot.subsystems.Climb;
import frc.robot.subsystems.CommandSwerveDrivetrain;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.Intake;
// import frc.robot.subsystems.Vision;
import frc.robot.subsystems.Wrist;
import frc.robot.subsystems.Shooter;

public class RobotContainer {
  public static final Elevator elevator = new Elevator();
  public static final Intake intake = new Intake();
  // public static final Vision vision = new Vision();
  public static final Wrist wrist = new Wrist();
  public static final Indexer indexer = new Indexer();
  public static final Shooter shooter = new Shooter();
  public static final Climb climb = new Climb();
  public static final CommandSwerveDrivetrain drivetrain = TunerConstants.DriveTrain; // My drivetrain

  private static final SwerveRequest.FieldCentric drive = new SwerveRequest.FieldCentric()
      .withDeadband(Constants.Drive.MaxSpeed * 0.1).withRotationalDeadband(Constants.Drive.MaxAngularRate * 0.1) // 10% Deadband
      .withDriveRequestType(DriveRequestType.OpenLoopVoltage);

  // private static final SwerveRequest.RobotCentric drives = new SwerveRequest.RobotCentric()
  //     .withDeadband(Constants.Drive.MaxSpeed * 0.1).withRotationalDeadband(Constants.Drive.MaxAngularRate * 0.1)
  //     .withDriveRequestType(DriveRequestType.OpenLoopVoltage);

  /*
   * InterpolatedPS4 GamePad Treats Axis Inputs Exponentially Instead Of Linearly
   */
  public static final InterpolatedPS4Gamepad driverPad = new InterpolatedPS4Gamepad(0); // Pilot Joystick
  public static final InterpolatedPS4Gamepad operatorPad = new InterpolatedPS4Gamepad(1); // Co-Pilot Joystick

  /* Driver Buttons */
  private final JoystickButton driverX = new JoystickButton(driverPad, PS4Controller.Button.kCross.value);
  private final JoystickButton driverSquare = new JoystickButton(driverPad, PS4Controller.Button.kSquare.value);
  private final JoystickButton driverTriangle = new JoystickButton(driverPad, PS4Controller.Button.kTriangle.value);
  private final JoystickButton driverCircle = new JoystickButton(driverPad, PS4Controller.Button.kCircle.value);

  private final JoystickButton driverL1 = new JoystickButton(driverPad, PS4Controller.Button.kL1.value);
  private final JoystickButton driverR1 = new JoystickButton(driverPad, PS4Controller.Button.kR1.value);
  private final JoystickButton driverL2 = new JoystickButton(driverPad, PS4Controller.Button.kL2.value);
  private final JoystickButton driverR2 = new JoystickButton(driverPad, PS4Controller.Button.kR2.value);
  private final JoystickButton driverL3 = new JoystickButton(driverPad, PS4Controller.Button.kL3.value);
  private final JoystickButton driverR3 = new JoystickButton(driverPad, PS4Controller.Button.kR3.value);

  private final JoystickButton driverPadButton = new JoystickButton(driverPad, PS4Controller.Button.kTouchpad.value);
  private final JoystickButton driverStart = new JoystickButton(driverPad, PS4Controller.Button.kPS.value);

  private final JoystickButton driverShare = new JoystickButton(driverPad, PS4Controller.Button.kShare.value);
  private final JoystickButton driverOptions = new JoystickButton(driverPad, PS4Controller.Button.kOptions.value);

  /* Operator Buttons */
  private final JoystickButton operatorX = new JoystickButton(operatorPad, PS4Controller.Button.kCross.value);
  private final JoystickButton operatorSquare = new JoystickButton(operatorPad, PS4Controller.Button.kSquare.value);
  private final JoystickButton operatorTriangle = new JoystickButton(operatorPad, PS4Controller.Button.kTriangle.value);
  private final JoystickButton operatorCircle = new JoystickButton(operatorPad, PS4Controller.Button.kCircle.value);

  private final JoystickButton operatorL1 = new JoystickButton(operatorPad, PS4Controller.Button.kL1.value);
  private final JoystickButton operatorR1 = new JoystickButton(operatorPad, PS4Controller.Button.kR1.value);

  private final JoystickButton operatorL2 = new JoystickButton(operatorPad, PS4Controller.Button.kL2.value);
  private final JoystickButton operatorR2 = new JoystickButton(operatorPad, PS4Controller.Button.kR2.value);
  private final JoystickButton operatorR3 = new JoystickButton(operatorPad, PS4Controller.Button.kR3.value);

  private final JoystickButton operatorPadButton = new JoystickButton(operatorPad,
      PS4Controller.Button.kTouchpad.value);
  private final JoystickButton operatorStart = new JoystickButton(operatorPad, PS4Controller.Button.kPS.value);

  private final JoystickButton operatorShare = new JoystickButton(operatorPad, PS4Controller.Button.kShare.value);
  private final JoystickButton operatorOptions = new JoystickButton(operatorPad, PS4Controller.Button.kOptions.value);

  private final SwerveRequest.PointWheelsAt point = new SwerveRequest.PointWheelsAt();

  /* Path follower */
  private final SendableChooser<Command> autoChooser;

  public Command runAuto = drivetrain.getAutoPath("Test");
  // public Command goToCloseRedTrap = drivetrain.getAutoPath("Red Trap Close");
  // public Command goToFarRedTrap = drivetrain.getAutoPath("Red Trap Far");
  // public Command goToRedTrapCenter = drivetrain.getAutoPath("Red Trap Center");

  private final Telemetry logger = new Telemetry(Constants.Drive.MaxSpeed);

  private void configureBindings() {

    drivetrain.setDefaultCommand( // Drivetrain will execute this command periodically
        drivetrain.applyRequest(() -> drive.withVelocityX(-driverPad.getLeftY() * Constants.Drive.MaxSpeed) // Drive forward with
            // Field-Centric Drivetrain // negative Y (forward)
            .withVelocityY(-driverPad.getLeftX() * Constants.Drive.MaxSpeed) // Drive left with negative X (left)
            .withRotationalRate(-driverPad.getRightX() * Constants.Drive.MaxAngularRate) // Drive counterclockwise with negative X
                                                                         // (left)
        ));
    // operatorR3.toggleOnTrue(drivetrain.applyRequest(() -> drives.withVelocityX(-driverPad.getLeftY() * Constants.Drive.MaxSpeed) // Robot-Centric
        //                                                                                                          // Drive
        // .withVelocityY(-driverPad.getLeftX() * Constants.Drive.MaxSpeed)
        // .withRotationalRate(-driverPad.getRightX() * Constants.Drive.MaxAngularRate)));

    driverX.whileTrue(drivetrain
        .applyRequest(() -> point.withModuleDirection(new Rotation2d(-driverPad.getLeftY(), -driverPad.getLeftX()))));

    // // reset the field-centric heading on options press
    driverOptions.onTrue(drivetrain.runOnce(() -> drivetrain.seedFieldRelative()));

    driverCircle.whileTrue(new ActuateIntake());

    drivetrain.registerTelemetry(logger::telemeterize);

    operatorStart.toggleOnTrue(new SetElevatorManualOverride());
    operatorStart.toggleOnTrue(new SetWristManualOverride());
  }

  public RobotContainer() {
    // NamedCommands.registerCommands(PPTrajectoryGenerator.eventMap);
    NamedCommands.registerCommand("Print", new PrintCommand("testing the registered Commands"));
    configureBindings();
    autoChooser = AutoBuilder.buildAutoChooser();
    SmartDashboard.putData("Auto Chooser", autoChooser);
  
  }

  public Command getAutonomousCommand() {
    return autoChooser.getSelected();
  }

 
}
