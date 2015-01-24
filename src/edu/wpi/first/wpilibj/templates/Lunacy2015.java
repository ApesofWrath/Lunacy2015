/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package edu.wpi.first.wpilibj.templates;

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

    //Deploying RobotDrive and the booleans we will need for it
    RobotDrive rd = new RobotDrive(driveMotorLeft, driveMotorRight);
    boolean isTankDrive = true;
    boolean isIntake = true;
    boolean isOff = true;

    
    public void operatorControl() {
        System.out.println("Operator");
        //Current Controls
        
        /*General:
        *   Button 8(Start) switches Drive mode from Arcade to Tank
        *   Button 7(Select) switches Intake on and off
        */
        
        /*Arcade:
        *    .
        *
        *
        */
        
        /*Tank:
        *   .
        *  
        *   
        */
        
        // Drive Code Bit Start
        
        while(true) {
            if (isTankDrive) {// The drive parameters are either perfect or completely wrong.
                rd.tankDrive(joyGamepad, 2, joyGamepad, 5);
                if (joyGamepad.getRawButton(8)) {
                    isTankDrive = false;
                }
            }

            if (!isTankDrive) {
                rd.arcadeDrive(joyGamepad, 1, joyGamepad, 2);
                if (joyGamepad.getRawButton(8)) {
                    isTankDrive = true;
                }
            }
        }
        
        // Drive Code Bit End
        
        /*
        // Intake Code Bit Start
        if (isIntake && !isOff) {//might have to make the values negative.
            motorBottom.set(0.5);
            motorTop.set(0.5);
            if (joyGamepad.getRawButton(7)) {
                isIntake = false;
            }
            if (joyGamepad.getRawButton(1)){
                isOff = true;
            }
        }

        if (!isIntake && !isOff) {//might have to make the values negative.
            motorBottom.set(0.5);
            motorTop.set(-0.5);
            if (joyGamepad.getRawButton(7)) {
                isIntake = true;
            }
            if (joyGamepad.getRawButton(1)){
                isOff = true;
            }
        }
            
        if (isOff){
            motorTop.set(0.0);
            motorBottom.set(0.0);
            if(joyGamepad.getRawButton(1)){
               isOff = false;
           }
        }
                */
           
        }
        //Intake Code Bit End
        
    }


