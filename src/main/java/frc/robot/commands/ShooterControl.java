package frc.robot.commands;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.util.sendable.SendableBuilder;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import frc.robot.Constants.ShooterConstants;
import frc.robot.LimelightHelpers;
import frc.robot.subsystems.Shooter;

public class ShooterControl extends Command{
    private Shooter shooter;
    public double distanceFromTarget;

    private boolean toggle = false;

    public ShooterControl (Shooter shooter) {
        this.shooter = shooter;
        addRequirements(shooter);
    }

    
    NetworkTable table = NetworkTableInstance.getDefault().getTable("");
    NetworkTableEntry tableTy = table.getEntry("ty");

   

    // distance from the center of the Limelight lens to the floor
    double limelightLensHeightInches = 57.0; 

    // distance from the target to the floor
    double goalHeightInches = 60.0; 

    double angleToGoalDegrees = tableTy.getDouble(0.0); //should add angle of limelight, but it is flat on wall
    double angleToGoalRadians = angleToGoalDegrees * (3.14159 / 180.0);

    //calculate distance
    public double distanceFromLimelightToGoalInches = (goalHeightInches - limelightLensHeightInches) / Math.tan(angleToGoalRadians);
    public double distanceFromLimelightToGoalFeet = distanceFromLimelightToGoalInches/12;

    public double distanceAdjusted () {
        return angleToGoalDegrees;
        /*if (distanceFromLimelightToGoalFeet != 0) {
            return angleToGoalDegrees;
            
        } else {
            return 0;
            //System.out.println("distance 0");
        }*/
        
    }
    
 

    double lowMotorSpeedAdjustment = /*200 */ distanceAdjusted(); //how much increases per foot

    public void setDistance (double distance) {
        distanceFromTarget = distance;
    }

    public double[] calculateMotorPower (double distance) {
        double highRpm = 6*distance + 7; // Adjust Equation based on Regression
        double lowRpm = 6*distance + 7; // Adjust Equation based on Regression
        
        double[] results = {highRpm, lowRpm};
        return new double[]{1, 1};
    }

    public Command accelerateMotorsCalculated = Commands.runOnce(() -> {
        double[] speeds = calculateMotorPower(distanceFromTarget);
        shooter.accelerateMotors(speeds[0], speeds[1]);
    });

    public Command accelerateMotorsHardValues = Commands.runOnce(() -> {
        shooter.accelerateMotors(ShooterConstants.startingHighSpeed, ShooterConstants.startingLowSpeed  );
    });

    public Command accelerateMotorLimelight = Commands.runOnce(() -> {
        System.out.println(distanceAdjusted());
        shooter.accelerateMotors(800, 0 + lowMotorSpeedAdjustment);
    });

    public Command stopMotors = Commands.runOnce(() -> {
        shooter.stop();
    });

    public Command toggleMotors = Commands.runOnce(() -> {
        toggle = !toggle;
        shooter.accelerateMotors(toggle ? ShooterConstants.startingHighSpeed : 0, toggle ? ShooterConstants.startingLowSpeed : 0);
    });

    public Command decreaseHighMotorSpeed = Commands.runOnce(() -> {
        shooter.adjustHighMotorSpeed(-50);
    });

    public Command increaseHighMotorSpeed = Commands.runOnce(() -> {
        shooter.adjustHighMotorSpeed(50);
    });

    public Command decreaseLowMotorSpeed = Commands.runOnce(() -> {
        shooter.adjustLowMotorSpeed(-50);
    });

    public Command increaseLowMotorSpeed = Commands.runOnce(() -> {
        shooter.adjustLowMotorSpeed(50);
    });

}
