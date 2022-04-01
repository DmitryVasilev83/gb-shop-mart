package ru.gb.gbshopmart.web.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.gb.gbshopmart.entity.Category;
import ru.gb.gbshopmart.entity.Manufacturer;
import ru.gb.gbshopmart.service.CategoryService;
import ru.gb.gbshopmart.web.dto.CategoryDto;
import ru.gb.gbshopmart.web.dto.ManufacturerDto;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class CategoryControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    CategoryService categoryService;

    @Test
    @Order(1)
    void testSaveManufacturerTest() throws Exception {

        mockMvc.perform(post("/api/v1/category")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper
                                .writeValueAsString(Category.builder()
                                        .name("Category1")
                                        .build())))
                .andExpect(status().isCreated());
    }

    @Test
    @Order(2)
    void testSaveManufacturerTest2() throws Exception {

        mockMvc.perform(post("/api/v1/category")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper
                                .writeValueAsString(Category.builder()
                                        .name("Category2")
                                        .build())))
                .andExpect(status().isCreated());
    }


    @Test
    public void findAllTest() throws Exception {

        mockMvc.perform(get("/api/v1/category"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("id")))
                .andExpect(jsonPath("$.[0].id").value("1"))
                .andExpect(jsonPath("$.[0].name").value("Category1"));
    }

    @Test
    public void findCategoryByIdTest() throws Exception {

        mockMvc.perform(get("/api/v1/category/2"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("id")))
                .andExpect(jsonPath("$.[1].id").value("2"))
                .andExpect(jsonPath("$.[1].name").value("Category2"));
    }

    @Test
    public void findCategoryByIdNotFoundTest() throws Exception {
        mockMvc.perform(get("/api/v1/category/3"))
                .andExpect(status().isNotFound());
    }

}
