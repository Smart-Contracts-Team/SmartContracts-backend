package com.SmartContracts.upc.smartcontract.repository;

import com.SmartContracts.upc.smartcontract.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task,Long> {
}
