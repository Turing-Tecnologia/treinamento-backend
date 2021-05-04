package com.turingtecnologia.albatroz.backendalbatroz.exceptions.error;

import lombok.Data;

@Data
public class ResourceNotAcceptableDetails {
    private String title;
    private int status;
    private String detail;
    private long timeStamp;

    public static final class Builder {
        private String title;
        private int status;
        private String detail;
        private long timeStamp;

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

        public ResourceNotAcceptableDetails build() {
            ResourceNotAcceptableDetails resourceNotAcceptableDetails = new ResourceNotAcceptableDetails();
            resourceNotAcceptableDetails.timeStamp = this.timeStamp;
            resourceNotAcceptableDetails.title = this.title;
            resourceNotAcceptableDetails.status = this.status;
            resourceNotAcceptableDetails.detail = this.detail;
            return resourceNotAcceptableDetails;
        }
    }
}
