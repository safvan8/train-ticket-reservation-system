package com.safvan.response;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * This object is used to sent the exception details to REST client, if
 * something went wrong inside REST Api.
 * 
 * @author Safvan
 * @version 1.0
 * @since 1.0
 */
@AllArgsConstructor
@Getter
@Setter
public class ErrorApiResponse {

	private LocalDateTime dateTime;
	private String message;
	private String status;
	private String path;
}