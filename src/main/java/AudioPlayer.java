import javax.sound.sampled.*;
import java.io.File;

public class AudioPlayer implements LineListener{

    boolean playCompleted = false;

    public boolean playClip(String chord)  {

        File audioFile = new File("./src/test/soundClips/cChord.wav");

        try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            AudioFormat format = audioStream.getFormat();
            DataLine.Info info = new DataLine.Info(Clip.class, format);
            Clip audioClip = (Clip) AudioSystem.getLine(info);

            audioClip.addLineListener(this);
            audioClip.open(audioStream);
            audioClip.start();

            while (!playCompleted) {
                // wait for the playback completes
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }

            playCompleted = false;
            audioClip.close();
            return true;

        } catch (Exception ex) {
            playCompleted = false;
            return false;
        }
    }

    @Override
    public void update(LineEvent event) {
        LineEvent.Type type = event.getType();
        if (type == LineEvent.Type.STOP) {
            playCompleted = true;
        }
    }
}
