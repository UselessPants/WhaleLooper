package sample;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

public class MergeAudio {
    File trackOne = new File(("C:/Users/bangsom/IdeaProjects/WhaleLooper/src/sample/Audio Files/TrackOne/RecordAudio.wav"));
    int trackNumber;
    String filePath;
    MergeAudio(int trackNumber, String filePath) {
        this.filePath = filePath;
        this.trackNumber = trackNumber;
    }
    void mainClip() {
        if (trackNumber == 1) {
            if (Files.exists(Path.of("C:\\Users\\bangsom\\IdeaProjects\\WhaleLooper\\src\\sample\\Audio Files\\TrackOne\\RecordAudio.wav"))) {

            } else {
                trackOne.renameTo(new File("C:\\Users\\bangsom\\IdeaProjects\\WhaleLooper\\src\\sample\\Audio Files\\TrackOne\\RecordAudio.wav"));
            }
        }
        if (trackNumber == 2) {
            if (Files.exists(Path.of("C:\\Users\\bangsom\\IdeaProjects\\WhaleLooper\\src\\sample\\Audio Files\\TrackTwo\\RecordAudio.wav"))) {

            } else {
                trackOne.renameTo(new File("C:\\Users\\bangsom\\IdeaProjects\\WhaleLooper\\src\\sample\\Audio Files\\TrackTwo\\RecordAudio.wav"));
            }
        }
        if (trackNumber == 3) {
            if (Files.exists(Path.of("C:\\Users\\bangsom\\IdeaProjects\\WhaleLooper\\src\\sample\\Audio Files\\TrackThree\\RecordAudio.wav"))) {

            } else {
                trackOne.renameTo(new File("C:\\Users\\bangsom\\IdeaProjects\\WhaleLooper\\src\\sample\\Audio Files\\TrackThree\\RecordAudio.wav"));
            }
        }
        if (trackNumber == 4) {
            if (Files.exists(Path.of("C:\\Users\\bangsom\\IdeaProjects\\WhaleLooper\\src\\sample\\Audio Files\\TrackFour\\RecordAudio.wav"))) {

            } else {
                trackOne.renameTo(new File("C:\\Users\\bangsom\\IdeaProjects\\WhaleLooper\\src\\sample\\Audio Files\\TrackFour\\RecordAudio.wav"));
            }
        }
    }
}
