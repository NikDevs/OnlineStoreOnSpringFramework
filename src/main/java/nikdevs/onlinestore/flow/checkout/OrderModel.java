package nikdevs.onlinestore.flow.checkout;

import nikdevs.onlinestore.service.repr.SystemUser;

import java.io.Serializable;

public class OrderModel implements Serializable {

    private SystemUser user;
    private AdressInfo adressInfo;

    public SystemUser getUser() {
        return user;
    }

    public void setUser(SystemUser user) {
        this.user = user;
    }

    public AdressInfo getAdressInfo() {
        return adressInfo;
    }

    public void setAdressInfo(AdressInfo adressInfo) {
        this.adressInfo = adressInfo;
    }
}
