package estateagency.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "contracts_sale",uniqueConstraints = {@UniqueConstraint(columnNames = {"flats_id"})})
public class ContractSale extends Contract {
}
