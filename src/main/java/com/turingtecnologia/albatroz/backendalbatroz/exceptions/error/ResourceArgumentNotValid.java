package com.turingtecnologia.albatroz.backendalbatroz.exceptions.error;

import lombok.Data;

@Data
public class ResourceArgumentNotValid extends ErrorDetaill {
    private String field;
    private String fieldMessage;

    public static final class Builder {
        private String title;
        private int status;
        private String detail;
        private long timeStamp;
        private String field;
        private String fieldMessage;

        private Builder() {
        }

        public static Builder newBuilder() {return new Builder();}

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder status(int status) {
            this.status = status;
            return this;
        }

        public Builder detail(String detail) {
            this.detail = detail;
            return this;
        }

        public Builder timeStamp(long timeStamp) {
            this.timeStamp = timeStamp;
            return this;
        }

        public Builder field(String field) {
            this.field = field;
            return this;
        }

        public Builder fieldMessage(String fieldMessage) {
            this.fieldMessage = fieldMessage;
            return this;
        }

        public ResourceArgumentNotValid build() {
            ResourceArgumentNotValid resourceArgumentNotValid = new ResourceArgumentNotValid();
            resourceArgumentNotValid.setDetail(detail);
            resourceArgumentNotValid.setStatus(status);
            resourceArgumentNotValid.setTimeStamp(timeStamp);
            resourceArgumentNotValid.setTitle(title);
            resourceArgumentNotValid.setField(field);
            resourceArgumentNotValid.setFieldMessage(fieldMessage);
            return resourceArgumentNotValid;
        }
    }
}
