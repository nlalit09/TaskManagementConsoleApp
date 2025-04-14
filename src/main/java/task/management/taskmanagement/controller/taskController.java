package task.management.taskmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import task.management.taskmanagement.Service.TaskService;
import task.management.taskmanagement.modelOREntity.Status;
import task.management.taskmanagement.modelOREntity.Task;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
public class taskController {

    private final TaskService taskService;

    @Autowired
    public taskController(TaskService taskService) {
        this.taskService = taskService;
    }
    @PostMapping(consumes = {"application/json", "text/plain"})
    public List<Task> createTask(@RequestBody List<Task> task){
        task.forEach(t->t.setStartDate(LocalDate.now()));
        return taskService.createTasks(task);
    }

    @GetMapping("/tasks")
    public List<Task> getAllTasks(){
        return taskService.getAllTasks();
    }
    @GetMapping("task/id/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id){
        Task task=taskService.getTaskById(id);
        return new ResponseEntity<>(task, HttpStatus.OK);
    }
    @GetMapping("/task/status/{status}")
    public ResponseEntity<List<Task>> getTasksByStatus(@PathVariable Status status){
        List<Task> tasks=taskService.getTaskByStatus(status);
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable Long id){
        taskService.deleteTask(id);
        return ResponseEntity.ok("Deleted Successfully");
    }
    @PutMapping("/{id}/status")
    public ResponseEntity<String> updateStatus(@PathVariable Long id, @RequestParam Status status){
        taskService.updateTask(id,status);
        return ResponseEntity.ok("Updated Successfully");
    }

}
