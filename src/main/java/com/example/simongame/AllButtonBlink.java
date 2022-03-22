package com.example.simongame;

import javafx.scene.image.ImageView;
import javafx.scene.image.Image;

public class AllButtonBlink extends Thread{
    private final ImageView yellowButton;
    private final ImageView greenButton;
    private final ImageView blueButton;
    private final ImageView redButton;
    private final String rsc;
    public AllButtonBlink(
            String rsc,
            ImageView greenButton,
            ImageView redButton,
            ImageView blueButton,
            ImageView yellowButton
    ) {
        this.rsc = rsc;
        this.greenButton = greenButton;
        this.redButton = redButton;
        this.blueButton = blueButton;
        this.yellowButton = yellowButton;
    }
    @Override
    public void run() {
        super.run();
        greenButton.setImage(new Image("file:" + rsc + "/Image/Simon/Green/GreenLitUp.png"));
        redButton.setImage(new Image("file:" + rsc + "/Image/Simon/Red/RedLitUp.png"));
        blueButton.setImage(new Image("file:" + rsc + "/Image/Simon/Blue/BlueLitUp.png"));
        yellowButton.setImage(new Image("file:" + rsc + "/Image/Simon/Yellow/YellowLitUp.png"));
        greenButton.setImage(new Image("file:" + rsc + "/Image/Simon/Green/GreenLitUp.png"));
        try {
            Thread.sleep(700);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        greenButton.setImage(new Image("file:" + rsc + "/Image/Simon/Green/GreenDefault.png"));
        redButton.setImage(new Image("file:" + rsc + "/Image/Simon/Red/RedDefault.png"));
        blueButton.setImage(new Image("file:" + rsc + "/Image/Simon/Blue/BlueDefault.png"));
        yellowButton.setImage(new Image("file:" + rsc + "/Image/Simon/Yellow/YellowDefault.png"));
    }
}
