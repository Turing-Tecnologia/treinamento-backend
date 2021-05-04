package com.turingtecnologia.albatroz.backendalbatroz.exceptions.error;

public class ResourceNotFoundDetails extends ErrorDetaill {
    public static final class Builder {
        private String title;
        private int status;
        private String detail;
        private long timeStamp;
        private String developerMessage;

        private Builder() {
        }

        public static Builder newBuilder() {
            return new Builder();
        }

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

        public ResourceNotFoundDetails build() {
            ResourceNotFoundDetails resourceNotFoundDetails = new ResourceNotFoundDetails();
            resourceNotFoundDetails.setDeveloperMessage(developerMessage);
            resourceNotFoundDetails.setDetail(detail);
            resourceNotFoundDetails.setTitle(title);
            resourceNotFoundDetails.setStatus(status);
            resourceNotFoundDetails.setTimeStamp(timeStamp);
            return resourceNotFoundDetails;
        }
    }
}
