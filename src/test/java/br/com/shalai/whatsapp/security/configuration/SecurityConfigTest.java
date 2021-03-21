package br.com.shalai.whatsapp.security.configuration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class SecurityConfigTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders
            .webAppContextSetup(webApplicationContext)
            .apply(springSecurity())
            .build();
    }

    @Test
    @DisplayName("Given anonymous user, when access public path, then return forbidden [403]")
    public void givenAnonymousUser_whenAccessPublicPath_thenReturn401() throws Exception {
        mockMvc.perform(get("/api/v1/healthcheck/public"))
            .andExpect(status().isForbidden());
    }

    @Test
    @DisplayName("Given anonymous user, when access protected path, then return forbidden [403]")
    public void givenAnonymousUser_whenAccessProtectedPath_thenReturn403() throws Exception {
        mockMvc.perform(get("/api/v1/healthcheck/protected"))
            .andExpect(status().isForbidden());
    }

    @Test
    @DisplayName("Given anonymous user, when access private path, then return forbidden [403]")
    public void givenAnonymousUser_whenAccessPrivatePath_thenReturn403() throws Exception {
        mockMvc.perform(get("/api/v1/healthcheck/private"))
            .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(username = "sa", roles = {"COMMON"})
    @DisplayName("Given common user, when access public path, then return ok [200]")
    public void givenCommonUser_whenAccessPublicPath_thenReturn200() throws Exception {
        mockMvc.perform(get("/api/v1/healthcheck/public"))
            .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "sa", roles = {"COMMON"})
    @DisplayName("Given common user, when access protected path, then return ok [200]")
    public void givenCommonUser_whenAccessProtectedPath_thenReturn200() throws Exception {
        mockMvc.perform(get("/api/v1/healthcheck/protected"))
            .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "sa", roles = {"COMMON"})
    @DisplayName("Given common user, when access private path, then return forbidden [403]")
    public void givenCommonUser_whenAccessPrivatePath_thenReturn401() throws Exception {
        mockMvc.perform(get("/api/v1/healthcheck/private"))
            .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(username = "sa", roles = {"ADMIN"})
    @DisplayName("Given admin user, when access public path, then return ok [200]")
    public void givenAdminUser_whenAccessPublicPath_thenReturn200() throws Exception {
        mockMvc.perform(get("/api/v1/healthcheck/public"))
            .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "sa", roles = {"ADMIN"})
    @DisplayName("Given admin user, when access protected path, then return ok [200]")
    public void givenAdminUser_whenAccessProtectedPath_thenReturn200() throws Exception {
        mockMvc.perform(get("/api/v1/healthcheck/protected"))
            .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "sa", roles = {"ADMIN"})
    @DisplayName("Given admin user, when access private path, then return ok [200]")
    public void givenAdminUser_whenAccessPrivatePath_thenReturn200() throws Exception {
        mockMvc.perform(get("/api/v1/healthcheck/protected"))
            .andExpect(status().isOk());
    }
}