package com.example.simongame;

import javafx.scene.media.MediaPlayer;
import javafx.scene.media.Media;
import java.io.File;

public class PlaySound {
    static private MediaPlayer mediaPlayer;
    static public void play(Sounds sound) {
        String path = new File("src/main/resources/Sounds/").getAbsolutePath();
        try {
            switch (sound) {
                case PLAY_BUTTON_ZERO -> mediaPlayer = new MediaPlayer(new Media(new File(path + "/button1.mp3").toURI().toString()));
                case PLAY_BUTTON_ONE -> mediaPlayer = new MediaPlayer(new Media(new File(path + "/button2.mp3").toURI().toString()));
                case PLAY_BUTTON_TWO -> mediaPlayer = new MediaPlayer(new Media(new File(path + "/button3.mp3").toURI().toString()));
                case PLAY_BUTTON_THREE -> mediaPlayer = new MediaPlayer(new Media(new File(path + "/button4.mp3").toURI().toString()));
                case PLAY_TAP -> mediaPlayer = new MediaPlayer(new Media(new File(path + "/tap.mp3").toURI().toString()));
                case PLAY_GAME_OVER -> mediaPlayer = new MediaPlayer(new Media(new File(path + "/lose.wav").toURI().toString()));
                case PLAY_SAVE -> mediaPlayer = new MediaPlayer(new Media(new File(path + "/save.mp3").toURI().toString()));
            }
            mediaPlayer.play();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    static public void stop() {
        mediaPlayer.stop();
    }
}
