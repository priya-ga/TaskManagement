package com.todo.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.todo.dao.ITaskDAO;
import com.todo.dao.IUserDAO;
import com.todo.dao.impl.TaskDAOImpl;
import com.todo.dao.impl.UserDAOImpl;
import com.todo.model.Task;
import com.todo.model.User;
import com.todo.model.UserTask;
import com.todo.model.UserTaskDTO;
import com.todo.service.impl.TaskServiceImpl;
import com.todo.util.Constant;

@WebServlet("/home")
public class HomeController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private ITaskDAO taskDaoImpl;
    private IUserDAO userDao;
    public static final String list_task = "/listTask.jsp";
    public static final String insert_or_edit = "/task.jsp";
    public static final String Home_page = "/success.jsp";
    public static final String Error_Page = "/error.jsp";
    public static final String list_task_for_Detail = "/viewTask.jsp";

    public HomeController() {
        taskDaoImpl = new TaskDAOImpl();
        userDao = new UserDAOImpl();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String forward = "";
        String action = request.getParameter("action");

        HttpSession httpSession = request.getSession(true);
        RequestDispatcher view1 = request.getRequestDispatcher(forward);

        if (httpSession == null) {
            System.err.println("session not available");
            forward = Error_Page;
            view1.forward(request, response);
        }

        String userName = (String) httpSession.getAttribute("username");
        User sessionUser = userDao.getUserByuserName(userName);

        if (action.equals(Constant.DELETE)) {
            forward = list_task;
            int taskId = Integer.parseInt(request.getParameter("taskId"));
            taskDaoImpl.deleteTask(taskId);
            request.setAttribute("task", taskDaoImpl.getUserTaskList());

        } else if (action.equals(Constant.EDIT)) {
            System.out.println("I am calling from here" + request.getParameter("taskId"));
            int taskId = Integer.parseInt(request.getParameter("taskId"));
            Task task = taskDaoImpl.getTask(taskId);
            request.setAttribute("task", task);
            forward = insert_or_edit;

        } else if (action.equals(Constant.INSERT)) {
            forward = insert_or_edit;

        } else if (action.equals(Constant.GETDETAIL)) {
            int taskId = Integer.parseInt(request.getParameter("taskId"));
            System.out.println("taskId::" + taskId);
            Task task = taskDaoImpl.getTask(taskId);
            System.out.println("I m that task :" + task);
            TaskServiceImpl taskservice = new TaskServiceImpl();
            List<UserTask> usertaskList = taskservice.getAllUserTaskList(task.getTaskId());

            if (usertaskList == null || usertaskList.isEmpty() || usertaskList.size() <= 0) {
                System.out.println("list is empty");

            } else {
                System.out.println("list have values");
            }

            List<UserTaskDTO> usertaskDTOList = new ArrayList<UserTaskDTO>();
            for (UserTask usertask : usertaskList) {
                // Validate validate = new Validate();
                User user = userDao.getUserByUserId(usertask.getUserId());
                UserTaskDTO userTaskDTO = new UserTaskDTO();
                userTaskDTO.setTaskId(task.getTaskId());
                userTaskDTO.setTaskName(task.getTaskName());
                userTaskDTO.setUserName(user.getUname());
                userTaskDTO.setLogStartTime(usertask.getLogStartTime());
                userTaskDTO.setLogEndTime(usertask.getLogEndTime());
                userTaskDTO.setLogDescription(usertask.getLogDescription());
                userTaskDTO.setTotalDuration(usertask.getTotalDuration());
                usertaskDTOList.add(userTaskDTO);

            }

            System.out.println("usertakList size ::" + usertaskDTOList.size());
            request.setAttribute("userTaskDTOList", usertaskDTOList);

            request.setAttribute("task", task);
            request.setAttribute("userName", sessionUser.getUname());
            forward = "/taskDetail.jsp";

        } else if (action.equals(Constant.ADDWORKLOG)) {
            String taskId = request.getParameter("taskId");
            Task task = taskDaoImpl.getTask(Integer.parseInt(taskId.trim()));
            request.setAttribute("task", task);
            request.setAttribute("userID", sessionUser.getUserId());
            request.setAttribute("UserName", sessionUser.getUname());
            forward = "/worklog.jsp";

        } else if (action.equals(Constant.HOMEPAGE)) {
            forward = Home_page;

        }
        
        else if(action.equalsIgnoreCase("getList")) {
            forward = list_task;
            request.setAttribute("task", taskDaoImpl.getUserTaskList());
        }
        
        
        else if(action.equalsIgnoreCase("getListForDetail")) {
            forward = list_task_for_Detail;
            request.setAttribute("task", taskDaoImpl.getUserTaskList());
        }
        
        
        else {
            forward = list_task;
            request.setAttribute("task", taskDaoImpl.getUserTaskList());
        }

        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        Task task = new Task();
        String taskId = request.getParameter("taskId");

        HttpSession httpSession = request.getSession(true);
        String userName = (String) httpSession.getAttribute("username");
        Date currentDate = new Date();
        SimpleDateFormat ft =  new SimpleDateFormat ("yyyy.MM.dd");
       
        User sessionUser = userDao.getUserByuserName(userName);
        int userId = sessionUser.getUserId();
        task.setUserId(userId);
        task.setTaskName(request.getParameter("taskName"));
        task.setTaskDescription(request.getParameter("taskDescription"));
        task.setTaskCreationDate(ft.format(currentDate));

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