package com.example.homeGym.order.controller;


import com.example.homeGym.common.util.AuthenticationFacade;
import com.example.homeGym.common.util.AuthenticationUtilService;
import com.example.homeGym.instructor.entity.Program;
import com.example.homeGym.instructor.service.ProgramService;
import com.example.homeGym.order.dto.ProgramOrderDto;
import com.example.homeGym.order.service.OrderService;
import com.example.homeGym.toss.dto.PaymentCancelDto;
import com.example.homeGym.toss.entity.Payment;
import com.example.homeGym.user.dto.ApplyDto;
import com.example.homeGym.user.dto.UserDto;
import com.example.homeGym.user.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriUtils;

import java.util.List;

@Controller
@RequestMapping("orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService service;
    private final ProgramService programService;
    private final AuthenticationUtilService authenticationUtilService;
    private final UserService userService;
  
    @GetMapping("/{programId}/schedule")
    public String selectSchedulePage(
            @PathVariable("programId") Long programId,
            Model model,
            Authentication authentication
    ){
        Long userId = authenticationUtilService.getId(authentication);

        //유저 정보
        UserDto userDto = userService.findById(userId);
        Program program = programService.findById(programId);
        model.addAttribute("program", program);
        model.addAttribute("user", userDto);
        return "order/select-schedule";
    }

    @GetMapping
    public List<ProgramOrderDto> readAll() {
        return service.readAll();
    }

    @GetMapping("{id}")
    public ProgramOrderDto readOne(
            @PathVariable("id")
            Long id
    ) {
        return service.readOne(id);
    }

    @GetMapping("/payment")
    public ModelAndView readTossPayment(
            @RequestParam(value = "programId", required = false) Long programId,
            @RequestParam(value = "userId", required = false) Long userId,
            @RequestParam(value = "count", required = false) String count,
            @RequestParam(value = "week", required = false) String week,
            @RequestParam(value = "time", required = false) String time
    ) throws JsonProcessingException {

        // URL 파라미터로 전달된 데이터를 사용하여 ProgramOrderDto를 생성합니다.
        ProgramOrderDto dto = new ProgramOrderDto();
        dto.setProgramId(programId);
        dto.setUserId(userId);
        dto.setCount(count);
        dto.setWeek(week);
        dto.setTime(time);

        System.out.println("dto = " + dto);

        //유저 정보
        UserDto userDto = userService.findById(userId);
        String userName = userDto.getName();
        ModelAndView mav = new ModelAndView();
        Program program = programService.findById(programId);
        int amount = 0;
        switch (count){
            case "1":
                amount = program.getPrice1();
                break;
            case "10":
                amount = program.getPrice10();
                break;
            case "20":
                amount = program.getPrice20();
                break;
        }
        String programName = program.getTitle();
        String userEmail = userDto.getEmail();


        mav.addObject("orderId", "abcddksdkf2203");
        mav.addObject("amount", amount);
        mav.addObject("orderName", programName);
        mav.addObject("userName", userName);
        mav.addObject("userEmail", userEmail);
        mav.addObject("userPhone", "01011111111");
        mav.setViewName("order/payment");

        return mav;
    }

    @GetMapping("/success")
    public String success() {
        //저장해야 되는 정보를 DB에 저장하는 서비스를 여기에 작성

        return "order/success";
    }

    @GetMapping("/fail")
    public ModelAndView fail(@RequestParam(value = "message") final String message, @RequestParam(value = "code")final String code) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("message", message);
        mav.addObject("code", code);
        mav.setViewName("order/fail");
        return mav;
    }

    @PostMapping("{id}/cancel")
    public Object cancelPayment(
            @PathVariable("id")
            Long id,
            @RequestBody
            PaymentCancelDto dto
    ) {
        return service.cancelPayment(id, dto);
    }

}
