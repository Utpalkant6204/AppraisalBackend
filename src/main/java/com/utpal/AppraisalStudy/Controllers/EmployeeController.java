package com.utpal.AppraisalStudy.Controllers;

import com.utpal.AppraisalStudy.Entity.DTO.*;
import com.utpal.AppraisalStudy.Entity.Tasks;
import com.utpal.AppraisalStudy.Services.Interfaces.EmployeeService;
import com.utpal.AppraisalStudy.Services.Interfaces.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private TaskService taskService;

    @GetMapping("/{id}/getEmployee")
    public ResponseEntity<EmployeeDTO> getEmployee(@PathVariable("id") long Id){
        EmployeeDTO employeeWithListDTO = employeeService.getEmployee(Id);
        return new ResponseEntity<>(employeeWithListDTO, HttpStatus.OK);
    }

    @GetMapping("/{id}/employeeDetails")
    public ResponseEntity<EmployeeWithListDTO> getEmployeeDetails(@PathVariable("id") long id){
        EmployeeWithListDTO emp = employeeService.getEmployeeDetails(id);
        return new ResponseEntity<>(emp, HttpStatus.OK);
    }

    @PostMapping("/{id}/saveTask")
    public ResponseEntity<PlainTaskResponse> saveTask(@RequestBody Tasks tasks, @PathVariable("id") long Id){
        PlainTaskResponse tsk = taskService.saveTask(tasks, Id);
        return new ResponseEntity<>(tsk, HttpStatus.CREATED);
    }

    @PutMapping("/{id}/updateTask")
    public ResponseEntity<TaskDTO> upDateTask(@PathVariable("id") long Id, @RequestBody  TaskDTO taskDTO){
        TaskDTO tsk =  taskService.updateTasks(Id, taskDTO);
        return new ResponseEntity<>(tsk, HttpStatus.OK);
    }

    @DeleteMapping("/{id}/deleteTask")
    public ResponseEntity<Boolean> deleteTasks(@PathVariable("id") long id){
        boolean val = taskService.deleteTasks(id);
        return new ResponseEntity<>(val, HttpStatus.OK);
    }

}
