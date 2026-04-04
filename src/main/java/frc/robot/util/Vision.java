package frc.robot.util;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import frc.robot.LimelightHelpers;
import frc.robot.Constants.DriveConstants;
import frc.robot.Constants.VisionConstants;
import frc.robot.LimelightHelpers.LimelightResults;

public class Vision {

    public Vision() {

    }

    private final PIDController lockOnPID = new PIDController(
        DriveConstants.lockOnP, DriveConstants.lockOnI, DriveConstants.lockOnD, DriveConstants.lockOnR);    



    // TODO: figure out the math for this
    // we may want to use pose estimation vs an absolute position here, could be easier
    // we also need to figure out linear approximation for shooter throttle based on distance error
    public ChassisSpeeds lockToDepot() {
        return new ChassisSpeeds();
    }


    public ChassisSpeeds approachTag(ControllerInput controllerInput) {

        double desiredRadius = 1.5; //meters from tag
        double distanceError = LimelightHelpers.getTA("") - desiredRadius;
        double distanceRotate = LimelightHelpers.getTX("");

        double forward = 0; //maintains radius
        double strafe = controllerInput.getX() * -3; //constant sideways motion
        double rotate = lockOnPID.calculate(distanceRotate, 0); //constant sideways motion

        //rotate -= (strafe*0.5);

        ChassisSpeeds visionSpeeds =
            new ChassisSpeeds(
                forward,
                strafe,
                rotate
        );


        return visionSpeeds;

    }

    public ChassisSpeeds autoTag() {
        double distanceRotate = LimelightHelpers.getTX("");

        double rotate = lockOnPID.calculate(distanceRotate, 0); //constant sideways motion

        //rotate -= (strafe*0.5);

        ChassisSpeeds visionSpeeds =
            new ChassisSpeeds(
                0,
                0,
                rotate
        );

        return visionSpeeds;
    }
}
