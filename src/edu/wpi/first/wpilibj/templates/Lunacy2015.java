 /*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SimpleRobot;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.Joystick;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the SimpleRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Lunacy2015 extends SimpleRobot {

    //Declaring Motors
    Victor driveMotorLeft = new Victor(2);
    Victor driveMotorRight = new Victor(1);
    Victor motorTop = new Victor(4);
    Victor motorBottom = new Victor(3);

    //Declaring Joystick/Gamepad
    Joystick joyGamepad = new Joystick(1);

    private final int INTAKE_MODE_ENABLED = 2;
    private final int OUTPUT_MODE_ENABLED = 3;
    private final int TANK_MODE_ENABLED = 1;
    private final int ARCADE_MODE_ENABLED = 4;
    private final int BALL_SYSTEM_DISABLED = 7;
    private final int BALL_SYSTEM_ENABLED = 8;

    //Deploying RobotDrive and the booleans we will need for it
    RobotDrive rd = new RobotDrive(driveMotorLeft, driveMotorRight);

    boolean isTankDrive = true;
    boolean isIntake = true;
    boolean isOff = true;

    public void operatorControl() {
        rd.setInvertedMotor(RobotDrive.MotorType.kFrontLeft, true);
        rd.setInvertedMotor(RobotDrive.MotorType.kFrontRight, true);
        System.out.println("Operator");
        System.out.println("Version 1/24 5:02 PM");
        //Current Controls

        /*General:
            Button 8(Start) switches Drive mode from Arcade to Tank
            Button 7(Select) switches Intake on and off
         */
       
        // Drive Code Bit Start

        while (isEnabled() && isOperatorControl()) {

            if (joyGamepad.getRawButton(ARCADE_MODE_ENABLED)) {
                isTankDrive = false;
                System.out.println("ARCADE ENABLED");
            }

            if (joyGamepad.getRawButton(TANK_MODE_ENABLED)) {
                isTankDrive = true;
                System.out.println("TANK ENABLED");
            }

            if (isTankDrive) {

                driveMotorLeft.set(-joyGamepad.getRawAxis(2));
                driveMotorRight.set(joyGamepad.getRawAxis(4));

            }

            if (!isTankDrive) {
                rd.arcadeDrive(-joyGamepad.getY(GenericHID.Hand.kLeft), -joyGamepad.getX(GenericHID.Hand.kLeft));
            }


            // Drive Code Bit End
            // Intake Code Bit Start
            if (joyGamepad.getRawButton(OUTPUT_MODE_ENABLED)) {
                isIntake = false;
                System.out.println("Outputting");
            }

            if (joyGamepad.getRawButton(INTAKE_MODE_ENABLED)) {
                isIntake = true;
                System.out.println("Intaking");
            }

            if (joyGamepad.getRawButton(BALL_SYSTEM_DISABLED)) {
                isOff = true;
                System.out.println("Rollers off");
            }

            if (joyGamepad.getRawButton(BALL_SYSTEM_ENABLED)) {
                isOff = false;
                System.out.println("Rollers on");
            }

            if (isIntake && !isOff) {
                motorBottom.set(1.0);
                motorTop.set(1.0); //change back to 1.0
            }

            if (!isIntake && !isOff) {
                motorBottom.set(-1.0);
                motorTop.set(1.0); //change back to 1.0
            }

            if (isOff) {
                motorTop.set(0.0);
                motorBottom.set(0.0);
            }

        }
        //Intake Code Bit End

    }
}
