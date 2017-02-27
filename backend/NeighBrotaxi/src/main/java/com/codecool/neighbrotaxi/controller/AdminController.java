package com.codecool.neighbrotaxi.controller;


import com.codecool.neighbrotaxi.model.Role;
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

    /**
     * This route is the home route of the admin UI. After a successful login the server redirects here.
     * @return The name of the html template to render.
     */
    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String home() {
        return "admin_page";
    }

    /**
     * This route add all users from the database into the Model object, then returns the renderable templates name.
     * @param model Its a Model object which is autowired automatically by the spring,
     *              and it is passed to the rendering process, and it can use its stored variables.
     * @return the name of the template to render in String.
     */
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String getAllUsers(Model model) {
        if (adminService.getAllUser() == null) return "admin_users";
        model.addAttribute("user_list", adminService.getAllUser());
        return "admin_users";
    }


    @RequestMapping(value = "/roles", method = RequestMethod.GET)
    public String getAllRoles(Model model) {
        if (adminService.getAllRole() == null) return "admin_roles";
        model.addAttribute("role_list", adminService.getAllRole());
        return "admin_roles";
    }

    /**
     * Delete a user from the database with the adminService's deleteUser method.
     * @param userID Its the Id of the user in string given in the url, as path variable.
     * @return A String, and with it the spring redirect to the /admin/users route.
     */
    @RequestMapping(value = "/delete/{userID}", method = RequestMethod.DELETE)
        public String deleteUser(@PathVariable(value = "userID") String userID) {
        ResponseEntity<String> response = null;
        adminService.deleteUser(Integer.parseInt(userID));
        response = new ResponseEntity<String>(HttpStatus.NO_CONTENT);
        return "redirect:/admin/users";
    }

    @RequestMapping(value = "/add-role", method = RequestMethod.POST)
    public String addRole(@RequestParam(value = "name") String name) {
        Role newRole = new Role();
        newRole.setName(name.toUpperCase());
        adminService.addRole(newRole);
        return "redirect:/admin/roles";
    }
}