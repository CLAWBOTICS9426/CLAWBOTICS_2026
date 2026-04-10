package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import frc.robot.subsystems.Transfer;

public class TransferControl extends Command{
    private Transfer transfer;

    private boolean toggle = false;
    
    public TransferControl (Transfer transfer) {
        this.transfer = transfer;
        addRequirements(transfer);
    }

    public Command powerBelt = Commands.runOnce(() -> {
        transfer.powerBelt(1);
    });

    public Command negativeBelt = Commands.runOnce(() -> {
        transfer.powerBelt(-1);
    });

    public Command stopBelt = Commands.runOnce(() -> {
        transfer.stopBelt();
    });

    public Command toggleBelt = Commands.runOnce(() -> {
        toggle = !toggle;
        transfer.powerBelt(toggle ? 1 : 0);
    });

    public Command autoToggle = Commands.sequence(
        Commands.waitSeconds(2).asProxy(),
        Commands.runOnce(() -> {
           toggle = !toggle;
            transfer.powerBelt(toggle ? 1 : 0); 
        }).beforeStarting(Commands.waitSeconds(2))
    );

    public Command OscilateBelt =
        Commands.repeatingSequence(
            powerBelt
                .andThen(Commands.waitSeconds(0.25)
                .andThen(negativeBelt)
                .andThen(Commands.waitSeconds(0.25))
        )).withTimeout(2.0).andThen(stopBelt);
}
