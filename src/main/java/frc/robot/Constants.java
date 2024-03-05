package frc.robot;

import edu.wpi.first.math.Matrix;
import edu.wpi.first.math.VecBuilder;
import edu.wpi.first.math.geometry.Rotation3d;
import edu.wpi.first.math.geometry.Transform3d;
import edu.wpi.first.math.geometry.Translation3d;
import edu.wpi.first.math.numbers.N1;
import edu.wpi.first.math.numbers.N3;
import edu.wpi.first.math.util.Units;
import frc.robot.generated.TunerConstants;

public class Constants {
	public static final class Ports {
		public static final class Drive_Ports {
			public static final int FL_DRIVE_MOTOR_ID = 0;
			public static final int FL_STEER_MOTOR_ID = 1;
			public static final int FL_ENCODER_ID = 2;

			public static final int FR_DRIVE_MOTOR_ID = 3;
			public static final int FR_STEER_MOTOR_ID = 4;
			public static final int FR_ENCODER_ID = 5;

			public static final int BL_DRIVE_MOTOR_ID = 6;
			public static final int BL_STEER_MOTOR_ID = 7;
			public static final int BL_ENCODER_ID = 8;

			public static final int BR_DRIVE_MOTOR_ID = 9;
			public static final int BR_STEER_MOTOR_ID = 10;
			public static final int BR_ENCODER_ID = 11;
			public static final int PIGEON_ID = 12;
		}

		public static final int INTAKE = 13;
		public static final int INDEXER = 14;
		public static final int BEAM_BREAK = 8;
		public static final int WRIST = 16;
		public static final int SHOOTER_LEFT = 17;
		public static final int SHOOTER_RIGHT = 18;
		public static final int ELEVATOR_A = 19;
		// public static final int ELEVATOR_B = 20;
		public static final int LEFT_CLIMB = 21;
		public static final int RIGHT_CLIMB = 22;
		public static final int CANdleID = 23;
	}

	public static final class Drive {
		public static double MaxAngularRate = 1.5 * Math.PI; // 3/4 of a rotation per second max angular velocity
		public static double MaxSpeed = TunerConstants.kSpeedAt12VoltsMps; // 6 meters per second desired top speed

		public static final double TRACK = Units.inchesToMeters(21.50);
		// Radians
		public static final double FL_ENCODER_OFFSET = 0.322265625;
		public static final double FR_ENCODER_OFFSET = -0.0185546875;
		public static final double BL_ENCODER_OFFSET = 0.19140625;
		public static final double BR_ENCODER_OFFSET = -0.423583984375;

		public static final double DRIVE_GEAR_RATIO = 6.122448979591837;
		public static final double STEER_GEAR_RATIO = 12.8;
		public static final double WHEEL_RADIUS = 2.0; // Inches
		public static final double SPEED_AT_12_VOLTS = 5.21; // Meters Per Second
		public static final double COUPLE_RATIO = 3.5714285714285716;
		public static final double SLIP_CURRENT = 300.0; // Amps
	}

	public static final class Elevator {
		// Elevator PID //
		public static final double ELEVATOR_KP = 0.0;
		public static final double ELEVATOR_KI = 0.0;
		public static final double ELEVATOR_KD = 0.0;

		// Elevator FF //
		public static final double ELEVATOR_KS = 0.0;
		public static final double ELEVATOR_KG = 0.0;
		public static final double ELEVATOR_KV = 0.0;
		public static final double ELEVATOR_MAX_VEL = 0;
		public static final double ELEVATOR_MAX_ACCEL = 0;
		public static final double ELEVATOR_TO_AMP = 0;
	}

	public static final class Shooter {
		public static final double SHOOTER_KP = 0.9;
		public static final double SHOOTER_KI = 0.0;
		public static final double SHOOTER_KD = 0.3;

	}

	public static final class Vision {
		public static final Transform3d shootCameraToRobot = new Transform3d(
				new Translation3d(Units.inchesToMeters(8.8), Units.inchesToMeters(11.2), Units.inchesToMeters(6.8)),
				new Rotation3d(Units.degreesToRadians(5), Units.degreesToRadians(65), 0));

		public static final Matrix<N3, N1> kDefaultStdDevs = VecBuilder.fill(0.9, 0.9, 0.9);

		// public static final double X_P = 2.7;
		// public static final double X_I = 0.0;
		// public static final double X_D = 0;

		// public static final double Y_P = 2.7;
		// public static final double Y_I = 0.0;
		// public static final double Y_D = 0;

		// public static final double THETA_P = 2;
		// public static final double THETA_I = 0.0;
		// public static final double THETA_D = 0;

		// public static final double X_TOLLERENCE = 0.01;
		// public static final double Y_TOLLERENCE = 0.02;
		// public static final double THETA_TOLLERENCE = 0.02;

		public static final double FIELD_LENGTH_METERS = 16.54175;
		public static final double FIELD_WIDTH_METERS = 8.0137;

	}

	public static final class Climb {

		public static final double CLIMB_KP = 0;
		public static final double CLIMB_KI = 0;
		public static final double CLIMB_KD = 0;
		public static final float FORWARD_SOFT_LIMIT = 0;
		public static final float REVERSE_SOFT_LIMIT = 0;
		public static final double CLIMB_KFF = 0;
	}

	public static final class Wrist {
		public static final double WRIST_KP = 0.000;
		public static final double WRIST_KI = 0;
		public static final double WRIST_KD = 0;
		public static final double WRIST_KS = 0.000;
		public static final double WRIST_KG = 0.000;
		public static final double WRIST_KV = 0;

		public static final double WRIST_GEAR_RATIO = 0;
		public static final float Forward_Limit = 53;
		public static final float Reverse_Limit = -28;
		public static final double K_P = 0;
		public static final double K_I = 0;
		public static final double K_D = 0;
		public static final double K_FF = 0;
		public static final double WRIST_TO_AMP = 0;
	}

}
