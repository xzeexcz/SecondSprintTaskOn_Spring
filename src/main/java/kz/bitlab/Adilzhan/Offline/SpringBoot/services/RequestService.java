package kz.bitlab.Adilzhan.Offline.SpringBoot.services;

import kz.bitlab.Adilzhan.Offline.SpringBoot.entities.ApplicationRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RequestService {
    void addRequest(ApplicationRequest applicationRequest);

    List<ApplicationRequest> getAllRequests();

    ApplicationRequest getRequestById(Long id);

    ApplicationRequest changeStatusRequest(ApplicationRequest applicationRequest);

    void geleteRequestById(Long id);
}
