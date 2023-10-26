package org.firstinspires.ftc.teamcode.subsystems;

import android.util.Size;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.utilities.Pose2D;
import org.firstinspires.ftc.teamcode.utilities.Rotation2D;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;

public class Vision {
    private VisionPortal visionPortal;
    private AprilTagProcessor processor;

    public Vision(HardwareMap hardwareMap) {
        processor = new AprilTagProcessor.Builder()
                .setDrawAxes(true)
                .setDrawCubeProjection(true)
                .setDrawTagID(true)
                .setDrawTagOutline(true)
                .build();
        visionPortal = new VisionPortal.Builder()
                .addProcessor(processor)
                .setCamera(hardwareMap.get(WebcamName.class, "frontCamera"))
                .setCameraResolution(new Size(960, 720))
                .build();
    }

    public Pose2D getPoseFromAprilTagDetection(Pose2D currentPose) {
        // Check if we see at least one april tag with the camera
        // This is redundant because we call seesAprilTag() prior to this function but we add this if statement for absolute safety and to avoid field crashes
        if(processor.getDetections().isEmpty() == false) {
            AprilTagDetection tag = processor.getDetections().get(0);
            // The ternary conditional statements here account for the fact that we may be in the 2nd, 3rd, or 4th quadrants of the field in which we must contend with negative x or y values
            double x = tag.metadata.fieldPosition.get(0) - (tag.metadata.fieldPosition.get(0) > 0 ? 1 : -1) * tag.ftcPose.x;
            double y = tag.metadata.fieldPosition.get(1) - (tag.metadata.fieldPosition.get(1) > 0 ? 1 : -1) * tag.ftcPose.y;
            return new Pose2D(x, y, currentPose.getRotation()); // Our IMU rotation values are fairly accurate so we don't need to scrap them.
        } else {
            return currentPose;
        }
    }

    public boolean seesAprilTag() {
        return (processor.getDetections().isEmpty() == false);
    }
}
