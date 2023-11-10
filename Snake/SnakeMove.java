package Snake;

public class SnakeMove {
    public static void move(int bodyParts, int[] x, int[] y, int UNIT_SIZE, char direction) {
        // moving each bodypart of the snake
        for (int i = bodyParts; i > 0; i--) {
            x[i] = x[i - 1];
            y[i] = y[i - 1];
            if(x[i] == 40 && y[i] == 0){
                y[0] = y[0] + UNIT_SIZE;
            }
        }

        switch (direction) {
            case 'U':
                y[0] = y[0] - UNIT_SIZE;
                break;
            case 'D':
                y[0] = y[0] + UNIT_SIZE;
                break;
            case 'L':
                x[0] = x[0] - UNIT_SIZE;
                break;
            case 'R':
                x[0] = x[0] + UNIT_SIZE;
                break;
            default:
                break;
        }
        
    }
}
