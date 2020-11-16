package com.udacity.jdnd.course3.critter.repository;

import java.util.List;
import javax.transaction.Transactional;
import com.udacity.jdnd.course3.critter.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    
    public List<Schedule> findByPetsId(Long id);

    public List<Schedule> findByEmployeesId(Long id);

    public List<Schedule> findByPetsCustomerId(Long id);
    
}
