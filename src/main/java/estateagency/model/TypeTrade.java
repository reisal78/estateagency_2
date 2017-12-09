package estateagency.model;

public enum TypeTrade {
    RENT, SALE;

    public static final TypeTrade[] ALL = {RENT, SALE};

    @Override
    public String toString() {
        return name();
    }



}
