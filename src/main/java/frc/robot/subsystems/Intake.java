package frc.robot.subsystems;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel;
import com.revrobotics.spark.config.SparkBaseConfig;
import com.revrobotics.spark.config.SparkParameters;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.subsystems.Swerve;
import frc.robot.Constants;
import frc.robot.Constants.OperatorConstants;



public class Intake extends SubsystemBase  {
   private final SparkMax intakeMotor;
   private final SparkMaxConfig config;

   public Intake () {
      intakeMotor = new SparkMax(Constants.IntakeMotorPorts.intakePort, SparkLowLevel.MotorType.kBrushless);
      config.inverted(false) // Adjust if necessary
         .idleMode(IdleMode.kCoast)
         .smartCurrentLimit(15);
      intakeMotor.configure(config, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters)
   }

   public void intake () {
      intakeMotor.set(1);
   }

   public void intake (double power) {
      intakeMotor.set(power);
   }

   public void stop () {
      intakeMotor.set(0);
   }

   public void backSpin (double power) {
      intakeMotor.set(-power);
   }

   public double getPower () {
      return intakeMotor.get();
   }
} 
