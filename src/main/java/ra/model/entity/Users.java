package ra.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Users")
@Data
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userId")
    private int userId;
    @Column(name = "userName",unique = true,nullable = false)
    private String userName;
    @Column(name = "password",nullable = false)
    private String password;
    @Column(name = "created")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date created;
    @Column(name = "email",unique = true,nullable = false)
    private String email;
    @Column(name = "phone")
    private String phone;
    @Column(name = "userStatus")
    private boolean userStatus;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_role",joinColumns =@JoinColumn(name = "userId"),
    inverseJoinColumns =@JoinColumn(name = "roleId"))
    private Set<Roles> listRoles = new HashSet<>();
}
