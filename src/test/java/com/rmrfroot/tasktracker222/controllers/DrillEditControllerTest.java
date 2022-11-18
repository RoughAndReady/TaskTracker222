package com.rmrfroot.tasktracker222.controllers;

import com.rmrfroot.tasktracker222.entities.DrillSchedules;
import com.rmrfroot.tasktracker222.services.DrillDaoService;
import com.rmrfroot.tasktracker222.services.DrillScheduleService;
import com.rmrfroot.tasktracker222.services.DrillScheduleServiceImpl;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;

@WebMvcTest(DrillEditController.class)
class DrillEditControllerTest {

    @MockBean
    private DrillDaoService drillDaoService;

    @MockBean
    private DrillScheduleService drillScheduleService;

    //@MockBean
    //private DrillSchedulesImpl drillSchedulesImpl;

    @MockBean
    private DrillScheduleServiceImpl drillScheduleServiceImpl;

    //@InjectMocks
    @MockBean
    private DrillEditController drillEditController;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    MockMvc mockMvc;

    private DrillSchedules drillSchedules = new DrillSchedules("event_title", "start_date", "deadline_date",
            "location", "admin_name", "officer_email@root.com","note", "created_timestamp");



    @Before
    public void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .apply(springSecurity())
                .build();
    }

    @WithMockUser(username = "admin",roles={"USER","ADMIN"})
    @Test
    void editDrill() throws Exception {

        //given(drillScheduleService.save(drillSchedules)).willReturn(drillSchedules);
        //given(drillScheduleServiceImpl.save(drillSchedules)).willReturn(redirectedUrl("/drill-schedule-recipient/drills");
        //mockMvc.perform(drillEditController.editDrill(drillSchedules)).andExpect(redirectedUrl("/drill-schedule-recipient/drills");
        given(drillEditController.editDrill(drillSchedules))
                .willReturn(String.valueOf(redirectedUrl("/drill-schedule-recipient/drills")));

        MockHttpServletResponse response = mockMvc.perform(
                        post("/drill-schedule-recipient/drills")
                                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

    }

    @WithMockUser(username = "admin",roles={"USER","ADMIN"})
    @Test
    void deleteDrillById() throws Exception {

        //mockMvc.perform( MockMvcRequestBuilders.delete("/drills/{id}", 1) ).andExpect(status().isAccepted());
        //Mockito.verify(drillDaoService, Mockito.times(1)).deleteById(id);

        int id = 1;
        Mockito.doNothing().when(drillDaoService).deleteById(id);


    }
}