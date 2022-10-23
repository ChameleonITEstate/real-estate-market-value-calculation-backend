package ru.chameleon.estate.dto;

public record UserDTO(Long userId, String name, String lastName, String password, String email) {
}
