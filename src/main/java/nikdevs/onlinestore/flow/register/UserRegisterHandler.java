package nikdevs.onlinestore.flow.register;

import nikdevs.onlinestore.service.interfaces.RoleService;
import nikdevs.onlinestore.service.interfaces.UserService;
import nikdevs.onlinestore.service.model.RoleRepr;
import nikdevs.onlinestore.service.model.SystemUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.binding.message.MessageBuilder;
import org.springframework.binding.message.MessageContext;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class UserRegisterHandler {

    private static final Logger logger = LoggerFactory.getLogger(UserRegisterHandler.class);

    private static final String FAILURE = "failure";
    private static final String SUCCESS = "success";

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public UserRegisterHandler(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    public UserRegisterModel init() {
        return new UserRegisterModel();
    }

    public void addBasicUserInfo(UserRegisterModel userRegisterModel, BasicUserInfo basicUserInfo) {
        userRegisterModel.setBasicUserInfo(basicUserInfo);
    }

    public void addPersonalUserInfo(UserRegisterModel userRegisterModel, PersonalUserInfo personalUserInfo) {
        userRegisterModel.setPersonalUserInfo(personalUserInfo);
    }

    public String validateBasicUserInfo(BasicUserInfo basicUserInfo, MessageContext error) {
        if (!basicUserInfo.getPassword().equals(basicUserInfo.getConfirmPassword())) {
            error.addMessage(new MessageBuilder()
                    .error()
                    .source("confirmPassword")
                    .defaultText("Password doesn't match up the confirm password!")
                    .build());

            return FAILURE;
        }
        return SUCCESS;
    }

    public String validatePersonalUserInfo(PersonalUserInfo personalUserInfo, MessageContext error) {
        return SUCCESS;
    }

    public String saveAll(UserRegisterModel urm, MessageContext error) {
        try {
            Set<RoleRepr> roles = new HashSet<>();
            roles.add(roleService.findByName("ROLE_USER"));
            SystemUser systemUser = new SystemUser(
                    urm.getBasicUserInfo().getUsername(),
                    urm.getBasicUserInfo().getPassword(),
                    urm.getPersonalUserInfo().getFirstName(),
                    urm.getPersonalUserInfo().getLastName(),
                    urm.getBasicUserInfo().getEmail(),
                    roles);
            userService.save(systemUser);
        } catch (Exception ex) {
            logger.error("", ex);
            error.addMessage(new MessageBuilder()
                    .error()
                    .source("email")
                    .defaultText("Internal error. Can't complete registration.")
                    .build());
            return FAILURE;
        }
        return SUCCESS;
    }
}
