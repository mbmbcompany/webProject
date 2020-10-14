package sk.bielik.webProject.response;

import javax.servlet.http.HttpServletResponse;

public class AddProductToTrolleyResponse {

    private boolean success;

    private String message;

    private String webPage;

    public AddProductToTrolleyResponse() {
    }

    public AddProductToTrolleyResponse(boolean success, String message, String webPage) {
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
