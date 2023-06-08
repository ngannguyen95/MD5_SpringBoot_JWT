package ra.dto.request;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Set;
@Data
public class SignupRequest {
    private String userName;
    private String password;
    private String email;
    private String phone;
    private Date created;
    private boolean useStatus;
    private Set<String> listRoles;

}
