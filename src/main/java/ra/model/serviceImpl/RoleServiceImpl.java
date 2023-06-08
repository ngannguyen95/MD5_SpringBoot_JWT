package ra.model.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.model.entity.ERole;
import ra.model.entity.Roles;
import ra.model.repository.RoleRepository;
import ra.model.service.IRoleService;

import java.util.Optional;
@Service
public class RoleServiceImpl implements IRoleService {
    @Autowired
    private RoleRepository repository;
    @Override
    public Optional<Roles> findAllByRoleName(ERole roleName) {
        return repository.findAllByRoleName(roleName);
    }
}
