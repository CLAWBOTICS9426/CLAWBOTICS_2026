package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import frc.robot.subsystems.Intake;

public class IntakeControl extends Command {
    private Intake intake;
    
    public IntakeControl (Intake intake) {
        this.intake = intake;
        addRequirements(intake);
    }

    public Command slurp = Commands.runOnce(() -> {
        intake.intake();
    });
    
    public Command yuck = Commands.runOnce(() -> {
        intake.backSpin(1);
    });

    public Command stop = Commands.runOnce(() -> {
        intake.stop();
    });
}
