package estateagency.model;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
public abstract class Contract extends BaseEntity {

    private Client client;

    private Realtor realtor;
    private Flat flat;
    private double commission;
    private double totalPrice;
    private Date createDate = new Date();
    private Date completeDate;

    public Contract() {
    }

    @ManyToOne
    @JoinColumn(name = "clients_id")
    public Client getClient() {
        return client;
    }

    @ManyToOne
    @JoinColumn(name = "realtors_id")
    public Realtor getRealtor() {
        return realtor;
    }

    @ManyToOne
    @JoinColumn(name = "flats_id")
    public Flat getFlat() {
        return flat;
    }

    public double getCommission() {
        return commission;
    }

    @Column(name = "total_price")
    @NumberFormat(style = NumberFormat.Style.CURRENCY)
    public double getTotalPrice() {
        return totalPrice;
    }

    @Column(name = "create_date")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    public Date getCreateDate() {
        return createDate;
    }

    @Column(name = "complete_date")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    public Date getCompleteDate() {
        return completeDate;
    }

    public void setCompleteDate(Date completeDate) {
        this.completeDate = completeDate;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void setRealtor(Realtor realtor) {
        this.realtor = realtor;
    }

    public void setFlat(Flat flat) {
        this.flat = flat;
    }

    public void setCommission(double commission) {
        this.commission = commission;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }


}
