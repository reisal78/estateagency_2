package estateagency.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "owners")
public class Owner extends Counterparty {
    @Override
    public String toString() {
        return super.toString() + "Owner{" +
                "lastName='" + getLastName() + '\'' +
                ", passport ='" + getPassport() + '\'' +
                '}';
    }
}
