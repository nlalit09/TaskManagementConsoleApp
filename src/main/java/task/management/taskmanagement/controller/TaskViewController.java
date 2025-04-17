package task.management.taskmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import task.management.taskmanagement.Service.TaskService;
import task.management.taskmanagement.modelOREntity.Task;

import java.time.LocalDate;
import java.util.List;

@Controller
public class TaskViewController {

    private final TaskService taskService;

    @Autowired
    public TaskViewController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/view-tasks")
    public String getTasks(Model model) {
        List<Task> tasks = taskService.getAllTasks();  // Fetch tasks from service
        model.addAttribute("tasks", tasks);  // Add tasks to model
        return "taskList";  // Return the view (taskList.html)
    }

    // Handle form submission for new task
    @PostMapping("/create-task")
    public String createTask(@ModelAttribute Task task) {
        task.setStartDate(LocalDate.now());
        taskService.createTask(task);
        return "redirect:/view-tasks";
    }


    // Show create task form
    @GetMapping("/create-task")
    public String showCreateTaskForm(Model model) {
        model.addAttribute("task", new Task());
        return "createTask";
    }


    // Show update form
    @GetMapping("/update-task/{id}")
    public String showUpdateForm(@PathVariable Long id, Model model) {
        Task task = taskService.getTaskById(id);
        model.addAttribute("task", task);
        return "updateTask";
    }


    // Handle update task
    @PostMapping("/update-task/{id}")
    public String updateTask(@PathVariable Long id, @ModelAttribute Task updatedTask) {
        taskService.updateTask(id, updatedTask.getStatus());
        return "redirect:/view-tasks";
    }

    // Delete task
    @GetMapping("/delete-task/{id}")
    public String deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return "redirect:/view-tasks";
    }
}
