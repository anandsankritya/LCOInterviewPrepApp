package com.interviewprepapp.lco.lcointerviewprepapp;

/**
 * Created by anandsankritya on 11/06/18.
 */

public class QuestionsAndAnswers {
    String question;
    String answer;

    public QuestionsAndAnswers(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
