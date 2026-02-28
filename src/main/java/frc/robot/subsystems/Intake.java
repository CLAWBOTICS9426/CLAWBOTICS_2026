package frc.robot.subsystems;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.PersistMode;
import com.revrobotics.ResetMode;
import com.revrobotics.spark.SparkLowLevel;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;



public class Intake extends SubsystemBase  {
   private final SparkMax intakeMotor;
   private final SparkMaxConfig config;

   public Intake () {
      intakeMotor = new SparkMax(Constants.IntakeMotorPorts.intakePort, SparkLowLevel.MotorType.kBrushless);
      config = new SparkMaxConfig();
      config.inverted(false) // Adjust if necessary
         .idleMode(IdleMode.kCoast)
         .smartCurrentLimit(15);
      intakeMotor.configure(config, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
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
