package org.firstinspires.ftc.teamcode.subsystems;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad2;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Arm {
    private DcMotor shoulder; // Use the private keyword to ensure we don't accidentally use armInstance.shoulder!

    public enum Direction {
        IN, OUT
    }

    public Arm(HardwareMap hardwareMap) {
        shoulder = hardwareMap.get(DcMotor.class, "shoulder");

        // There is some extra setup needed for shoulder. We use DcMotor in other classes, so try to look at them to see what's missing
        // HINT: setShoulderSpeed(speed) from the pseudocode below should rotate the arm away from the robot. We probably need to test this physically before we finish the code, but give it a try by guessing first

    }

    public void rotateShoulder(Direction direction, double speed) {


        if (direction == Direction.OUT)
        {
            shoulder.setPower(speed);

        } else
        {
            shoulder.setPower(-speed);
        }
    }
}
