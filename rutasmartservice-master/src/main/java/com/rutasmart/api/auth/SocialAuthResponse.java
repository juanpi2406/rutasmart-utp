package com.rutasmart.api.auth;

public record SocialAuthResponse(String provider, String url, String message) {
}
