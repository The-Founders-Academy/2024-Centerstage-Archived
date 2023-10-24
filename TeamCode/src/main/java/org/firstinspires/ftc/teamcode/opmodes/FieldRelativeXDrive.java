package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.subsystems.drivetrain.XDrive;

import java.util.function.Supplier;

@TeleOp(name="2024 FieldRelativeX", group="Iterative OpMode")
public class FieldRelativeXDrive extends OpMode {
    private XDrive m_drive;
    private double previousTime = 0;
    private Supplier<Double> timeElapsedSinceLastLoop;

    @Override
    public void init() {
        m_drive = new XDrive(null, hardwareMap);
        timeElapsedSinceLastLoop = new Supplier<Double>() {
            @Override
            public Double get() {
                double tempPreviousTime = previousTime;
                previousTime = getRuntime();
                return getRuntime() - tempPreviousTime;
            }
        };
    }

    @Override
    public void loop() {
        // Calculate velocity and drive angle from controller inputs
        double velocity = Math.sqrt(Math.pow(gamepad1.left_stick_x, 2) + Math.pow(-gamepad1.left_stick_y, 2));
        double velocityAngle = Math.atan2(-gamepad1.left_stick_y, gamepad1.left_stick_x);
        double angularVelocity = gamepad1.right_stick_x;

        m_drive.fieldRelativeDrive(velocity, velocityAngle, angularVelocity, timeElapsedSinceLastLoop.get()); // Drive the robot

        // Telemetry updates down here
        telemetry.addData("robotPoseX", m_drive.getRobotPose().getTranslation().getX());
        telemetry.addData("robotPoseY", m_drive.getRobotPose().getTranslation().getY());
        telemetry.addData("headingDegrees", m_drive.getRobotPose().getRotation().getAngleDegrees());
        telemetry.update();
    }
}
