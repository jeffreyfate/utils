package com.jeffthefate.utils.json;

/**
 * Created by Jeff on 6/28/2014.
 */
public class Question {

    private String answer;
    private String category;
    private String question;
    private int score;
    private int trivia;
    private String createdAt;
    private String updatedAt;
    private String objectId;

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public int getScore() {
        if (score == 0) {
            return 1000;
        }
        else {
            return score;
        }
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getTrivia() {
        return trivia;
    }

    public void setTrivia(int trivia) {
        this.trivia = trivia;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    @Override
    public String toString(){
        return "answer: " + getAnswer() + ", category: " + getCategory() +
                ", question: " + getQuestion() + ", score: " + getScore() +
                ", trivia: " + getTrivia() + ", createdAt: " + getCreatedAt()
                + ", updatedAt: " + getUpdatedAt() + ", objectId: " +
                getObjectId();
    }

}
