package estateagency.model;

import javax.persistence.*;

@Entity
@Table(name = "flats")
public class Flat extends BaseEntity {

    private int rooms;
    private double area;
    private int floor;
    private String district;
    private double price;
    private String address;
    private boolean available = true;
    private boolean reserved = false;
    private Owner owner;
    private TypeTrade typeTrade;



    public Flat() {
    }

    @ManyToOne
    public Owner getOwner() {
        return owner;
    }

    @Enumerated(EnumType.STRING)
    public TypeTrade getTypeTrade() {
        return typeTrade;
    }

    public int getRooms() {
        return rooms;
    }

    public double getArea() {
        return area;
    }

    public int getFloor() {
        return floor;
    }

    public String getDistrict() {
        return district;
    }

    public double getPrice() {
        return price;
    }

    public String getAddress() {
        return address;
    }

    public boolean isAvailable() {
        return available;
    }

    public boolean isReserved() {
        return reserved;
    }

    public void setRooms(int rooms) {
        this.rooms = rooms;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public void setReserved(boolean reserved) {
        this.reserved = reserved;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public void setTypeTrade(TypeTrade typeTrade) {
        this.typeTrade = typeTrade;
    }

    @Override
    public String toString() {
        return super.toString() + "Flat{" +
                "rooms=" + rooms +
                ", area=" + area +
                ", floor=" + floor +
                ", district='" + district + '\'' +
                ", price=" + price +
                ", address='" + address + '\'' +
                ", available=" + available +
                ", reserved=" + reserved +
                ", typeTrade=" + typeTrade +
                "}\n";
    }
}
