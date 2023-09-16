package org.firstinspires.ftc.teamcode.utilities;

public class Pose2D {
    private Translation2D m_translation;
    private Rotation2D m_rotation;

    /**
     * A constructor that takes in one parameter
     * @param translation A translation representing some position
     * @param rotation A rotation on the field
     */
    public Pose2D(Translation2D translation, Rotation2D rotation) {
        this.m_translation = translation;
        this.m_rotation = rotation;
    }

    /**
     * A constructor that takes in one parameter
     * @param x An x position on the field
     * @param y A y position on the field
     * @param rotation A rotation on the field
     */
    public Pose2D(double x, double y, Rotation2D rotation) {
        this.m_translation = new Translation2D(x, y);
        this.m_rotation = rotation;
    }

    public Translation2D getTranslation() {
        return m_translation;
    }

    public Rotation2D getRotation() {
        return m_rotation;
    }

    public void setRotation(Rotation2D rotation) {
        m_rotation = rotation;
    }
}
