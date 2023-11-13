package Snake;

import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Main{

    public static void main(String[] args) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        GameFrame frame = new GameFrame();
        frame.showStartMenu(); // Affiche le menu de démarrage
    }


}