package com.adambots.commands;

import com.adambots.subsystems.ShooterSubsystem;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;

/**
 * Combo commands for coordinating shooter and hopper subsystems.
 */
public class ShootCommand {

    /**
     * Command to run shooter, uptake, and carousel together while held.
     * All stop when the command ends.
     */
    public static Command shootWithHopper(ShooterSubsystem shooter) {
        return Commands.parallel(
            shooter.runShooterCommand()
            
        ).withName("Shoot With Hopper");
    }
}
