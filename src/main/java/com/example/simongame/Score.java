package com.example.simongame;

import javafx.scene.layout.StackPane;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

public class Score {
    private ImageView[] numberScore = new ImageView[0];
    private final StackPane stackPane;
    private final ImageView firstNumberScore;
    private final String path;
    public Score(StackPane stackPane, ImageView firstNumberScore, String path) {
        this.firstNumberScore = firstNumberScore;
        this.stackPane = stackPane;
        this.path = path;
    }
    private void expandArray() {
        SimonGame simonGame = new SimonGame();
        int length = numberScore.length;
        deleteScore();
        numberScore = new ImageView[length + 1];
        for(int i = 0; i < numberScore.length; i++) {
            numberScore[i] = new ImageView();
            stackPane.getChildren().add(numberScore[i]);
            numberScore[i].setPreserveRatio(true);
            numberScore[i].setSmooth(true);
            numberScore[i].setFitWidth(simonGame.getDpX(67));
            numberScore[i].setFitHeight(simonGame.getDpY(94));
            StackPane.setAlignment(numberScore[i], Pos.CENTER_RIGHT);
            int INDENT = 40 + 75;
            StackPane.setMargin(numberScore[i],
                    new Insets(0, simonGame.getDpX(INDENT + simonGame.getDpX((i) * 67)),
                            simonGame.getDpY(160), 0));
        }
    }
    private int[] breakNumber(int source) {
        String stringSource = Integer.toString(source);
        int[] destination = new int[stringSource.length()];
        for(int i = 0; i < destination.length; i++)
            destination[i] = Integer.parseInt(stringSource.substring(stringSource.length() - 1 - i,
                    stringSource.length() - i));
        if(numberScore.length < destination.length - 1)
            expandArray();
        return destination;
    }
    private void assignNumbers(ImageView image, int number) {
        switch(number) {
            case 0 -> image.setImage(new Image("file:" + path + "/Image/Simon/Numbers/Zero.png"));
            case 1 -> image.setImage(new Image("file:" + path + "/Image/Simon/Numbers/One.png"));
            case 2 -> image.setImage(new Image("file:" + path + "/Image/Simon/Numbers/Two.png"));
            case 3 -> image.setImage(new Image("file:" + path + "/Image/Simon/Numbers/Three.png"));
            case 4 -> image.setImage(new Image("file:" + path + "/Image/Simon/Numbers/Four.png"));
            case 5 -> image.setImage(new Image("file:" + path + "/Image/Simon/Numbers/Five.png"));
            case 6 -> image.setImage(new Image("file:" + path + "/Image/Simon/Numbers/Six.png"));
            case 7 -> image.setImage(new Image("file:" + path + "/Image/Simon/Numbers/Seven.png"));
            case 8 -> image.setImage(new Image("file:" + path + "/Image/Simon/Numbers/Eight.png"));
            case 9 -> image.setImage(new Image("file:" + path + "/Image/Simon/Numbers/Nine.png"));
        }
    }
    public void setupScore(int score) {
        int[] arraySource = breakNumber(score);
        assignNumbers(firstNumberScore, arraySource[0]);
        for(int i = 0; i < numberScore.length; i++)
            assignNumbers(numberScore[i], arraySource[i + 1]);
    }
    public void deleteScore() {
        for(ImageView image : numberScore)
            stackPane.getChildren().remove(image);
        numberScore = new ImageView[0];
    }
}
