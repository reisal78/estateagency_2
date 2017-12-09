package estateagency.dto.realtors;

import estateagency.model.Authority;
import estateagency.validation.Name;
import estateagency.validation.Phone;
import estateagency.validation.UniqueUsername;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.Arrays;

public class NewRealtorForm {

    private final Authority[] authorities = Authority.allAuthorities;

    private Long id;
    @NotEmpty(message = "{message.errors.empty_field}")
    @Name
    private String fullName;
    @NotEmpty(message = "{message.errors.empty_field}")
    @Phone
    private String phone;
    @UniqueUsername
    @NotEmpty(message = "{message.errors.empty_field}")
    private String username;
    @NotEmpty(message = "{message.errors.empty_field}")
    private String password;
    private boolean enabled;
    private Authority authority;

    public NewRealtorForm() {
    }

    @Override
    public String toString() {
        return "NewRealtorForm{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", phone='" + phone + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", enabled=" + enabled +
                ", authorities=" + Arrays.toString(authorities) +
                ", authority=" + authority +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Authority[] getAuthorities() {
        return authorities;
    }

    public Authority getAuthority() {
        return authority;
    }

    public void setAuthority(Authority authority) {
        this.authority = authority;
    }
}
