package estateagency.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "clients")
public class Client extends Counterparty {
}
