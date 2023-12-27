package air.found.payprowebbackend.core;

import lombok.Getter;

@Getter
public enum ApiError {
    ERR_INVALID_REQUEST(1, "ERR_INVALID_REQUEST", "Your request is invalid!");

    private final int errorCode;
    private final String errorName;
    private final String errorMessage;

    ApiError(int errorCode, String errorName, String errorMessage) {
        this.errorCode = errorCode;
        this.errorName = errorName;
        this.errorMessage = errorMessage;
    }
}

