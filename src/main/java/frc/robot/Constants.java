// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.util.Units;
import frc.robot.util.Elastic.Notification;
import frc.robot.util.Elastic.Notification.NotificationLevel;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide
 * numerical or boolean
 * constants. This class should not be used for any other purpose. All constants
 * should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>
 * It is advised to statically import this class (or one of its inner classes)
 * wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {


    /** A set of constants related to the drivetrain. */
    public static class DriveConstants {

        public static final int frontLeftSwervePort = 1;
        public static final int frontRightSwervePort = 7;
        public static final int backLeftSwervePort = 3;
        public static final int backRightSwervePort = 5;

        public static final int[] swerveMotorPorts = {
            frontLeftSwervePort,
            backLeftSwervePort,
            backRightSwervePort,
            frontRightSwervePort
        };

        public static final int frontLeftDrivePort = 2;
        public static final int frontRightDrivePort = 8;
        public static final int backLeftDrivePort = 4;
        public static final int backRightDrivePort = 6;

        public static final int[] driveMotorPorts = {
            frontLeftDrivePort,
            backLeftDrivePort,
            backRightDrivePort,
            frontRightDrivePort,
        };

        public static final int frontLeftEncoder = 0;
        public static final int frontRightEncoder = 3;
        public static final int backLeftEncoder = 1;
        public static final int backRightEncoder = 2;

        public static final int[] encoders = {
            frontLeftEncoder,
            backLeftEncoder,
            backRightEncoder,
            frontRightEncoder,
        };

        public static final double[] absoluteOffsets = {
            38.67, //67
            76.5,
            281.67, //67
            259.3
        };

        public static final double inchesFromRobotCenterWidth = 13.5;
        public static final double inchesFromRobotCenterHeight = 8;
        public static final double metersFromRobotCenterWidth = 
            Units.inchesToMeters(inchesFromRobotCenterWidth);
        public static final double metersFromRobotCenterHeight = 
            Units.inchesToMeters(inchesFromRobotCenterHeight);

        public static final Translation2d frontLeft = new Translation2d(
            metersFromRobotCenterWidth, metersFromRobotCenterHeight);
        public static final Translation2d frontRight = new Translation2d(
            metersFromRobotCenterWidth, -metersFromRobotCenterHeight);
        public static final Translation2d backLeft = new Translation2d(
            -metersFromRobotCenterWidth, metersFromRobotCenterHeight);
        public static final Translation2d backRight = new Translation2d(
            -metersFromRobotCenterWidth, -metersFromRobotCenterHeight);

        public static final double swerveP = 0.032;
        public static final double swerveI = 0.0;
        public static final double swerveD = 0.015;
        public static final double swerveFF = 0.0;

        public static final double xyP = 1.05;
        public static final double xyI = 0;
        public static final double xyD = 0;

        public static final double turnP = 8.192;
        public static final double turnI = 0.00000;
        public static final double turnD = 0.0016;
        public static final double turnR = 0.02;

        public static final double driveKs = 0.65;
        public static final double driveKv = 3.11;
        public static final double driveKa = 1.05;

        public static final double lockOnP = 0.2;
        public static final double lockOnI = 0.0;
        public static final double lockOnD = 0.00024;
        public static final double lockOnR = 0.02;
    

        public static final SimpleMotorFeedforward[] driveFeedForward = {
            new SimpleMotorFeedforward(driveKs, driveKv, driveKa),
            new SimpleMotorFeedforward(driveKs, driveKv, driveKa),
            new SimpleMotorFeedforward(driveKs, driveKv, driveKa),
            new SimpleMotorFeedforward(driveKs, driveKv, driveKa)
        };


        public static final double highDriveSpeed = 5.9;
        public static final double speedModifier = 2.0;

        public static final double inchesPerRotation = Math.PI * 3.875;
        public static final double metersPerRotation = Units.inchesToMeters(inchesPerRotation);

        public static final double swerveRotationToDegrees = 360.0 / (12.8);
        // TODO: Check this
        public static final double driveMotorToWheel = 1 / 8.14;

        public static final double nosBooster = 5.25;


        public static final Notification encoderError = new Notification(
            NotificationLevel.ERROR,
            "ABSOLUTE ENCODER NOT CONNECTED ON ONE OR MORE MODULES",
            "The absolute encoder for a swerve module is not connected! Please check the debugging tab to see which one."
        );
            
                



    }
    
    public static class IntakeConstants {
        public static final int intakePort = 9;
    }

    public static class TransferConstants {
        public static final int beltPort = 10;
    }

    public static class ShooterConstants {
        public static final int highMotorPort = 12;
        public static final int lowMotorPort = 11;

        public static final double shooterP = 0.0004;
        public static final double shooterI = 0.0;
        public static final double shooterD = 0.0128;
        public static final double shooterF = 0.000;

        public static final double shooterMaxAccel = 2500;

        public static final double neoFreeSpeedRPM = 5676.0;

        public static final int startingHighSpeed = 800;
        public static final int startingLowSpeed = 3000;
    }

    public static class HopperConstants {
        public static int hopperMotorPort = 13;

        public static double hopperSpeed = 0.5;

        public static double extendedPos = 2.1;
    }

    public static class FeederConstants {
        public static final int feederPort = 13;
    }

	public static class OperatorConstants {
		public static final int driverControllerPort = 1;
		public static final int operatorControllerPort = 0;
	}

    public static class VisionConstants {
        public static final double desiredRadius = 1.5; // in meters
    }

}
