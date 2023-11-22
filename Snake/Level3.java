package Snake;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Level3 extends Levels {
    Level3(GameFrame gameFrame) {
        super(gameFrame, gamePanel);
        
    }
    @Override
    public void Img() {
        // read img
        try {
            level = ImageIO.read(new File(
                    ".\\T-JAV-501-MPL_5\\Snake\\ressources\\level3.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

