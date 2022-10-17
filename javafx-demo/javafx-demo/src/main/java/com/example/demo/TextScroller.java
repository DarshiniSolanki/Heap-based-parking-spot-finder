package com.example.demo;

import javafx.application.Platform;
import javafx.scene.control.Label;

public class TextScroller implements Runnable {

    public TextScroller(int wordsPerMinute, String[] scrollArray, int numberOfWords, Label currentText) {
        this.wordsPerMinute = wordsPerMinute;
        this.scrollArray = scrollArray;
        this.numberOfWords = numberOfWords;
        this.currentText = currentText;
    }

    private boolean stop;
    private int wordsPerMinute;
    private int scrollPosition=0;
    private String[] scrollArray;
    private int numberOfWords;
    private Label currentText;

        @Override
        public void run() {
            while (!stop){
                int wordsPerSec = this.wordsPerMinute / 60;
                long repeatInterval = wordsPerSec * 1000;
                if (this.scrollPosition < this.scrollArray.length) {
                    //System.out.println("repeat interval: " + repeatInterval);
                    String currentScroll = "";
                    for (int i = this.scrollPosition; i < this.scrollPosition + this.numberOfWords; i++) {
                        if (i > this.scrollArray.length) {
                            i = this.scrollArray.length;
                        }
                        currentScroll += this.scrollArray[i];
                        currentScroll += " ";
                    }
                    String finalCurrentScroll = currentScroll;
                    Platform.runLater(()->{
                        this.scrollPosition+=this.numberOfWords;
                        this.currentText.setText(finalCurrentScroll);
                    });
                    try{
                        Thread.sleep(repeatInterval);}
                    catch (Exception exc){
                        this.currentText.setText(Utility.getExceptionAsString(exc));
                    }
                }
            }
    }

    public void stop(){
            this.stop=true;
    }
}
