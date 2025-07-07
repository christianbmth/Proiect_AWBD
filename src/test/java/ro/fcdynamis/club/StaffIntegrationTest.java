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
@ActiveProfiles("test")                // folosește application-test.properties (H2)
@AutoConfigureMockMvc
@AutoConfigureTestDatabase           // înlocuiește JDBC cu H2 pentru test
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class StaffIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @BeforeAll
    void setupData() throws Exception {
        // Creează doi membri staff
        mockMvc.perform(post("/api/staff")
                .contentType("application/json")
                .content("{\"prenume\":\"Ion\",\"nume\":\"Popescu\",\"functie\":\"Manager\"}"))
            .andExpect(status().isOk());

        mockMvc.perform(post("/api/staff")
                .contentType("application/json")
                .content("{\"prenume\":\"Ana\",\"nume\":\"Ionescu\",\"functie\":\"Secretar\"}"))
            .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Integration: GET /api/staff returns created staff ordered by nume")
    void testGetStaffIntegration() throws Exception {
        mockMvc.perform(get("/api/staff")
                .param("page", "0")
                .param("size", "10")
                .param("sort", "nume,asc")
            )
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.content.length()").value(2))
            .andExpect(jsonPath("$.content[0].nume").value("Ionescu"))
            .andExpect(jsonPath("$.content[1].nume").value("Popescu"))
            .andExpect(jsonPath("$.totalElements").value(2));
    }
}
