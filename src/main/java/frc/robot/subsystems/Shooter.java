package frc.robot.subsystems;

import com.revrobotics.spark.FeedbackSensor;
import com.revrobotics.spark.SparkClosedLoopController;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkBase.ControlType;
import com.revrobotics.PersistMode;
import com.revrobotics.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.util.sendable.SendableBuilder;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.LimelightHelpers;
import frc.robot.Constants.ShooterConstants;
import frc.robot.commands.ShooterControl;

public class Shooter extends SubsystemBase{
    private static SparkMax highShootMotor;
    private static SparkMaxConfig highMotorConfig;

    private static SparkMax lowShootMotor;
    private static SparkMaxConfig lowMotorConfig;

    private static SparkClosedLoopController shooterPidLow;
    private static SparkClosedLoopController shooterPidHigh;

    private static double highRpmAdjust = 0.0;
    private static double lowRpmAdjust = 0.0;

    public Shooter () {
        highShootMotor = new SparkMax(ShooterConstants.highMotorPort, MotorType.kBrushless);
        highMotorConfig = new SparkMaxConfig();
        highMotorConfig
            .inverted(false)  // Change if needed
            .idleMode(IdleMode.kBrake)
            .smartCurrentLimit(35);

        highMotorConfig.closedLoop
            .pid(ShooterConstants.shooterP, ShooterConstants.shooterI, ShooterConstants.shooterD)
            .feedbackSensor(FeedbackSensor.kPrimaryEncoder);

        highMotorConfig.closedLoop.maxMotion
            .maxAcceleration(ShooterConstants.shooterMaxAccel)
            .cruiseVelocity(ShooterConstants.neoFreeSpeedRPM);

        lowShootMotor = new SparkMax(ShooterConstants.lowMotorPort, MotorType.kBrushless);
        lowMotorConfig = new SparkMaxConfig();
        lowMotorConfig
            .inverted(true) // Change if needed
            .idleMode(IdleMode.kBrake)
            .smartCurrentLimit(35);

        lowMotorConfig.closedLoop
            .pid(ShooterConstants.shooterP, ShooterConstants.shooterI, ShooterConstants.shooterD)
            .feedbackSensor(FeedbackSensor.kPrimaryEncoder);

        lowMotorConfig.closedLoop.maxMotion
            .maxAcceleration(ShooterConstants.shooterMaxAccel)
            .cruiseVelocity(ShooterConstants.neoFreeSpeedRPM);

        highShootMotor.configure(highMotorConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
        lowShootMotor.configure(lowMotorConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

        shooterPidHigh = highShootMotor.getClosedLoopController();
        shooterPidLow = lowShootMotor.getClosedLoopController();
    }

    public void accelerateMotors (double highRpm, double lowRpm) {
        shooterPidHigh.setSetpoint(
            (highRpm + highRpmAdjust),
            ControlType.kMAXMotionVelocityControl
        );

        shooterPidLow.setSetpoint(
            (lowRpm + lowRpmAdjust),
            ControlType.kMAXMotionVelocityControl
        );

   
    }

    public void stop () {
        shooterPidHigh.setSetpoint(
            0,
            ControlType.kMAXMotionVelocityControl
        );

        shooterPidLow.setSetpoint(
            0,
            ControlType.kMAXMotionVelocityControl
        );

    }

    public double getHighMotorVelocity () {
        return highShootMotor.getEncoder().getVelocity();
    }

    public double getLowMotorVelocity () {
        return lowShootMotor.getEncoder().getVelocity();
    }

    public void adjustHighMotorSpeed (double adjustmentValueRPM) {
        highRpmAdjust += adjustmentValueRPM;
    }

    public void adjustLowMotorSpeed (double adjustmentValueRPM) {
        lowRpmAdjust += adjustmentValueRPM;
    }

    public long[] getShooterSpeeds() {
        return new long[]{ShooterConstants.startingHighSpeed + (int)highRpmAdjust, ShooterConstants.startingLowSpeed + (int)lowRpmAdjust};
    }

    @Override
    public void initSendable(SendableBuilder builder) {
        
        builder.setSmartDashboardType("Shooter Speeds");

        builder.addIntegerArrayProperty("Shooter Speeds", this::getShooterSpeeds, null);

    }
    
}
