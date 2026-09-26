package com.example.backend.organization.exceptions;

public class OrganizationNotFoundException extends RuntimeException {

	public OrganizationNotFoundException(Long id)
	{
		super("Organization with id " + id + " not found");
	}
}
