package com.yole.carapp.dto;

public record AuthResponse(String token, String username, Long expiresAt) {
}