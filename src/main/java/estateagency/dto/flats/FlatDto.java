package estateagency.dto.flats;

import estateagency.model.TypeTrade;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

public class FlatDto {

    public static final TypeTrade[] allTypeTrades = TypeTrade.ALL;

    private Long id;

    @NotNull (message = "{message.errors.empty_field}")
    @Min(value = 1)
    private Double area;

    @NotNull (message = "{message.errors.empty_field}")
    @Min(value = 1)
    private Integer floor;

    @NotNull (message = "{message.errors.empty_field}")
    @Min(value = 1)
    private Integer rooms;

    @NotEmpty(message = "{message.errors.empty_field}")
    private String district;

    @NotNull (message = "{message.errors.empty_field}")
    @Min(value = 1)
    private Double price;

    @NotEmpty(message = "{message.errors.empty_field}")
    private String address;

    private boolean reserved;

    private boolean available = true;

    @NotNull (message = "{message.errors.empty_field}")
    private Long ownerId;

    @NotEmpty(message = "{message.errors.empty_field}")
    private String ownerFullName;

    private TypeTrade typeTrade;

    private List<String> imagesPath = new ArrayList<>();

    public FlatDto() {

    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public static TypeTrade[] getAllTypeTrades() {
        return allTypeTrades;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getArea() {
        return area;
    }

    public void setArea(Double area) {
        this.area = area;
    }

    public Integer getFloor() {
        return floor;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    public Integer getRooms() {
        return rooms;
    }

    public void setRooms(Integer rooms) {
        this.rooms = rooms;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isReserved() {
        return reserved;
    }

    public void setReserved(boolean reserved) {
        this.reserved = reserved;
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

    public TypeTrade getTypeTrade() {
        return typeTrade;
    }

    public void setTypeTrade(TypeTrade typeTrade) {
        this.typeTrade = typeTrade;
    }

    public List<String> getImagesPath() {
        return imagesPath;
    }

    public void setImagesPath(List<String> imagesPath) {
        this.imagesPath = imagesPath;
    }

    @Override
    public String toString() {
        return "FlatDto{" +
                "id=" + id +
                ", area=" + area +
                ", floor=" + floor +
                ", rooms=" + rooms +
                ", district='" + district + '\'' +
                ", price=" + price +
                ", address='" + address + '\'' +
                ", reserved=" + reserved +
                ", ownerId=" + ownerId +
                ", ownerFullName='" + ownerFullName + '\'' +
                ", typeTrade=" + typeTrade +
                '}';
    }
}
