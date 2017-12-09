package estateagency.dto.realtors;

import estateagency.validation.Name;
import estateagency.validation.Phone;
import org.hibernate.validator.constraints.NotEmpty;

public class EditRealtorForm {

    private Long id;

    @NotEmpty(message = "{message.errors.empty_field}")
    @Name
    private String fullName;

    @Phone
    @NotEmpty(message = "{message.errors.empty_field}")
    private String phone;

    public EditRealtorForm() {
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
}
