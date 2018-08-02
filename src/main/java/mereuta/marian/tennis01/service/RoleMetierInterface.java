package mereuta.marian.tennis01.service;

import mereuta.marian.tennis01.model.Role;

import java.util.List;

public interface RoleMetierInterface {

    public List<Role> getRoles();
    public Role getRole(Integer idrole);
}
