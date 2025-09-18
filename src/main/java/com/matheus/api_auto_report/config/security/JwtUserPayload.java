package com.matheus.api_auto_report.config.security;

public record JwtUserPayload(Long userId, String username) {
}
