package frc.robot.util;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import frc.robot.LimelightHelpers;
import frc.robot.Constants.DriveConstants;

public class Vision {

    public static HashSet<Integer> tagIDs = new HashSet<Integer>();

    public Vision() {

        // TODO put this in constants
        tagIDs.add(10);
        tagIDs.add(5);
        tagIDs.add(18);
        tagIDs.add(26);
        tagIDs.add(21);
        tagIDs.add(2);

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

        LimelightHelpers.LimelightResults results = LimelightHelpers.getLatestResults("");
        LimelightHelpers.LimelightTarget_Fiducial[] tags = results.targets_Fiducials;

        ChassisSpeeds visionSpeeds = new ChassisSpeeds();

        for (LimelightHelpers.LimelightTarget_Fiducial tag : tags) {

            int id = (int) tag.fiducialID;
            if (!tagIDs.contains(id)) continue;

            double desiredRadius = 1.5; //meters from tag
            double distanceError = tag.ta - desiredRadius;
            double distanceRotate = tag.tx;

            double forward = 0; //maintains radius
            double strafe = controllerInput.getX() * -3; //constant sideways motion
            double rotate = lockOnPID.calculate(distanceRotate, 0); //constant sideways motion

            //rotate -= (strafe*0.5);

            visionSpeeds =
                new ChassisSpeeds(
                    forward,
                    strafe,
                    rotate
            );
            break;
        }

       


        return visionSpeeds;

    }

    public ChassisSpeeds autoTag() {

        LimelightHelpers.LimelightResults results = LimelightHelpers.getLatestResults("");
        LimelightHelpers.LimelightTarget_Fiducial[] tags = results.targets_Fiducials;

        ChassisSpeeds visionSpeeds = new ChassisSpeeds();

        for (LimelightHelpers.LimelightTarget_Fiducial tag : tags) {

            int id = (int) tag.fiducialID;
            if (!tagIDs.contains(id)) continue;

            double distanceRotate = tag.tx;

            double rotate = lockOnPID.calculate(distanceRotate, 0); //constant sideways motion

            //rotate -= (strafe*0.5);

            visionSpeeds =
                new ChassisSpeeds(
                    0,
                    0,
                    rotate
            );
            break;
        }
        return visionSpeeds;
    }
}
