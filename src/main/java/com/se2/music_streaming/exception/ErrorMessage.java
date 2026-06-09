package com.se2.music_streaming.exception;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorMessage {
    private LocalDateTime timestamp;
    private int status;
    private String error;
    private String message;
    private String path;
}