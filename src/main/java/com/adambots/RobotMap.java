package com.adambots;

import com.adambots.lib.actuators.BaseMotor;
import com.adambots.lib.actuators.TalonFXMotor;
import com.adambots.lib.sensors.*;
import com.adambots.lib.sensors.LimitSwitch;

/**
 * RobotMap defines all hardware port assignments and motor instances for TestBoard.
 *
 * This is a subsystem testing platform - add your motor CAN IDs here.
 * Motors are created here and passed to subsystems via constructor injection.
 *
 * CAN ID Assignment Guidelines:
 *   0-9:   Reserved for drivetrain (if ever added)
 *   10-19: Intake/indexer mechanisms
 *   20-29: Shooter/launcher mechanisms
 *   30-39: Arm/elevator mechanisms
 *   40-49: Climber mechanisms
 *   50+:   Miscellaneous
 */
public class RobotMap {

    // ==================== CAN IDs ====================
    // Shooter Prototype
    public static final int x60Port = 11;   
    public static final int x40Port = 10;

    public static final int limitSwitch1Port = 4;
    public static final int limitSwitch2Port = 5;   

    public static final BaseMotor x60 = new TalonFXMotor(x60Port, false, 60.0, true);
    public static final BaseMotor x40 = new TalonFXMotor(x40Port, false, 40.0, true);

    public static final LimitSwitch limitSwitch1 = new LimitSwitch(limitSwitch1Port, false);
    public static final LimitSwitch limitSwitch2 = new LimitSwitch(limitSwitch2Port, false);

    public static final BaseAbsoluteEncoder turretEncoder = new CANCoder(0);
}
