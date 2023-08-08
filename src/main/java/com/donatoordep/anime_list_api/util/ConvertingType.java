package com.donatoordep.anime_list_api.util;

import com.donatoordep.anime_list_api.dto.RoleDTO;
import com.donatoordep.anime_list_api.entities.Role;
import com.donatoordep.anime_list_api.enums.RoleName;
import com.donatoordep.anime_list_api.services.exceptions.InvalidEnumValueException;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ConvertingType {

    private ConvertingType() {

    }

    public static RoleName verifyRoleNameValueAndConvert(String roleNameString) {

        try {
            if(roleNameString== null) {
                return null;
            }
            return RoleName.valueOf(roleNameString.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new InvalidEnumValueException(roleNameString, "RoleName",
                    ListEnumValues.returnEnumValues(Arrays.asList(RoleName.values())));
        }

    }

    public static List<RoleDTO> convertToRoleDTOList(List<Role> roles) {
        return roles.stream().map(RoleDTO::new).toList();
    }

}
