package air.found.payprowebbackend.core;

import lombok.Getter;

@Getter
public enum ApiError {
    ERR_INVALID_REQUEST(1, "ERR_INVALID_REQUEST", "Your request is invalid!"),
    ERR_INVALID_OR_EXPIRED_REFRESH_TOKEN(2, "ERR_INVALID_OR_EXPIRED_REFRESH_TOKEN", "Refresh token is invalid or expired!"),
    ERR_INVALID_CREDENTIALS(3, "ERR_INVALID_CREDENTIALS", "The provided credentials are invalid!"),
    ERR_INVALID_EMAIL_FORMAT(4, "ERR_INVALID_EMAIL_FORMAT", "The email address you entered is not in a valid format. Please enter a valid email address!");

    private final int errorCode;
    private final String errorName;
    private final String errorMessage;

    ApiError(int errorCode, String errorName, String errorMessage) {
        this.errorCode = errorCode;
        this.errorName = errorName;
        this.errorMessage = errorMessage;
    }
}

