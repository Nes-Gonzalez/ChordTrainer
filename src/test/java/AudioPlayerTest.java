import org.junit.Test;
import static org.junit.Assert.*;

public class AudioPlayerTest {
    AudioPlayer player = new AudioPlayer();

    @Test
    public void testCanary() {
        assert true;
    }

    @Test
    public void testAudioClip() {
        String chord = "cChord";
        assert player.playClip(chord);
    }

}