package com.donatoordep.anime_list_api.dto;

import com.donatoordep.anime_list_api.entities.Role;
import com.donatoordep.anime_list_api.enums.RoleName;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Objects;

public class RoleDTO {

    @JsonIgnore
    private Long id;
    private RoleName roleName;

    public RoleDTO() {
    }

    public RoleDTO(Role entity) {
        this.roleName = entity.getRoleName();
        this.id = entity.getId();
    }

    public RoleName getRoleName() {
        return roleName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setRoleName(RoleName roleName) {
        this.roleName = roleName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoleDTO role = (RoleDTO) o;
        return roleName == role.roleName;
    }

    @Override
    public int hashCode() {
        return Objects.hash(roleName);
    }
}
