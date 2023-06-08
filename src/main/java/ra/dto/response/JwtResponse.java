package ra.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtResponse {
    private String token;
    private String type= " Bearer";
    private String userName;
    private String email;
    private String phone;
    private List<String> listRoles;

    public JwtResponse(String token, String userName, String email, String phone, List<String> listRoles) {
        this.token = token;
        this.userName = userName;
        this.email = email;
        this.phone = phone;
        this.listRoles = listRoles;
    }
}
