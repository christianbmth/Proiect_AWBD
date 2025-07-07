// src/test/java/ro/fcdynamis/club/UserIntegrationTest.java
package ro.fcdynamis.club;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@ActiveProfiles("test")
public class UserIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @Test
    void setupData() throws Exception {
        mvc.perform(get("/api/useri"))
           .andExpect(status().isOk());
    }
}
