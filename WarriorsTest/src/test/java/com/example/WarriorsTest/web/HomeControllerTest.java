package com.example.WarriorsTest.web;


import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class HomeControllerTest {


    @Autowired
    private MockMvc mockMvc;


    @Test
    @Order(1)
    void testRegistration() throws Exception {
        mockMvc.perform(post("/auth/register").
                        param("email", "lolo@example.com").
                        param("username", "lololololololo").
                        param("password", "12345").
                        param("confirmPassword", "12345")
                        .with(csrf())
                ).
                andExpect(status().is3xxRedirection()).
                andExpect(redirectedUrl("/auth/login"));
    }

    @Test
    @Order(2)
    void testLogin() throws Exception {
        mockMvc.perform(post("/auth/login").
                        param("username", "lololololololo").
                        param("password", "12345").
                        with(csrf())
                ).
                andExpect(status().is3xxRedirection()).
                andExpect(redirectedUrl("/hero/details/my-hero"));
    }

    @Test
    @Order(3)
    @WithMockUser(username = "lololololololo", password = "12345", roles = "")
    void TestHeroCreation() throws Exception {
        mockMvc.perform(post("/hero/creation").
                        param("name", "buk").
                        param("heroClass", "KNIGHT").
                        with(csrf())
                ).
                andExpect(status().is3xxRedirection()).
                andExpect(redirectedUrl("/hero/details/my-hero"));
    }

    @Test
    @Order(4)
    @WithMockUser(username = "lololololololo", password = "12345", roles = "")
    void testEquip() throws Exception {

        mockMvc.perform(post("/inventory/equip/1").
                        with(csrf())
                ).
                andExpect(status().is3xxRedirection()).
                andExpect(redirectedUrl("/inventory"));
    }

    @Test
    @Order(5)
    @WithMockUser(username = "lololololololo", password = "12345", roles = "")
    void testUnEquip() throws Exception {

        mockMvc.perform(post("/inventory/unEquip/1").
                        with(csrf())
                ).
                andExpect(status().is3xxRedirection()).
                andExpect(redirectedUrl("/inventory"));
    }

    @Test
    @Order(6)
    void newRegistration() throws Exception {
        mockMvc.perform(post("/auth/register").
                        param("email", "ss@example.com").
                        param("username", "ssssssssss").
                        param("password", "12345").
                        param("confirmPassword", "12345")
                        .with(csrf())
                ).
                andExpect(status().is3xxRedirection()).
                andExpect(redirectedUrl("/auth/login"));
    }

    @Test
    @Order(7)
    @WithMockUser(username = "ssssssssss", password = "12345", roles = "")
    void NEWHeroCreation() throws Exception {
        mockMvc.perform(post("/hero/creation").
                        param("name", "sks").
                        param("heroClass", "MAGE").
                        with(csrf())
                ).
                andExpect(status().is3xxRedirection()).
                andExpect(redirectedUrl("/hero/details/my-hero"));
    }

    @Test
    @Order(8)
    @WithMockUser(username = "lololololololo", password = "12345", roles = "")
    void testERROREquip() throws Exception {

        mockMvc.perform(post("/inventory/equip/6").
                        with(csrf())
                ).
                andExpect(status().isForbidden());
    }

    @Test
    @Order(9)
    @WithMockUser(username = "lololololololo", password = "12345", roles = "")
    void testDetailsError() throws Exception {

        mockMvc.perform(post("/inventory/details/-1").
                        with(csrf())
                ).
                andExpect(status().isNotFound());
    }

    @Test
    @Order(10)
    @WithMockUser(username = "lololololololo", password = "12345", roles = "")
    void testItemDetail() throws Exception {

        mockMvc.perform(get("/items/details/2")
                ).
                andExpect(status().isOk());
    }

    @Test
    @Order(11)
    @WithMockUser(username = "lololololololo", password = "12345", roles = "")
    void testBattle() throws Exception {

        mockMvc.perform(post("/battle/start").
                        with(csrf())
                ).
                andExpect(status().is3xxRedirection()).
                andExpect(redirectedUrl("/battle/result"));
    }

    @Test
    @Order(12)
    @WithMockUser(username = "admin", password = "admin", roles = "ADMIN")
    void TestAdminPanelADD() throws Exception {


        mockMvc.perform(post("/admin/addUserRole").
                        param("username", "pesho").
                        param("userRole", "ADMIN").
                        with(csrf())
                ).
                andExpect(status().is3xxRedirection()).
                andExpect(redirectedUrl("/admin/changeUserRoles"));
    }

    @Test
    @Order(13)
    @WithMockUser(username = "admin", password = "admin", roles = "ADMIN")
    void TestAdminPanelREMOVE() throws Exception {


        mockMvc.perform(post("/admin/removeUserRole").
                        param("username", "pesho").
                        param("userRole", "ADMIN").
                        with(csrf())
                ).
                andExpect(status().is3xxRedirection()).
                andExpect(redirectedUrl("/admin/changeUserRoles"));
    }

    @Test
    @Order(14)
    @WithMockUser(username = "lololololololo", password = "12345", roles = "")
    void testItemDelete() throws Exception {
        mockMvc.perform(post("/inventory/delete/1").
                        with(csrf())
                ).
                andExpect(status().is3xxRedirection()).
                andExpect(redirectedUrl("/inventory"));
    }
    @Test
    @Order(15)
    @WithMockUser(username = "lololololololo", password = "12345", roles = "")
    void GetInventory() throws Exception {
        mockMvc.perform(get("/inventory").
                        with(csrf())
                ).
                andExpect(status().isOk()).
                andExpect(view().name("Inventory"));
    }
    @Test
    @Order(16)
    @WithMockUser(username = "lololololololo", password = "12345", roles = "")
    void GetRanking() throws Exception {
        mockMvc.perform(get("/ranking").
                        with(csrf())
                ).
                andExpect(status().isOk()).
                andExpect(view().name("Ranking"));
    }
    @Test
    @Order(17)
    @WithMockUser(username = "lololololololo", password = "12345", roles = "")
    void GetIndex() throws Exception {
        mockMvc.perform(get("/").
                        with(csrf())
                ).
                andExpect(status().isOk()).
                andExpect(view().name("index"));
    }
    @Test
    @Order(18)
    @WithMockUser(username = "lololololololo", password = "12345", roles = "")
    void GetRESULTERROR() throws Exception {
        mockMvc.perform(get("/battle/result").
                        with(csrf())
                ).
                andExpect(status().isOk()).
                andExpect(view().name("BattleResult"));
    }


}
