package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import frc.robot.Constants.HopperConstants;
import frc.robot.subsystems.Hopper;

public class HopperControl extends Command {
    private Hopper hopper;

    public enum States {
        OPEN,
        CLOSE,
        OSCILLATE,
        MANUAL
    }

    private States state = States.CLOSE;

    public HopperControl (Hopper hopper) {
        this.hopper = hopper;
        addRequirements(hopper);
        hopper.setDefaultCommand(this);
    }

    @Override
    public void execute() {
        switch (state) {
            case CLOSE:
            
                if (hopper.isClosed()) {
                    hopper.expand(0);
                } else {
                    hopper.retract(HopperConstants.hopperSpeed);
                }
                break;

            case OPEN:
               
                if (hopper.isOpen()) {
                    hopper.expand(0);
                } else {
                    hopper.expand(HopperConstants.hopperSpeed);
                }

                break;  

            case OSCILLATE:
                //TODO: figure out 
                break;
            
            case MANUAL:
                break;

            default:
                hopper.expand(0);
                break;
            
        }
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

    public Command openHopper = Commands.runOnce(() -> {
        state = States.OPEN;
    });
    public Command closeHopper = Commands.runOnce(() -> {
        state = States.CLOSE;
    });
}
