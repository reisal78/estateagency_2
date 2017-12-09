package estateagency.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "contracts_rent",uniqueConstraints = {@UniqueConstraint(columnNames = {"flats_id"})})
public class ContractRent extends Contract {
}
