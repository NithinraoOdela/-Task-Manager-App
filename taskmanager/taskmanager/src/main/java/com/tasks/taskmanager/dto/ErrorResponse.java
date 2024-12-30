package com.tasks.taskmanager.dto;

public record ErrorResponse(

		int status, String message, String details) {

}
