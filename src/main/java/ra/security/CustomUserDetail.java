package ra.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ra.model.entity.Users;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
public class CustomUserDetail implements UserDetails {
    private int userId;
    private String userName;
    @JsonIgnore
    private String password;
    private String email;
    private String phone;
    private boolean userStatus;
    // lấy ra các quyền của user
    private Collection<? extends GrantedAuthority> authorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    // lấy thông tin của user bên model chuyển thành UserDetail
    public static CustomUserDetail mapUserToUserDetail(Users users) {
        // lấy các quyền của user
        List<GrantedAuthority> authorityList = users.getListRoles().stream()
                .map(roles -> new SimpleGrantedAuthority(roles.getRoleName().name()))// mỗi vòng lặp duyệt qua và trả lại role
                .collect(Collectors.toList());// duyệt hết tất cả  gán lại danh sách và trả lại cho authorityList
      // trả về dối tượng UserDetail
        return new CustomUserDetail(
                users.getUserId(),
                users.getUserName(),
                users.getPassword(),
                users.getEmail(),
                users.getPhone(),
                users.isUserStatus(),
                authorityList
        );
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
