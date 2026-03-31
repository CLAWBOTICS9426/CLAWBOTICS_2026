package frc.robot.subsystems;

import edu.wpi.first.util.sendable.SendableBuilder;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.HopperConstants;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkRelativeEncoder;
import com.revrobotics.PersistMode;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.ResetMode;
import com.revrobotics.spark.SparkLowLevel;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;

public class Hopper extends SubsystemBase{
    private final SparkMax hopperMotor;
    private final RelativeEncoder hopperEncoder;
    private final SparkMaxConfig config;

    public Hopper () {
        hopperMotor = new SparkMax(HopperConstants.hopperMotorPort, SparkLowLevel.MotorType.kBrushed);
        hopperEncoder = hopperMotor.getEncoder();
        config = new SparkMaxConfig();

        config.inverted(true) // Adj ust if necessary
            .idleMode(IdleMode.kBrake)
            .smartCurrentLimit(15);

      hopperMotor.configure(config, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    }

    public void expand(double power) {
        hopperMotor.set(power);
    }

    public void retract(double power) {
        hopperMotor.set(-power);
    }

    public boolean isClosed() {

        return hopperEncoder.getPosition() <= 0.1;
    }

    public boolean isOpen() {

        return hopperEncoder.getPosition() >= HopperConstants.extendedPos;
    }

    @Override
    public void initSendable(SendableBuilder builder) {
        builder.setSmartDashboardType("Hopper");

        builder.addDoubleProperty("Hopper Pos", hopperEncoder::getPosition, null);
    }
}
