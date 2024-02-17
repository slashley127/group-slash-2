package org.launchcode.roomranger.controllers;

import org.launchcode.roomranger.models.Leave;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("leave")
public class LeaveRequestController {

//    @Autowired
//    private LeaveRepository leaveRepository;
    @GetMapping
    public String displayLeaveRequestForm(Model model){
        model.addAttribute("leaveRequest",new Leave());
        return "attendant/leave_request";
    }

//    @PostMapping("submit")
//    public String submitLeaveRequestForm(@ModelAttribute Leave leave){
//        leave.setStatus("Pending");
//        leaveRepository.save(leave);
//        return "redirect:/leave";
//    }
//
//    @PostMapping("list")
//    public String listLeaveRequest(Model model){
//       List<Leave> leaves = leaveRepository.findAll();
//       model.addAttribute("leaveRequests",leaves);
//        return "attendant/list_request";
//    }
}
