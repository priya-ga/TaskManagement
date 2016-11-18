package com.todo.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.todo.dao.ITaskDAO;
import com.todo.dao.impl.TaskDAOImpl;
import com.todo.model.Task;
import com.todo.model.User;
import com.todo.model.UserTask;
import com.todo.model.UserTaskDTO;
import com.todo.service.impl.TaskServiceImpl;

public class UserTaskController extends HttpServlet 	{
	private ITaskDAO dao;

	public UserTaskController() {
		dao = new TaskDAOImpl();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		
		
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		UserTask userTask = new UserTask();

		System.out.println("in save userTask");

		HttpSession httpSession = request.getSession();
//
		
		String userName = (String) httpSession.getAttribute("username");
		
		User sessionUser = Validate.getUserByuserName(userName);
//
//		int userId = sessionUser.getUserId();
//		userTask.setUserId(userId);

		String taskId = request.getParameter("taskId");
		System.out.println("taskID ::" + taskId);
		// Task dbTask = dao.getTask();
		userTask.setTaskId(Integer.parseInt(taskId));

//		System.out.println("I am User : " + sessionUser.getUserId()
//				+ "I want to log for : " + taskId);
		
		userTask.setUserId(Integer.parseInt(request.getParameter("userId")));
		userTask.setLogStartTime(Integer.parseInt(request.getParameter("logStartTime")));
		userTask.setLogEndTime(Integer.parseInt(request.getParameter("logEndTime")));
		userTask.setLogDescription(request.getParameter("logDescription"));
		
		System.out.println("userTAsk :::" + userTask);

		
		dao.addUserTask(userTask);
		
		Task dbtask = dao.getTask(Integer.parseInt(taskId));
		request.setAttribute("task", dbtask);
		
		
		TaskServiceImpl taskservice = new TaskServiceImpl();
		List<UserTask> usertaskList = taskservice.getAllUserTaskList(dbtask.getTaskId());
		List<UserTaskDTO> usertaskDTOList = new ArrayList<UserTaskDTO>();
		for (UserTask usertask : usertaskList) {
			
			Validate validate = new Validate();
			User user = validate.getUserByUserId(usertask.getUserId());
			System.out.println("USERNAME OF TASK CREATOR");
			System.out.println(user.getUname());
			UserTaskDTO userTaskDTO = new UserTaskDTO();
			userTaskDTO.setTaskId(dbtask.getTaskId());
			userTaskDTO.setTaskName(dbtask.getTaskName());
			userTaskDTO.setUserName(user.getUname());
			userTaskDTO.setLogStartTime(usertask.getLogStartTime());
			userTaskDTO.setLogEndTime(usertask.getLogEndTime());
			userTaskDTO.setLogDescription(usertask.logDescription);
			usertaskDTOList.add(userTaskDTO);

		}
		System.out.println("usertakList size ::" + usertaskDTOList.size());
		request.setAttribute("userTaskDTOList", usertaskDTOList);
		
		RequestDispatcher rd= request.getRequestDispatcher("taskDetail.jsp");
		rd.include(request, response);
//		response.sendRedirect("taskDetail.jsp");

	}

}
