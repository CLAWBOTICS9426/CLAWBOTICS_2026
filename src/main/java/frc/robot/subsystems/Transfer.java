package frc.robot.subsystems;

import com.revrobotics.PersistMode;
import com.revrobotics.ResetMode;
import com.revrobotics.spark.SparkLowLevel;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.config.SparkMaxConfig;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.TransferConstants;

public class Transfer extends SubsystemBase {

    private static SparkMax hopperMotor;
    private static SparkMaxConfig hopperConfig;

    public Transfer () {

        hopperMotor = new SparkMax(TransferConstants.hopperPort, SparkLowLevel.MotorType.kBrushless);
        hopperConfig = new SparkMaxConfig();
        hopperConfig
            .inverted(true) // Adjust if necessary
            .idleMode(IdleMode.kCoast)
            .smartCurrentLimit(15);    
        hopperMotor.configure(hopperConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);


    }


    public void powerHopper (double power) {
        hopperMotor.set(power);
    }

    public void powerHopper () {
        hopperMotor.set(1);
    }

    public void stopHopper () {
        hopperMotor.set(0);
    }
}
