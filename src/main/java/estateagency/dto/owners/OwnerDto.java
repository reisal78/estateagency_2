package estateagency.dto.owners;

import estateagency.validation.Name;
import estateagency.validation.Passport;
import estateagency.validation.Phone;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

public class OwnerDto {

    private Long id;
    @NotEmpty(message = "{message.errors.empty_field}")
    @Name
    private String firstName;
    @NotEmpty(message = "{message.errors.empty_field}")
    @Name
    private String surName;
    @NotEmpty(message = "{message.errors.empty_field}")
    @Name
    private String lastName;
    @NotEmpty(message = "{message.errors.empty_field}")
    @Phone
    private String phone;
    @NotEmpty(message = "{message.errors.empty_field}")
    @Passport
    private String passport;

    public OwnerDto(String firstName, String surName, String lastName, String phone, String passport) {
        this.firstName = firstName;
        this.surName = surName;
        this.lastName = lastName;
        this.phone = phone;
        this.passport = passport;
    }

    public OwnerDto() {
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSurName() {
        return surName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhone() {
        return phone;
    }

    public String getPassport() {
        return passport;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    @Override
    public String toString() {
        return "OwnerDto{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", surName='" + surName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phone='" + phone + '\'' +
                ", passport='" + passport + '\'' +
                '}';
    }
}
