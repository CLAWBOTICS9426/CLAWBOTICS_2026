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

    private static SparkMax beltMotor;
    private static SparkMaxConfig beltConfig;

    public Transfer () {

        beltMotor = new SparkMax(TransferConstants.beltPort, SparkLowLevel.MotorType.kBrushless);
        beltConfig = new SparkMaxConfig();
        beltConfig
            .inverted(false) // Adjust if necessary
            .idleMode(IdleMode.kCoast)
            .smartCurrentLimit(15);    
        beltMotor.configure(beltConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);


    }


    public void powerBelt (double power) {
        beltMotor.set(power);
    }

    public void powerBelt () {
        beltMotor.set(1);
    }

    public void stopBelt () {
        beltMotor.set(0);
    }

}
