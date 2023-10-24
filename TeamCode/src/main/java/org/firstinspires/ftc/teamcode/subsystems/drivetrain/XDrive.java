package org.firstinspires.ftc.teamcode.subsystems.drivetrain;

import static java.lang.Math.PI;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.subsystems.Vision;
import org.firstinspires.ftc.teamcode.utilities.Pose2D;
import org.firstinspires.ftc.teamcode.utilities.Rotation2D;
import org.firstinspires.ftc.teamcode.utilities.Translation2D;

public class XDrive {

    // Declare hardware
    private DcMotor frontLeft;
    private DcMotor frontRight;
    private DcMotor backLeft;
    private DcMotor backRight;

    // Declare member variables
    private Pose2D m_robotPose; // The robot's pose in FIELD coordinates
    private Odometry m_odometry;
    private Vision m_vision;

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
       backRight.setDirection(DcMotorSimple.Direction.REVERSE);

       // If currentPose is equal to null, it likely means we are testing the robot and not actually on a field competing
       if(currentPose != null) {
           this.m_robotPose = currentPose;
       } else {
           this.m_robotPose = new Pose2D(0, 0, new Rotation2D(0, AngleUnit.DEGREES));
       }

       m_vision = new Vision(hardwareMap);
       m_odometry = new Odometry(hardwareMap.get(IMU.class, "imu"));
       m_odometry.resetAngle(); // Make sure the robot always starts with an angular displacement of zero
    }

    public void fieldRelativeDrive(double velocity, double linearAngle, double angularSpeed, double timeElapsedSinceLastLoop) {
        double robotAngle = linearAngle - m_robotPose.getRotation().getAngleRadians(); // Transform linearAngle to robot coordinates. 0 degree is ALWAYS 3 o'clock.

        double powerFLBR = velocity * Math.cos(robotAngle - PI/4 );
        double powerFRBL = velocity * Math.sin(robotAngle - PI/4);

        // TO-DO: figure out power for each wheel
        frontLeft.setPower(powerFLBR + angularSpeed);
        backRight.setPower(powerFLBR - angularSpeed);

        backLeft.setPower(powerFRBL + angularSpeed);
        frontRight.setPower(powerFRBL - angularSpeed);

        m_robotPose.setRotation(new Rotation2D(m_odometry.getYawDegrees(), AngleUnit.DEGREES));

        // If we don't see an april tag, estimate our position
        if(m_vision.seesAprilTag() == false) {
            estimatePositionFromWheelPowers(timeElapsedSinceLastLoop, velocity * Math.cos(linearAngle), velocity * Math.sin(linearAngle));
        } else {
            m_robotPose = m_vision.getPoseFromAprilTagDetection(m_robotPose); // Use april tag values exclusively so long as we can detect them
        }
    }

    public void driveToPosition(Pose2D targetPose) {
        // TO-DO: Implement this function
    }

    private void estimatePositionFromWheelPowers(double timeSinceLastLoop, double velocityX, double velocityY) {
        Translation2D displacement = new Translation2D(velocityX * Constants.XDriveConstants.PowerToVelocityInchesPerSecond * timeSinceLastLoop, velocityY * Constants.XDriveConstants.PowerToVelocityInchesPerSecond * timeSinceLastLoop); // Speed * time along both the x and y axis
        m_robotPose.getTranslation().add(displacement);
    }

    public Pose2D getRobotPose() {
        return m_robotPose;
    }

}
