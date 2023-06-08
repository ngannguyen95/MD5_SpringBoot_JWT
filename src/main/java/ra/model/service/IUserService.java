package ra.model.service;

import ra.model.entity.Users;

public interface IUserService {
    Users findAllByUserName (String userName);
    boolean existsByUserName (String userName);
    boolean existsByEmail (String email);
    Users saveOrUpdate(Users user);
}
