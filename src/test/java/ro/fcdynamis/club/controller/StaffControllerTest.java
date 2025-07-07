// File: src/test/java/ro/fcdynamis/club/controller/StaffControllerTest.java
package ro.fcdynamis.club.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.web.servlet.MockMvc;
import ro.fcdynamis.club.entity.Staff;
import ro.fcdynamis.club.repository.StaffRepository;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(StaffController.class)
class StaffControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StaffRepository staffRepository;

    @Test
    @DisplayName("GET /api/staff should return paged list")
    void testGetAllStaff() throws Exception {
        var staffList = List.of(new Staff("Ion","Popescu","Antrenor"));
        var page = new PageImpl<>(staffList, PageRequest.of(0, 10), 1);

        given(staffRepository.findAll(PageRequest.of(0,10))).willReturn(page);

        mockMvc.perform(get("/api/staff?page=0&size=10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].nume").value("Popescu"))
                .andExpect(jsonPath("$.totalElements").value(1));
    }
}
