package com.todo.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
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

//@WebServlet("/HomeController")
public class HomeController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private ITaskDAO taskDaoImpl;
	public static final String list_task = "/listTask.jsp";
	public static final String insert_or_edit = "/task.jsp";
	public static final String Home_page = "/success.jsp";

	public HomeController() {
		taskDaoImpl = new TaskDAOImpl();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String forward = "";
		String action = request.getParameter("action");
		System.out.println("****" + action);
		System.out.println("userId::" + request.getParameter("userId"));
		// int hidden = Integer.parseInt(request.getParameter("userId"));

		// dao.setUserId(hidden);
		// Task.setUserId(request.getParameter("userId"));

		HttpSession httpSession = request.getSession();

		System.out.println("username" + httpSession.getAttribute("username"));
		String userName = (String) httpSession.getAttribute("username");
		System.out.println("username ::" + userName);
		User sessionUser = Validate.getUserByuserName(userName);

		if (action.equalsIgnoreCase("delete")) {
			forward = list_task;
			int taskId = Integer.parseInt(request.getParameter("taskId"));
			taskDaoImpl.deleteTask(taskId);
			request.setAttribute("task", taskDaoImpl.getUserTaskList());
		} else if (action.equalsIgnoreCase("edit")) {
			System.out.println("I am calling from here"
					+ request.getParameter("taskId"));
			int taskId = Integer.parseInt(request.getParameter("taskId"));
			Task task = taskDaoImpl.getTask(taskId);
			request.setAttribute("task", task);
			forward = insert_or_edit;
		} else if (action.equalsIgnoreCase("insert")) {
			forward = insert_or_edit;
		} else if (action.equalsIgnoreCase("getDetail")) {
			int taskId = Integer.parseInt(request.getParameter("taskId"));
			System.out.println("taskId::" + taskId);
			Task task = taskDaoImpl.getTask(taskId);
			TaskServiceImpl taskservice = new TaskServiceImpl();
			List<UserTask> usertaskList = taskservice.getAllUserTaskList(task
					.getTaskId());

			System.out.println("DTO method called::" + usertaskList.size());
			if (usertaskList == null || usertaskList.isEmpty()
					|| usertaskList.size() <= 0) {
				System.out.println("list is empty");
			} else {
				System.out.println("list have values");
			}
			List<UserTaskDTO> usertaskDTOList = new ArrayList<UserTaskDTO>();
			for (UserTask usertask : usertaskList) {
				Validate validate = new Validate();
				User user = validate.getUserByUserId(usertask.getUserId());
				UserTaskDTO userTaskDTO = new UserTaskDTO();
				userTaskDTO.setTaskId(task.getTaskId());
				userTaskDTO.setTaskName(task.getTaskName());
				userTaskDTO.setUserName(user.getUname());
				userTaskDTO.setLogStartTime(usertask.getLogStartTime());
				userTaskDTO.setLogEndTime(usertask.getLogEndTime());
				userTaskDTO.setLogDescription(usertask.logDescription);
				usertaskDTOList.add(userTaskDTO);

			}
			System.out.println("usertakList size ::" + usertaskDTOList.size());
			request.setAttribute("userTaskDTOList", usertaskDTOList);

			request.setAttribute("task", task);
			request.setAttribute("userName", sessionUser.getUname());
			forward = "/taskDetail.jsp";
		} else if (action.equalsIgnoreCase("addWorklog")) {
			String taskId = request.getParameter("taskId");
			System.out.println("taskID ::" + taskId);
			Task task = taskDaoImpl.getTask(Integer.parseInt(taskId.trim()));
			request.setAttribute("task", task);

			// TODO : userID and TaskId
			System.out.println("I am User : " + sessionUser.getUserId()
					+ "I want to log for : " + task.getTaskId());
			request.setAttribute("userID", sessionUser.getUserId());

			request.setAttribute("UserName", sessionUser.getUname());

			forward = "worklog.jsp";
		} else if (action.equalsIgnoreCase("HomePage")) {
			forward = Home_page;
		} else {
			forward = list_task;
			request.setAttribute("task", taskDaoImpl.getUserTaskList());
		}
		RequestDispatcher view = request.getRequestDispatcher(forward);
		view.forward(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Task task = new Task();
		String taskId = request.getParameter("taskId");

		System.out.println("in save task");
		HttpSession httpSession = request.getSession();

		System.out.println("username" + httpSession.getAttribute("username"));

		String userName = (String) httpSession.getAttribute("username");
		System.out.println("username ::" + userName);
		Date currentDate = new Date();
		User sessionUser = Validate.getUserByuserName(userName);

		int userId = sessionUser.getUserId();
		task.setUserId(userId);
		task.setTaskName(request.getParameter("taskName"));
		task.setTaskDescription(request.getParameter("taskDescription"));
		task.setTaskCreationDate(currentDate.toString());
		System.out.println("Current Date::" + currentDate.toString());
		if (taskId == null || taskId.isEmpty()) {
			System.out.println("if task ID is null");
			taskDaoImpl.saveOrUpdateTask(task);
		} else {
			System.out.println("in else if taskID is present");
			task.setTaskId(Integer.parseInt(taskId));
			taskDaoImpl.updateTask(task);
		}
		RequestDispatcher view = request.getRequestDispatcher(list_task);
		request.setAttribute("task", taskDaoImpl.getUserTaskList());
		view.forward(request, response);

	}
}