package kz.bitlab.Adilzhan.Offline.SpringBoot.services.impl;

import kz.bitlab.Adilzhan.Offline.SpringBoot.entities.Operators;
import kz.bitlab.Adilzhan.Offline.SpringBoot.repositories.OperatorsRepository;
import kz.bitlab.Adilzhan.Offline.SpringBoot.services.OperatorService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@RequiredArgsConstructor
public class OperatorImpls implements OperatorService {
     private final OperatorsRepository operatorsRepository;
    @Override
    public List<Operators> getAll() {
        return operatorsRepository.findAll();
    }
}
