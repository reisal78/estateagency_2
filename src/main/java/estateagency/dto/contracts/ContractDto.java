package estateagency.dto.contracts;

import estateagency.model.*;
import estateagency.validation.Commission;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;

public class ContractDto {
    public final static TypeTrade[] allTypeTrades = TypeTrade.ALL;
    private Long id;
    private Long flatId;
    private String flatAddress;
    private Long ownerId;
    private String ownerFullName;
    private Long clientId;

    @NotEmpty(message = "{message.errors.empty_field}")
    private String clientFullName;
    private String realtorFullName;
    private Long realtorId;
    private TypeTrade typeTrade;


    @NotNull (message = "{message.errors.empty_field}")
    @Commission
    private Double commission =0D;
    private Double totalPrice;
    private Long currentRealtorId;
    private Date createDate;
    private Date completeDate = new Date();
    private boolean reserved = false;
    private boolean available;


    public ContractDto() {
    }

    public boolean isAvailable() {
        return available;
    }



    public void setAvailable(boolean available) {
        this.available = available;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public static TypeTrade[] getAllTypeTrades() {
        return allTypeTrades;
    }

    public void setFlat(Flat flat) {
        this.flatId = flat.getId();
        this.flatAddress = flat.getAddress();
        this.typeTrade = flat.getTypeTrade();
        this.reserved = flat.isReserved();
    }

    public void setOwner(Owner owner) {
        this.ownerId = owner.getId();
        this.ownerFullName = createFullName(owner);
    }

    public void setClient(Client client) {
        this.clientId = client.getId();
        this.clientFullName = createFullName(client);
    }

    public void setRealtor(Realtor realtor) {
        this.realtorId = realtor.getId();
        this.realtorFullName = realtor.getFullName();
    }

    private String createFullName(Counterparty counterparty) {
        StringBuilder sb = new StringBuilder();
        sb
                .append(counterparty.getLastName())
                .append(" ")
                .append(counterparty.getFirstName())
                .append(" ")
                .append(counterparty.getSurName());
        return sb.toString();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getCompleteDate() {
        return completeDate;
    }

    public void setCompleteDate(Date completeDate) {
        this.completeDate = completeDate;
    }

    public String getRealtorFullName() {
        return realtorFullName;
    }

    public void setRealtorFullName(String realtorFullName) {
        this.realtorFullName = realtorFullName;
    }

    public Long getRealtorId() {
        return realtorId;
    }

    public void setRealtorId(Long realtorId) {
        this.realtorId = realtorId;
    }

    public boolean isReserved() {
        return reserved;
    }

    public void setReserved(boolean reserved) {
        this.reserved = reserved;
    }

    public Double getCommission() {
        return commission;
    }

    public void setCommission(Double commission) {
        this.commission = commission;
    }

    public TypeTrade getTypeTrade() {
        return typeTrade;
    }

    public void setTypeTrade(TypeTrade typeTrade) {
        this.typeTrade = typeTrade;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFlatId() {
        return flatId;
    }

    public void setFlatId(Long flatId) {
        this.flatId = flatId;
    }

    public String getFlatAddress() {
        return flatAddress;
    }

    public void setFlatAddress(String flatAddress) {
        this.flatAddress = flatAddress;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public String getOwnerFullName() {
        return ownerFullName;
    }

    public void setOwnerFullName(String ownerFullName) {
        this.ownerFullName = ownerFullName;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public String getClientFullName() {
        return clientFullName;
    }

    public void setClientFullName(String clientFullName) {
        this.clientFullName = clientFullName;
    }

    public Long getCurrentRealtorId() {
        return currentRealtorId;
    }

    public void setCurrentRealtorId(Long currentRealtorId) {
        this.currentRealtorId = currentRealtorId;
    }

    @Override
    public String toString() {
        return "ContractDto{" +
                "id=" + id +
                ", flatId=" + flatId +
                ", flatAddress='" + flatAddress + '\'' +
                ", ownerId=" + ownerId +
                ", ownerFullName='" + ownerFullName + '\'' +
                ", clientId=" + clientId +
                ", clientFullName='" + clientFullName + '\'' +
                ", realtorFullName='" + realtorFullName + '\'' +
                ", realtorId=" + realtorId +
                ", typeTrade=" + typeTrade +
                ", commission=" + commission +
                ", reserved=" + reserved +
                '}';
    }
}
