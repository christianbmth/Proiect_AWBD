// File: src/test/java/ro/fcdynamis/club/controller/StaffControllerTest.java
package ro.fcdynamis.club.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.data.domain.Pageable;   // ← import adăugat
import ro.fcdynamis.club.entity.Staff;
import ro.fcdynamis.club.repository.StaffRepository;

import static org.mockito.Mockito.when;          // ← pentru consistență
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = StaffController.class)
@AutoConfigureMockMvc(addFilters = false)
@ActiveProfiles("test")
public class StaffControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private StaffRepository repo;

    @Autowired
    private ObjectMapper json;

    @Test
    @DisplayName("GET /api/staff?page… returnează listă goală")
    void testGetAllPaged() throws Exception {
        // Specificăm findAll(Pageable) ca să nu fie ambiguu
        when(repo.findAll(Mockito.any(Pageable.class)))
               .thenReturn(org.springframework.data.domain.Page.empty());

        mvc.perform(get("/api/staff?page=0&size=5"))
           .andExpect(status().isOk())
           .andExpect(jsonPath("$.content").isArray());
    }

    @Test
    @DisplayName("POST /api/staff creează un staff nou")
    void testCreate() throws Exception {
        Staff s = new Staff("Ion", "Pop", "Antrenor");
        when(repo.save(Mockito.any(Staff.class))).thenReturn(s);

        mvc.perform(post("/api/staff")
            .contentType(MediaType.APPLICATION_JSON)
            .content(json.writeValueAsString(s)))
           .andExpect(status().isOk())
           .andExpect(jsonPath("$.prenume").value("Ion"));
    }

    @Test
    @DisplayName("DELETE /api/staff/{id} șterge staff existent")
    void testDelete() throws Exception {
        when(repo.existsById(5L)).thenReturn(true);

        mvc.perform(delete("/api/staff/5"))
           .andExpect(status().isNoContent());
    }
}
