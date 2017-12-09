package estateagency.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table (name = "realtors")
public class Realtor extends BaseEntity {

    private String fullName;
    private String phone;

    public Realtor() {
    }

    public Realtor(String fullName, String phone, String username, String password, boolean enabled) {
        this.fullName = fullName;
        this.phone = phone;
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

    @Override
    public String toString() {
        return super.toString() + "Realtor{" +
                "fullName='" + fullName + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
