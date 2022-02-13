package hu.uni.miskolc.teszteles.beadando.controller.officeworker;

import hu.uni.miskolc.teszteles.beadando.dao.OfficeWorker.OfficeWorkerRepository;
import hu.uni.miskolc.teszteles.beadando.model.OfficeWorker.OfficeWorkerModel;
import hu.uni.miskolc.teszteles.beadando.model.OfficeWorker.enums.WorkStatus;
import org.junit.jupiter.api.Test;
import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.Matchers.hasItem;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest (classes = {hu.uni.miskolc.teszteles.beadando.webapp.DemoApp.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class IntegrationOfficeWorkerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @LocalServerPort
    private int post;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private OfficeWorkerRepository officeWorkerRepository;


    @Test
    public void shouldNoMainPage() throws Exception {
        this.mockMvc.perform(get("/"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void shouldReturnTheGivenPeople() throws Exception {
        OfficeWorkerModel officeWorkerModel = new OfficeWorkerModel(1L,"Janos Bela", "bela@gmail.com", true, LocalDate.of(1990,10,11), WorkStatus.FULLTIME, 1000);
        officeWorkerRepository.save(officeWorkerModel);

        this.mockMvc.perform(get("/office/workers/all"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("[{\"id\":1,\"name\":\"Janos Bela\",\"email\":\"bela@gmail.com\",\"admin\":true,\"birthday\":\"1990-10-11\",\"status\":\"FULLTIME\",\"salary\":1000.0}]"));
    }

    @Test
    public void testWorker(){
        OfficeWorkerModel officeWorkerModel = new OfficeWorkerModel(1L,"Janos Bela", "bela@gmail.com", true, LocalDate.of(1990,10,11), WorkStatus.FULLTIME, 1000);
        OfficeWorkerModel officeWorkerModel2 = new OfficeWorkerModel(2L,"Nagy Peter", "peter@gmail.com", true, LocalDate.of(2000,10,20), WorkStatus.INTERN, 1030);
        officeWorkerRepository.save(officeWorkerModel);
        officeWorkerRepository.save(officeWorkerModel2);
        List<OfficeWorkerModel> list = testRestTemplate.getForObject("http://localhost:" + post + "/office/workers/all", List.class);
        assertEquals(2, list.size());
    }

}