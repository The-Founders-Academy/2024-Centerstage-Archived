package org.firstinspires.ftc.teamcode.utilities;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.teamcode.Constants;

// This class is for managing and storing the robot's rotation and translation.
// This class is separate from drive classes because we will have multiple drive classes.
public class RobotOdometry {
    private IMU m_imu;

    public RobotOdometry(IMU imu) {
        m_imu = imu;
        IMU.Parameters imuParameters = new IMU.Parameters(new RevHubOrientationOnRobot(
                Constants.LogoOrientation,
                Constants.UsbOrientation
        ));
        m_imu.initialize(imuParameters);
    }

    /**
     * @return The angle, in radians, of the robot heading/yaw
     */
    public double getYawDegrees() {
        // Create Orientation variable
        Orientation myRobotOrientation;

        // Get Robot Orientation
        myRobotOrientation = m_imu.getRobotOrientation(
                AxesReference.EXTRINSIC,
                AxesOrder.ZYX,
                AngleUnit.RADIANS
        );
        return (double) myRobotOrientation.firstAngle;
    }

    public void resetAngle() {
        m_imu.resetYaw();
    }
}
