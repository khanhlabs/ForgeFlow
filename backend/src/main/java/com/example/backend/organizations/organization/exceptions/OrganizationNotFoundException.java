package com.example.backend.organizations.organization.exceptions;

public class OrganizationNotFoundException extends RuntimeException {

	public OrganizationNotFoundException(Long id)
	{
		super("Organization with id " + id + " not found");
	}
}
