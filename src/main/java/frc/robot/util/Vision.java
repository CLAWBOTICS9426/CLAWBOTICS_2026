package frc.robot.util;

import edu.wpi.first.math.kinematics.ChassisSpeeds;
import frc.robot.LimelightHelpers;
import frc.robot.Constants.VisionConstants;

public class Vision {

    public Vision() {

    }


    // TODO: figure out the math for this
    // we may want to use pose estimation vs an absolute position here, could be easier
    // we also need to figure out linear approximation for shooter throttle based on distance error
    public ChassisSpeeds lockToDepot() {
        return new ChassisSpeeds();
    }


    public ChassisSpeeds approachTag() {

        double desiredRadius = 1.5; //meters from tag
        double distanceError = LimelightHelpers.getTA("") - desiredRadius;
        double distanceStrafe = LimelightHelpers.getTX("");

        double forward = -distanceError * 1.2; //maintains radius
        double strafe = distanceStrafe * -35; //constant sideways motion
        double rotate = 0; //DONT face the tag

        ChassisSpeeds visionSpeeds =
            new ChassisSpeeds(
                forward,
                strafe,
                rotate
        );


        return visionSpeeds;

    }

}
