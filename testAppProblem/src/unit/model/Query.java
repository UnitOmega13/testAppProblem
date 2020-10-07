package unit.model;

import java.time.LocalDate;

public class Query {
    String serviceId;
    String questionTypeId;
    String answerType;
    LocalDate start;
    LocalDate end;

    public Query(String serviceId, String questionTypeId, String answerType, LocalDate start, LocalDate end) {
        this.serviceId = serviceId;
        this.questionTypeId = questionTypeId;
        this.answerType = answerType;
        this.start = start;
        this.end = end;
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

    public LocalDate getStart() {
        return start;
    }

    public void setStart(LocalDate start) {
        this.start = start;
    }

    public LocalDate getEnd() {
        return end;
    }

    public void setEnd(LocalDate end) {
        this.end = end;
    }
}
