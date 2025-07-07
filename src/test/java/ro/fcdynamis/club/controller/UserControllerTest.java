package ro.fcdynamis.club.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.data.domain.*;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import ro.fcdynamis.club.entity.User;
import ro.fcdynamis.club.entity.Staff;
import ro.fcdynamis.club.repository.UserRepository;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserRepository userRepository;

    @Test
    @DisplayName("GET /api/useri returns paged list of users")
    void testGetAllUsersPaged() throws Exception {
        // Staff doar cu nume și funcție
        Staff st = new Staff("Ion","Popescu", "Manager");
        st.setIdStaff(7L);

        User u1 = new User(st, "u1@x.ro", "pass1");
        u1.setIdUser(1L);
        User u2 = new User(null, "u2@x.ro", "pass2");
        u2.setIdUser(2L);

        Pageable pageable = PageRequest.of(0, 5, Sort.by("username").ascending());
        Page<User> page = new PageImpl<>(List.of(u1, u2), pageable, 2);

        Mockito.when(userRepository.findAll(Mockito.any(Pageable.class)))
               .thenReturn(page);

        mockMvc.perform(get("/api/useri")
                .param("page", "0")
                .param("size", "5")
                .param("sort", "username,asc")
                .accept(MediaType.APPLICATION_JSON)
            )
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.content.length()").value(2))
            .andExpect(jsonPath("$.content[0].username").value("u1@x.ro"))
            .andExpect(jsonPath("$.totalElements").value(2))
            .andExpect(jsonPath("$.number").value(0));
    }
}
