package Util;

public class JsonResponse<T> {
	private boolean success;
    private int statusCode;
    private String message;
    private T result;

    public JsonResponse() {}

    public JsonResponse(boolean success, int statusCode, String message, T result) {
        this.success = success;
        this.statusCode = statusCode;
        this.message = message;
        this.result = result;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}
