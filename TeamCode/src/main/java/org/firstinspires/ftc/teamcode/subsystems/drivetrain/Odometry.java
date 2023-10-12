package org.firstinspires.ftc.teamcode.subsystems.drivetrain;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;

/**
 * This class will keep track of robot heading and position on the field
 * NOTE: The heading produced by this class doesn't represent the robot's actual angle but instead its angle offset. m_robotPose holds the absolute angle
 */
public class Odometry {
    private IMU m_imu;
    public Odometry(IMU imu) {
        m_imu = imu;
        IMU.Parameters imuParameters = new IMU.Parameters(new RevHubOrientationOnRobot(
                RevHubOrientationOnRobot.LogoFacingDirection.BACKWARD,
                RevHubOrientationOnRobot.UsbFacingDirection.LEFT
        ));
        m_imu.initialize(imuParameters);
    }

    public double getYawDegrees() {
        // Get Robot Orientation
        Orientation robotOrientation = m_imu.getRobotOrientation(
                AxesReference.EXTRINSIC,
                AxesOrder.ZYX,
                AngleUnit.DEGREES
        );
        return (double) robotOrientation.firstAngle;
    }

    public void resetAngle() {
        m_imu.resetYaw();
    }
}

