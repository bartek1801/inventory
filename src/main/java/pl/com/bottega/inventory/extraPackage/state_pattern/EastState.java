package pl.com.bottega.inventory.extraPackage.state_pattern;

public class EastState implements RobotState {
    @Override
    public void doRotate(Robot robot) {
        robot.setState(new SouthState());
    }
}
