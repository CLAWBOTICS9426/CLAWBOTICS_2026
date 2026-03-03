// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import choreo.auto.AutoChooser;
import edu.wpi.first.wpilibj.PowerDistribution;
import edu.wpi.first.wpilibj.PowerDistribution.ModuleType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandJoystick;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.IntakeControl;
import frc.robot.commands.TransferControl;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Swerve;
import frc.robot.subsystems.Transfer;
import frc.robot.util.Auto;
import frc.robot.util.ControllerInput;
import frc.robot.util.Vision;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in
 * the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of
 * the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {

	CommandJoystick joystick = new CommandJoystick(OperatorConstants.operatorControllerPort);
	CommandXboxController gamepad = new CommandXboxController(OperatorConstants.driverControllerPort);

	PowerDistribution powerDistribution = new PowerDistribution(16, ModuleType.kRev);

	ControllerInput controller = new ControllerInput(gamepad, joystick);

    Vision visionSystem = new Vision();

	Swerve swerve = new Swerve(controller, visionSystem);

	final AutoChooser autoChooser;
    Auto auto = new Auto(swerve);
	Intake intake;
	IntakeControl intakeControl;

	Transfer transfer;
	TransferControl transferControl;

	/**
	 * The container for the robot. Contains subsystems, OI devices, and commands.
	 */
	public RobotContainer() {
		
		autoChooser = new AutoChooser();

        autoChooser.addRoutine("FromLeft", auto::fromLeft);
        autoChooser.addRoutine("FromMid", auto::fromMid);
        autoChooser.addRoutine("FromRight", auto::fromRight);

        SmartDashboard.putData("Autos", autoChooser);

        autoChooser.select("FromMid");

        SmartDashboard.putData("Swerve", swerve);
        SmartDashboard.putData("Field", swerve.field);
        SmartDashboard.putData("Gyro", swerve.gyroAhrs);

        SmartDashboard.putData("Power Distribution", powerDistribution);

		intake = new Intake();
		intakeControl = new IntakeControl(intake);

		transfer = new Transfer();
		transferControl = new TransferControl(transfer);

		// Configure the trigger bindings
		configureBindings();
	}

	/**
	 * Use this method to define your trigger->command mappings. Triggers can be
	 * created via the
	 * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with
	 * an arbitrary
	 * predicate, or via the named factories in {@link
	 * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for
	 * {@link
	 * CommandXboxController
	 * Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
	 * PS4} controllers or
	 * {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
	 * joysticks}.
	 */
	private void configureBindings() {

		// driver bindings 
        gamepad.start()
            .onChange(controller.toggleNos);

        gamepad.leftTrigger(0.75)
            .onChange(controller.toggleFeildRelative);

        // gamepad.rightBumper()
        //     .onTrue(controller.upShift);
        
        // gamepad.leftBumper()
        //     .onTrue(controller.downShift);

        // gamepad.b()
        //     .onChange(controller.toggleRightBumper);
        
        // gamepad.x()
        //     .onChange(controller.toggleLeftBumper);
        
        // gamepad.a()
        //     .onChange(controller.a);

		gamepad.x()
			.onTrue(intakeControl.slurp)
			.onFalse(intakeControl.stop);
		gamepad.a()
			.onTrue(intakeControl.yuck)
			.onFalse(intakeControl.stop);
		gamepad.y()
			.onTrue(transferControl.powerFeeder)
			.onFalse(transferControl.stopFeeder);
		gamepad.b()
			.onTrue(transferControl.powerHopper)
			.onFalse(transferControl.stopHopper);
	}

	/**
	 * Use this to pass the autonomous command to the main {@link Robot} class.
	 *
	 * @return the command to run in autonomous
	 */
	public Command getAutonomousCommand() {
		return autoChooser.selectedCommandScheduler();
	}
}
