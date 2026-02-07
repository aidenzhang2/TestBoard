package com.adambots;

import static edu.wpi.first.units.Units.*;
import edu.wpi.first.units.measure.*;

/**
 * Constants for TestBoard subsystem testing platform.
 *
 * Add constants for your prototype mechanisms here.
 * Use WPILib Units library for type-safe measurements.
 */
public final class Constants {

    /**
     * Shooter constants for shooter wheel motors.
     */
    public static final class ShooterConstants {
        public static final double kShooterSpeed = 0.3;
        public static final Current kShooterCurrentLimit = Amps.of(60);

    }
}
