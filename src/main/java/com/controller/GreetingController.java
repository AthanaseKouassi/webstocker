package com.controller;

import com.services.CustomerService;
import com.entities.Customer;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GreetingController {

    CustomerService service;

    @Autowired
    public GreetingController(CustomerService service) {
        this.service = service;
    }

    @RequestMapping(value = "/greeting", method = RequestMethod.GET)
    public String greeting(@RequestParam(value = "name", required = false, defaultValue = "World") String name, Model model) {

        Customer customer = service.getOne(1L);

        model.addAttribute("name", customer.getFirstName()+customer.getLastName());

        return "greeting";
    }

    @ModelAttribute("allCustomers")
    public List<Customer> populateFeatures() {
        return (List<Customer>) service.getAll();
    }

}
