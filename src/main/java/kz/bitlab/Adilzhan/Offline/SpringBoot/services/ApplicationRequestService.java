package kz.bitlab.Adilzhan.Offline.SpringBoot.services;


import kz.bitlab.Adilzhan.Offline.SpringBoot.entities.ApplicationRequest;
import kz.bitlab.Adilzhan.Offline.SpringBoot.entities.Courses;
import kz.bitlab.Adilzhan.Offline.SpringBoot.entities.Operators;
import kz.bitlab.Adilzhan.Offline.SpringBoot.repositories.CoursesRepository;
import kz.bitlab.Adilzhan.Offline.SpringBoot.repositories.OperatorsRepository;
import kz.bitlab.Adilzhan.Offline.SpringBoot.repositories.RequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ApplicationRequestService {
    private final CoursesRepository coursesRepository;
    private final RequestRepository requestRepository;
    private final OperatorsRepository operatorsRepository;
    public List<ApplicationRequest> getAllRequests() {
        List<ApplicationRequest> applicationRequests = requestRepository.findAll();
        Collections.sort(applicationRequests, Comparator.comparing(ApplicationRequest::getHandled));
        return applicationRequests;
    }
    public List<ApplicationRequest> getAllUnHandledRequest() {
        List<ApplicationRequest> applicationRequests = requestRepository.findAllByHandledFalse();
        return applicationRequests;
    }
    public List<ApplicationRequest> getAllHandledRequest() {
        List<ApplicationRequest> applicationRequests = requestRepository.findAllByHandledTrue();
        return applicationRequests;
    }
    public boolean addRequest(String name, String phone, String description, Long id) {
        boolean added;
        if ((name != null) || (id != null) || (phone != null) || (description != null)) {
            ApplicationRequest applicationRequest = new ApplicationRequest();
            applicationRequest.setCommentary(description);
            applicationRequest.setCourseName(new Courses(id));
            applicationRequest.setPhone(phone);
            applicationRequest.setHandled(false);
            applicationRequest.setUserName(name);
            requestRepository.save(applicationRequest);
            added = true;
        } else {
            added = false;
        }
        return added;
    }
    public void deleteRequest(Long id) {
        requestRepository.deleteById(id);
    }
    public ApplicationRequest getOneById(Long id) {
        ApplicationRequest applicationRequest = requestRepository.getOne(id);
        return applicationRequest;
    }
    public boolean change(Long[] ids, Long id2) {
        boolean changed;
        if(ids == null || ids.length == 0) {
            changed = false;
        } else {
            ApplicationRequest applicationRequest = requestRepository.findById(id2).orElseThrow();
            List<Operators> operatorsList = new ArrayList<>();
            for (Long id : ids) {
                Operators operators = operatorsRepository.findById(id).orElseThrow();
                operatorsList.add(operators);
            }
            applicationRequest.setOperatorsList(operatorsList);
            requestRepository.updateStatusHandledTrue(id2);
            requestRepository.save(applicationRequest);
            changed = true;
        }
        return changed;
    }
}
