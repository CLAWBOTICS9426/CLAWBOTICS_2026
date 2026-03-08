package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import frc.robot.subsystems.Shooter;

public class ShooterControl extends Command{
    private Shooter shooter;
    public double distanceFromTarget;

    public ShooterControl (Shooter shooter) {
        this.shooter = shooter;
        addRequirements(shooter);
    }

    public void setDistance (double distance) {
        distanceFromTarget = distance;
    }

    public double[] calculateMotorPower (double distance) {
        double baseRPM = 6*distance + 7; // Adjust Equation based on Regression
        double motorRatio = 6*distance + 7; // Adjust Equation based on Regression
        
        double[] results = {baseRPM, baseRPM*motorRatio};
        return new double[]{1, 1};
    }

    public Command accelerateMotors = Commands.runOnce(() -> {
        shooter.accelerateMotors(calculateMotorPower(distanceFromTarget)[0], calculateMotorPower(distanceFromTarget)[1]);
    });

    public Command stopMotors = Commands.runOnce(() -> {
        shooter.accelerateMotors(0, 0);
    });

    public Command decreaseMotorSpeed = Commands.runOnce(() -> {
        shooter.adjustSpeed(-0.05);
    });

    public Command increaseMotorSpeed = Commands.runOnce(() -> {
        shooter.adjustSpeed(0.05);
    });

}
