package sample;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import javax.sound.sampled.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Timer;
import java.util.TimerTask;

public class Controller {
    PlayMusic musicPlayer = new PlayMusic();
    public Label bpmLabel;
    @FXML
    ImageView leftButton;
    @FXML
    ImageView rightButton;
    public boolean leftButtonPressed = false;
    public boolean rightButtonPressed = false;
    public int trackNumber = 1; // denotes the selected track
    public boolean[] tracksMuted = new boolean[4];
    public static int[] timeSignature = new int[]{4,4};
    int numberOfBPMClicks = -1;
    long lastClicked = 0;
    long timeSinceLastClicked = 0;
    long totalBPMTime = 0;
    private static int BPM = 120;
    public static long loopLengthMillis = (60000 / BPM * (timeSignature[0])) * 8;
    public boolean startPlaying = false;
    Timer bpmTime = new Timer();
    TimerTask timedPlay = new TimerTask() {
        @Override
        public void run() {
            if ( !tracksMuted[0] && Files.exists(Path.of(("C:/Users/bangsom/IdeaProjects/WhaleLooper/src/sample/Audio Files/TrackOne/RecordAudio.wav")))) {
                musicPlayer.executePlay("C:/Users/bangsom/IdeaProjects/WhaleLooper/src/sample/Audio Files/TrackOne/RecordAudio.wav");
            }
            if ( !tracksMuted[2] && Files.exists(Path.of(("C:/Users/bangsom/IdeaProjects/WhaleLooper/src/sample/Audio Files/TrackThree/RecordAudio.wav")))) {
                musicPlayer.executePlay("C:/Users/bangsom/IdeaProjects/WhaleLooper/src/sample/Audio Files/TrackThree/RecordAudio.wav");
            }
            if ( !tracksMuted[1] && Files.exists(Path.of(("C:/Users/bangsom/IdeaProjects/WhaleLooper/src/sample/Audio Files/TrackTwo/RecordAudio.wav")))) {
                musicPlayer.executePlay("C:/Users/bangsom/IdeaProjects/WhaleLooper/src/sample/Audio Files/TrackTwo/RecordAudio.wav");
            }
            if ( !tracksMuted[3] && Files.exists(Path.of(("C:/Users/bangsom/IdeaProjects/WhaleLooper/src/sample/Audio Files/TrackFour/RecordAudio.wav")))) {
                musicPlayer.executePlay("C:/Users/bangsom/IdeaProjects/WhaleLooper/src/sample/Audio Files/TrackFour/RecordAudio.wav");
            }
        }
    };

    public Controller() throws IOException {
    }

    public void leftButtonClicked(MouseEvent mouseEvent) {
        System.out.println();
        leftButtonPressed = !(leftButtonPressed);
        if (leftButtonPressed && !rightButtonPressed) {
            leftButton.setImage(new Image("sample/WhaleImg/buttonclose.png"));
        } else {
            leftButton.setImage(new Image("sample/WhaleImg/buttonopen.png"));
        }
    }
    public void rightButtonClicked(MouseEvent mouseEvent) throws IOException {
        if (Files.exists(Path.of("C:/Users/bangsom/IdeaProjects/WhaleLooper/src/sample/Audio Files/RecordAudio.wav"))) {
            MergeAudio merger = new MergeAudio(trackNumber, ("C:/Users/bangsom/IdeaProjects/WhaleLooper/src/sample/Audio Files/RecordAudio.wav"));
            merger.mainClip();
            bpmTime.scheduleAtFixedRate(timedPlay, 0, loopLengthMillis);
            rightButton.setImage(new Image("sample/WhaleImg/buttonopen.png"));
        } else {
            rightButtonPressed = !(rightButtonPressed);
            if (rightButtonPressed && !leftButtonPressed) {
                rightButton.setImage(new Image("sample/WhaleImg/buttonclose.png"));
                final JavaSoundRecorder recorder = new JavaSoundRecorder();

                // creates a new thread that waits for a specified
                // of time before stopping
                Thread stopper = new Thread(new Runnable() {
                    public void run() {
                        try {
                            Thread.sleep(loopLengthMillis);
                            startPlaying = true;
                        } catch (InterruptedException ex) {
                            ex.printStackTrace();
                        }
                        recorder.finish();
                    }
                });

                stopper.start();

                // start recording
                recorder.start();
            } else {
                rightButton.setImage(new Image("sample/WhaleImg/buttonopen.png"));
            }
        }

    }

    AudioFormat getAudioFormat() {
        float sampleRate = 16000;
        int sampleSizeInBits = 8;
        int channels = 2;
        boolean signed = true;
        boolean bigEndian = true;
        AudioFormat format = new AudioFormat(sampleRate, sampleSizeInBits,
                channels, signed, bigEndian);
        return format;
    }

    public void bpmButtonClicked(MouseEvent mouseEvent) {
        numberOfBPMClicks += 1;

        if (lastClicked != 0) {
            timeSinceLastClicked = System.currentTimeMillis() - lastClicked;
        }
        if (timeSinceLastClicked > 2000) {
            numberOfBPMClicks = -1;
            lastClicked = 0;
            totalBPMTime = 0;
            timeSinceLastClicked = 0;
        } else {
            lastClicked = System.currentTimeMillis();
            totalBPMTime += timeSinceLastClicked;
            if (numberOfBPMClicks > 1) {
                BPM = (int) (60000 / (totalBPMTime / numberOfBPMClicks));
                System.out.println("BPM = " + BPM);
                bpmLabel.textProperty().bind(new SimpleIntegerProperty(BPM).asString());
                loopLengthMillis = (60000 / BPM * (timeSignature[0])) * 8;
            }
        }

    }

    public void t1ButtonClicked(MouseEvent mouseEvent) {
        if (trackNumber == 1) {
            tracksMuted[0] = !tracksMuted[0];
        } else {
            trackNumber = 1;
        }
    }

    public void t4ButtonClicked(MouseEvent mouseEvent) {
        if (trackNumber == 2) {
            tracksMuted[1] = !tracksMuted[1];
        } else {
            trackNumber = 2;
        }
    }

    public void t3ButtonClicked(MouseEvent mouseEvent) {
        if (trackNumber == 3) {
            tracksMuted[2] = !tracksMuted[2];
        } else {
            trackNumber = 3;
        }
    }

    public void t2ButtonClicked(MouseEvent mouseEvent) {
        if (trackNumber == 4) {
            tracksMuted[3] = !tracksMuted[3];
        } else {
            trackNumber = 4;
        }
    }

}
