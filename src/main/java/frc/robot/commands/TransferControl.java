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

    public Command stopBelt = Commands.runOnce(() -> {
        transfer.stopBelt();
    });

    public Command toggleBelt = Commands.runOnce(() -> {
        transfer.powerBelt(toggle ? 1 : 0);
        toggle = !toggle;
    });
}
