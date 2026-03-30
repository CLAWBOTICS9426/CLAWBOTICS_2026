package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import frc.robot.subsystems.Shooter;

public class ShooterControl extends Command{
    private Shooter shooter;
    public double distanceFromTarget;

    private boolean toggle = false;

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
        double[] speeds = calculateMotorPower(distanceFromTarget);
        shooter.accelerateMotors(speeds[0], speeds[1]);
    });

    public Command accelerateMotorsHardValues = Commands.runOnce(() -> {
        shooter.accelerateMotors(2000, 2000);
    });

    public Command stopMotors = Commands.runOnce(() -> {
        shooter.stop();
    });

    public Command toggleMotors = Commands.runOnce(() -> {
        toggle = !toggle;
        shooter.accelerateMotors(toggle ? 2000 : 0, toggle ? 2000 : 0);
    });

    public Command decreaseHighMotorSpeed = Commands.runOnce(() -> {
        shooter.adjustHighMotorSpeed(1000);
    });

    public Command increaseHighMotorSpeed = Commands.runOnce(() -> {
        shooter.adjustLowMotorSpeed(-1000);
    });

    public Command decreaseLowMotorSpeed = Commands.runOnce(() -> {
        shooter.adjustHighMotorSpeed(1000);
    });

    public Command increaseLowMotorSpeed = Commands.runOnce(() -> {
        shooter.adjustLowMotorSpeed(-1000);
    });

}
