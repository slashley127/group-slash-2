//package org.launchcode.roomranger.authorization;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.io.IOException;
//
//@RestController
//@RequestMapping("/api")
//public class AuthorizationController {
//
//    private final JwtClaimsService jwtClaimsService;
//
//    @Autowired
//    public AuthorizationController(JwtClaimsService jwtClaimsService) {
//        this.jwtClaimsService = jwtClaimsService;
//    }
//
//    @GetMapping("/authorize")
//    public boolean authorize(String jwtToken) {
//        try {
//            JwtClaims jwtClaims = jwtClaimsService.parseJwtClaims(jwtToken);
//            String roles = jwtClaims.getRoles();
//
//            // Implement authorization logic based on roles
//            if (roles.contains("manager")) {
//                // Managers can create tasks
//                return true;
//            } else if (roles.contains("roomAttendant")) {
//                // Room attendants can view tasks, update status, and apply for leave
//                return true;
//            } else {
//                // Default case: unauthorized
//                return false;
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//            return false;
//        }
//    }
//}
