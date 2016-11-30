package com.todo.controller;

import java.io.IOException;
import java.text.ParseException;
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

@WebServlet("/UserTask")
public class UserTaskController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private ITaskDAO dao;
    private IUserDAO userDao;

    public UserTaskController() {
        dao = new TaskDAOImpl();
        userDao = new UserDAOImpl();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        UserTask userTask = new UserTask();
        HttpSession httpSession = request.getSession();
        String userName = (String) httpSession.getAttribute("username");
        User sessionUser = userDao.getUserByuserName(userName);

        String start = request.getParameter("logStartTime");
        String end = request.getParameter("logEndTime");
        
        SimpleDateFormat curFormater = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        Date startDateObj = null;
        try {
            startDateObj = curFormater.parse(start);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Date endDateObj = null;
        try {
            endDateObj = curFormater.parse(end);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        long diff =  endDateObj.getTime() - startDateObj.getTime();

        long diffSeconds = diff / 1000 % 60;
        long diffMinutes = diff / (60 * 1000) % 60;
        long diffHours = diff / (60 * 60 * 1000) % 24;
        long diffDays = diff / (24 * 60 * 60 * 1000);
        String difference = diffDays + " days, " + diffHours + " hours, " + diffMinutes + " minutes, " + diffSeconds + " seconds.";
        System.out.print(diffDays + " days, " + diffHours + " hours, " + diffMinutes + " minutes, " + diffSeconds + " seconds.");

       

        String taskId = request.getParameter("taskId");
        userTask.setTaskId(Integer.parseInt(taskId));
        userTask.setUserId(Integer.parseInt(request.getParameter("userId")));
        userTask.setLogStartTime(request.getParameter("logStartTime"));
        userTask.setLogEndTime(request.getParameter("logEndTime"));
        userTask.setLogDescription(request.getParameter("logDescription"));
        userTask.setTotalDuration(difference);
        dao.addUserTask(userTask);

        Task dbtask = dao.getTask(Integer.parseInt(taskId));
        request.setAttribute("task", dbtask);

        TaskServiceImpl taskservice = new TaskServiceImpl();
        List<UserTask> usertaskList = taskservice.getAllUserTaskList(dbtask.getTaskId());
        List<UserTaskDTO> usertaskDTOList = new ArrayList<UserTaskDTO>();
        for (UserTask usertask : usertaskList) {

            Validate validate = new Validate();
            User user = userDao.getUserByUserId(usertask.getUserId());
            UserTaskDTO userTaskDTO = new UserTaskDTO();
            userTaskDTO.setTaskId(dbtask.getTaskId());
            userTaskDTO.setTaskName(dbtask.getTaskName());
            userTaskDTO.setUserName(user.getUname());
            userTaskDTO.setLogStartTime(usertask.getLogStartTime());
            userTaskDTO.setLogEndTime(usertask.getLogEndTime());
            userTaskDTO.setLogDescription(usertask.logDescription);
            usertaskDTOList.add(userTaskDTO);

        }

        request.setAttribute("userTaskDTOList", usertaskDTOList);

        RequestDispatcher rd = request.getRequestDispatcher("taskDetail.jsp");
        rd.include(request, response);

    }

}
