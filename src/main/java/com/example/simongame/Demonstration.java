package com.example.simongame;

import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import java.util.Iterator;
import java.util.Queue;

public class Demonstration extends Thread {
    ImageView greenButton;
    ImageView redButton;
    ImageView blueButton;
    ImageView yellowButton;
    String rsc;
    Queue<Integer> trueSubsequence;
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
                litUpButton(iterator.next());
                if(iterator.hasNext())
                    Thread.sleep(500);
            }
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
    }
    private void litUpButton(int indexButton) throws InterruptedException {
        switch (indexButton) {
            case 0 -> {
                greenButton.setImage(new Image(rsc + "\\Image\\Simon\\Green\\GreenLitUp.png"));
                Thread.sleep(1300);
                greenButton.setImage(new Image(rsc + "\\Image\\Simon\\Green\\GreenDefault.png"));
            }
            case 1 -> {
                redButton.setImage(new Image(rsc + "\\Image\\Simon\\Red\\RedLitUp.png"));
                Thread.sleep(1300);
                redButton.setImage(new Image(rsc + "\\Image\\Simon\\Red\\RedDefault.png"));
            }
            case 2 -> {
                blueButton.setImage(new Image(rsc + "\\Image\\Simon\\Blue\\BlueLitUp.png"));
                Thread.sleep(1300);
                blueButton.setImage((new Image(rsc + "\\Image\\Simon\\Blue\\BlueDefault.png")));
            }
            case 3 -> {
                yellowButton.setImage(new Image(rsc + "\\Image\\Simon\\Yellow\\YellowLitUp.png"));
                Thread.sleep(1300);
                yellowButton.setImage(new Image(rsc + "\\Image\\Simon\\Yellow\\YellowDefault.png"));
            }
        }
    }
}
