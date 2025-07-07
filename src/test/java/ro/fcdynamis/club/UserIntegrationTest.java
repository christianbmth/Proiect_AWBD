package ro.fcdynamis.club;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UserIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    private Long staffId;

    @BeforeAll
    void setupData() throws Exception {
        // Creăm un staff pentru user
        String resp = mockMvc.perform(post("/api/staff")
                .contentType("application/json")
                .content("{\"prenume\":\"Mihai\",\"nume\":\"Georgescu\",\"functie\":\"Antrenor\"}"))
            .andExpect(status().isOk())
            .andReturn().getResponse().getContentAsString();

        // Extragem id-ul staff creat (simplu, parsam JSON manual)
        // De exemplu folosind un mic obiect Map
        com.fasterxml.jackson.databind.JsonNode node = 
            new com.fasterxml.jackson.databind.ObjectMapper().readTree(resp);
        staffId = node.get("idStaff").asLong();

        // Creăm doi useri:
        mockMvc.perform(post("/api/useri")
                .contentType("application/json")
                .content("{\"username\":\"u1@x.ro\",\"parola\":\"p1\",\"staff\":{\"idStaff\":" + staffId + "}}"))
            .andExpect(status().isOk());

        mockMvc.perform(post("/api/useri")
                .contentType("application/json")
                .content("{\"username\":\"u2@x.ro\",\"parola\":\"p2\",\"staff\":null}"))
            .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Integration: GET /api/useri returns created users")
    void testGetUsersIntegration() throws Exception {
        mockMvc.perform(get("/api/useri")
                .param("page", "0")
                .param("size", "10")
                .param("sort", "username,asc")
            )
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.content.length()").value(2))
            .andExpect(jsonPath("$.content[0].username").value("u1@x.ro"))
            .andExpect(jsonPath("$.content[1].username").value("u2@x.ro"))
            .andExpect(jsonPath("$.totalElements").value(2));
    }
}
