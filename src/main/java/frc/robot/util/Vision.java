package frc.robot.util;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import frc.robot.LimelightHelpers;
import frc.robot.Constants.VisionConstants;
import frc.robot.LimelightHelpers.LimelightResults;

public class Vision {

    public Vision() {

    }


    // TODO: figure out the math for this
    // we may want to use pose estimation vs an absolute position here, could be easier
    // we also need to figure out linear approximation for shooter throttle based on distance error
    public ChassisSpeeds lockToDepot() {
        return new ChassisSpeeds();
    }


    public ChassisSpeeds approachTag(PIDController turnPID, ControllerInput controllerInput) {

        double desiredRadius = 1.5; //meters from tag
        double distanceError = LimelightHelpers.getTA("") - desiredRadius;
        double distanceRotate = LimelightHelpers.getTX("");

        double forward = 0; //maintains radius
        double strafe = controllerInput.getX() * 3; //constant sideways motion
        double rotate = turnPID.calculate(distanceRotate * 0.02, 0); //constant sideways motion
        ; //DONT face the tag

        ChassisSpeeds visionSpeeds =
            new ChassisSpeeds(
                forward,
                strafe,
                rotate
        );


        return visionSpeeds;

    }

}
