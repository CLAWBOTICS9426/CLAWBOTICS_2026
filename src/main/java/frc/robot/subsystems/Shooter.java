package frc.robot.subsystems;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Shooter extends SubsystemBase{
    private static SparkMax highShootMotor;
    private static SparkMaxConfig highMotorConfig;

    private static SparkMax lowShootMotor;
    private static SparkMaxConfig lowMotorConfig;


    public Shooter () {
        highShootMotor = new SparkMax(Constants.ShooterMotorPorts.highMotorPort, MotorType.kBrushless);
        highMotorConfig = new SparkMaxConfig();
        highMotorConfig
            .inverted(false)  // Change if needed
            .idleMode(IdleMode.kBrake)
            .smartCurrentLimit(15);

        lowShootMotor = new SparkMax(Constants.ShooterMotorPorts.lowMotorPort, MotorType.kBrushless);
        lowMotorConfig = new SparkMaxConfig();
        lowMotorConfig
            .inverted(false) // Change if needed
            .idleMode(IdleMode.kBrake)
            .smartCurrentLimit(15);
    }

    public void accelerateMotors (double highPower, double lowPower) { // TODO: Change this to work with Velocity PIDF, not power
        highShootMotor.set(highPower);
        lowShootMotor.set(lowPower);
    }

    public void stop () {
        highShootMotor.set(0);
        lowShootMotor.set(0);
    
    }

    public double getHighMotorVelocity () {
        return highShootMotor.getEncoder().getVelocity();
    }

    public double getLowMotorVelocity () {
        return lowShootMotor.getEncoder().getVelocity();
    }
    
}
