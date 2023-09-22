package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.utilities.Pose2D;
import org.firstinspires.ftc.teamcode.utilities.Rotation2D;

public class XDrive {

    // Declare hardware
    private DcMotor frontLeft;
    private DcMotor frontRight;
    private DcMotor backLeft;
    private DcMotor backRight;

    // Declare member variables
    private Pose2D m_robotPose;

    public XDrive(DcMotor frontLeft, DcMotor frontRight, DcMotor backLeft, DcMotor backRight, Pose2D currentPose) {
       // Initialize hardware
       this.frontLeft = frontLeft;
       this.frontRight = frontRight;
       this.backLeft = backLeft;
       this.backRight = backRight;

       // We want to set the motor directions so that pressing forward moves the robot forward
       frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
       frontRight.setDirection(DcMotorSimple.Direction.FORWARD);
       backLeft.setDirection(DcMotorSimple.Direction.REVERSE);
       backRight.setDirection(DcMotorSimple.Direction.FORWARD);

       // Initialize member variables
       // If currentPose is equal to null, it likely means we are testing the robot and not actually on a field competing
       if(currentPose != null) {
           this.m_robotPose = currentPose;
       } else {
           this.m_robotPose = new Pose2D(0, 0, new Rotation2D(0));
       }


    }

    public void fieldRelativeDrive(double velocity, double linearAngle, double angularSpeed) {
        double robotAngle = linearAngle - m_robotPose.getRotation().getAngleRadians(); // Transform linearAngle to robot coordinates. 0 degree is ALWAYS 3 o'clock.

        // In this case, x and y refer to the x/y directions in the ROBOTS coordinate system
        double powerX = velocity * Math.cos(robotAngle);
        double powerY = velocity * Math.sin(robotAngle);

        // This is last year's code. It must be adapted to this year's drivetrain
        /* front.setPower(pFB - angularSpeed);
        back.setPower(pFB + angularSpeed);
        left.setPower(pLR - angularSpeed);
        right.setPower(pLR + angularSpeed); */
    }

    public void driveToPosition(Pose2D targetPose) {
        // TO-DO: Implement this function
    }
}
