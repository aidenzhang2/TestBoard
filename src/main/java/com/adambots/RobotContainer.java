package com.adambots;

import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;

import com.adambots.commands.ShootCommand;
import com.adambots.lib.sensors.LimitSwitch;
import com.adambots.subsystems.ShooterSubsystem;

/**
 * RobotContainer for TestBoard - a subsystem testing platform.
 *
 * This is designed for testing prototype mechanisms using Shuffleboard.
 * Each subsystem gets its own tab with commands pre-arranged.
 *
 * To add a new subsystem:
 * 1. Create the subsystem class in the subsystems package
 * 2. Create motors in RobotMap and pass them to the subsystem constructor
 * 3. Instantiate the subsystem here
 * 4. Create a Shuffleboard tab and add commands in setupDashboard()
 */
public class RobotContainer {

    // ==================== Subsystems ====================
    private final ShooterSubsystem shooter;


    public RobotContainer() {
        // Initialize subsystems with motors from RobotMap
        shooter = new ShooterSubsystem(RobotMap.x60, RobotMap.x40, RobotMap.limitSwitch1, RobotMap.limitSwitch2);
        setupDashboard();
    }

    /**
     * Create Shuffleboard tabs for each subsystem with commands pre-arranged.
     * Tabs persist between runs - just rearrange widgets as needed.
     */
    private void setupDashboard() {
        // ==================== Shooter Tab ====================
        ShuffleboardTab shooterTab = Shuffleboard.getTab("Shooter");

        // Shooter wheel commands (row 0)
        shooterTab.add("Run Shooter", shooter.runShooterCommand())
            .withPosition(0, 0).withSize(2, 1);
        shooterTab.add("Stop Shooter 60", shooter.stopShooter60())
            .withPosition(2, 0).withSize(2, 1);
        shooterTab.add("Stop Shooter 40", shooter.stopShooter40())
            .withPosition(4, 0).withSize(2, 1);
        shooterTab.add("Reverse Shooter", shooter.reverseShooterCommand())
            .withPosition(6, 0).withSize(2, 1);
        shooterTab.add("Run x60", shooter.runx60())
            .withPosition(8, 0).withSize(2, 1);
        shooterTab.add("Run x40", shooter.runx40())
            .withPosition(10, 0).withSize(2, 1);
        // RPM telemetry (row 1)
        shooterTab.addNumber("Left RPM", shooter::getLeftRPM)
            .withPosition(0, 1).withSize(2, 1);
        shooterTab.addNumber("Right RPM", shooter::getRightRPM)
            .withPosition(2, 1).withSize(2, 1);
        
        // Subsystem status (row 2)
        shooterTab.add("Shooter Subsystem", shooter)
            .withPosition(0, 2).withSize(3, 2);
        shooterTab.addBoolean("LimitSwitch1", shooter.limitSwitch1True)
            .withPosition(8, 2).withSize(2, 1);
        shooterTab.addBoolean("LimitSwitch2", shooter.limitSwitch2True)
            .withPosition(10, 2).withSize(2, 1);

        shooter.limitSwitch1True.onTrue(shooter.stopShooter60());
        shooter.limitSwitch1True.whileFalse(shooter.runx60());
        
        shooter.limitSwitch1True.onFalse(
            Commands.runOnce(() -> shooter.runx60(), shooter)
        );
        
        shooter.limitSwitch2True.onTrue(Commands.runEnd(()->shooter.stopShooter40(), ()->shooter.runx40()));
        
    }


      
    public Command getAutonomousCommand() {
        return Commands.none();
    }
}
