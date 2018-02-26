package pl.com.bottega.inventory.extraPackage.state_pattern;

public class SouthState implements RobotState {
    @Override
    public void doRotate(Robot robot) {
        robot.setState(new WestState());
    }
}
