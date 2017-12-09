package estateagency.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class FlatImage extends BaseEntity {
    private String url;
    private Flat flat;

    public FlatImage() {

    }

    public String getUrl() {
        return url;
    }

    @ManyToOne
    public Flat getFlat() {
        return flat;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setFlat(Flat flat) {
        this.flat = flat;
    }


}
