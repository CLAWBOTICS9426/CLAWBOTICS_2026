package frc.robot.subsystems;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.PersistMode;
import com.revrobotics.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ShooterConstants;

public class Shooter extends SubsystemBase{
    private static SparkMax highShootMotor;
    private static SparkMaxConfig highMotorConfig;

    private static SparkMax lowShootMotor;
    private static SparkMaxConfig lowMotorConfig;

    private double speedChanger = 0.6;


    public Shooter () {
        highShootMotor = new SparkMax(ShooterConstants.highMotorPort, MotorType.kBrushless);
        highMotorConfig = new SparkMaxConfig();
        highMotorConfig
            .inverted(false)  // Change if needed
            .idleMode(IdleMode.kCoast)
            .smartCurrentLimit(35);

        lowShootMotor = new SparkMax(ShooterConstants.lowMotorPort, MotorType.kBrushless);
        lowMotorConfig = new SparkMaxConfig();
        lowMotorConfig
            .inverted(true) // Change if needed
            .idleMode(IdleMode.kCoast)
            .smartCurrentLimit(35);

        highShootMotor.configure(highMotorConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
        lowShootMotor.configure(lowMotorConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    }

    public void accelerateMotors (double highPower, double lowPower) { // TODO: Change this to work with Velocity PIDF, not power
        highShootMotor.set(highPower * speedChanger);
        lowShootMotor.set(lowPower * speedChanger);
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

    public void adjustSpeed (double adjustmentValueRPM) {
        speedChanger += adjustmentValueRPM;
    }
    
}
