package com.utpal.AppraisalStudy.Controllers;

import com.utpal.AppraisalStudy.Entity.DTO.AttributeDTO;
import com.utpal.AppraisalStudy.Entity.DTO.EmployeeWithListDTO;
import com.utpal.AppraisalStudy.Entity.DTO.PlainTaskResponse;
import com.utpal.AppraisalStudy.Entity.DTO.TaskDTO;
import com.utpal.AppraisalStudy.Entity.Tasks;
import com.utpal.AppraisalStudy.Services.EmployeeService;
import com.utpal.AppraisalStudy.Services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/getEmployees")
    public ResponseEntity<List<EmployeeWithListDTO>> getEmployees(){
        List<EmployeeWithListDTO> emp = employeeService.getAllEmployees();
        return new ResponseEntity<>(emp, HttpStatus.OK);
    }

    @GetMapping("/getTask")
    public ResponseEntity<List<TaskDTO>> getTasks(){
        List<TaskDTO> tsk = taskService.getTasks();
        return new ResponseEntity<>(tsk, HttpStatus.OK);
    }

    @DeleteMapping("/{id}/deleteTask")
    public ResponseEntity<Boolean> deleteTasks(@PathVariable("id") long id){
        boolean val = taskService.deleteTasks(id);
        return new ResponseEntity<>(val, HttpStatus.OK);
    }

    @PostMapping("/{id}/saveAttribute")
    public ResponseEntity<AttributeDTO> saveAttribute(@PathVariable("id") long id, @RequestBody AttributeDTO attributeDTO){
        AttributeDTO attributeDTO1 = employeeService.saveAttribute(id, attributeDTO);
        return new ResponseEntity<>(attributeDTO1, HttpStatus.CREATED);
    }

    @GetMapping("/search")
    public ResponseEntity<List<EmployeeWithListDTO>> searchEmployee(@RequestParam("name") String s){
        List<EmployeeWithListDTO> emp = employeeService.searchEmployees(s);
        return new ResponseEntity<>(emp, HttpStatus.OK);
    }
}
