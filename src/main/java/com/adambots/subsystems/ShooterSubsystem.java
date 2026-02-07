package com.adambots.subsystems;

import static edu.wpi.first.units.Units.RPM;

import com.adambots.Constants.ShooterConstants;
import com.adambots.RobotMap;
import com.adambots.lib.actuators.BaseMotor;
import com.adambots.lib.sensors.LimitSwitch;

import edu.wpi.first.util.sendable.Sendable;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.button.Trigger;

/**
 * Shooter subsystem with two motors (left/right) for shooter wheels.
 */
public class ShooterSubsystem extends SubsystemBase {

    private final BaseMotor x60;
    private final BaseMotor x40;
    private LimitSwitch limitSwitch1;
    private LimitSwitch limitSwitch2;

    public ShooterSubsystem(BaseMotor x60, BaseMotor x40, LimitSwitch limitSwitch1, LimitSwitch limitSwitch2) {
        this.x60 = x60;
        this.x40 = x40;
        this.limitSwitch1 = limitSwitch1; 
        this.limitSwitch2 = limitSwitch2;
        configureMotors();

    }

    private void configureMotors() {
        x40.setBrakeMode(false);  // Coast mode for shooter wheels
        x60.setBrakeMode(false);

        // Right motor follows left motor in opposite direction (for shooter wheels)
        x60.setInverted(true);
       // x60.setStrictFollower(RobotMap.x60Port);
    }

        public final Trigger limitSwitch1True = new Trigger(
        ()-> limitSwitch1.isDetecting());

        public final Trigger limitSwitch2True = new Trigger(
        ()-> limitSwitch2.isDetecting());
        
    /**
     * Run the shooter at the configured speed.
     */
    public Command runShooter() {
        return runOnce(()->x40.set(ShooterConstants.kShooterSpeed));
    }

    /**
     * Run the shooter in reverse.
     */
    public void reverseShooter() {
        x40.set(-ShooterConstants.kShooterSpeed);
    }

    public Command runx60() {
        System.out.println("diddy blud");
        return runOnce(()->x60.set(ShooterConstants.kShooterSpeed)).withName("X60 Shooter");
        
    }

    public Command runx40() {
        return runOnce(()->x40.set(ShooterConstants.kShooterSpeed)).withName("X40 Shooter");
        
    }
    /**
     * Stop the shooter motors.
     */
    public Command stopShooter40() {
        return runOnce(()->x40.set(0));

    }

    public Command stopShooter60() {
        return runOnce(()->x60.set(0));

    }

    /**
     * Get the left shooter motor RPM.
     */
    public double getLeftRPM() {
        return x40.getVelocity().in(RPM);
    }

    /**
     * Get the right shooter motor RPM.
     */
    public double getRightRPM() {
        return x60.getVelocity().in(RPM);
    }

    // ==================== Command Factory Methods ====================

    /**
     * Command to run the shooter while held.
     */
    public Command runShooterCommand() {
        return runEnd(this::runShooter, this::stopShooter60)
            .withName("Run Shooter");
    }

    /**
     * Command to reverse the shooter while held.
     */
    public Command reverseShooterCommand() {
        return runEnd(this::reverseShooter, this::stopShooter60)
            .withName("Reverse Shooter");
    }

    /**
     * Command to stop the shooter (instant).
     */
    public Command stopShooterCommand() {
        return runOnce(this::stopShooter60)
            .withName("Stop Shooter");
    }

    public Command runKrakenx60() {
        return runEnd(this::runx60, this::stopShooter60);
    }

    public Command runKrakenx40() {
        return runEnd(this::runx40, this::stopShooter40);
    }
    @Override
    public void periodic() {
        // Add telemetry here if needed
    }
}
