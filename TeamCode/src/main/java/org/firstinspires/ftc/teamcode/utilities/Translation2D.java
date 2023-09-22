package org.firstinspires.ftc.teamcode.utilities;

public class Translation2D {
    private double m_x;
    private double m_y;

    public Translation2D() {

    }

    public Translation2D(double x, double y) {
        this.m_x = x;
        this.m_y = y;
    }

    public double getX() {
        return m_x;
    }

    public void setX(double x) {
        this.m_x = x;
    }

    public double getY() {
        return m_y;
    }

    public void setY(double y) {
        this.m_y = y;
    }
}
