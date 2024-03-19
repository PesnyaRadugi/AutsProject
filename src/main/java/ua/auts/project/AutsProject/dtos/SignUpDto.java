package ua.auts.project.AutsProject.dtos;

import ua.auts.project.AutsProject.enums.UserRole;

public record SignUpDto(
    String login,
    String password,
    UserRole role) {

}
