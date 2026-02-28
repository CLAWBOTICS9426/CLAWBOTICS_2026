package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import frc.robot.subsystems.Transfer;

public class TransferControl extends Command{
    private Transfer transfer;
    
    public TransferControl (Transfer transfer) {
        this.transfer = transfer;
        addRequirements(transfer);
    }

    public Command powerFeeder = Commands.runOnce(() -> {
        transfer.powerFeeder(1);
    });

    public Command stopFeeder = Commands.runOnce(() -> {
        transfer.stopFeeder();
    });

    public Command powerHopper = Commands.runOnce(() -> {
        transfer.powerHopper(1);
    });

    public Command stopHopper = Commands.runOnce(() -> {
        transfer.stopHopper();
    });
}
