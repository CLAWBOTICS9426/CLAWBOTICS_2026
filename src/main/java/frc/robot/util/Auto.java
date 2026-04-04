package frc.robot.util;

import choreo.auto.AutoFactory;
import choreo.auto.AutoRoutine;
import choreo.auto.AutoTrajectory;
import edu.wpi.first.wpilibj.DataLogManager;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.IntakeControl;
import frc.robot.commands.ShooterControl;
import frc.robot.commands.TransferControl;
import frc.robot.subsystems.Swerve;
import frc.robot.util.Vision;


/** Simple wrapper class to load routines. */
public class Auto {
    
    Swerve swerve;

    Vision vision;

    IntakeControl intakeControl;

    ShooterControl shooterControl;

    TransferControl transferControl;

    AutoFactory autoFactory;

    /**
     * Constructs a new Auto object with a swerve object to use for path control.

     * @param swerve - the swerve object to be used
     */
    public Auto(Swerve swerve, Vision vision, IntakeControl intakeControl, ShooterControl shooterControl, TransferControl transferControl) {

        this.swerve = swerve;

        this.vision = vision;

        this.intakeControl = intakeControl;

        this.shooterControl = shooterControl;

        this.transferControl = transferControl;


        autoFactory = new AutoFactory(
			swerve::getPose,
			swerve::resetOdometry, 
            swerve::followTrajectory, 
            false, 
            swerve
        ).bind("Intake", intakeControl.slurp)
        .bind("StopIntake", intakeControl.stop);
    }
    
    public AutoRoutine FirstRoute() {
        DataLogManager.log("Starting Auto Routine: FirstRoute");

        AutoRoutine FirstRoute = autoFactory.newRoutine("FirstRoute");

        AutoTrajectory firstRoute = FirstRoute.trajectory("FirstRoute");


        FirstRoute.active().onTrue(
            Commands.sequence(
                firstRoute.resetOdometry(),
                firstRoute.cmd()
            )
        );

        return FirstRoute;
    }

    public AutoRoutine tingLeft() {
        DataLogManager.log("Starting Auto Routine: tingLeft");

        AutoRoutine tingLeft = autoFactory.newRoutine("tingLeft");
        
        AutoTrajectory Ting1Left = tingLeft.trajectory("Ting1Left");
        AutoTrajectory Ting2Left = tingLeft.trajectory("Ting2Left");

        tingLeft.active().onTrue(
            Commands.sequence(
                Ting1Left.resetOdometry(),
                Ting1Left.cmd()

            )
        );

        Ting1Left.done()
            .onTrue(shootAfterPath
            .andThen(Ting2Left.cmd()));
            
        Ting2Left.done()
            .onTrue(shootAfterPath);

        return tingLeft;
    }

    public AutoRoutine tingRight() {
        DataLogManager.log("Starting Auto Routine: tingRight");

        AutoRoutine tingRight = autoFactory.newRoutine("tingRight");
        
        AutoTrajectory Ting1Right = tingRight.trajectory("Ting1Right");
        AutoTrajectory Ting2Right = tingRight.trajectory("Ting2Right");

        tingRight.active().onTrue(
            Commands.sequence(
                Ting1Right.resetOdometry(),
                Ting1Right.cmd()

            )
        );

        Ting1Right.done()
            .onTrue(shootAfterPath
            .andThen(Ting2Right.cmd()));
            
        Ting2Right.done()
            .onTrue(shootAfterPath);

        return tingRight;
    }

    public AutoRoutine MiddleOutOfWay() {
        DataLogManager.log("Starting Auto Routine: MiddleOutOfWay");

        AutoRoutine MiddleOutOfWay = autoFactory.newRoutine("MiddleOutOfWay");

        AutoTrajectory MOOW1 = MiddleOutOfWay.trajectory("MOOW1");
        AutoTrajectory MOOW2 = MiddleOutOfWay.trajectory("MOOW2");
        AutoTrajectory MOOW3 = MiddleOutOfWay.trajectory("MOOW3");

        MiddleOutOfWay.active().onTrue(
            Commands.sequence(
                MOOW1.resetOdometry(),
                MOOW1.cmd()
            )
        );

        MOOW1.done()
            .onTrue(MOOW2.cmd());

        MOOW2.done()
            .onTrue(shootAfterPath
            .andThen(MOOW3.cmd()));
            
        MOOW3.done()
            .onTrue(shootAfterPath);

        return MiddleOutOfWay;
    }


    public SequentialCommandGroup shootAfterPath =
        Commands.parallel(
            Commands.run(() -> {
                swerve.swerveDrive(vision.autoTag());
            }).withTimeout(1),
            shooterControl.accelerateMotorLimelight.withTimeout(6),
            transferControl.toggleBelt.beforeStarting(Commands.waitSeconds(1)) // TODO figure out how to make this wait for setpoint
        ).andThen(
            Commands.parallel(
                shooterControl.stopMotors,
                transferControl.toggleBelt
            )
        );
}
