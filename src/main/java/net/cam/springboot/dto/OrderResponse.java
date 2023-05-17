package net.cam.springboot.dto;

import lombok.Builder;

@Builder
public record OrderResponse(
    String orderTrackingNumber,
    String status,
    String message
) {
}
