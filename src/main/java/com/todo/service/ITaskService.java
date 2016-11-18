package com.todo.service;

import java.util.List;

import com.todo.model.Task;
import com.todo.model.UserTask;

public interface ITaskService 
{

	public void saveOrUpdateTask(Task task);

	public void deleteTask(int taskid);

	public Task getTask(int taskid);

	public List<Task> getUserTaskList();

	public void updateTask(Task task);

	public List<UserTask> getAllUserTaskList(int taskId);

	public void addUserTask(UserTask usertask);

	
}
