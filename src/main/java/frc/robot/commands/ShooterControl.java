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
        double highRpm = 6*distance + 7; // Adjust Equation based on Regression
        double lowRpm = 6*distance + 7; // Adjust Equation based on Regression
        
        double[] results = {highRpm, lowRpm};
        return new double[]{1, 1};
    }

    public Command accelerateMotorsCalculated = Commands.runOnce(() -> {
        shooter.accelerateMotors(calculateMotorPower(distanceFromTarget)[0], calculateMotorPower(distanceFromTarget)[1]);
    });

    public Command accelerateMotorsHardValues = Commands.runOnce(() -> {
        shooter.accelerateMotors(2000, 2000);
    });

    public Command stopMotors = Commands.runOnce(() -> {
        shooter.stop();
    });

    public Command decreaseHighMotorSpeed = Commands.runOnce(() -> {
        shooter.adjustHighMotorSpeed(10);
    });

    public Command increaseHighMotorSpeed = Commands.runOnce(() -> {
        shooter.adjustLowMotorSpeed(-10);
    });

    public Command decreaseLowMotorSpeed = Commands.runOnce(() -> {
        shooter.adjustHighMotorSpeed(10);
    });

    public Command increaseLowMotorSpeed = Commands.runOnce(() -> {
        shooter.adjustLowMotorSpeed(-10);
    });

}
