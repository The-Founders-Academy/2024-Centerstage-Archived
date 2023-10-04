package org.firstinspires.ftc.teamcode.subsystems;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad2;

import com.qualcomm.robotcore.hardware.DcMotor;

public class Arm {
    static DcMotor shoulderMotor;

    public static void MoveShoulder()
    {
        if (gamepad2.x)
        {
            //Change if needed, just example number
            shoulderMotor.setPower(-0.75);
        }
        else
        {
            shoulderMotor.setPower(0);
        }
    }
}
