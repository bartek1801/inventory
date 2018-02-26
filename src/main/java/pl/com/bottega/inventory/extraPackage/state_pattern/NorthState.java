package pl.com.bottega.inventory.extraPackage.state_pattern;

public class NorthState implements RobotState {


    @Override
    public void doRotate(Robot robot) {
        robot.setState(new EastState());
    }
}
