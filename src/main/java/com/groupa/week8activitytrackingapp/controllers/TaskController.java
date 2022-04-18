package com.groupa.week8activitytrackingapp.controllers;
import com.groupa.week8activitytrackingapp.dto.TaskEditResponse;
import com.groupa.week8activitytrackingapp.dtos.SearchDto;
import com.groupa.week8activitytrackingapp.dtos.TaskDto;
import com.groupa.week8activitytrackingapp.model.Task;
import com.groupa.week8activitytrackingapp.services.servicesImpl.TaskServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
public class TaskController {

    private final TaskServiceImpl taskService;


    @Autowired
    public TaskController(TaskServiceImpl taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/tasks")
    public String home(Model model) {

        TaskDto task = new TaskDto();
        SearchDto searchDto = new SearchDto();

        var taskList = taskService.getAllTasks();
        model.addAttribute("list",taskList);
        model.addAttribute("taskObject", task);
        model.addAttribute("searchDto", searchDto);

        return "tasks";
    }


    @GetMapping("/error")
    public String error() {
        return "error";
    }



    @PostMapping("/tasks")
    public String createTask(@ModelAttribute("taskObject") TaskDto task) {
        try {
            Task createdTask = taskService.createTask(task.getTitle(), task.getDescription());
        } catch (Exception ex) {
            return "task_creation_error";
        }
        return "redirect:/tasks";
    }

    @GetMapping("/tasks/search")
    public String findTask(@ModelAttribute("searchDto") SearchDto searchDto, Model model) {
        TaskDto task = new TaskDto();
        System.out.println(searchDto.toString());

        Task searchTask = taskService.findTaskByTitle(searchDto.getTitle());
        model.addAttribute("searchFor",searchTask);
        model.addAttribute("taskObject", task);
        return "search_task";
    }


    @PutMapping("/tasks/edit/{id}")
    @ResponseBody
    public TaskEditResponse editTask(@PathVariable Long id, @RequestParam("title")String title,
                                     @RequestParam("description")String description,
                                     @RequestParam("status")String status, Model model) {


        taskService.editTask(id, title, description, status);

        TaskEditResponse taskEditResponse = new TaskEditResponse();

        taskEditResponse.setSuccess(true);
        taskEditResponse.setMessage("Successful!");

        return taskEditResponse;
    }

    @GetMapping("/tasks/delete/{id}")
    public String delete (@PathVariable(value = "id") long id) {
        taskService.deleteTask(id);

        return "redirect:/tasks";
    }

}

