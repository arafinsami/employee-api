package com.employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.employee.entity.ActionLog;

@Repository
public interface ActionLogRepository extends JpaRepository<ActionLog, Long> {

}
