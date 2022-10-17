package com.example.demo;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class HelloController {

    @FXML
    Label txtNumOfWords;

    int numberOfWords = 3;

    @FXML
    Label txtWordsPerMinute;

    int wordsPerMinute = 60;
    String[] scrollArray;
    boolean isScrolling=true;

    Thread scrollingThread;

    @FXML
    private Label currentText;

    @FXML
    private TextArea inputText;

    @FXML
    protected void onStartButtonClick() {
        this.isScrolling=this.isScrolling?false:true;

        if(this.inputText.getText().length()>=0){
            this.scrollArray = this.inputText.getText().trim().split(" ");
            startScrolling();
        }
    }

    @FXML
    protected void onStopButtonClick() {
        this.isScrolling=this.isScrolling?false:true;
        scrollingThread.stop();
        scrollingThread=null;
    }

    public  void startScrolling() {
        scrollingThread = new Thread(new TextScroller(wordsPerMinute,scrollArray,numberOfWords,currentText));
        scrollingThread.setDaemon(true);
        scrollingThread.start();
    }

    @FXML
    public  void decreaseWpm(){
        if(this.wordsPerMinute>30){
            this.wordsPerMinute-=30;
            Platform.runLater(()->{
                txtWordsPerMinute.setText(String.format("Words Per Minute: ", this.wordsPerMinute));
            });
        }

    }

    @FXML
    public  void increaseWpm(){
        if(this.wordsPerMinute<=2400){
            this.wordsPerMinute+=30;
            Platform.runLater(()-> {

                txtWordsPerMinute.setText(String.format("Words Per Minute: ", this.wordsPerMinute));
            });
        }
    }

    @FXML
    public void decreaseNumberOfWords(){
        if(this.numberOfWords>0)
        {
            this.numberOfWords-=1;
            Platform.runLater(()-> {

                txtNumOfWords.setText(String.format("Number of Words per glance: ", this.wordsPerMinute));
            });

        }
    }

    @FXML
    public void increaseNumberOfWords(){
        if(this.numberOfWords<=20)
        {this.numberOfWords+=1;
            Platform.runLater(()-> {

                txtNumOfWords.setText(String.format("Number of Words per glance: ", this.wordsPerMinute));
            });
        }
    }
}