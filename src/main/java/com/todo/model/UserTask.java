package com.todo.model;

import java.sql.Time;

public class UserTask {

    public int userId;
    public int taskId;
    public int userTaskId;
    public String logStartTime;
    public String logEndTime;
    public String logDescription;
    public String totalDuration;


    public String getTotalDuration() {
        return totalDuration;
    }

    public void setTotalDuration(String totalDuration) {
        this.totalDuration = totalDuration;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public String getLogStartTime() {
        return logStartTime;
    }

    public void setLogStartTime(String logStartTime) {
        this.logStartTime = logStartTime;
    }

    public String getLogEndTime() {
        return logEndTime;
    }

    public void setLogEndTime(String logEndTime) {
        this.logEndTime = logEndTime;
    }

    public String getLogDescription() {
        return logDescription;
    }

    public void setLogDescription(String logDescription) {
        this.logDescription = logDescription;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getUserTaskId() {
        return userTaskId;
    }

    public void setUserTaskId(int userTaskId) {
        this.userTaskId = userTaskId;
    }

    @Override
    public String toString() {
        return "UserTask [userId=" + userId + ", taskId=" + taskId + ", logStartTime=" + logStartTime + ", logEndTime="
                + logEndTime + ", logDescription=" + logDescription + "]";
    }

}
