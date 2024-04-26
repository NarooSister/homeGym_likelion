package com.example.homeGym.instructor.controller;

import com.example.homeGym.instructor.dto.ScheduleDto;
import com.example.homeGym.instructor.entity.Instructor;
import com.example.homeGym.instructor.service.ScheduleService;
import com.example.homeGym.common.util.AuthenticationFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/instructor/schedule")
@RequiredArgsConstructor
public class ScheduleController {
    private final ScheduleService scheduleService;
    private final AuthenticationFacade facade;

    private boolean isAuthenticated(Long instructorId) {
        Long currentInstructorId = facade.getCurrentInstructor().getId();
        return currentInstructorId.equals(instructorId);
    }

    @GetMapping()
    public String readSchedule(
            Model model
    ) {
        Instructor currentInstructor = facade.getCurrentInstructor();
        if (!isAuthenticated(currentInstructor.getId())) {
            throw new IllegalArgumentException("Authentication failed");
        }

        List<ScheduleDto> scheduleDtos = scheduleService.findAllByOrderByWeek();
        model.addAttribute("scheduleDtos", scheduleDtos);
        return "instructor/schedule/instructor-schedule";
    }

    @PostMapping()
    public String createSchedule(
            @RequestParam("week") String week,
            @RequestParam("time") String time,
            Model model
    ) {
        Instructor currentInstructor = facade.getCurrentInstructor();
        if (!isAuthenticated(currentInstructor.getId())) {
            throw new IllegalArgumentException("Authentication failed");
        }

        ScheduleDto scheduleDto = scheduleService.createSchedule(week, time);
        model.addAttribute("scheduleDto", scheduleDto);
        return "redirect:/instructor/schedule";
    }

    @PutMapping("/{scheduleId}")
    public String updateSchedule(
            @PathVariable("scheduleId") Long scheduleId,
            @RequestParam("week") String week,
            @RequestParam("time") String time,
            Model model
    ) {
        Instructor currentInstructor = facade.getCurrentInstructor();
        if (!isAuthenticated(currentInstructor.getId())) {
            throw new IllegalArgumentException("Authentication failed");
        }

        ScheduleDto scheduleDto = scheduleService.updateSchedule(scheduleId, week, time);
        model.addAttribute("scheduleDto", scheduleDto);
        return "redirect:/instructor/schedule";
    }

    @DeleteMapping("/{scheduleId}")
    public String deleteSchedule(
            @PathVariable("scheduleId") Long scheduleId
    ) {
        Instructor currentInstructor = facade.getCurrentInstructor();
        if (!isAuthenticated(currentInstructor.getId())) {
            throw new IllegalArgumentException("Authentication failed");
        }

        scheduleService.deleteSchedule(scheduleId);
        return "redirect:/instructor/schedule";
    }
}
