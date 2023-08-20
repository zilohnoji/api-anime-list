package com.donatoordep.anime_list_api.services;

import com.donatoordep.anime_list_api.entities.Role;
import com.donatoordep.anime_list_api.enums.RoleName;
import com.donatoordep.anime_list_api.repositories.RoleRepository;
import com.donatoordep.anime_list_api.services.exceptions.NotFoundEntityException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RoleServiceTest {

    @Mock
    RoleRepository repository;

    @InjectMocks
    RoleService service;

    Role admin;
    Role client;
    Role moderator;

    @BeforeEach
    void setup() {
        admin = new Role(1L, RoleName.ROLE_ADMIN);
        client = new Role(2L, RoleName.ROLE_CLIENT);
        moderator = new Role(3L, RoleName.ROLE_MODERATOR);
    }

    @Test
    @DisplayName("Given Role Object When FindById Is Called Should Return Role Object")
    void testGivenRoleObject_When_FindByIdIsCalled_ShouldReturn_RoleObject() {

        when(repository.findById(1L)).thenReturn(Optional.ofNullable(admin));

        Role output = service.findById(1L);

        Assertions.assertNotNull(output, () -> "Role can´t should returned null");
        Assertions.assertEquals(admin, output, () -> String.format(
                "Expected: %s\nActual: %s", admin.getRoleName(), output.getRoleName()));
    }

    @Test
    @DisplayName("Given Throw NotFoundEntityException When FindById Is Called")
    void testGivenThrowNotFoundEntityException_When_FindByIdIsCalled() {

        when(repository.findById(4L)).thenThrow(NotFoundEntityException.class);

        Assertions.assertThrows(NotFoundEntityException.class,
                () -> service.findById(4L), () -> "This id not exists");
    }

    @Test
    @DisplayName("Given List<Role> When SeparateRolesWithHierarchy Is Called Should Return List<Role>")
    void testGivenListOfRole_When_SeparateRolesWithHierarchyIsCalled_ShouldReturn_ListOfRole() {

        RoleName role = RoleName.ROLE_ADMIN;

        when(repository.findById(1L)).thenReturn(Optional.ofNullable(admin));
        when(repository.findById(2L)).thenReturn(Optional.ofNullable(client));
        when(repository.findById(3L)).thenReturn(Optional.ofNullable(moderator));

        List<Role> output = service.separateRolesWithHierarchy(role);

        Assertions.assertNotNull(output, () -> "Role can´t should returned null");
    }

    @Test
    @DisplayName("Given Boolean Value When Is() Is Called Should Return Boolean")
    void testGiven_Boolean_Value_When_Is_IsCalled_Should_Return_Boolean() {

        boolean output = RoleService.is(RoleName.ROLE_ADMIN, RoleName.ROLE_ADMIN);
        Assertions.assertTrue(output, () -> "RoleName should be equals");
    }
}
