package estateagency.dto.flats;

import estateagency.model.TypeTrade;

public class FlatSearchDto {

    private Integer minRoom = null;
    private Integer maxRoom = null;
    private Double minArea = null;
    private Double maxArea = null;
    private Integer minFloor = null;
    private Integer maxFloor = null;
    private String district = new String();
    private Double minPrice = null;
    private Double maxPrice = null;
    private TypeTrade typeTrade = TypeTrade.SALE;
    private Integer page;
    private Integer size;
    private boolean reserved;


    public FlatSearchDto() {
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public TypeTrade getTypeTrade() {
        return typeTrade;
    }

    public void setTypeTrade(TypeTrade typeTrade) {
        this.typeTrade = typeTrade;
    }

    public Integer getMinRoom() {
        return minRoom;
    }

    public void setMinRoom(Integer minRoom) {
        this.minRoom = minRoom;
    }

    public Integer getMaxRoom() {
        return maxRoom;
    }

    public void setMaxRoom(Integer maxRoom) {
        this.maxRoom = maxRoom;
    }

    public Double getMinArea() {
        return minArea;
    }

    public void setMinArea(Double minArea) {
        this.minArea = minArea;
    }

    public Double getMaxArea() {
        return maxArea;
    }

    public void setMaxArea(Double maxArea) {
        this.maxArea = maxArea;
    }

    public Integer getMinFloor() {
        return minFloor;
    }

    public void setMinFloor(Integer minFloor) {
        this.minFloor = minFloor;
    }

    public Integer getMaxFloor() {
        return maxFloor;
    }

    public void setMaxFloor(Integer maxFloor) {
        this.maxFloor = maxFloor;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public Double getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(Double minPrice) {
        this.minPrice = minPrice;
    }

    public Double getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(Double maxPrice) {
        this.maxPrice = maxPrice;
    }

    public boolean isReserved() {
        return reserved;
    }

    public void setReserved(boolean reserved) {
        this.reserved = reserved;
    }

    @Override
    public String toString() {
        return "FlatSearchDto{" +
                "minRoom=" + minRoom +
                ", maxRoom=" + maxRoom +
                ", minArea=" + minArea +
                ", maxArea=" + maxArea +
                ", minFloor=" + minFloor +
                ", maxFloor=" + maxFloor +
                ", district='" + district + '\'' +
                ", minPrice=" + minPrice +
                ", maxPrice=" + maxPrice +
                ", typeTrade=" + typeTrade +
                ", page=" + page +
                ", size=" + size +
                ", reserved=" + reserved +
                '}';
    }
}
