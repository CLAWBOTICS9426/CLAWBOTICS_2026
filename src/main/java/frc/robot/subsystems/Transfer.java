package frc.robot.subsystems;

import com.revrobotics.PersistMode;
import com.revrobotics.ResetMode;
import com.revrobotics.spark.SparkLowLevel;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.config.SparkMaxConfig;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Transfer extends SubsystemBase {
    private static SparkMax feederMotor;
    private static SparkMaxConfig feederConfig;

    private static SparkMax hopperMotor;
    private static SparkMaxConfig hopperConfig;

    public Transfer () {
        feederMotor = new SparkMax(Constants.TransferMotorPorts.feederPort, SparkLowLevel.MotorType.kBrushless);
        feederConfig = new SparkMaxConfig();
        feederConfig
            .inverted(false) // Adjust if necessary
            .idleMode(IdleMode.kCoast)
            .smartCurrentLimit(15);
        feederMotor.configure(feederConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

        hopperMotor = new SparkMax(Constants.TransferMotorPorts.hopperPort, SparkLowLevel.MotorType.kBrushless);
        hopperConfig = new SparkMaxConfig();
        hopperConfig
            .inverted(false) // Adjust if necessary
            .idleMode(IdleMode.kCoast)
            .smartCurrentLimit(15);    
        hopperMotor.configure(hopperConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);


    }

    public void powerFeeder (double power) {
        feederMotor.set(power);
    }

    public void powerFeeder () {
        feederMotor.set(1);
    }

    public void powerHopper (double power) {
        hopperMotor.set(power);
    }

    public void powerHopper () {
        hopperMotor.set(1);
    }

    public void stopFeeder () {
        feederMotor.set(0);
    }

    public void stopHopper () {
        hopperMotor.set(0);
    }
}
