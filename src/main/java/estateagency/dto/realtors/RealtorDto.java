package estateagency.dto.realtors;

import estateagency.model.Authority;
import estateagency.validation.Phone;
import org.hibernate.validator.constraints.NotEmpty;

public class RealtorDto {

    private Long id;

    @NotEmpty(message = "{message.errors.empty_field}")
    private String fullName;


    @NotEmpty(message = "{message.errors.empty_field}")
    @Phone
    private String phone;

    @NotEmpty(message = "{message.errors.empty_field}")
    private String username;

    private boolean enabled;
    private Authority[] authorities = Authority.allAuthorities;
    private Authority authority;

    public RealtorDto(Long id, String fullName, String username, String password) {
        this.id = id;
        this.fullName = fullName;
    }

    public RealtorDto() {
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


    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Authority[] getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Authority[] authorities) {
        this.authorities = authorities;
    }

    public Authority getAuthority() {
        return authority;
    }

    public void setAuthority(Authority authority) {
        this.authority = authority;
    }

    @Override
    public String toString() {
        return "RealtorDto{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", phone='" + phone + '\'' +
                ", username='" + username + '\'' +
                ", enabled=" + enabled +
                '}';
    }
}
