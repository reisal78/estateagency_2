package estateagency.model;

import javax.persistence.*;

@Entity
@Table(name = "user_authorities")
public class UserAuthority extends BaseEntity {

    private Authority authority;

    private User user;

    public UserAuthority() {
    }

    public UserAuthority(Authority authority, User user) {
        this.authority = authority;
        this.user = user;
    }

    @Enumerated(EnumType.STRING)
    public Authority getAuthority() {
        return authority;
    }

    @ManyToOne
    public User getUser() {
        return user;
    }

    public void setAuthority(Authority authority) {
        this.authority = authority;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

