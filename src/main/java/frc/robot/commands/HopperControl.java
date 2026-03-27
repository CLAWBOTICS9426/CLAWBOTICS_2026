package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import frc.robot.subsystems.Hopper;

public class HopperControl extends Command {
    private Hopper hopper;
    
    private boolean toggle = false;

    public HopperControl (Hopper hopper) {
        this.hopper = hopper;
        addRequirements(hopper);
    }

    public Command ToggleHopper = Commands.runOnce(() -> {
        hopper.expand(toggle ? 1 : -1);
        toggle = !toggle;
    });
}
