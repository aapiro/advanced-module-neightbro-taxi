package com.codecool.neighbrotaxi.controller;

import com.codecool.neighbrotaxi.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String getAllUsers(Model model) {
        System.out.println(adminService.getAllUser().get(0).getRoles());
        model.addAttribute("user_list", adminService.getAllUser());
        return "admin_users";
    }

    @RequestMapping(value = "/delete/{userID}", method = RequestMethod.DELETE)
        public String deleteUser(@PathVariable(value = "userID") String userID) {
        ResponseEntity<String> response = null;
        adminService.deleteUser(Integer.parseInt(userID));
        response = new ResponseEntity<String>(HttpStatus.NO_CONTENT);
        return "redirect:/admin/users";
    }

}
