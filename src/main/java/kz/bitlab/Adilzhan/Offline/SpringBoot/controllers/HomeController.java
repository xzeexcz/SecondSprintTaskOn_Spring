package kz.bitlab.Adilzhan.Offline.SpringBoot.controllers;

import kz.bitlab.Adilzhan.Offline.SpringBoot.entities.ApplicationRequest;
import kz.bitlab.Adilzhan.Offline.SpringBoot.entities.Courses;
import kz.bitlab.Adilzhan.Offline.SpringBoot.entities.Operators;
import kz.bitlab.Adilzhan.Offline.SpringBoot.repositories.CoursesRepository;
import kz.bitlab.Adilzhan.Offline.SpringBoot.repositories.OperatorsRepository;
import kz.bitlab.Adilzhan.Offline.SpringBoot.repositories.RequestRepository;
import kz.bitlab.Adilzhan.Offline.SpringBoot.services.ApplicationRequestService;
import kz.bitlab.Adilzhan.Offline.SpringBoot.services.CoursesService;
import kz.bitlab.Adilzhan.Offline.SpringBoot.services.OperatorsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

@Controller
@RequiredArgsConstructor
public class HomeController {
    private final RequestRepository requestRepository;
    private final CoursesRepository coursesRepository;
    private final OperatorsRepository operatorsRepository;

    private final ApplicationRequestService applicationRequestsService;
    private final CoursesService coursesService;
    private final OperatorsService operatorsService;

    @GetMapping(value = "/404")
    public String error() {
        return "404";
    }

    @GetMapping(value = "/")
    public String redirecting() {
        return "redirect:/home";
    }

    @GetMapping(value = "/home")
    public String home(Model model) {
        model.addAttribute("zayavki", applicationRequestsService.getAllRequests());
        return "home";
    }

    @GetMapping(value = "/add")
    public String add(Model model) {
        model.addAttribute("cursi", coursesService.getAllCourses());
        return "addRequest";
    }

    @GetMapping(value = "/unhandled")
    public String unhandled(Model model) {
        model.addAttribute("zayavki", applicationRequestsService.getAllUnHandledRequest());
        return "unhandled";
    }

    @GetMapping(value = "/handled")
    public String handled(Model model) {
        model.addAttribute("zayavki", applicationRequestsService.getAllHandledRequest());
        return "handled";
    }

    @PostMapping(value = "/add")
    public String add(@RequestParam(name = "request_name") String name,
                      @RequestParam(name = "request_course_id") Long id,
                      @RequestParam(name = "request_phone") String phone,
                      @RequestParam(name = "request_descr") String description) {
        if(applicationRequestsService.addRequest(name,phone,description,id)) {
            return "redirect:/home";
        } else {
            return "redirect:/404";
        }

    }

    @GetMapping(value = "/details/{idshka}")
    public String details(@PathVariable(name = "idshka") Long id, Model model) {
        model.addAttribute("zayavka", applicationRequestsService.getOneById(id));
        model.addAttribute("operatori", operatorsService.getAllOperators() );
        return "details";
    }

    @PostMapping(value = "/delete")
    public String delete(@RequestParam(name = "idshka") Long id) {
        applicationRequestsService.deleteRequest(id);
        return "redirect:/home";
    }

    @PostMapping(value = "/change")
    public String change(@RequestParam(name = "operator_id") Long[] id,
                         @RequestParam(name = "idshka") Long ids) {
            if (applicationRequestsService.change(id, ids)) {
                return "redirect:/details/" + applicationRequestsService.getOneById(ids).getId();
            } else {
                return "redirect:/404";
            }
    }

    @PostMapping(value = "/delete-operator")
    public String delete(@RequestParam(name = "operator_id") Long[] id,
                         @RequestParam(name = "idshka") Long id1) {
        operatorsService.deleteOperator(id1, id);
        return "redirect:/details/"+applicationRequestsService.getOneById(id1).getId();
    }
}
