package nikdevs.onlinestore.persist.model;

import javax.persistence.*;

@Entity
@Table(name = "pictures_data")
public class PictureData extends StandartEntity {

    @Column(nullable = false, columnDefinition="MEDIUMBLOB")
    private byte[] data;

    public PictureData() {
    }

    public PictureData(byte[] data) {
        this.data = data;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
}
