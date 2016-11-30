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
import com.todo.model.UserTask;
import com.todo.util.DBUtil;

public class TaskDAOImpl implements ITaskDAO {
    private Connection conn;
    public final String InsertQuery = "insert into task (taskId, taskName,taskDescription,taskCreationDate, userId) values (?,?,?,?,?)";
    public final String DeleteQuery = "delete from task where taskId=?";
    public final String GetTaskById = "select * from task where taskid=?";
    public final String GetTaskList = "select * from task";
    public final String UpdateTask = "update task set taskName=? where taskId=?";
    public final String GetUserTaskList = "select * from usertask where taskId=?";
    public final String InsertUserTask = "insert into usertask (userId, taskId, logStartTime,logEndTime,logDescription,totalDuration) values (?,?,?,?,?,?)";

    public TaskDAOImpl() {
        conn = DBUtil.getConnection();
    }

    @Override
    public void saveOrUpdateTask(Task task) {

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(InsertQuery);
            preparedStatement.setInt(1, task.getTaskId());
            preparedStatement.setString(2, task.getTaskName());
            preparedStatement.setString(3, task.getTaskDescription());
            preparedStatement.setString(4, task.getTaskCreationDate());
            preparedStatement.setInt(5, task.getUserId());
            preparedStatement.executeUpdate();
            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteTask(int taskId) {
        try {

            PreparedStatement preparedStatement = conn.prepareStatement(DeleteQuery);
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

            PreparedStatement preparedStatement = conn.prepareStatement(GetTaskById);
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
            resultSet = statement.executeQuery(GetTaskList);
            while (resultSet.next()) {
                Task task = new Task();
                task.setTaskId(resultSet.getInt("taskId"));
                task.setTaskName(resultSet.getString("taskName"));
                task.setTaskDescription(resultSet.getString("taskDescription"));
                task.setTaskCreationDate(resultSet.getString("taskCreationDate"));
                taskList.add(task);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();

        }
        return taskList;
    }

    @Override
    public void updateTask(Task task) {
        try {

            PreparedStatement preparedStatement = conn.prepareStatement(UpdateTask);
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
        System.out.println("Inside DAO::" + taskId);
        List<UserTask> userList = new ArrayList<UserTask>();
        ResultSet resultSet = null;
     
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(GetUserTaskList);
            preparedStatement.setInt(1, taskId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                UserTask userTask = new UserTask();
                userTask.setUserId(resultSet.getInt("userId"));
                userTask.setTaskId(resultSet.getInt("taskId"));
                userTask.setLogStartTime(resultSet.getString("logStartTime"));
                userTask.setLogEndTime(resultSet.getString("logEndTime"));
                userTask.setLogDescription(resultSet.getString("logDescription"));
                userTask.setTotalDuration(resultSet.getString("totalDuration"));
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
            PreparedStatement preparedStatement = conn.prepareStatement(InsertUserTask);
            preparedStatement.setInt(1, usertask.getUserId());
            preparedStatement.setInt(2, usertask.getTaskId());
            preparedStatement.setString(3, usertask.getLogStartTime());
            preparedStatement.setString(4, usertask.getLogEndTime());
            preparedStatement.setString(5, usertask.getLogDescription());
            preparedStatement.setString(6, usertask.getTotalDuration());
            preparedStatement.executeUpdate();
            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    

}
