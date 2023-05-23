package kz.bitlab.Adilzhan.Offline.SpringBoot.services.impl;

import kz.bitlab.Adilzhan.Offline.SpringBoot.entities.ApplicationRequest;
import kz.bitlab.Adilzhan.Offline.SpringBoot.repositories.RequestRepository;
import kz.bitlab.Adilzhan.Offline.SpringBoot.services.RequestService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class RequestImpl implements RequestService {
    @Autowired
    RequestRepository requestRepository;

    @Override
    public void addRequest(ApplicationRequest applicationRequest) {
        requestRepository.save(applicationRequest);
    }

    @Override
    public List<ApplicationRequest> getAllRequests() {
        return requestRepository.findAll();
    }

    @Override
    public ApplicationRequest getRequestById(Long id) {
        return requestRepository.getOne(id);
    }

    @Override
    public ApplicationRequest changeStatusRequest(ApplicationRequest applicationRequest) {
        return requestRepository.save(applicationRequest);
    }

    @Override
    public void geleteRequestById(Long id) {
        requestRepository.deleteById(id);
    }
}
