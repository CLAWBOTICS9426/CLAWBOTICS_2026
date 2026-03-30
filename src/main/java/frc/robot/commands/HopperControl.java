package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import frc.robot.Constants.HopperConstants;
import frc.robot.subsystems.Hopper;

public class HopperControl extends Command {
    private Hopper hopper;
    
    private boolean toggle = false;

    public HopperControl (Hopper hopper) {
        this.hopper = hopper;
        addRequirements(hopper);
    }

    public Command powerHopper = Commands.runOnce(() -> {
        hopper.expand(HopperConstants.hopperSpeed);
    });

    public Command negativeHopper = Commands.runOnce(() -> {
        hopper.retract(HopperConstants.hopperSpeed);
    });

    public Command stopHopper = Commands.runOnce(() -> {
        hopper.expand(0);
    });
}
