package com.todo.model;

public class UserTaskDTO {

	int taskId;
	String taskName;
	String userName;
	int logStartTime;
	int logEndTime;
	String logDescription;

	public int getTaskId() {
		return taskId;
	}

	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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

}
