package com.todo.dao;

import java.util.List;

import com.todo.model.Task;
import com.todo.model.UserTask;

public interface ITaskDAO 
{
	void saveOrUpdateTask(Task task);

	void deleteTask(int taskid);

	Task getTask(int taskid);

	List<Task> getUserTaskList();

	void updateTask(Task task);

	List<UserTask> getAllUserTaskList(int taskId);

	void addUserTask(UserTask usertask);
	

}
