package Snake;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Level2 extends Levels{

    Level2(GameFrame gameFrame) {
        super(gameFrame, gamePanel);
        //TODO Auto-generated constructor stub
    }
    @Override
    public void Img() {
        // read img
        try {
            level = ImageIO.read(new File(
                    "C:\\Users\\antho\\Desktop\\T-JAV-501-MPL_5\\T-JAV-501-MPL_5\\Snake\\ressources\\level2.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
