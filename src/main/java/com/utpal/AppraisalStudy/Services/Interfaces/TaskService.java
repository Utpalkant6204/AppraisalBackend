package com.utpal.AppraisalStudy.Services.Interfaces;

import com.utpal.AppraisalStudy.DTO.PlainTaskResponse;
import com.utpal.AppraisalStudy.DTO.TaskDTO;
import com.utpal.AppraisalStudy.Entity.Tasks;

import java.util.List;

public interface TaskService {
    public PlainTaskResponse saveTask(Tasks tasks, long Id);

    public List<TaskDTO> getTasks();

    public TaskDTO updateTasks(long id, TaskDTO taskDTO);

    public boolean deleteTasks(long id);
}
