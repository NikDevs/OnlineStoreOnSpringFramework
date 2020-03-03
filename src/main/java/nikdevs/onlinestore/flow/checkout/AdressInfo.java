package nikdevs.onlinestore.flow.checkout;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

public class AdressInfo implements Serializable {

    @NotBlank
    private String country;
    @NotBlank
    private String zipcode;
    @NotBlank
    private String adress;
    private String additionalInfo;

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }
}
