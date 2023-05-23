package kz.bitlab.Adilzhan.Offline.SpringBoot.repositories;

import jakarta.transaction.Transactional;
import kz.bitlab.Adilzhan.Offline.SpringBoot.entities.ApplicationRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public interface RequestRepository extends JpaRepository<ApplicationRequest, Long> {

    List<ApplicationRequest> findAllByHandledTrue();

    List<ApplicationRequest> findAllByHandledFalse();

    @Modifying
    @Query("UPDATE ApplicationRequest as a SET a.handled = true WHERE a.id = :id")
    void updateStatusHandledTrue(@Param("id") Long id);

}
