package estateagency.model;

public enum Authority {
    USER, ADMIN;

    public final static Authority[] allAuthorities = {USER, ADMIN};

    @Override
    public String toString() {
        return name();
    }
    public String getUserAuthority() {
        return name();
    }
}
