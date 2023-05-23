package kz.bitlab.Adilzhan.Offline.SpringBoot.controllers;

import kz.bitlab.Adilzhan.Offline.SpringBoot.entities.ApplicationRequest;
import kz.bitlab.Adilzhan.Offline.SpringBoot.entities.Courses;
import kz.bitlab.Adilzhan.Offline.SpringBoot.repositories.CoursesRepository;
import kz.bitlab.Adilzhan.Offline.SpringBoot.repositories.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

@Controller
public class HomeController {
    @Autowired
    RequestRepository requestRepository;
    @Autowired
    CoursesRepository coursesRepository;

    @GetMapping(value = "/")
    public String redirecting() {
        return "redirect:/home";
    }

    @GetMapping(value = "/home")
    public String home(Model model) {
        List<ApplicationRequest> applicationRequests = requestRepository.findAll();
        Collections.sort(applicationRequests, Comparator.comparing(ApplicationRequest::getHandled));
        model.addAttribute("zayavki", applicationRequests);
        return "home";
    }

    @GetMapping(value = "/add")
    public String add(Model model) {
        List<Courses> courses = coursesRepository.findAll();
        model.addAttribute("cursi", courses);
        return "addRequest";
    }

    @GetMapping(value = "/unhandled")
    public String unhandled(Model model) {
        List<ApplicationRequest> applicationRequests = requestRepository.findAllByHandledFalse();
        model.addAttribute("zayavki", applicationRequests);
        return "unhandled";
    }

    @GetMapping(value = "/handled")
    public String handled(Model model) {
        List<ApplicationRequest> applicationRequests = requestRepository.findAllByHandledTrue();
        model.addAttribute("zayavki", applicationRequests);
        return "handled";
    }

    @PostMapping(value = "/add")
    public String add(@RequestParam(name = "request_name") String name,
                      @RequestParam(name = "request_course_id") Long id,
                      @RequestParam(name = "request_phone") String phone,
                      @RequestParam(name = "request_descr") String description) {
        if ((name != null) && (id != null) && (phone != null) && (description != null)) {
            ApplicationRequest applicationRequest = new ApplicationRequest();
            applicationRequest.setCommentary(description);
            applicationRequest.setCourseName(new Courses(id));
            applicationRequest.setPhone(phone);
            applicationRequest.setHandled(false);
            applicationRequest.setUserName(name);
            requestRepository.save(applicationRequest);
            return "redirect:/home";
        } else {
            return "redirect:/404";
        }
    }

    @GetMapping(value = "/details/{idshka}")
    public String details(@PathVariable(name = "idshka") Long id, Model model) {
        ApplicationRequest applicationRequest = requestRepository.getOne(id);
        model.addAttribute("zayavka", applicationRequest);
        return "details";
    }

    @PostMapping(value = "/delete")
    public String delete(@RequestParam(name = "idshka") Long id) {
        requestRepository.deleteById(id);
        return "redirect:/home";
    }

    @PostMapping(value = "/change")
    public String change(@RequestParam(name = "idshka") Long id) {
        requestRepository.updateStatusHandledTrue(id);
        return "redirect:/home";
    }
}
