package org.firstinspires.ftc.teamcode.utilities;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;

public class RobotOdometry {
    private IMU m_imu;

    public RobotOdometry(IMU imu) {
        m_imu = imu;
        IMU.Parameters imuParameters = new IMU.Parameters(new RevHubOrientationOnRobot(
                RevHubOrientationOnRobot.LogoFacingDirection.UP,
                RevHubOrientationOnRobot.UsbFacingDirection.BACKWARD
        ));
        m_imu.initialize(imuParameters);
    }

    public double getYawDegrees() {
        // Create Orientation variable
        Orientation myRobotOrientation;

// Get Robot Orientation
        myRobotOrientation = m_imu.getRobotOrientation(
                AxesReference.EXTRINSIC,
                AxesOrder.ZYX,
                AngleUnit.DEGREES
        );
        return (double) myRobotOrientation.firstAngle;
    }

    public void resetAngle() {
        m_imu.resetYaw();
    }
}
