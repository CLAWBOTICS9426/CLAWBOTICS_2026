package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.networktables.NetworkTable;

public class Vision extends SubsystemBase{
    public final NetworkTable limelight;

    public boolean setMove = false;

    double forward = 0;
    double strafe = 0;
    double rotate = 0;

    //CommandXboxController controller = new CommandXboxController(0);

    public Vision () {
        limelight = NetworkTableInstance.getDefault().getTable("limelight");
    }

    public boolean hasTag() {
        return limelight.getEntry("tv").getDouble(0) == 1;
        //tv stands for target valid
    }

    public double getZ() {
        double[] pose = limelight.getEntry("botpose_targetspace")
            .getDoubleArray(new double[6]);
        //nets the current position and orientation related to April Tag
        //double has a length 6, for the pose being [X, Y, Z, roll, pitch, yaw]
        return pose.length >= 3 ? pose [2] : 0.0;
        //returns z (third) value of poses
    }

    public double getX() {
        double[] pose = limelight.getEntry("botpose_targetspace")
                .getDoubleArray(new double[6]);
        return pose.length >= 3 ? pose [0] : 0.0;
    }

    public double getYaw() {
        double[] pose = limelight.getEntry("botpose_targetspace")
                .getDoubleArray(new double[6]);
        return pose.length >= 3 ? pose [5] : 0.0;
    }
}
