package com.soprasteria.Microservice.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class RoleTest {

    @Test
    void testAllArgsConstructor() {

        int roleId = 1;
        String roleName = "ROLE_ADMIN";

        Role role = new Role(roleId, roleName);
        assertNotNull(role);
        assertEquals(roleId, role.getRoleId());
        assertEquals(roleName, role.getRoleName());
    }

    @Test
    void testGettersAndSetters() {

        Role role = new Role();
        int roleId = 1;
        String roleName = "ROLE_USER";

        role.setRoleId(roleId);
        role.setRoleName(roleName);

        assertEquals(roleId, role.getRoleId());
        assertEquals(roleName, role.getRoleName());
    }

    @Test
    void testNoArgsConstructor() {

        Role role = new Role();

        assertNotNull(role);
        assertNull(role.getRoleName());
    }

}
