package kz.bitlab.Adilzhan.Offline.SpringBoot.repositories;

import java.util.*;
import jakarta.transaction.Transactional;
import kz.bitlab.Adilzhan.Offline.SpringBoot.entities.Operators;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
@Transactional
public interface OperatorsRepository extends JpaRepository<Operators, Long> {
    List<Operators> getOperatorsById(Long id);
}
