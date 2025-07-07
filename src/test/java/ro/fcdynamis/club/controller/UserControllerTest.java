// src/test/java/ro/fcdynamis/club/controller/UserControllerTest.java
package ro.fcdynamis.club.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;
import ro.fcdynamis.club.entity.Staff;
import ro.fcdynamis.club.entity.User;
import ro.fcdynamis.club.repository.UserRepository;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
@AutoConfigureMockMvc(addFilters = false)
@ActiveProfiles("test")
public class UserControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserRepository repo;

    @Autowired
    private ObjectMapper json;

    @Test
    @DisplayName("GET /api/useri/{id} găsește un user existent")
    void testGetById() throws Exception {
        Staff s = new Staff("Ion","Pop","Antrenor");
        User u = new User(s, "ion.pop@example.com", "pwd");
        when(repo.findById(3L)).thenReturn(Optional.of(u));

        mvc.perform(get("/api/useri/3"))
           .andExpect(status().isOk())
           .andExpect(jsonPath("$.username").value("ion.pop@example.com"));
    }

    @Test
    @DisplayName("PUT /api/useri/{id} actualizează user")
    void testUpdate() throws Exception {
        Staff s = new Staff("Ion","Pop","Antrenor");
        User existing = new User(s, "old@example.com", "pwd");
        when(repo.findById(7L)).thenReturn(Optional.of(existing));
        when(repo.save(any(User.class))).thenAnswer(i -> i.getArgument(0));

        User upd = new User(s, "nou@example.com", "pwd2");
        mvc.perform(put("/api/useri/7")
            .contentType(MediaType.APPLICATION_JSON)
            .content(json.writeValueAsString(upd)))
           .andExpect(status().isOk())
           .andExpect(jsonPath("$.username").value("nou@example.com"));
    }
}
