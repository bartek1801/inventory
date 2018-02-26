package pl.com.bottega.inventory.extraPackage.state_pattern;

public class Robot {

    private RobotState state;

    private Position position;

    public Robot() {
        this.state = new NorthState();
        this.position = new Position(0,0);
    }

    public void move(){

    }

    public void rotate(){
        state.doRotate(this);
    }


    public RobotState getState() {
        return state;
    }

    public void setState(RobotState state) {
        this.state = state;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }



    private class Position{

        private int x;
        private int y;


        private Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
        private Position() {
            this.x = 0;
            this.y = 0;
        }
    }


}
