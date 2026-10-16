package com.uberits.uberitspring.dtos;

import lombok.Builder;

@Builder
public record AlertaDto(String mensaje, String nivel) {
}
