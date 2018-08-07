package mereuta.marian.tennis01.service;

import mereuta.marian.tennis01.model.Role;
import mereuta.marian.tennis01.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleMetier implements RoleMetierInterface {

    @Autowired
    RoleRepository roleRepository;

    @Override
    public List<Role> getRoles() {
        return roleRepository.findAll();
    }

    @Override
    public Role getRole(Integer idrole) {
        return roleRepository.getOne(idrole);
    }
}
