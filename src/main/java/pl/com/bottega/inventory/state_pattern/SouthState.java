package pl.com.bottega.inventory.state_pattern;

public class SouthState implements RobotState {
    @Override
    public void doRotate(Robot robot) {
        robot.setState(new WestState());
    }
}
