package sk.bielik.webProject.response;

public class SignInResponse {

   private boolean success;

    private String message;

    private String webPage;

    public SignInResponse() {
    }

    public SignInResponse(boolean success, String message, String webPage) {
        this.success = success;
        this.message = message;
        this.webPage = webPage;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getWebPage() {
        return webPage;
    }

    public void setWebPage(String webPage) {
        this.webPage = webPage;
    }
}
