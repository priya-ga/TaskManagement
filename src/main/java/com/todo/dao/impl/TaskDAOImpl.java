package com.todo.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.todo.dao.ITaskDAO;
import com.todo.model.Task;
import com.todo.model.User;
import com.todo.model.UserTask;
import com.todo.util.DBUtil;

public class TaskDAOImpl implements ITaskDAO {
	private Connection conn;

	public TaskDAOImpl() {
		conn = DBUtil.getConnection();
	}

	@Override
	public void saveOrUpdateTask(Task task) {

		try {
			String query = "insert into task (taskId, taskName,taskDescription,taskCreationDate, userId) values (?,?,?,?,?)";
			PreparedStatement preparedStatement = conn.prepareStatement(query);
			preparedStatement.setInt(1, task.getTaskId());
			preparedStatement.setString(2, task.getTaskName());
			preparedStatement.setString(3, task.getTaskDescription());
			preparedStatement.setString(4, task.getTaskCreationDate());
			preparedStatement.setInt(5, task.getUserId());
			System.out.println("task query :" + query);
			preparedStatement.executeUpdate();
			preparedStatement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void deleteTask(int taskId) {
		try {
			String query = "delete from task where taskId=?";
			PreparedStatement preparedStatement = conn.prepareStatement(query);
			preparedStatement.setInt(1, taskId);
			preparedStatement.executeUpdate();
			preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Task getTask(int taskId) {

		Task task = new Task();
		try {
			String query = "select * from task where taskid=?";
			PreparedStatement preparedStatement = conn.prepareStatement(query);
			preparedStatement.setInt(1, taskId);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {

				task.setTaskId(resultSet.getInt("taskId"));
				task.setTaskName(resultSet.getString("taskName"));
				task.setTaskDescription(resultSet.getString("taskDescription"));

			}
			resultSet.close();
			preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return task;

	}

	@Override
	public List<Task> getUserTaskList() {

		List<Task> taskList = new ArrayList<Task>();
		ResultSet resultSet = null;
		Statement statement = null;
		try {
			statement = conn.createStatement();
			resultSet = statement.executeQuery("select * from task");
			while (resultSet.next()) {
				Task task = new Task();
				task.setTaskId(resultSet.getInt("taskId"));
				task.setTaskName(resultSet.getString("taskName"));
				taskList.add(task);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} 
			try {
				/* resultSet.close(); */
				statement.close();
			} catch (Exception e) {
				e.printStackTrace();
			
		}
		return taskList;
	}

	@Override
	public void updateTask(Task task) {
		try {
			String query = "update task set taskName=? where taskId=?";
			PreparedStatement preparedStatement = conn.prepareStatement(query);

			preparedStatement.setString(1, task.getTaskName());
			preparedStatement.setInt(2, task.getTaskId());

			preparedStatement.executeUpdate();
			preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<UserTask> getAllUserTaskList(int taskId) {
		
		System.out.println("Inside DAO::"+taskId);
		List<UserTask> userList = new ArrayList<UserTask>();
		ResultSet resultSet = null;
		Statement statement = null;
		try {
			String query = "select * from usertask where taskId=?";
			PreparedStatement preparedStatement = conn.prepareStatement(query);

			preparedStatement.setInt(1, taskId);

			resultSet=preparedStatement.executeQuery();
			while (resultSet.next()) 
			{
				UserTask userTask = new UserTask();
				userTask.setUserId(resultSet.getInt("userId"));
				userTask.setTaskId(resultSet.getInt("taskId"));
				userTask.setLogStartTime(resultSet.getInt("logStartTime"));
				userTask.setLogEndTime(resultSet.getInt("logEndTime"));
				userTask.setLogDescription(resultSet.getString("logDescription"));
				userTask.setTotalDuration(resultSet.getInt("totalDuration"));
				userList.add(userTask);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		
		return userList;
	}

	@Override
	public void addUserTask(UserTask usertask) {
		System.out.println("usertask dao :" + usertask.toString());
		try {
			String query = "insert into usertask (userId, taskId, logStartTime,logEndTime,logDescription,totalDuration) values (?,?,?,?,?,?)";

			PreparedStatement preparedStatement = conn.prepareStatement(query);
			preparedStatement.setInt(1, usertask.getUserId());
			preparedStatement.setInt(2, usertask.getTaskId());
			preparedStatement.setInt(3, usertask.getLogStartTime());
			preparedStatement.setInt(4, usertask.getLogEndTime());
			preparedStatement.setString(5, usertask.getLogDescription());
			preparedStatement.setInt(6, usertask.logEndTime - usertask.logStartTime);
			System.out.println("query :" + query);
			preparedStatement.executeUpdate();
			preparedStatement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

//	


}
