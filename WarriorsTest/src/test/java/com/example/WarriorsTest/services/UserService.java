package com.example.WarriorsTest.services;

import com.example.WarriorsTest.enums.UserRoles;
import com.example.WarriorsTest.models.entity.RoleEntity;
import com.example.WarriorsTest.models.entity.UserEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.opentest4j.AssertionFailedError;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AppUserDetailsTest {


    private AppUserDetailsService toTest;

    @Mock
    private UserService mockUserRepository;

    @BeforeEach
    void setUp() {
        toTest = new AppUserDetailsService(
                mockUserRepository);
    }

    @Test
    void testUserFound() {

        // ARRANGE
        RoleEntity testAdminRole = new RoleEntity().setUserRole(UserRoles.ADMIN);
        RoleEntity testUserRole = new RoleEntity().setUserRole(UserRoles.MODERATOR);

        String EXISTING_NAME = "admin";
        UserEntity testUserEntity = new UserEntity().
                setUsername(EXISTING_NAME).
                setPassword("admin").
                setRoles(List.of(testAdminRole, testUserRole));


        when(mockUserRepository.findByUsernameOptional(EXISTING_NAME)).
                thenReturn(Optional.of(testUserEntity));
        // EO: ARRANGE


        // ACT
        UserDetails adminDetails =
                toTest.loadUserByUsername(EXISTING_NAME);
        // EO: ACT

        // ASSERT
        Assertions.assertNotNull(adminDetails);
        Assertions.assertEquals(EXISTING_NAME, adminDetails.getUsername());
        Assertions.assertEquals(testUserEntity.getPassword(), adminDetails.getPassword());

        Assertions.assertEquals(2,
                adminDetails.getAuthorities().size(),
                "The authorities are supposed to be just two - ADMIN/USER.");

        assertRole(adminDetails.getAuthorities(), "ROLE_ADMIN");
        assertRole(adminDetails.getAuthorities(), "ROLE_MODERATOR");
        // EO: ASSERT
    }
    private void assertRole(Collection<? extends GrantedAuthority> authorities,
                            String role) {
        authorities.
                stream().
                filter(a -> role.equals(a.getAuthority())).
                findAny().
                orElseThrow(() -> new AssertionFailedError("Role " + role + " not found!"));
    }
    @Test
    void testUserNotFound() {
        assertThrows(
                UsernameNotFoundException.class,
                () -> toTest.loadUserByUsername("123")
        );
    }

}
