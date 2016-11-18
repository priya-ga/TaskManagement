package com.todo.service.impl;

import java.util.List;

import com.todo.dao.ITaskDAO;
import com.todo.dao.impl.TaskDAOImpl;
import com.todo.model.Task;
import com.todo.model.UserTask;
import com.todo.service.ITaskService;

public class TaskServiceImpl implements ITaskService
{
	private ITaskDAO taskDAO;
	
	@Override
	public void saveOrUpdateTask(Task task) {
		
		 taskDAO.saveOrUpdateTask(task);
		
	}

	@Override
	public void deleteTask(int taskid) {
		taskDAO.deleteTask(taskid);
		
	}

	@Override
	public Task getTask(int taskid) {
		return taskDAO.getTask(taskid);
		
	}

	@Override
	public List<Task> getUserTaskList() {
		return taskDAO.getUserTaskList();
		
	}

	@Override
	public void updateTask(Task task) {
		taskDAO.updateTask(task);
		
	}

	@Override
	public List<UserTask> getAllUserTaskList(int taskId) {
		System.out.println("in taskService");
		
		TaskDAOImpl taskDAOImpl = new TaskDAOImpl();
		List<UserTask> userTaskList = taskDAOImpl.getAllUserTaskList(taskId);
		
		return userTaskList;
		
	}

	@Override
	public void addUserTask(UserTask usertask) {
		taskDAO.addUserTask(usertask);
		
	}

	
}
