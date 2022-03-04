package frc.robot;

import edu.wpi.first.wpilibj.motorcontrol.VictorSP;

public class Mecanum {
    VictorSP frontLeft;
    VictorSP frontRight;
    VictorSP backLeft;
    VictorSP backRight;

    public Mecanum(){
        
        frontLeft = new VictorSP(0);
        frontRight = new VictorSP(3);
        backLeft = new VictorSP(1);
        backRight = new VictorSP(2);

        frontRight.setInverted(true);
        backRight.setInverted(true);

    }

    //drive smoothing, allows for better driving 
    private static double smooth(double value, double deadBand, double max) {
		double aValue = Math.abs(value);
		if (aValue > max)
			return (value / aValue);
		else if (aValue < deadBand)
			return 0;
		else
			return aValue * aValue * (value / aValue);
    }

    public void drive(double y, double x, double rx){

        if(Math.abs(x) > .7){
            y /= 2.5;
        }

        double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
        double frontLeftPower = (y + x + rx) / denominator;
        double backLeftPower = (y - x + rx) / denominator;
        double frontRightPower = (y - x - rx) / denominator;
        double backRightPower = (y + x - rx) / denominator;

        if(Math.abs(x) > .7){
            frontLeft.set(smooth(frontLeftPower,.6,.9));
            backLeft.set(smooth(backLeftPower,.6,.9));
            frontRight.set(smooth(frontRightPower,.6,.9));
            backRight.set(smooth(backRightPower,.6,.9));
        } else {
            frontLeft.set(smooth(frontLeftPower,.1,.9));
            backLeft.set(smooth(backLeftPower,.1,.9));
            frontRight.set(smooth(frontRightPower,.1,.9));
            backRight.set(smooth(backRightPower,.1,.9));
        }
    }

}
