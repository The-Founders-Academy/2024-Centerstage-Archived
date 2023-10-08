package org.firstinspires.ftc.teamcode.subsystems.drivetrain;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.utilities.Pose2D;
import org.firstinspires.ftc.teamcode.utilities.Rotation2D;

public class XDrive {

    // Declare hardware
    private DcMotor frontLeft;
    private DcMotor frontRight;
    private DcMotor backLeft;
    private DcMotor backRight;

    // Declare member variables
    private Pose2D m_robotPose; // The robot's pose in FIELD coordinates
    private Odometry m_odometry;

    public XDrive(Pose2D currentPose, HardwareMap hardwareMap) {
       // Initialize hardware
       this.frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
       this.frontRight = hardwareMap.get(DcMotor.class, "frontRight");
       this.backLeft = hardwareMap.get(DcMotor.class, "backLeft");
       this.backRight = hardwareMap.get(DcMotor.class, "backRight");

       // We want to set the motor directions so that applying positive power to all wheels moves the robot forward
       frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
       frontRight.setDirection(DcMotorSimple.Direction.FORWARD);
       backLeft.setDirection(DcMotorSimple.Direction.REVERSE);
       backRight.setDirection(DcMotorSimple.Direction.FORWARD);

       // If currentPose is equal to null, it likely means we are testing the robot and not actually on a field competing
       if(currentPose != null) {
           this.m_robotPose = currentPose;
       } else {
           this.m_robotPose = new Pose2D(0, 0, new Rotation2D(0, AngleUnit.DEGREES));
       }

       m_odometry = new Odometry(hardwareMap.get(IMU.class, "imu"));
       m_odometry.resetAngle(); // Make sure the robot always starts with an angular displacement of zero
    }

    public void fieldRelativeDrive(double velocity, double linearAngle, double angularSpeed) {
        double robotAngle = linearAngle - m_robotPose.getRotation().getAngleRadians(); // Transform linearAngle to robot coordinates. 0 degree is ALWAYS 3 o'clock.

        // In this case, x and y refer to the x/y directions in the ROBOTS coordinate system
        double powerX = velocity * Math.cos(robotAngle);
        double powerY = velocity * Math.sin(robotAngle);

        // TO-DO: figure out power for each wheel
        frontLeft.setPower(powerX);
        backRight.setPower(powerX);

        backLeft.setPower(powerY);
        frontRight.setPower(powerY);

        m_robotPose.setRotation(new Rotation2D(m_odometry.getYawDegrees(), AngleUnit.DEGREES));

    }

    public void driveToPosition(Pose2D targetPose) {
        // TO-DO: Implement this function
    }
}
