package com.example.backend.common.exception;

import com.example.backend.organizations.organization.exceptions.OrganizationAlreadyExistException;
import com.example.backend.organizations.organization.exceptions.OrganizationHasMemberException;
import com.example.backend.organizations.organization.exceptions.OrganizationHasProjectException;
import com.example.backend.organizations.organization.exceptions.OrganizationNotFoundException;
import com.example.backend.projects.project.exceptions.OrganizationProjectNotFoundException;
import com.example.backend.projects.project.exceptions.ProjectHasMemberException;
import com.example.backend.projects.project.exceptions.ProjectHasSprintException;
import com.example.backend.projects.project.exceptions.ProjectNotFoundException;
import com.example.backend.organizations.organization_member.exceptions.InvalidOrganizationRoleScopeException;
import com.example.backend.organizations.organization_member.exceptions.OrganizationMemberAlreadyExistException;
import com.example.backend.organizations.organization_member.exceptions.OrganizationMemberNotFoundException;
import com.example.backend.projects.project_members.exceptions.*;
import com.example.backend.roles.exceptions.RoleNotFoundException;
import com.example.backend.sprints.exceptions.InvalidSprintName;
import com.example.backend.sprints.exceptions.NotEmptySprintException;
import com.example.backend.sprints.exceptions.SprintNotFoundException;
import com.example.backend.users.exceptions.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(OrganizationNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleOrganizationNotFoundException(
            OrganizationNotFoundException exception
    ){
        return exception.getMessage();
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleUserNotFoundException(
            UserNotFoundException exception
    ){
        return exception.getMessage();
    }

    @ExceptionHandler(RoleNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleRoleNotFoundException(
            RoleNotFoundException exception
    ){
        return exception.getMessage();
    }

    @ExceptionHandler(OrganizationMemberAlreadyExistException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public String handleOrganizationMemberAlreadyExist(
            OrganizationMemberAlreadyExistException exception
    ){
        return exception.getMessage();
    }

    @ExceptionHandler(InvalidOrganizationRoleScopeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleInvalidRoleScopeException(
            InvalidOrganizationRoleScopeException exception
    ){
        return exception.getMessage();
    }

    @ExceptionHandler(OrganizationMemberNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleOrganizationMemberNotFoundException(
            OrganizationMemberNotFoundException exception
    ){
        return exception.getMessage();
    }

    @ExceptionHandler(ProjectNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleProjectNotFoundException(
            ProjectNotFoundException exception
    ){
        return exception.getMessage();
    }

    @ExceptionHandler(OrganizationProjectNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleOrganizationProjectNotFoundException(
            OrganizationProjectNotFoundException exception
    ){
        return exception.getMessage();
    }

    @ExceptionHandler(OrganizationHasMemberException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public String handleExistingOrganizationMemberException(
            OrganizationHasMemberException exception
    ){
        return exception.getMessage();
    }

    @ExceptionHandler(OrganizationHasProjectException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public String handleExistingOrganizationProjectMemberException(
            OrganizationHasProjectException exception
    ){
        return exception.getMessage();
    }

    @ExceptionHandler(OrganizationAlreadyExistException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public String handleOrganizationAlreadyExistException(
            OrganizationAlreadyExistException exception
    ){
        return exception.getMessage();
    }

    @ExceptionHandler(AlreadyExistProjectMemberException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public String handleAlreadyExistProjectMemberException(
            AlreadyExistProjectMemberException exception
    ){
        return exception.getMessage();
    }

    @ExceptionHandler(InvalidProjectRoleScopeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleInvalidProjectRoleScopeException(
            InvalidProjectRoleScopeException exception
    ){
        return exception.getMessage();
    }

    @ExceptionHandler(ProjectMemberNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleProjectMemberNotFoundException(
            ProjectMemberNotFoundException exception
    ){
        return exception.getMessage();
    }

    @ExceptionHandler(ProjectHasMemberException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public String handleProjectHasMemberException(
            ProjectHasMemberException exception
    ){
        return exception.getMessage();
    }

    @ExceptionHandler(MemberNotFoundInProjectException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleMemberNotFoundInProjectException(
            MemberNotFoundInProjectException exception
    ){
        return exception.getMessage();
    }

    @ExceptionHandler(ProjectHasSprintException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public String handleProjectHasSprintException(
            ProjectHasSprintException exception
    ){
        return exception.getMessage();
    }

    @ExceptionHandler(InvalidSprintName.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public String handleInvalidSprintName(
            InvalidSprintName exception
    ){
        return exception.getMessage();
    }

    @ExceptionHandler(SprintNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleSprintNotFoundException(
            SprintNotFoundException exception
    ){
        return exception.getMessage();
    }

    @ExceptionHandler(NotEmptySprintException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public String handleNotEmptySprintException(
            NotEmptySprintException exception
    ){
        return exception.getMessage();
    }
}
