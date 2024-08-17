package com.novalabs.task.repository;

import com.novalabs.task.model.Tasks;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Tasks,Long> {
}
