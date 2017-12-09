package estateagency.dto.realtors;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

public class ChangePasswordForm {

    @NotNull
    @NotEmpty(message = "{message.errors.empty_field}")
    private String password;

    public ChangePasswordForm() {
    }

    public ChangePasswordForm(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
