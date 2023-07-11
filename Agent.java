import java.util.Random;

public class Agent {
    public int x, y;
    public String name;
    public Environment environment;
    private static double[][] qTable = new double[5][10];
    private static final double LEARNING_RATE = 0.5;
    private static final double DISCOUNT_FACTOR = 0.9;
    private Random random = new Random();

    public int amountsOfMovements = 0;

    public Agent(String name, int x, int y, Environment environment) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.environment = environment;
    }

    // movement methods...
    // reachedExit method...
    public void moveUp() {
        if (x > 0) x--;
    }

    public void moveDown() {
        if (x < 4) x++;

    }

    public void moveLeft() {
        if (y > 0) y--;

    }

    public void moveRight() {
        if (y < 9) y++;

    }

    public boolean reachedExit() {
        return environment.isExit(x, y);
    }

    public void updateQValue(int prevX, int prevY, int reward) {
        double oldQValue = qTable[prevX][prevY];
        double maxQValue = getMaxQValue(x, y);
        double newQValue = (1 - LEARNING_RATE) * oldQValue + LEARNING_RATE * (reward + DISCOUNT_FACTOR * maxQValue);
        qTable[prevX][prevY] = newQValue;
    }

    public double getMaxQValue(int x, int y) {
        double up = (x > 0) ? qTable[x-1][y] : -Double.MAX_VALUE;
        double down = (x < 4) ? qTable[x+1][y] : -Double.MAX_VALUE;
        double left = (y > 0) ? qTable[x][y-1] : -Double.MAX_VALUE;
        double right = (y < 9) ? qTable[x][y+1] : -Double.MAX_VALUE;
        return Math.max(Math.max(up, down), Math.max(left, right));
    }

    public void chooseAction() {
        if (random.nextDouble() < 0.5) {
            // exploration: choose a random action
            int action = random.nextInt(4);
            switch (action) {
                case 0:
                    moveUp();
                    break;
                case 1:
                    moveDown();
                    break;
                case 2:
                    moveLeft();
                    break;
                case 3:
                    moveRight();
                    break;
            }
        } else {
            // exploitation: choose the action with the highest Q-value
            double up = (x > 0) ? qTable[x-1][y] : -Double.MAX_VALUE;
            double down = (x < 4) ? qTable[x+1][y] : -Double.MAX_VALUE;
            double left = (y > 0) ? qTable[x][y-1] : -Double.MAX_VALUE;
            double right = (y < 9) ? qTable[x][y+1] : -Double.MAX_VALUE;
            double maxQValue = Math.max(Math.max(up, down), Math.max(left, right));
            if (maxQValue == up) {
                moveUp();
            } else if (maxQValue == down) {
                moveDown();
            } else if (maxQValue == left) {
                moveLeft();
            } else if (maxQValue == right) {
                moveRight();
            }
        }
    }

    public void increaseAmountOfMovements(int amount){

        this.amountsOfMovements += amount;

    }

    public void resetAgent(int posX, int posY, int amountsOfMovements){

        this.x = posX;
        this.y = posY;
        this.amountsOfMovements = amountsOfMovements;

    }
}
