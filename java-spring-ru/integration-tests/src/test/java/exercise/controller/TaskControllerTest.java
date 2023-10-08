package exercise.controller;

import org.junit.jupiter.api.Test;
import static net.javacrumbs.jsonunit.assertj.JsonAssertions.assertThatJson;
import static org.assertj.core.api.Assertions.assertThat;
import org.instancio.Instancio;
import org.instancio.Select;

import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

import java.util.HashMap;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.datafaker.Faker;
import exercise.repository.TaskRepository;
import exercise.model.Task;

// END
@SpringBootTest
@AutoConfigureMockMvc
// BEGIN
public class TaskControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    private Faker faker;

    @Autowired
    ObjectMapper om;

    @Test
    public void testWelcomePage() throws Exception {
        var result = mockMvc.perform(get("/"))
            .andExpect(status().isOk())
            .andReturn();

        var body = result.getResponse().getContentAsString();
        assertThat(body).contains("Welcome to Spring!");
    }


    @Test
    public void testShow() throws Exception {
        var task = Instancio.of(Task.class)
            .ignore(Select.field((Task::getId)))
            .supply(Select.field((Task::getDescription)), () -> faker.lorem().paragraph())
            .supply(Select.field((Task::getTitle)), () -> faker.lorem().word())
            .create();

        taskRepository.save(task);

        var request = get("/tasks/" + task.getId());

        var result = mockMvc.perform(request).andExpect(status().isOk()).andReturn();

        var responseContent = result.getResponse().getContentAsString();

        var objectMapper = new ObjectMapper();
        var taskFromResponse = objectMapper.readValue(responseContent, Task.class);

        assertThat(taskFromResponse).isEqualTo(task);
    }

    @Test
    public void testCreate() throws Exception {
        var task = Instancio.of(Task.class)
            .ignore(Select.field((Task::getId)))
            .supply(Select.field((Task::getDescription)), () -> faker.lorem().paragraph())
            .supply(Select.field((Task::getTitle)), () -> faker.lorem().word())
            .create();


        var request = post("/tasks")
            .contentType(MediaType.APPLICATION_JSON)
            .content(om.writeValueAsString(task));

        var result = mockMvc.perform(request).andExpect(status().isCreated()).andReturn();

        var responseContent = result.getResponse().getContentAsString();

        var objectMapper = new ObjectMapper();
        var taskFromResponse = objectMapper.readValue(responseContent, Task.class);

        assertThat(taskFromResponse).isEqualTo(task);
    }

    @Test
    public void testUdate() throws Exception {
        var task = Instancio.of(Task.class)
            .ignore(Select.field((Task::getId)))
            .supply(Select.field((Task::getDescription)), () -> faker.lorem().paragraph())
            .supply(Select.field((Task::getTitle)), () -> faker.lorem().word())
            .create();
        taskRepository.save(task);

        var taskData = new HashMap<>();
        taskData.put("title", "titleUpdate");
        taskData.put("description", "descriptionUpdate");


        var request = put("/tasks/" + task.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .content(om.writeValueAsString(taskData));

        mockMvc.perform(request)
            .andExpect(status().isOk());

        var taskUpdated = taskRepository.findById(task.getId()).get();
        assertThat(taskUpdated.getDescription()).isEqualTo(("descriptionUpdate"));
        assertThat(taskUpdated.getTitle()).isEqualTo(("titleUpdate"));
    }

    @Test
    public void deleteTest() throws Exception {
        var task = Instancio.of(Task.class)
            .ignore(Select.field((Task::getId)))
            .supply(Select.field((Task::getDescription)), () -> faker.lorem().paragraph())
            .supply(Select.field((Task::getTitle)), () -> faker.lorem().word())
            .create();
        taskRepository.save(task);

        var request = delete("/tasks/" + task.getId());

        mockMvc.perform(request)
            .andExpect(status().isOk());

        var taskDeleted = taskRepository.findById(task.getId());
        assertThat(taskDeleted.isPresent()).isEqualTo(false);


    }

    /*
      @Test
    public void testWelcomePage() throws Exception {
        var result = mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andReturn();

        var body = result.getResponse().getContentAsString();
        assertThat(body).contains("Welcome to Spring!");
    }

    @Test
    public void testIndex() throws Exception {
        var result = mockMvc.perform(get("/tasks"))
                .andExpect(status().isOk())
                .andReturn();

        var body = result.getResponse().getContentAsString();
        assertThatJson(body).isArray();
    }


    private Task generateTask() {
        return Instancio.of(Task.class)
                .ignore(Select.field(Task::getId))
                .supply(Select.field(Task::getTitle), () -> faker.lorem().word())
                .supply(Select.field(Task::getDescription), () -> faker.lorem().paragraph())
                .create();
    }

    // BEGIN
    @Test
    public void testShow() throws Exception {

        var task = generateTask();
        taskRepository.save(task);

        var request = get("/tasks/{id}", task.getId());
        var result = mockMvc.perform(request)
                .andExpect(status().isOk())
                .andReturn();
        var body = result.getResponse().getContentAsString();

        assertThatJson(body).and(
            v -> v.node("title").isEqualTo(task.getTitle()),
            v -> v.node("description").isEqualTo(task.getDescription())
        );
    }

    @Test
    public void testCreate() throws Exception {
        var data = generateTask();

        var request = post("/tasks")
                .contentType(MediaType.APPLICATION_JSON)
                .content(om.writeValueAsString(data));

        mockMvc.perform(request)
                .andExpect(status().isCreated());

        var task = taskRepository.findByTitle(data.getTitle()).get();

        assertThat(task).isNotNull();
        assertThat(task.getTitle()).isEqualTo(data.getTitle());
        assertThat(task.getDescription()).isEqualTo(data.getDescription());
    }

    @Test
    public void testUpdate() throws Exception {
        var task = generateTask();
        taskRepository.save(task);

        var data = new HashMap<>();
        data.put("title", "new title");

        var request = put("/tasks/{id}", task.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(om.writeValueAsString(data));

        mockMvc.perform(request)
                .andExpect(status().isOk());

        task = taskRepository.findById(task.getId()).get();

        assertThat(task.getTitle()).isEqualTo(data.get("title"));
    }

    @Test
    public void testDelete() throws Exception {

        var task = generateTask();
        taskRepository.save(task);

        var request = delete("/tasks/{id}", task.getId());

        mockMvc.perform(request)
                .andExpect(status().isOk());

        task = taskRepository.findById(task.getId()).orElse(null);
        assertThat(task).isNull();
    }
     */

    // END
}
