package com.example.simongame;

import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import java.util.Iterator;
import java.util.Queue;

public class Demonstration extends Thread {
    private final ImageView greenButton;
    private final ImageView redButton;
    private final ImageView blueButton;
    private final ImageView yellowButton;
    private final String rsc;
    private final Queue<Integer> trueSubsequence;
    public Demonstration(
            String rsc,
            ImageView greenButton,
            ImageView redButton,
            ImageView blueButton,
            ImageView yellowButton,
            Queue<Integer> trueSubsequence
    ) {
        this.rsc = rsc;
        this.greenButton = greenButton;
        this.redButton = redButton;
        this.blueButton = blueButton;
        this.yellowButton = yellowButton;
        this.trueSubsequence = trueSubsequence;
    }
    @Override
    public void run() {
        super.run();
        demonstration();
    }
    public void demonstration() {
        Iterator<Integer> iterator = trueSubsequence.iterator();
        try {
            while (iterator.hasNext()) {
                Thread.sleep(500);
                litUpButton(iterator.next());
            }
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
    }
    private void litUpButton(int indexButton) throws InterruptedException {
        switch (indexButton) {
            case 0 -> {
                greenButton.setImage(new Image(rsc + "\\Image\\Simon\\Green\\GreenLitUp.png"));
                PlaySound.play(Sounds.PLAY_BUTTON_ZERO);
                Thread.sleep(1300);
                greenButton.setImage(new Image(rsc + "\\Image\\Simon\\Green\\GreenDefault.png"));
            }
            case 1 -> {
                redButton.setImage(new Image(rsc + "\\Image\\Simon\\Red\\RedLitUp.png"));
                PlaySound.play(Sounds.PLAY_BUTTON_ONE);
                Thread.sleep(1300);
                redButton.setImage(new Image(rsc + "\\Image\\Simon\\Red\\RedDefault.png"));
            }
            case 2 -> {
                blueButton.setImage(new Image(rsc + "\\Image\\Simon\\Blue\\BlueLitUp.png"));
                PlaySound.play(Sounds.PLAY_BUTTON_TWO);
                Thread.sleep(1300);
                blueButton.setImage((new Image(rsc + "\\Image\\Simon\\Blue\\BlueDefault.png")));
            }
            case 3 -> {
                yellowButton.setImage(new Image(rsc + "\\Image\\Simon\\Yellow\\YellowLitUp.png"));
                PlaySound.play(Sounds.PLAY_BUTTON_THREE);
                Thread.sleep(1300);
                yellowButton.setImage(new Image(rsc + "\\Image\\Simon\\Yellow\\YellowDefault.png"));
            }
        }
    }
}