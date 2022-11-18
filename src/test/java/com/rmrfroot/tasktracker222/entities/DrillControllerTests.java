package com.rmrfroot.tasktracker222.entities;

import com.rmrfroot.tasktracker222.dao.DrillSchedulesImpl;
import com.rmrfroot.tasktracker222.dao.UsersDAOImpl;
import com.rmrfroot.tasktracker222.services.DrillDaoImpl;
import com.rmrfroot.tasktracker222.services.DrillDaoService;
import com.rmrfroot.tasktracker222.services.DrillScheduleServiceImpl;
import com.rmrfroot.tasktracker222.services.UsersDaoServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class DrillControllerTests {

    @InjectMocks
    private DrillScheduleServiceImpl drillScheduleServiceImpl;

    @Mock
    DrillSchedulesImpl drillScheduleRepository;

    private DrillSchedules drillSchedules = new DrillSchedules("event_title", "start_date", "deadline_date",
            "location", "admin_name", "officer_email@root.com","note", "created_timestamp");

    @Test
    public void saveTest() throws Exception{

        drillScheduleServiceImpl.save(drillSchedules);
        verify(drillScheduleRepository,times(1)).save(drillSchedules);

    }

    @Test
    public void findDrillSchedulesByIdTest() throws Exception{

        drillSchedules.setId(1);
        drillScheduleServiceImpl.save(drillSchedules);
        when(drillScheduleServiceImpl.findDrillSchedulesById(1)).thenReturn(drillSchedules);
        assertEquals(drillSchedules,drillScheduleServiceImpl.findDrillSchedulesById(1));

    }


}
