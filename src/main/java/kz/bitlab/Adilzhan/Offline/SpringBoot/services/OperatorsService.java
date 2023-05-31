package kz.bitlab.Adilzhan.Offline.SpringBoot.services;

import kz.bitlab.Adilzhan.Offline.SpringBoot.entities.ApplicationRequest;
import kz.bitlab.Adilzhan.Offline.SpringBoot.entities.Operators;
import kz.bitlab.Adilzhan.Offline.SpringBoot.repositories.CoursesRepository;
import kz.bitlab.Adilzhan.Offline.SpringBoot.repositories.OperatorsRepository;
import kz.bitlab.Adilzhan.Offline.SpringBoot.repositories.RequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OperatorsService {
    private final RequestRepository requestRepository;
    private final CoursesRepository coursesRepository;
    private final OperatorsRepository operatorsRepository;

    public List<Operators> getAllOperators() {
        List<Operators> operatorsList = operatorsRepository.findAll();
//        operatorsList.removeAll(applicationRequestsService.getOneById(id).getOperatorsList());
        return operatorsList;
    }
    public void deleteOperator(Long idz, Long[] ido) {
        ApplicationRequest applicationRequest = requestRepository.findById(idz).orElseThrow();
        for(Long id : ido) {
            Operators operators = operatorsRepository.findById(id).orElseThrow();
            if(applicationRequest.getOperatorsList()!=null && applicationRequest.getOperatorsList().size()>0) {
                applicationRequest.getOperatorsList().remove(operators);
            }
        }
        requestRepository.save(applicationRequest);
    }
}
