package task.management.taskmanagement.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import task.management.taskmanagement.Exception.TaskNotFoundException;
import task.management.taskmanagement.modelOREntity.Status;
import task.management.taskmanagement.modelOREntity.Task;
import task.management.taskmanagement.repository.Repository;

import java.time.LocalDate;
import java.util.List;

@Service
public class TaskService {

    private final Repository repository;

    @Autowired
    public TaskService(Repository repository) {
        this.repository = repository;
    }

    public Task createTask(Task task) {
        return repository.save(task);
    }

    public List<Task> getAllTasks(){
        return repository.findAll();
    }

    public Task getTaskById(Long id) {
        return repository.findById(id).orElseThrow(()-> new TaskNotFoundException("Task Not found"));
    }

    public List<Task> getTaskByStatus(Status status) {
        return repository.findByStatus(status);
    }
    public Task updateTask(Long id,Status newstatus) {
        Task task = getTaskById(id);
        task.setStatus(newstatus);
        return repository.save(task);
    }

    public void deleteTask(Long id) {
        Task task = getTaskById(id);
        repository.delete(task);
    }

    public List<Task> createTasks(List<Task> task) {

        for(Task task1: task){

            repository.save(task1);
        }
        return task;
    }
}
