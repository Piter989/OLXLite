package com.olxLite.olxLite_bff;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.olxLite.olxLite_bff.controller.ItemController;
import com.olxLite.olxLite_bff.model.Item;

class ItemControllerTest {

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    /**
     * 
     */
    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(new ItemController()).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void testCreateAndGetItem() throws Exception {
        Item item = new Item(null, "Test", 5, "user1", "desc", "opinion");
        String json = objectMapper.writeValueAsString(item);

        // Create item
        String response = mockMvc.perform(post("/item")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Test"))
                .andReturn().getResponse().getContentAsString();

        Item created = objectMapper.readValue(response, Item.class);

        // Get item by id
        mockMvc.perform(get("/item/" + created.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Test"));
    }

    @Test
    void testGetAllItems() throws Exception {
        // Add one item
        Item item = new Item(null, "Test", 5, "user1", "desc", "opinion");
        String json = objectMapper.writeValueAsString(item);

        mockMvc.perform(post("/item")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk());

        // Get all items
        mockMvc.perform(get("/item"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Test"));
    }

    @Test
    void testUpdateItem() throws Exception {
        // Create item
        Item item = new Item(null, "Test", 5, "user1", "desc", "opinion");
        String json = objectMapper.writeValueAsString(item);

        String response = mockMvc.perform(post("/item")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andReturn().getResponse().getContentAsString();

        Item created = objectMapper.readValue(response, Item.class);

        // Update item
        created.setName("Updated");
        String updatedJson = objectMapper.writeValueAsString(created);

        mockMvc.perform(put("/item/" + created.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(updatedJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Updated"));
    }

    @Test
    void testDeleteItem() throws Exception {
        // Create item
        Item item = new Item(null, "Test", 5, "user1", "desc", "opinion");
        String json = objectMapper.writeValueAsString(item);

        String response = mockMvc.perform(post("/item")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andReturn().getResponse().getContentAsString();

        Item created = objectMapper.readValue(response, Item.class);

        // Delete item
        mockMvc.perform(delete("/item/" + created.getId()))
                .andExpect(status().isOk());

        // Try to get deleted item
        mockMvc.perform(get("/item/" + created.getId()))
                .andExpect(status().isOk())
                .andExpect(content().string(""));
    }
}
