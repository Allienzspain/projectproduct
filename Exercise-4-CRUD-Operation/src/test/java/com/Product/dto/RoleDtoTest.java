package com.Product.dto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class RoleDtoTest {

    @Test
    public void testRoleDtoConstructor() {
        int roleId = 1;
        String roleName = "ROLE_USER";

        RoleDto roleDto = new RoleDto(roleId, roleName);

        assertEquals(roleId, roleDto.getRoleId());
        assertEquals(roleName, roleDto.getRoleName());
    }

    @Test
    public void testRoleDtoSetters() {
        RoleDto roleDto = new RoleDto();
        int roleId = 1;
        String roleName = "ROLE_ADMIN";

        roleDto.setRoleId(roleId);
        roleDto.setRoleName(roleName);

        assertEquals(roleId, roleDto.getRoleId());
        assertEquals(roleName, roleDto.getRoleName());
    }

}