package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.subsystems.XDrive;

@TeleOp(name="2024 FieldRelativeX", group="Iterative OpMode")
public class FieldRelativeXDrive extends OpMode {
    private XDrive m_drive;

    @Override
    public void init() {
        m_drive = new XDrive(null, hardwareMap);
    }

    @Override
    public void loop() {
        // Calculate velocity and drive angle from controller inputs
        double velocity = Math.sqrt(Math.pow(gamepad1.left_stick_x, 2) + Math.pow(-gamepad1.left_stick_y, 2));
        double velocityAngle = Math.atan2(-gamepad1.left_stick_y, gamepad1.left_stick_x);
        double angularVelocity = gamepad1.right_stick_x;

        m_drive.fieldRelativeDrive(velocity, velocityAngle, angularVelocity); // Drive the robot



        // Telemetry updates down here

    }
}
