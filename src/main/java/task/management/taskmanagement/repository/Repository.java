package task.management.taskmanagement.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import task.management.taskmanagement.modelOREntity.Status;
import task.management.taskmanagement.modelOREntity.Task;

import java.util.List;


public interface Repository extends JpaRepository<Task,Long> {
    List<Task> findByStatus(Status status);
}
