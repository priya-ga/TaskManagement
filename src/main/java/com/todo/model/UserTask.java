package com.todo.model;

public class UserTask {

	public int userId;
	public int taskId;
	public int userTaskId;

	public int logStartTime;
	public int logEndTime;
	public String logDescription;
	public int totalDuration;

	public int getTotalDuration() {
		return totalDuration;
	}

	public void setTotalDuration(int totalDuration) {
		this.totalDuration = totalDuration;
	}

	public int getTaskId() {
		return taskId;
	}

	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}

	

	public int getLogStartTime() {
		return logStartTime;
	}

	public void setLogStartTime(int logStartTime) {
		this.logStartTime = logStartTime;
	}

	public int getLogEndTime() {
		return logEndTime;
	}

	public void setLogEndTime(int logEndTime) {
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
		return "UserTask [userId=" + userId + ", taskId=" + taskId
				+ ", logStartTime=" + logStartTime + ", logEndTime="
				+ logEndTime + ", logDescription=" + logDescription + "]";
	}

}
