package com.rmrfroot.tasktracker222.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rmrfroot.tasktracker222.awsCognito.PoolClientInterface;
import com.rmrfroot.tasktracker222.entities.Drill;
import com.rmrfroot.tasktracker222.services.DrillDaoService;
import com.rmrfroot.tasktracker222.services.UsersDaoService;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@WebMvcTest(DrillController.class)
class DrillControllerTest {

    @MockBean
    private DrillDaoService drillDaoService;

    @MockBean
    private UsersDaoService usersDaoService;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }




    private Drill drillSchedules = new Drill("event_title", "start_date", "deadline_date",
            "location", "admin_name", "officer_email@root.com","note", "created_timestamp");



    @Test
    void drillScheduleRecipientGenericTest() {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String weekOf = dateFormat.format(LocalDateTime.now());
        //mockMvc.perform(get("/drill-schedule-recipient/week/"+weekOf).with(csrf())


        //.andExpect(status().isFound());
        //.contentType(MediaType.APPLICATION_JSON)
        //.content(asJsonString(drill1)))
    }
    @Test
    void drillScheduleRecipientTest() {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String weekOf = dateFormat.format(LocalDateTime.now());
        //mockMvc.perform(get("/drill-schedule-recipient/week/"+weekOf).with(csrf())


                //.andExpect(status().isFound());
        //.contentType(MediaType.APPLICATION_JSON)
        //.content(asJsonString(drill1)))
    }

    @Test
    void drillScheduleManagerTest() {
    }



    @Test
    void editDrillIsFoundTest() throws Exception {

        Drill drill1 = new Drill("event_title", "start_date", "deadline_date",
                "location", "admin_name", "officer_email@root.com",
                "note", "created_timestamp");

        when(drillDaoService.findById(drill1.getId())).thenReturn(drill1);
        when(drillDaoService.update(drill1.getId(),drill1)).thenReturn(drill1);

        mockMvc.perform(post("/edit-drill").with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(drill1)))
                    .andExpect(status().isFound());
    }


    @Test
    void deleteDrillTest() {
    }



    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}