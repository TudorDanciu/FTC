package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.*;

public class Autonomie extends LinearOpMode {

    static final double COUNTS_PER_MOTOR_REV = 1680;
    static final double DRIVE_GEAR_REDUCTION = 1;
    static final double WHEEL_DIAMETER_CM = 10;
    static final double COUNTS_PER_INCH = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) /
            (WHEEL_DIAMETER_CM * 3.1415);


    public DcMotor frontLeft = null;
    public DcMotor frontRight = null;
    public DcMotor backLeft = null;
    public DcMotor backRight = null;

    public Servo servo = null;
    public ColorSensor senzor = null;

    HardwareMap hwMap;

    Autonomie()
    {

    }

    public void initializare(HardwareMap ahwmap)
    {

        backLeft = hwMap.get(DcMotor.class, "backleft");
        frontLeft = hwMap.get(DcMotor.class, "frontleft");
        backRight = hwMap.get(DcMotor.class, "backright");
        frontRight = hwMap.get(DcMotor.class,"frontright");

        servo = hwMap.get(Servo.class,"servo");
        senzor = hwMap.get(ColorSensor.class, "culoare");
        senzor.enableLed(true);

        frontLeft.setDirection(DcMotorSimple.Direction.FORWARD);
        backLeft.setDirection(DcMotorSimple.Direction.FORWARD);
        frontRight.setDirection(DcMotorSimple.Direction.REVERSE);
        backRight.setDirection(DcMotorSimple.Direction.REVERSE);

        backLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        frontLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        backLeft.setPower(0);
        frontLeft.setPower(0);
        backRight.setPower(0);
        frontRight.setPower(0);

    }

    @Override
    public void runOpMode() throws InterruptedException {
        initializare(hardwareMap);

        waitForStart();
        if(opModeIsActive()){}

        servo.setPosition(1);
        moveForwardBackward(62, 1);
        
        if(senzor.red() < 37 && senzor.green() < 60&& senzor.blue() < 50 && senzor.alpha() < 152){

            moveForwardBackward(8,0.5);
            servo.setPosition(0.5);
            moveForwardBackward(-19,0.75);
            moveLeftRight(88,1);
            servo.setPosition(1);

            sleep(250);
            moveLeftRight(-125,1);
            moveForwardBackward(26,0.5);

            sleep(500);
            servo.setPosition(0.5);
            moveForwardBackward(-21,1);
            moveLeftRight(133,1);
            servo.setPosition(1);
            moveLeftRight(-35,1);
        }

        else{
            moveLeftRight(-14,0.5);
            sleep(500);

            if(senzor.red() < 35 && senzor.green() < 55 && senzor.blue() < 47) {

                moveForwardBackward(18, 0.5);
                servo.setPosition(0.5);
                moveForwardBackward(-30, 1);
                moveLeftRight(88, 1);
                servo.setPosition(1);

                sleep(250);
                moveLeftRight(-162, 1);
                moveForwardBackward(23, 0.5);

                sleep(500);
                servo.setPosition(0.5);
                moveForwardBackward(-35, 1);
                moveLeftRight(133, 1);
                servo.setPosition(1);
                moveLeftRight(-35, 1);
            }

            else{

                moveLeftRight(-14,0.5);
                moveForwardBackward(9,0.5);

                sleep(150);
                servo.setPosition(0.5);

                sleep(500);
                moveForwardBackward(-26,1);
                moveLeftRight(105,1);
                servo.setPosition(1);

                sleep(250);
                moveLeftRight(-35,1);
            }
        }
    }



    public void moveForwardBackward(int distanceCM, double speed) throws InterruptedException {
        int distance = (int) (distanceCM * COUNTS_PER_INCH);
        backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        Thread.sleep(100);
        backLeft.setTargetPosition(-distance);
        backRight.setTargetPosition(-distance);
        frontRight.setTargetPosition(-distance);
        frontLeft.setTargetPosition(-distance);

        backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        backRight.setPower(speed);
        backLeft.setPower(speed);
        frontLeft.setPower(speed);
        frontRight.setPower(speed);

        while (backLeft.isBusy() && backRight.isBusy() && frontRight.isBusy() && frontLeft.isBusy()){

        }

        backLeft.setPower(0);
        backRight.setPower(0);
        frontLeft.setPower(0);
        frontRight.setPower(0);

        backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

    }

    public void moveLeftRight(int distanceCM, double speed) throws  InterruptedException {
        int distance = (int) (distanceCM * COUNTS_PER_INCH);

        Thread.sleep(100);
        frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        Thread.sleep(100);

        frontRight.setTargetPosition(-distance);
        backLeft.setTargetPosition(-distance);
        frontLeft.setTargetPosition(distance);
        backRight.setTargetPosition(distance);

        backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);


        backRight.setPower(speed);
        backLeft.setPower(speed);
        frontLeft.setPower(speed);
        frontRight.setPower(speed);

        while (backLeft.isBusy() && backRight.isBusy() && frontRight.isBusy() && frontLeft.isBusy()) {

        }

        backLeft.setPower(0);
        backRight.setPower(0);
        frontLeft.setPower(0);
        frontRight.setPower(0);

        backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

    }

    public void pick() throws InterruptedException {

        opreste();

        Thread.sleep(100);
        servo.setPosition(0.5);

    }

    public void arunca() throws InterruptedException {

        opreste();

        Thread.sleep(100);
        servo.setPosition(0);

    }

    public void opreste(){
        frontLeft.setPower(0);
        frontRight.setPower(0);
        backLeft.setPower(0);
        backRight.setPower(0);
    }
}