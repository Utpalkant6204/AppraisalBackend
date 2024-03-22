package com.utpal.AppraisalStudy.Repository;

import com.utpal.AppraisalStudy.Entity.Employees;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employees, Long> {
    public List<Employees> findAllByOrderByIdAsc();
    public Employees findByEmail(String email);

    @Query("SELECT p FROM Employees p WHERE " +
            "p.name LIKE CONCAT('%',:s, '%')")
    public List<Employees> searchEmployee(String s);
}
