package unit.model;

import java.time.LocalDate;

public class WTimeline {
    String serviceId;
    String questionTypeId;
    String answerType;
    LocalDate date;
    String time;

    public WTimeline(String serviceId, String questionTypeId, String answerType, LocalDate date, String time) {
        this.serviceId = serviceId;
        this.questionTypeId = questionTypeId;
        this.answerType = answerType;
        this.date = date;
        this.time = time;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getQuestionTypeId() {
        return questionTypeId;
    }

    public void setQuestionTypeId(String questionTypeId) {
        this.questionTypeId = questionTypeId;
    }

    public String getAnswerType() {
        return answerType;
    }

    public void setAnswerType(String answerType) {
        this.answerType = answerType;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
