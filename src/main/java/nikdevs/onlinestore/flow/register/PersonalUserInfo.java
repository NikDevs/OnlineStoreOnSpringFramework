package nikdevs.onlinestore.flow.register;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

public class PersonalUserInfo implements Serializable {

    private String firstName;
    private String lastName;

    public PersonalUserInfo() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
